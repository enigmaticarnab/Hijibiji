package supportlibraries;

import com.util.frameworkutils.TestParameters;

import org.openqa.selenium.Platform;


/**
 * Class to encapsulate various input parameters required for each test script
 * @author Ravi
 */
public class SeleniumTestParameters extends TestParameters
{
	public SeleniumTestParameters(String currentScenario, String currentTestcase)
	{
		super(currentScenario, currentTestcase);
	}
	
	
	private Browser browser;
	/**
	 * Function to get the browser for a specific test
	 * @return The browser
	 */
	public Browser getBrowser()
	{
		return browser;
	}
	/**
	 * Function to set the browser for a specific test
	 */
	public void setBrowser(Browser browser)
	{
		this.browser = browser;
	}
	
	private String browserVersion;
	/**
	 * Function to get the browserVersion for a specific test
	 * @return The browserVersion
	 */
	public String getBrowserVersion()
	{
		return browserVersion;
	}
	/**
	 * Function to set the browserVersion for a specific test
	 */
	public void setBrowserVersion(String version)
	{
		this.browserVersion = version;
	}
	
	private Platform platform;
	/**
	 * Function to get the platform for a specific test
	 * @return The platform
	 */
	public Platform getPlatform()
	{
		return platform;
	}
	/**
	 * Function to set the platform for a specific test
	 */
	public void setPlatform(Platform platform)
	{
		this.platform = platform;
	}
	private String filePath;
	/**
	 * Function to get the filePath for a specific test
	 * @return The filePath
	 */
	public String getFilePath()
	{
		return filePath;
	}
	/**
	 * Function to set the filePath for a specific test
	 */
	public void setFilePath(String filepath)
	{
		this.filePath = filepath;
	}
	private String count;
	/**
	 * Function to get the Count for a specific test
	 * @return The count
	 */	
	public String getCount()
	{
		return count;
	}
	/**
	 * Function to set the Count for a specific test
	 */
	public void setCount(String count)
	{
		this.count = count;
	}
}