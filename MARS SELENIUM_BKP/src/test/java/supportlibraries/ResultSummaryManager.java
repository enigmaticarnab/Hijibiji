package supportlibraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;

import com.util.frameworkutils.FrameworkParameters;
import com.util.frameworkutils.ReportSettings;
import com.util.frameworkutils.ReportTheme;
import com.util.frameworkutils.ReportThemeFactory;
import com.util.frameworkutils.Settings;
import com.util.frameworkutils.TimeStamp;
import com.util.frameworkutils.Util;
import com.util.frameworkutils.ReportThemeFactory.Theme;


/**
 * Abstract class that manages the result summary creation during a batch execution
 * @author Ravi
 */
public abstract class ResultSummaryManager
{
	/**
	 * The {@link SeleniumReport} object used for managing the result summary
	 */
	protected static SeleniumReport summaryReport;
	
	private static ReportSettings reportSettings;
	protected static String reportPath;
	private static String timeStamp;
	protected static String strMarket = "AU";
	
	private static Date overallStartTime, overallEndTime;
	private static String strPathToJava = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "java";
	// All the above variables have been marked as static
	// so that they will maintain their state across multiple threads
	
	/**
	 * The {@link Properties} object with settings loaded from the framework properties file
	 */
	protected static Properties properties;
	/**
	 * The {@link FrameworkParameters} object
	 */
	protected static FrameworkParameters frameworkParameters =
									FrameworkParameters.getInstance();
	
	
	protected static void setRelativePath()
	{
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		if(relativePath.contains("supportlibraries")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		relativePath = relativePath + strPathToJava;
		frameworkParameters.setRelativePath(relativePath);
	}
	
	protected static void initializeTestBatch()
	{
		overallStartTime = Util.getCurrentTime();
		
		properties = Settings.getInstance();
	}
	
	protected static void initializeSummaryReport(String runConfiguration, int nThreads)
	{
		frameworkParameters.setRunConfiguration(runConfiguration);
		timeStamp = TimeStamp.getInstance();
		
		initializeReportSettings();
		ReportTheme reportTheme =
				ReportThemeFactory.getReportsTheme(Theme.valueOf(properties.getProperty("ReportsTheme")));
		
		summaryReport = new SeleniumReport(reportSettings, reportTheme);
		
		summaryReport.initialize();
		summaryReport.initializeResultSummary(strMarket);
		createResultSummaryHeader(nThreads);
	}
	
	protected static void initializeReportSettings()
	{
		
		String strReportRelativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
//		if(strReportRelativePath.contains("supportlibraries")) {
//			strReportRelativePath = new File(System.getProperty("user.dir")).getParent();
//		}
		
		strReportRelativePath = strReportRelativePath + Util.getFileSeparator() + "target";		
		reportPath = strReportRelativePath +
						Util.getFileSeparator() + "Results" +
						Util.getFileSeparator() + timeStamp;
		reportSettings = new ReportSettings(reportPath, "");
	
//		strReportRelativePath = strReportRelativePath + "\\target";		
//		reportPath = strReportRelativePath +						
//						Util.getFileSeparator() + timeStamp;
//		reportSettings = new ReportSettings(reportPath, "");
		
		reportSettings.setDateFormatString(properties.getProperty("DateFormatString"));
		reportSettings.setProjectName(properties.getProperty("ProjectName"));
		reportSettings.generateExcelReports = Boolean.parseBoolean(properties.getProperty("ExcelReport"));
		reportSettings.generateHtmlReports = Boolean.parseBoolean(properties.getProperty("HtmlReport"));
		reportSettings.linkTestLogsToSummary = true;
	}
	
	protected static void createResultSummaryHeader(int nThreads)
	{
		summaryReport.addResultSummaryHeading("PIM2C - Automation Execution Result Summary", strMarket);
		summaryReport.addResultSummarySubHeading("Date & Time",
								": " + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString")),
								"OnError", ": " + properties.getProperty("OnError"), strMarket);
		summaryReport.addResultSummarySubHeading("Run Configuration",
								": " + properties.getProperty("RunConfiguration"),
								"No. of threads", ": " + nThreads, strMarket);
		
		summaryReport.addResultSummaryTableHeadings(strMarket);
	}
	
	protected static void setupErrorLog() throws FileNotFoundException
	{
		String errorLogFile = reportPath + Util.getFileSeparator() + "ErrorLog.txt";
		System.setErr(new PrintStream(new FileOutputStream(errorLogFile)));			
	}
	
	protected static void wrapUp()
	{
		overallEndTime = Util.getCurrentTime();
		String totalExecutionTime =
				Util.getTimeDifference(overallStartTime, overallEndTime);
		String avgtime=Util.getTimeDifference(overallStartTime, overallEndTime);
		String peaktime="Peak time";
		summaryReport.addResultSummaryFooter(totalExecutionTime,avgtime,peaktime, strMarket);
	}
	
	protected static void launchResultSummary()
	{
		if (reportSettings.generateHtmlReports) {
			try {
				Runtime.getRuntime().exec("RunDLL32.EXE shell32.dll,ShellExec_RunDLL " +
												reportPath + Util.getFileSeparator() + "HTML Results" + Util.getFileSeparator() + "Summary_"+strMarket+".Html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (reportSettings.generateExcelReports) {
			try {
				Runtime.getRuntime().exec("RunDLL32.EXE shell32.dll,ShellExec_RunDLL " +
												reportPath + Util.getFileSeparator() + "Excel Results" + Util.getFileSeparator() + "Summary"+strMarket+".xls");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}