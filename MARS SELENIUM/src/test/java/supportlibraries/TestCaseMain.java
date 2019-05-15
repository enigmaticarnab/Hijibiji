package supportlibraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.frameworkutils.ExcelDataAccess;
import com.util.frameworkutils.FrameworkException;
import com.util.frameworkutils.IterationOptions;
import com.util.frameworkutils.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;




/**
 * Abstract base class for all the test cases to be automated
 * @author Ravi
 */
public abstract class TestCaseMain extends ResultSummaryManager
{
	/**
	 * The {@link SeleniumTestParameters} object to be used to specify the test parameters
	 */
	protected static SeleniumTestParameters testParameters;
	/**
	 * The {@link DriverScript} object to be used to execute the required test case
	 */
	protected DriverScript driverScript;
	
	protected Date startTime;
	protected Date endTime;
	protected static int RetryCount;
	protected static String strStatus = "true";
	protected static List<SeleniumTestParameters> testInstancesToRun;	
	protected static String strPathToResources = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "resources";
	static 
	{
		setUpClass();
	}
	@BeforeClass
	public static void setUpClass()
	{
		if(RetryCount >= 1)
		{
			return;
		}
		setRelativePath();
		initializeTestBatch();
		testInstancesToRun = getRunInfo(properties.getProperty("RunConfiguration"));
		RetryCount = 0;
		
		int nThreads = 1;
//		if (testContext.getSuite().getParallel().equalsIgnoreCase("false")) {
//			nThreads = 1;
//		} else {
//			nThreads = testContext.getCurrentXmlTest().getThreadCount();
//		}
		initializeSummaryReport("Regression", nThreads);
		
		try {
			setupErrorLog();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("Error while setting up the Error log!");
		}
	}
	

	private static List<SeleniumTestParameters> getRunInfo(String sheetName)
	{
		
		String strRunManRelativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		
		String strTestCategoryToRun = System.getenv("TESTCATEGORY");
		if(strTestCategoryToRun == null){
			strTestCategoryToRun = properties.getProperty("TestCategory");
		}
		
		String strEnvRunAgainstEnv = System.getenv("RUN_AGAINST_ENV");	//From Env variables
		String strPropRunAgainstEnv = properties.getProperty("RunAgainstEnv");	//From Properties file
		String strActRunAgainstEnv = null;
		
		if(strEnvRunAgainstEnv != null){
			strActRunAgainstEnv = strEnvRunAgainstEnv;
		}else if(strPropRunAgainstEnv != null){
			strActRunAgainstEnv = strPropRunAgainstEnv;
		}
		
		boolean strEnvPhantomJS = Boolean.parseBoolean(System.getenv("GATE"));	//From Env variables
		boolean strPropPhantomJS = Boolean.parseBoolean(properties.getProperty("Gate"));	//From Properties file
				
		if(strRunManRelativePath.contains("supportlibraries")) {
			strRunManRelativePath = new File(System.getProperty("user.dir")).getParent();
		}
		strRunManRelativePath = strRunManRelativePath + strPathToResources;
		
		ExcelDataAccess runManagerAccess = new ExcelDataAccess(strRunManRelativePath, "Run Manager");			
		runManagerAccess.setDatasheetName(sheetName);
		
		int nTestInstances = runManagerAccess.getLastRowNum();
		List<SeleniumTestParameters> testInstancesToRun = new ArrayList<SeleniumTestParameters>();
		
		for (int currentTestInstance = 1; currentTestInstance <= nTestInstances; currentTestInstance++) {
			String executeFlag = runManagerAccess.getValue(currentTestInstance, "Execute");
			
			if (executeFlag.equalsIgnoreCase("Yes")) {
				String currentScenario = runManagerAccess.getValue(currentTestInstance, "TestScenario");
				
				ExcelDataAccess testScenarioAccess = new ExcelDataAccess(strRunManRelativePath, "Run Manager");			
				testScenarioAccess.setDatasheetName(currentScenario);
				
				int nTestScenarioInstances = testScenarioAccess.getLastRowNum();
				
				for (int currentTestScenarioInstance = 1; currentTestScenarioInstance <= nTestScenarioInstances; currentTestScenarioInstance++){
					
					String[] executeTestFlag = testScenarioAccess.getValue(currentTestScenarioInstance, "Execute").split(",");
					
					ArrayList<String> executeTestFlagList = new ArrayList<String>();
					for(int i = 0 ; i < executeTestFlag.length ; i++){
						executeTestFlagList.add(executeTestFlag[i]);
					}
					
					//if (executeTestFlag.equalsIgnoreCase("Yes")) {					
					if (executeTestFlagList.contains(strTestCategoryToRun)) {
						String currentTestcase = testScenarioAccess.getValue(currentTestScenarioInstance, "TestCase");
						testParameters =
								new SeleniumTestParameters(currentScenario, currentTestcase);
						
						testParameters.setCurrentTestDescription(testScenarioAccess.getValue(currentTestScenarioInstance, "Description"));
						
						String iterationMode = testScenarioAccess.getValue(currentTestScenarioInstance, "IterationMode");
						if (!iterationMode.equals("")) {
							testParameters.setIterationMode(IterationOptions.valueOf(iterationMode));
						} else {
							testParameters.setIterationMode(IterationOptions.RunAllIterations);
						}
						
						String startIteration = testScenarioAccess.getValue(currentTestScenarioInstance, "StartIteration");
						if (!startIteration.equals("")) {
							testParameters.setStartIteration(Integer.parseInt(startIteration));
						}
						String endIteration = testScenarioAccess.getValue(currentTestScenarioInstance, "EndIteration");
						if (!endIteration.equals("")) {
							testParameters.setEndIteration(Integer.parseInt(endIteration));
						}
						
						String browser = testScenarioAccess.getValue(currentTestScenarioInstance, "Browser");
						if (!browser.equals("")) {
							if((strEnvPhantomJS == true) | (strPropPhantomJS == true)){									
								testParameters.setBrowser(Browser.valueOf("PhantomJS"));
							}else{
								testParameters.setBrowser(Browser.valueOf(browser));
							}
						} else {
							testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
						}
						String browserVersion = testScenarioAccess.getValue(currentTestScenarioInstance, "BrowserVersion");
						if (!browserVersion.equals("")) {
							testParameters.setBrowserVersion(browserVersion);
						}
						String platform = testScenarioAccess.getValue(currentTestScenarioInstance, "Platform");
						if (!platform.equals("")) {
							testParameters.setPlatform(Platform.valueOf(platform));
						} else {
							testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
						}						
						String filePath = testScenarioAccess.getValue(currentTestScenarioInstance, "FilePath");
						if (!filePath.equals("")) {
							testParameters.setFilePath(filePath);
						}
						String count = testScenarioAccess.getValue(currentTestScenarioInstance, "Count");
						if (!count.equals("")) {
							testParameters.setCount(count);
						}
						
						if(strTestCategoryToRun.equalsIgnoreCase("health")){
							if(strActRunAgainstEnv.equalsIgnoreCase("ALL")){
								testInstancesToRun.add(testParameters);
							}else if(currentTestcase.contains(strActRunAgainstEnv)){
								testInstancesToRun.add(testParameters);
							}else{								
							}
						}else{
							testInstancesToRun.add(testParameters);
						}										
				
					}					
				}
				
			}
		}
		
		return testInstancesToRun;
	}
	
	@Before
	public void setUp()
	{
		if(frameworkParameters.getStopExecution()) {
			tearDown();
						
			//throw new SkipException("Aborting all subsequent tests!");
		} else {
			startTime = Util.getCurrentTime();
		}
	}
	
	@After
	public void tearDown()
	{
//		String testStatus = driverScript.getTestStatus();
//		endTime = Util.getCurrentTime();
//		String executionTime = Util.getTimeDifference(startTime, endTime);
//		summaryReport.updateResultSummary(testParameters.getCurrentScenario(),
//									testParameters.getCurrentTestcase(),
//									testParameters.getCurrentTestDescription(),
//									executionTime, testStatus);
	}
	
	@AfterClass
	public static void tearDownClass()
	{
		
		if(System.getProperty("SYS_RERUN_COUNT") == null){
			wrapUp();
			if(properties.getProperty("RunningInLocal").equalsIgnoreCase("True"))
			{
				launchResultSummary();
			}
			return;
		}
		if(System.getProperty("SYS_RERUN_COUNT").equalsIgnoreCase(String.valueOf(0))){
			wrapUp();
			if(properties.getProperty("RunningInLocal").equalsIgnoreCase("True"))
			{
				launchResultSummary();
			}
			return;
		}
		if(RetryCount < Integer.parseInt(System.getProperty("SYS_RERUN_COUNT")) && strStatus.equalsIgnoreCase("false")){
			RetryCount++;
			strStatus = "true";
		}else{
			wrapUp();
			if(properties.getProperty("RunningInLocal").equalsIgnoreCase("True"))
			{
				launchResultSummary();
			}
		}
	}
}