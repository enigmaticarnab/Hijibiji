package supportlibraries;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.util.frameworkutils.ExcelDataAccess;
import com.util.frameworkutils.FrameworkParameters;
import com.util.frameworkutils.Settings;
import com.util.frameworkutils.Util;


/**
 * Class to facilitate parallel execution of test scripts
 * @author Ravi
 */


//public class ParallelRunner implements Runnable
public class ParallelRunner 
{
	private final SeleniumTestParameters testParameters;
	private final SeleniumReport summaryReport;	
	private final String strExcelName;
	private final String strReportPath;
	//private final String strMarket;
	private final int RetryCount;
	private Properties properties;
	public static long totalTime=0;	
	public static int total_count=0;
	public static long avg_time;
	public static String time_avg;
	public static int maxval=0;
	public static String time_max;
	//private static ArrayList<Long> execution_time = new ArrayList<Long>();
	private static ArrayList<String> executiontime1 = new ArrayList<String>();
	
	
	/**
	 * Constructor to initialize the details of the test case to be executed
	 * @param testParameters The {@link SeleniumTestParameters} object (passed from the {@link AllocatorTest})
	 * @param summaryReport The {@link SeleniumReport} object (passed from the {@link AllocatorTest})
	 */
	public ParallelRunner(SeleniumTestParameters testParameters, SeleniumReport summaryReport, String strExcelName, String strReportPath, int RetryCount)
	{
		super();
		
		this.testParameters = testParameters;
		this.summaryReport = summaryReport;		
		this.strExcelName = strExcelName;
		this.strReportPath = strReportPath;
		//this.strMarket = strMarket;
		this.RetryCount = RetryCount;
	}
	
	
	//@Override
	//public void run()
	 public Map call() throws Exception 
	{
			Map dictionary = new HashMap();			
			
			try
			{								
				final Properties properties;
				properties = Settings.getInstance();				
				Date startTime = Util.getCurrentTime();				
				dictionary= invokeTestScript();
				Date endTime = Util.getCurrentTime();				
				String executionTime = Util.getTimeDifference(startTime, endTime);				
				// Getting the time difference in seconds
				String executionTimeInSeconds = Util.getTimeDifferenceInSeconds(startTime, endTime);
				System.out.println("Execition time in seconds:" + executionTimeInSeconds);
				executiontime1.add(executionTimeInSeconds);				
				String strSheetName = testParameters.getCurrentScenario();
				String strTestCase = testParameters.getCurrentTestcase();		
				//putDataTime(strExcelName, strSheetName, strTestCase, "ExecutionTime", executionTime);	
				double cnt = 0;
				try
				{
					cnt = Double.parseDouble(testParameters.getCount());
					total_count=total_count+Integer.parseInt(testParameters.getCount());
					System.out.println("Count : "+cnt);
				}
				catch(NullPointerException e)
				{			
				}
				String testStatus = (String) dictionary.get("TestStatus");
				String strIDs = (String) dictionary.get("ReferenceNumber");
				if(System.getProperty("SYS_RERUN_COUNT") == null)
				{
					summaryReport.updateResultSummary(testParameters.getCurrentScenario(),
					testParameters.getCurrentTestcase(),
					testParameters.getCurrentTestDescription(),
					executionTime, testStatus, cnt);						
				}
				else if(System.getProperty("SYS_RERUN_COUNT").equalsIgnoreCase(String.valueOf(0)))
				{
					summaryReport.updateResultSummary(testParameters.getCurrentScenario(),
					testParameters.getCurrentTestcase(),
					testParameters.getCurrentTestDescription(),
					executionTime, testStatus, cnt);
				}
				else if(RetryCount < Integer.parseInt(System.getProperty("SYS_RERUN_COUNT")) && testStatus.equalsIgnoreCase("failed"))
				{
				}
				else
				{
					summaryReport.updateResultSummary(testParameters.getCurrentScenario(),
												testParameters.getCurrentTestcase(),
												testParameters.getCurrentTestDescription(),
												executionTime, testStatus, cnt);
				}					
					
			}				
			
			catch(Exception e)
			{
//				System.out.println("Error in call method"+e.getMessage());
				e.printStackTrace();
				dictionary.put("TestStatus","failed");
				dictionary.put("ErrorDescription",e.getMessage());
				//dictionary.put("ReferenceNumber","");
			}
			System.out.println("arrey value in string : "+executiontime1);
			System.out.println("Count : "+executiontime1.size());
			totalTime=0;
			for (int i=0;i<executiontime1.size();i++)
			{
				int elementval=Integer.parseInt(executiontime1.get(i));
				System.out.println("Element value : "+elementval);
				if(maxval<elementval)
				{
					maxval=elementval;
				}
				totalTime=totalTime+elementval;
			}
		System.out.println("Max value : "+maxval);
		System.out.println("Total Value : "+totalTime);
		avg_time=totalTime/executiontime1.size();
		System.out.println("Avg time of execution: "+avg_time);
		int sec=(int)(avg_time%60);
		int min=(int)((avg_time/60)%60);
		time_avg= String.valueOf(min)+" minute(s), "+String.valueOf(sec)+" seconds";
		
		int sec1=maxval%60;
		int min1=(maxval/60)%60;
		time_max= String.valueOf(min1)+" minute(s), "+String.valueOf(sec1)+" seconds";
		
		return dictionary;
		
	}
	
	private Map invokeTestScript()
	{
		String testStatus, strIDs = "";
		String errorDescription="";   
		Map dictionary = new HashMap();
		
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		
		if(frameworkParameters.getStopExecution()) {
			testStatus = "Aborted";
		} else {
			DriverScript driverScript = new DriverScript(this.testParameters);
			driverScript.setTestExecutedInUnitTestFramework(false);
			driverScript.driveTestExecution();
			testStatus = driverScript.getTestStatus();
			strIDs = driverScript.getIDs();
			errorDescription = driverScript.getErrorDescription();
		}
        dictionary.put("TestStatus",testStatus);
        dictionary.put("ErrorDescription",errorDescription);
        //dictionary.put("ReferenceNumber",strIDs);
		
		
		return dictionary;
	}
	
	public void putDataTime(String datatableName, String datasheetName, String testCaseName, String fieldName, String dataValue)
	{
		//checkPreRequisites();
		
		String runTimeDatatablePath = strReportPath;
		
		ExcelDataAccess testDataAccess = new ExcelDataAccess(runTimeDatatablePath, datatableName);
		testDataAccess.setDatasheetName(datasheetName);	
		
		int nPutInstances = testDataAccess.getLastRowNum();
		for (int currentPutInstance = 1; currentPutInstance <= nPutInstances; currentPutInstance++) {
			String strCurrentTestcase = testDataAccess.getValue(currentPutInstance, "TestCase");			
			if(strCurrentTestcase.equalsIgnoreCase(testCaseName)){
					
				testDataAccess.setValue(currentPutInstance, fieldName, dataValue);
				break;
			
			}
		}
		
	}
	
}