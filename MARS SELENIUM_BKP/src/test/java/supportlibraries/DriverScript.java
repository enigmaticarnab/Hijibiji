package supportlibraries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;




import com.util.frameworkutils.*;
import com.util.frameworkutils.ReportThemeFactory.Theme;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;





import java.lang.reflect.*;
import java.nio.file.Files;

import junit.framework.Assert;

/**
 * Driver script class which encapsulates the core logic of the framework
 * 
 * @author Ravi
 */
public class DriverScript {
	private List<String> businessFlowData;
	private int currentIteration, currentSubIteration;
	private Date startTime, endTime;
	private String timeStamp;
	private String reportPath;
	

	private CraftDataTable dataTable;
	private ReportSettings reportSettings;
	private SeleniumReport report;
	private WebDriver driver;
	private ScriptHelper scriptHelper;

	private Properties properties;
	private ExecutionMode executionMode;
	private final FrameworkParameters frameworkParameters = FrameworkParameters
			.getInstance();
	private Boolean testExecutedInUnitTestFramework = true;
	private Boolean linkScreenshotsToTestLog = true;
	private String testStatus;
	private String strAllIDs;
	private String errorDescription;
	private String sessionId;
	
	static ConcurrentHashMap<String,String> SGHmap = new ConcurrentHashMap<String,String>();
	static ConcurrentHashMap<String,String> AUHmap = new ConcurrentHashMap<String,String>();
	static ConcurrentHashMap<String,String> NZHmap = new ConcurrentHashMap<String,String>();
	static ConcurrentHashMap<String,String> UKHmap = new ConcurrentHashMap<String,String>();
	static ConcurrentHashMap<String,String> USHmap = new ConcurrentHashMap<String,String>();
		
	private final SeleniumTestParameters testParameters;
	private String strPathToResources = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "resources";
	

	/**
	 * Function to set a Boolean variable indicating whether the test is
	 * executed in JUnit
	 * 
	 * @param testExecutedInUnitTestFramework
	 */
	public void setTestExecutedInUnitTestFramework(
			Boolean testExecutedInUnitTestFramework) {
		this.testExecutedInUnitTestFramework = testExecutedInUnitTestFramework;
	}

	
	/**
	 * Function to set a Boolean variable indicating whether any screenshots
	 * taken should be linked to the test log
	 * 
	 * @param linkScreenshotsToTestLog
	 */
	public void setLinkScreenshotsToTestLog(Boolean linkScreenshotsToTestLog) {
		this.linkScreenshotsToTestLog = linkScreenshotsToTestLog;
	}

	/**
	 * Function to get the status of the test case executed
	 * 
	 * @return The test status
	 */
	public String getTestStatus() {
		return testStatus;
	}
	
	public String getIDs() {
		return strAllIDs = report.getIDs();
	}

	/**
	 * Function to get the Error Message of the test case executed
	 * 
	 * @return The Error Message from test case
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * Constructor to initialize the DriverScript
	 */
	public DriverScript(SeleniumTestParameters testParameters) {
		this.testParameters = testParameters;
	}

	public ConcurrentHashMap<String, String> getSGTxnDetails()
	{
		return SGHmap; 
	}
	public ConcurrentHashMap<String, String> getAUTxnDetails()
	{
		return AUHmap; 
	}
	public ConcurrentHashMap<String, String> getNZTxnDetails()
	{
		return NZHmap; 
	}
	public ConcurrentHashMap<String, String> getUKTxnDetails()
	{
		return UKHmap; 
	}
	public ConcurrentHashMap<String, String> getUSTxnDetails()
	{
		return USHmap; 
	}
	/**
	 * Function to execute the given test case
	 */
	public void driveTestExecution() {
		
		final Properties properties;
		properties = Settings.getInstance();
		
		String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");	//From Env variables
		String strPropRunAgainstEnv = properties.getProperty("RunAgainstEnv");	//From Properties file
		String strActRunAgainstEnv = null;
		
		if(strEnvRunAgainstEnv != null){
			strActRunAgainstEnv = strEnvRunAgainstEnv;
		}else if(strPropRunAgainstEnv != null){
			strActRunAgainstEnv = strPropRunAgainstEnv;
		}
		
		if(strActRunAgainstEnv.equalsIgnoreCase("ALL")){
    		String strTCID = testParameters.getCurrentTestcase().toUpperCase();
    		if(strTCID.contains("RR")){
    			strActRunAgainstEnv = "RR";
    		}    		
    	}
		
		startUp();
		initializeTestIterations();
		initializeWebDriver();
		initializeTestReport();
		initializeDatatable();
		initializeTestScript();

		try {
			executeTestIterations();
			//Adedd Arumugam 
//			SaveTXNDetails();
		} catch (FrameworkException fx) {
			exceptionHandler(fx, fx.errorName);
		} catch (InvocationTargetException ix) {
			exceptionHandler((Exception) ix.getCause(), "Error");
		} catch (Exception ex) {
			exceptionHandler(ex, "Error");
		} finally {
			try {
				// Writing the TransactionID to a text file.
				try
				{
					SaveTXNDetails();
				}
				catch(Exception e)
				{}
				
				try{
					String strTransIDs = dataTable.getDataAll("General_Data", "TransactionID");
					String strContractIDs = dataTable.getDataAll("General_Data", "ContractID");
					String[] arrTransIDs = strTransIDs.split(";");
					String[] arrContractIDs = strContractIDs.split(";");
					ArrayList<String> LstTransIDs = new ArrayList<String>();
					ArrayList<String> LstContractIDs = new ArrayList<String>();		
					if(strTransIDs.replaceAll(";", "").equalsIgnoreCase("")){
						strTransIDs = "";
					}
					if(strContractIDs.replaceAll(";", "").equalsIgnoreCase("")){
						strContractIDs = "";
					}
					for(int i = 0 ; i < arrTransIDs.length ; i++){
						if(arrTransIDs[i].trim().isEmpty() == false){
							LstTransIDs.add(arrTransIDs[i]);
						}
					}
					for(int i = 0 ; i < LstTransIDs.size() ; i++){
						if(i == 0){
							strTransIDs = LstTransIDs.get(i);
						}else{
							strTransIDs = strTransIDs + ";" + LstTransIDs.get(i);
						}
					}
					for(int i = 0 ; i < arrContractIDs.length ; i++){
						if(arrContractIDs[i].trim().isEmpty() == false){
							LstContractIDs.add(arrContractIDs[i]);
						}
					}
					for(int i = 0 ; i < LstContractIDs.size() ; i++){
						if(i == 0){
							strContractIDs = LstContractIDs.get(i);
						}else{
							strContractIDs = strContractIDs + ";" + LstContractIDs.get(i);
						}
					}					
					report.updateTestLogWithIDs(strTransIDs+ ";"+strContractIDs);
				}catch(Exception e){}
				String strTestCategoryToRun = System.getenv("TESTCATEGORY");
				if(strTestCategoryToRun == null){
					strTestCategoryToRun = properties.getProperty("TestCategory");
				}
				if(strTestCategoryToRun.toLowerCase().contains("endofday")){					
				}else{
					File f = new File(reportPath + Util.getFileSeparator()
							+ "Datatables" + Util.getFileSeparator() + strActRunAgainstEnv
							+ Util.getFileSeparator()
							+ testParameters.getCurrentScenario()
							+ testParameters.getCurrentTestcase() + ".xls");
					f.delete();
				}
			} catch (Exception e) {
				System.out.println("Datatable could not be deleted from path "
						+ reportPath + Util.getFileSeparator() + "Datatables"
						+ Util.getFileSeparator() + strActRunAgainstEnv
						+ Util.getFileSeparator() 
						+ testParameters.getCurrentScenario()
						+ testParameters.getCurrentTestcase() + ".xls");
				e.printStackTrace();
			}
		}
		quitWebDriver();
		wrapUp();
	}
	
	
	//Fetching Transaction reference ID
	public void SaveTXNDetails()
	{
		String market=null;
		String testCaseName =null;
		String txnID=null;
		try{			
			testCaseName = dataTable.getData("General_Data", "TC_ID");
			market = dataTable.getData("General_Data", "Market");
			txnID = dataTable.getDataForTRNid("General_Data", "TransactionID");
			if ((txnID==null)||(txnID=="") )
			{
				txnID = dataTable.getDataForTRNid("General_Data", "ContractID");
			}
		}catch(Exception e){
			if(testCaseName==null)
			System.out.println("Test case not found in General_Data");
			//else if(txnID==null)
			//System.out.println("Transaction/Contract ID is not found for test case :"+testCaseName);
		}	

		//System.out.println("testCaseName:" +testCaseName + "; txnID="+txnID +"\n");
		if(txnID==null){
			txnID="";
		}
		switch(market)
		{
		case "SG":
			SGHmap.put(testCaseName, txnID);
			break;

		case "AU":
			AUHmap.put(testCaseName, txnID);
			break;

		case "NZ":
			NZHmap.put(testCaseName, txnID);
			break;

		case "UK":
			UKHmap.put(testCaseName, txnID);
			break;

		case "US":
			USHmap.put(testCaseName, txnID);
			break;

		}


	}

	private void startUp() {
		startTime = Util.getCurrentTime();

		properties = Settings.getInstance();

		setDefaultTestParameters();
	}

	private void setDefaultTestParameters() {
		if (testParameters.getIterationMode() == null) {
			testParameters.setIterationMode(IterationOptions.RunAllIterations);
		}

		if (testParameters.getBrowser() == null) {
			testParameters.setBrowser(Browser.valueOf(properties
					.getProperty("DefaultBrowser")));
		}

		if (testParameters.getPlatform() == null) {
			testParameters.setPlatform(Platform.valueOf(properties
					.getProperty("DefaultPlatform")));
		}
	}

	private void initializeTestIterations() {
		
		String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");	//From Env variables
		String strPropRunAgainstEnv = properties.getProperty("RunAgainstEnv");	//From Properties file
		String strActRunAgainstEnv = null;
		
		if(strEnvRunAgainstEnv != null){
			strActRunAgainstEnv = strEnvRunAgainstEnv;
		}else if(strPropRunAgainstEnv != null){
			strActRunAgainstEnv = strPropRunAgainstEnv;
		}
		
		if(strActRunAgainstEnv.equalsIgnoreCase("ALL")){
			String strTCID = testParameters.getCurrentTestcase().toUpperCase();
			if(strTCID.contains("RR")){
    			strActRunAgainstEnv = "RR";
    		}    		
    	}
		
		switch (testParameters.getIterationMode()) {
		case RunAllIterations:
			String strDatatableRelativePath = new File(
					System.getProperty("user.dir")).getAbsolutePath();
			if (strDatatableRelativePath.contains("supportlibraries")) {
				strDatatableRelativePath = new File(
						System.getProperty("user.dir")).getParent();
			}
			strDatatableRelativePath = strDatatableRelativePath
					+ strPathToResources;
			String datatablePath = strDatatableRelativePath
					+ Util.getFileSeparator() + "Datatables" + Util.getFileSeparator() + strActRunAgainstEnv;
			ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath,
					testParameters.getCurrentScenario());
			testDataAccess.setDatasheetName(properties
					.getProperty("DefaultDataSheet"));

			int startRowNum = testDataAccess.getRowNum(
					testParameters.getCurrentTestcase(), 0);
			int nTestcaseRows = testDataAccess.getRowCount(
					testParameters.getCurrentTestcase(), 0, startRowNum);
			int nSubIterations = testDataAccess
					.getRowCount("1", 1, startRowNum); // Assumption: Every test
														// case will have at
														// least one iteration
			System.out.println("nTestcaseRows : "+nTestcaseRows);
			System.out.println("nSubIterations : "+nSubIterations);
			int nIterations = nTestcaseRows / nSubIterations;
			System.out.println("nIterations : "+nIterations);
			testParameters.setEndIteration(nIterations);

			currentIteration = 1;
			break;

		case RunOneIterationOnly:
			currentIteration = 1;
			break;

		case RunRangeOfIterations:
			if (testParameters.getStartIteration() > testParameters
					.getEndIteration()) {
				throw new FrameworkException("Error",
						"StartIteration cannot be greater than EndIteration!");
			}
			currentIteration = testParameters.getStartIteration();
			break;

		default:
			throw new FrameworkException("Unhandled Iteration Mode!");
		}
	}

	private void initializeWebDriver() {
		executionMode = ExecutionMode.valueOf(properties
				.getProperty("ExecutionMode"));

		switch (executionMode) {
		case Local:
			// to assign a name for Sauce lab jobs
			String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");
			String strPropRunAgainstEnv = properties
					.getProperty("RunAgainstEnv");
			String strActRunAgainstEnv = "";
			if (strEnvRunAgainstEnv != null) {
				strActRunAgainstEnv = strEnvRunAgainstEnv;
			} else if (strPropRunAgainstEnv != null) {
				strActRunAgainstEnv = strPropRunAgainstEnv;
			}
			String strEnvJob = System.getenv("JOB");
			if (strEnvJob == null) {
				strEnvJob = "Dev";
			}
			String SauceJobName = strEnvJob + ":" + strActRunAgainstEnv + ":"
					+ testParameters.getCurrentScenario() + ":"
					+ testParameters.getCurrentTestcase();
			driver = WebDriverFactory.getDriver(testParameters.getFilePath(),
					testParameters.getBrowser(), SauceJobName);
			break;

		case Remote:
			driver = WebDriverFactory.getDriver(testParameters.getBrowser(),
					properties.getProperty("RemoteUrl"));
			break;

		case Grid:
			driver = WebDriverFactory.getDriver(testParameters.getBrowser(),
					testParameters.getBrowserVersion(),
					testParameters.getPlatform(),
					properties.getProperty("RemoteUrl"));
			break;

		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}
	}

	private void initializeTestReport() {
		timeStamp = TimeStamp.getInstance();

		initializeReportSettings();
		ReportTheme reportTheme = ReportThemeFactory.getReportsTheme(Theme
				.valueOf(properties.getProperty("ReportsTheme")));

		report = new SeleniumReport(reportSettings, reportTheme);

		report.initialize();
		report.setDriver(driver);
		report.initializeTestLog();
		createTestLogHeader();
	}

	private void initializeReportSettings() {
		
		final Properties properties;
		properties = Settings.getInstance();
		
		String strEnvTakeScreenshotFailedStep = System.getenv("TAKE_SCREENSHOT_FOR_FAILED");	//From Env variables
		String strPropTakeScreenshotFailedStep = properties.getProperty("TakeScreenshotFailedStep");	//From Properties file
		String strActTakeScreenshotFailedStep = null;
		
		if(strEnvTakeScreenshotFailedStep != null){
			strActTakeScreenshotFailedStep = strEnvTakeScreenshotFailedStep;
		}else if(strPropTakeScreenshotFailedStep != null){
			strActTakeScreenshotFailedStep = strPropTakeScreenshotFailedStep;
		}
		
		String strEnvTakeScreenshotPassedStep = System.getenv("TAKE_SCREENSHOT_FOR_PASSED");	//From Env variables
		String strPropTakeScreenshotPassedStep = properties.getProperty("TakeScreenshotPassedStep");	//From Properties file
		String strActTakeScreenshotPassedStep = null;
		
		if(strEnvTakeScreenshotPassedStep != null){
			strActTakeScreenshotPassedStep = strEnvTakeScreenshotPassedStep;
		}else if(strPropTakeScreenshotPassedStep != null){
			strActTakeScreenshotPassedStep = strPropTakeScreenshotPassedStep;
		}

		String strResultsRelativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (strResultsRelativePath.contains("supportlibraries")) {
			strResultsRelativePath = new File(System.getProperty("user.dir"))
					.getParent();
		}

		strResultsRelativePath = strResultsRelativePath + Util.getFileSeparator() + "target";
		reportPath = strResultsRelativePath + Util.getFileSeparator()
				+ "Results" + Util.getFileSeparator() + timeStamp;

		// strResultsRelativePath = strResultsRelativePath + "\\target";
		// reportPath = strResultsRelativePath +
		// Util.getFileSeparator() + timeStamp;

		reportSettings = new ReportSettings(reportPath,
				testParameters.getCurrentScenario() + "_"
						+ testParameters.getCurrentTestcase());

		reportSettings.setDateFormatString(properties
				.getProperty("DateFormatString"));
		reportSettings.setLogLevel(Integer.parseInt(properties
				.getProperty("LogLevel")));
		reportSettings.setProjectName(properties.getProperty("ProjectName"));
		reportSettings.generateExcelReports = Boolean.parseBoolean(properties
				.getProperty("ExcelReport"));
		reportSettings.generateHtmlReports = Boolean.parseBoolean(properties
				.getProperty("HtmlReport"));
		reportSettings.takeScreenshotFailedStep = Boolean
				.parseBoolean(strActTakeScreenshotFailedStep);
		reportSettings.takeScreenshotPassedStep = Boolean
				.parseBoolean(strActTakeScreenshotPassedStep);
		if (testParameters.getBrowser().equals(Browser.HtmlUnit)) {
			// Screenshots not supported in headless mode
			reportSettings.linkScreenshotsToTestLog = false;
		} else {
			reportSettings.linkScreenshotsToTestLog = this.linkScreenshotsToTestLog;
		}
	}

	private void createTestLogHeader() {
		report.addTestLogHeading("RML - "
				+ reportSettings.getReportName()
				+ " Automation Execution Results");
		report.addTestLogSubHeading(
				"Date & Time",
				": "
						+ Util.getCurrentFormattedTime(properties
								.getProperty("DateFormatString")),
				"Iteration Mode", ": " + testParameters.getIterationMode());
		report.addTestLogSubHeading("Start Iteration",
				": " + testParameters.getStartIteration(), "End Iteration",
				": " + testParameters.getEndIteration());

		switch (executionMode) {
		case Local:
			report.addTestLogSubHeading("Browser",
					": " + testParameters.getBrowser(), "Executed on", ": "
							+ "Local Machine");
			break;

		case Remote:
			report.addTestLogSubHeading("Browser",
					": " + testParameters.getBrowser(), "Executed on", ": "
							+ properties.getProperty("RemoteUrl"));
			break;

		case Grid:
			String browserVersion = testParameters.getBrowserVersion();
			if (browserVersion == null) {
				browserVersion = "Not specified";
			}
			report.addTestLogSubHeading("Browser",
					": " + testParameters.getBrowser(), "Version", ": "
							+ browserVersion);
			report.addTestLogSubHeading("Platform", ": "
					+ testParameters.getPlatform().toString(), "Executed on",
					": " + "Grid @ " + properties.getProperty("RemoteUrl"));
			break;

		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}

		report.addTestLogTableHeadings();
	}

	private void initializeDatatable() {
		
		String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");	//From Env variables
		String strPropRunAgainstEnv = properties.getProperty("RunAgainstEnv");	//From Properties file
		String strActRunAgainstEnv = null;
		
		if(strEnvRunAgainstEnv != null){
			strActRunAgainstEnv = strEnvRunAgainstEnv;
		}else if(strPropRunAgainstEnv != null){
			strActRunAgainstEnv = strPropRunAgainstEnv;
		}
		
		if(strActRunAgainstEnv.equalsIgnoreCase("ALL")){
			String strTCID = testParameters.getCurrentTestcase().toUpperCase();
			if(strTCID.contains("RR")){
    			strActRunAgainstEnv = "RR";
    		}    		
    	}

		String strTestCategoryToRun = System.getenv("TESTCATEGORY");
		if(strTestCategoryToRun == null){
			strTestCategoryToRun = properties.getProperty("TestCategory");
		}
		
		String strDatatableRelativePath = new File(
				System.getProperty("user.dir")).getAbsolutePath();
		if (strDatatableRelativePath.contains("supportlibraries")) {
			strDatatableRelativePath = new File(System.getProperty("user.dir"))
					.getParent();
		}
		strDatatableRelativePath = strDatatableRelativePath
				+ strPathToResources;
		String datatablePath = strDatatableRelativePath
				+ Util.getFileSeparator() + "Datatables" + Util.getFileSeparator() + strActRunAgainstEnv;

		String runTimeDatatablePath;
		Boolean includeTestDataInReport = Boolean.parseBoolean(properties
				.getProperty("IncludeTestDataInReport"));
		if (includeTestDataInReport) {
			runTimeDatatablePath = reportPath + Util.getFileSeparator()
					+ "Datatables" + Util.getFileSeparator() + strActRunAgainstEnv;
						
			File runTimeDatatable = null;
			if(strTestCategoryToRun.toLowerCase().contains("endofday")){
				runTimeDatatable = new File(runTimeDatatablePath + Util.getFileSeparator() +
						testParameters.getCurrentScenario() + ".xls");
			}else{
				runTimeDatatable = new File(runTimeDatatablePath
						+ Util.getFileSeparator()
						+ testParameters.getCurrentScenario()
						+ testParameters.getCurrentTestcase() + ".xls");
			}
			if (!runTimeDatatable.exists()) {
				File datatable = new File(datatablePath
						+ Util.getFileSeparator()
						+ testParameters.getCurrentScenario() + ".xls");

				try {
					FileUtils.copyFile(datatable, runTimeDatatable);
				} catch (IOException e) {
					e.printStackTrace();
					throw new FrameworkException(
							"Error in creating run-time datatable: Copying the datatable failed...");
				}
			}

			File runTimeCommonDatatable = new File(runTimeDatatablePath
					+ Util.getFileSeparator() + "Common Testdata.xls");
			if (!runTimeCommonDatatable.exists()) {
				File commonDatatable = new File(datatablePath
						+ Util.getFileSeparator() + "Common Testdata.xls");

				try {
					FileUtils.copyFile(commonDatatable, runTimeCommonDatatable);
				} catch (IOException e) {
					e.printStackTrace();
					throw new FrameworkException(
							"Error in creating run-time datatable: Copying the common datatable failed...");
				}
			}

			} else {
			runTimeDatatablePath = datatablePath;
		}

		if(strTestCategoryToRun.toLowerCase().contains("endofday")){
			dataTable = new CraftDataTable(runTimeDatatablePath, testParameters.getCurrentScenario());
		}else{
			dataTable = new CraftDataTable(runTimeDatatablePath,
					testParameters.getCurrentScenario()
							+ testParameters.getCurrentTestcase());
		}
		dataTable.setDataReferenceIdentifier(properties
				.getProperty("DataReferenceIdentifier"));
	}

	private void initializeTestScript() {
		scriptHelper = new ScriptHelper(dataTable, report, driver);

		businessFlowData = getBusinessFlow();
	}

	private List<String> getBusinessFlow() {

		String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");	//From Env variables
		String strPropRunAgainstEnv = properties.getProperty("RunAgainstEnv");	//From Properties file
		String strActRunAgainstEnv = null;
		
		if(strEnvRunAgainstEnv != null){
			strActRunAgainstEnv = strEnvRunAgainstEnv;
		}else if(strPropRunAgainstEnv != null){
			strActRunAgainstEnv = strPropRunAgainstEnv;
		}
		
		if(strActRunAgainstEnv.equalsIgnoreCase("ALL")){
			String strTCID = testParameters.getCurrentTestcase().toUpperCase();
			if(strTCID.contains("RR")){
    			strActRunAgainstEnv = "RR";
    		}    		
    	}
		
		String strDatatableRelativePath = new File(
				System.getProperty("user.dir")).getAbsolutePath();
		if (strDatatableRelativePath.contains("supportlibraries")) {
			strDatatableRelativePath = new File(System.getProperty("user.dir"))
					.getParent();
		}
		strDatatableRelativePath = strDatatableRelativePath
				+ strPathToResources;

		ExcelDataAccess businessFlowAccess = new ExcelDataAccess(
				strDatatableRelativePath + Util.getFileSeparator()
						+ "Datatables" + Util.getFileSeparator() + strActRunAgainstEnv, testParameters.getCurrentScenario());
		businessFlowAccess.setDatasheetName("Business_Flow");

		int rowNum = businessFlowAccess.getRowNum(
				testParameters.getCurrentTestcase(), 0);
		if (rowNum == -1) {
			throw new FrameworkException("The test case \""
					+ testParameters.getCurrentTestcase()
					+ "\" is not found in the Business Flow sheet!");
		}

		String dataValue;
		List<String> businessFlowData = new ArrayList<String>();
		int currentColumnNum = 1;
		while (true) {
			dataValue = businessFlowAccess.getValue(rowNum, currentColumnNum);
			if (dataValue.equals("")) {
				break;
			}
			businessFlowData.add(dataValue);
			currentColumnNum++;
		}

		if (businessFlowData.isEmpty()) {
			throw new FrameworkException(
					"No business flow found against the test case \""
							+ testParameters.getCurrentTestcase() + "\"");
		}

		return businessFlowData;
	}

	private void executeTestIterations() throws IllegalAccessException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException {
		while (currentIteration <= testParameters.getEndIteration()) {
			report.addTestLogSection("Iteration: "
					+ Integer.toString(currentIteration));

			executeTestcase(businessFlowData);

			currentIteration++;
		}
	}

	private void executeTestcase(List<String> businessFlowData)
			throws IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		HashMap<String, Integer> keywordDirectory = new HashMap<String, Integer>();

		for (int currentKeywordNum = 0; currentKeywordNum < businessFlowData
				.size(); currentKeywordNum++) {
			String[] currentFlowData = businessFlowData.get(currentKeywordNum)
					.split(",");
			String currentKeyword = currentFlowData[0];

			int nKeywordIterations;
			if (currentFlowData.length > 1) {
				nKeywordIterations = Integer.parseInt(currentFlowData[1]);
			} else {
				nKeywordIterations = 1;
			}

			for (int currentKeywordIteration = 0; currentKeywordIteration < nKeywordIterations; currentKeywordIteration++) {
				if (keywordDirectory.containsKey(currentKeyword)) {
					keywordDirectory.put(currentKeyword,
							keywordDirectory.get(currentKeyword) + 1);
				} else {
					keywordDirectory.put(currentKeyword, 1);
				}
				currentSubIteration = keywordDirectory.get(currentKeyword);

				dataTable.setCurrentRow(testParameters.getCurrentTestcase(),
						currentIteration, currentSubIteration);

				if (currentSubIteration > 1) {
					report.addTestLogSubSection(currentKeyword
							+ " (Sub-Iteration: " + currentSubIteration + ")");
				} else {
					report.addTestLogSubSection(currentKeyword);
				}

				invokeBusinessComponent(currentKeyword);
			}
		}
	}

	private void invokeBusinessComponent(String currentKeyword)
			throws IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		Boolean isMethodFound = false;
		final String CLASS_FILE_EXTENSION = ".class";
		String strRelativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (strRelativePath.contains("supportlibraries")) {
			strRelativePath = new File(System.getProperty("user.dir"))
					.getParent();
		}
		strRelativePath = strRelativePath + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "test-classes";
		File[] packageDirectories = {
				new File(strRelativePath + Util.getFileSeparator()
						+ "businesscomponents"),
				new File(strRelativePath + Util.getFileSeparator()
						+ "componentgroups") };
		for (File packageDirectory : packageDirectories) {
			File[] packageFiles = packageDirectory.listFiles();
			String packageName = packageDirectory.getName();

			for (int i = 0; i < packageFiles.length; i++) {

				File packageFile = packageFiles[i];
				String fileName = packageFile.getName();

				//Below Condition is change by Omar(561131) previously it was
				// if (fileName.endsWith(CLASS_FILE_EXTENSION)
				// We only want the .class file and that is alo mathcing with the current Test Case
				
				if (fileName.endsWith(CLASS_FILE_EXTENSION) && fileName.equalsIgnoreCase(testParameters.getCurrentScenario().concat(CLASS_FILE_EXTENSION))) {
					// Remove the .class extension to get the class name
					String className = fileName.substring(0, fileName.length()
							- CLASS_FILE_EXTENSION.length());

					Class<?> reusableComponents = Class.forName(packageName
							+ "." + className);
					Method executeComponent;

					try {
						// Convert the first letter of the method to lowercase
						// (in line with java naming conventions)
						currentKeyword = currentKeyword.substring(0, 1)
								.toLowerCase() + currentKeyword.substring(1);
						executeComponent = reusableComponents.getMethod(
								currentKeyword, (Class<?>[]) null);
					} catch (NoSuchMethodException ex) {
						// If the method is not found in this class, search the
						// next class
						continue;
					}

					isMethodFound = true;

					Constructor<?> ctor = reusableComponents
							.getDeclaredConstructors()[0];
					Object businessComponent = ctor.newInstance(scriptHelper);

					executeComponent.invoke(businessComponent, (Object[]) null);

					break;
				}
			}
		}

		if (!isMethodFound) {
			throw new FrameworkException("Keyword " + currentKeyword
					+ " not found within any class "
					+ "inside the businesscomponents package");
		}
	}

	private void exceptionHandler(Exception ex, String exceptionName) {
		// Error reporting
		String exceptionDescription = ex.getMessage();
		if (exceptionDescription == null) {
			exceptionDescription = ex.toString();
		}

		try {
			if (ex.getCause() != null) {

				report.updateTestLog(exceptionName, exceptionDescription
						+ " <b>Caused by: </b>" + ex.getCause(), Status.FAIL);
			} else {
				report.updateTestLog(exceptionName, exceptionDescription,
						Status.FAIL);
			}
		} catch (Exception e) {
			System.out
					.println("Some weird exception while updating test log - may be screenshots are taken at the same time - Ignoring for now");
		}
		ex.printStackTrace();

		// Error response
		if (frameworkParameters.getStopExecution()) {
			report.updateTestLog(
					"Exception Info",
					"Test execution terminated by user! All subsequent tests aborted...",
					Status.DONE);
			currentIteration = testParameters.getEndIteration();
		} else {
			OnError onError = OnError
					.valueOf(properties.getProperty("OnError"));
			switch (onError) {
			case NextIteration:
				report.updateTestLog(
						"Exception Info",
						"Test case iteration terminated by user! Proceeding to next iteration (if applicable)...",
						Status.DONE);	
				break;

			case NextTestCase:
				report.updateTestLog(
						"Exception Info",
						"Test case terminated by user! Proceeding to next test case (if applicable)...",
						Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			case Stop:				
				frameworkParameters.setStopExecution(true);
				report.updateTestLog(
						"Exception Info",
						"Test execution terminated by user! All subsequent tests aborted...",
						Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			default:
				throw new FrameworkException("Unhandled OnError option!");
			}			
		}
	}

	private void quitWebDriver() {
		// added to get the session id of the Sauce lab job.
		try {
//			SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(System.getenv("SAUCE_USER_NAME"), System.getenv("SAUCE_API_KEY"));
			
//			 SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
			
			String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");
			String strPropRunAgainstEnv = properties
					.getProperty("RunAgainstEnv");
			String strActRunAgainstEnv = "";
			if (strEnvRunAgainstEnv != null) {
				strActRunAgainstEnv = strEnvRunAgainstEnv;
			} else if (strPropRunAgainstEnv != null) {
				strActRunAgainstEnv = strPropRunAgainstEnv;
			}
			testStatus = report.getTestStatus();
			String strEnvJob = System.getenv("JOB");
			if (strEnvJob == null) {
				strEnvJob = "Dev";
			}			
			String SauceJobName = strEnvJob + ":" + strActRunAgainstEnv + ":"
					+ testParameters.getCurrentScenario() + ":"
					+ testParameters.getCurrentTestcase();
			boolean sauceLabs = Boolean.parseBoolean(properties
					.getProperty("SauceLabs"));
			if (sauceLabs) {
				String message = String.format(
						"SauceOnDemandSessionID=%1$s job-name=%2$s job-result=%3$s",
						(((RemoteWebDriver) driver).getSessionId()).toString(),
						SauceJobName,testStatus);
//				this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
				System.out.println(message); //This line is used to display the Session id in Jenkins job.
				
			}
		} catch (Exception ex) {
		}

		try{
			driver.manage().deleteAllCookies();
			driver.quit();
		}catch(Exception e){
			report.updateTestLog("Terminated", testParameters.getCurrentScenario() + " : "+ testParameters.getCurrentTestcase() + " already driver closed", Status.FAIL);
			report.updateTestLog("Terminated", e.getMessage(), Status.FAIL);
//			endTime = Util.getCurrentTime();
            long timeDifference = endTime.getTime() - startTime.getTime();
            timeDifference /= 1000;    // to convert from milliseconds to seconds            
            if(timeDifference > 5400){
            	report.updateTestLog("Terminated", testParameters.getCurrentScenario() + " : "+ testParameters.getCurrentTestcase() + " is running for more than 90 mins", Status.FAIL);
            }  
		}
	}

	@SuppressWarnings("deprecation")
	private void wrapUp() {
		endTime = Util.getCurrentTime();
		closeTestReport();

		testStatus = report.getTestStatus();		
		// Assert.assertFalse(report.getFailureDescription(),
		// (testExecutedInUnitTestFramework &&
		// testStatus.equalsIgnoreCase("Failed")));
		errorDescription = report.getFailureDescription();
		if (testExecutedInUnitTestFramework
				&& testStatus.equalsIgnoreCase("Failed")) {

			// Assert.fail(report.getFailureDescription());

		}
	}
//	@Override
//    public String getSessionId() {
//        return sessionId;
//    }
	private void closeTestReport() {
		String executionTime = Util.getTimeDifference(startTime, endTime);
		report.addTestLogFooter(executionTime);
	}
}