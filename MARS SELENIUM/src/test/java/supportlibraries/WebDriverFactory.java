package supportlibraries;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;

import org.openqa.selenium.ie.InternetExplorerDriver;

 

import com.util.frameworkutils.CraftDataTable;
import com.util.frameworkutils.FrameworkException;
import com.util.frameworkutils.Settings;
import com.util.frameworkutils.Util;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.*;


/**
 * Factory class for creating the {@link WebDriver} object as required
 * @author Ravi
 */
public class WebDriverFactory
{
	private static Properties properties;
	private static String strPathToDownloads = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "resources" + Util.getFileSeparator() + "Downloads";
		
	private WebDriverFactory()
	{
		// To prevent external instantiation of this class
	}
	
	
	/**
	 * Function to return the appropriate {@link WebDriver} object based on the parameters passed
	 * @param browser The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	public static WebDriver getDriver(String strPath, Browser browser,String SauceJobName )
	{
		WebDriver driver = null;
		
		if(strPath == null){				
			strPath = new File(System.getProperty("user.dir")).getAbsolutePath();
			strPath = strPath + strPathToDownloads;
		}else{
			String strPath1 = new File(System.getProperty("user.dir")).getAbsolutePath();
			strPath1 = strPath1 + strPathToDownloads;
			strPath = strPath1 + strPath;
		}
		
		//Browser Selection
		String strEnvBrowser = System.getenv("SELENIUM_BROWSER");			
		//Version Selection
		String strEnvVersion = System.getenv("SELENIUM_VERSION");		
		
		if(strEnvBrowser != null){
			if(strEnvBrowser.equalsIgnoreCase("chrome")){
				browser.setValue("chrome");
			}else if(strEnvBrowser.equalsIgnoreCase("firefox")){
				browser.setValue("firefox");
				if(strEnvVersion == null){
					strEnvVersion = "30";
				}
			}else if(strEnvBrowser.equalsIgnoreCase("htmlunit")){
				browser.setValue("htmlunit");
			}else if(strEnvBrowser.equalsIgnoreCase("internet explorer")){
				browser.setValue("internet explorer");
			}else if(strEnvBrowser.equalsIgnoreCase("opera")){
				browser.setValue("opera");
			}else if(strEnvBrowser.equalsIgnoreCase("safari")){
				browser.setValue("safari");
			}else if(strEnvBrowser.equalsIgnoreCase("phantomjs")){
				browser.setValue("phantomjs");
			}
		}
		
		//Platform Selection		
		Platform envPlatform = Platform.VISTA;
		String strEnvPlatform = System.getenv("SELENIUM_PLATFORM");
		if(strEnvPlatform != null){			
			if(strEnvPlatform.toLowerCase().contains("windows")){
				envPlatform = Platform.VISTA;
			}else if(strEnvPlatform.toLowerCase().contains("linux")){
				envPlatform = Platform.LINUX;
			}else if(strEnvPlatform.toLowerCase().contains("unix")){
				envPlatform = Platform.UNIX;
			}
		}
		
		properties = Settings.getInstance();
				
		boolean proxyRequired =
				Boolean.parseBoolean(properties.getProperty("ProxyRequired"));
		boolean sauceLabs =
				Boolean.parseBoolean(properties.getProperty("SauceLabs"));
				
		String sauceUser =  System.getenv("SAUCE_USER_NAME");
		String sauceKey =  System.getenv("SAUCE_API_KEY");		
//		String sauceURL = System.getenv("SELENIUM_STARTING_URL");
		String sauceURL = "localhost:" + System.getenv("SELENIUM_PORT") + "/wd/hub"; 					//added temporarily as jobs are not working
		String url = "http://" + sauceUser +":" + sauceKey + "@"+ sauceURL;
		
		switch(browser) {
		case Chrome:
			// Takes the system proxy settings automatically
			
			try{
				if(!sauceLabs){
					System.setProperty("webdriver.chrome.driver",
							properties.getProperty("ChromeDriverPath"));
					driver = new ChromeDriver();
				}else{						
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setPlatform(envPlatform);
					capabilities.setBrowserName(strEnvBrowser);
					if(strEnvVersion != null){
						capabilities.setVersion(strEnvVersion);		
					}
					//added name capabilities to display test case name in Sauce labs.
					capabilities.setCapability("name", SauceJobName);	
					capabilities.setCapability("seleniumVersion", "2.44.0");
					capabilities.setCapability("idleTimeout", "180");
					capabilities.setCapability("maxDuration", "5400");//Added this to run the test cases upto 1 hour
					// Create the connection to Sauce Labs to run the tests
					driver = new RemoteWebDriver(new URL(url), capabilities);	
					((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				}
			}catch(MalformedURLException e){
				e.printStackTrace();
			}		
			
			break;
			
		case Firefox:
				// Takes the system proxy settings automatically
				try{	
					
					//Proxy setting
					org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
					boolean isProxyNeeded = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));
					System.out.println("isProxyNeeded: "+isProxyNeeded);
					if(isProxyNeeded)
					{
						String PROXY = properties.getProperty("LocalProxy") + ":" +
								properties.getProperty("LocalProxyPort");	
						
						proxy.setHttpProxy(PROXY)
						     .setFtpProxy(PROXY)
						     .setSslProxy(PROXY);
						System.out.println("Firefox in getDriver selected");
						System.out.println("PROXY: "+PROXY);
						proxy.setNoProxy(properties.getProperty("LocalNoProxyFor"));
						proxy.setProxyType(ProxyType.MANUAL);
					}
					else
					{
						String PROXY = properties.getProperty("ProxyHost") + ":" +
								properties.getProperty("ProxyPort");	
						proxy.setHttpProxy(PROXY)
						     .setFtpProxy(PROXY)
						     .setSslProxy(PROXY);
						System.out.println("Firefox in getDriver selected");
						System.out.println("PROXY: "+PROXY);
						proxy.setNoProxy(properties.getProperty("NoProxyFor"));
						proxy.setProxyType(ProxyType.MANUAL);
					}
					
					
					
					//Download setting
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.folderList", 2);     
	                profile.setPreference("browser.download.dir",strPath);
	                profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/comma,application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/msword,application/x-rar-compressed,application/octet-stream,application/csv,text/csv,application/Zip");
				
	                if(sauceLabs){
	                	DesiredCapabilities capabilities = new DesiredCapabilities();
						capabilities.setPlatform(envPlatform);
						capabilities.setBrowserName(strEnvBrowser);
						capabilities.setVersion(strEnvVersion);					        						
						//added name capabilities to display test case name in Sauce labs.
						capabilities.setCapability("name", SauceJobName);	
						capabilities.setCapability("seleniumVersion", "2.44.0");
						capabilities.setCapability("idleTimeout", "180");
						capabilities.setCapability("maxDuration", "5400");//Added this to run the test cases upto 1 hour
						capabilities.setCapability(FirefoxDriver.PROFILE, profile);
						// Create the connection to Sauce Labs to run the tests
//						System.out.println(" Capability set ");
//						System.out.println(" url: "+ url );
						driver = new RemoteWebDriver(new URL(url), capabilities);
//						System.out.println(" Object for the driver is created ");
						((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
//						System.out.println(" Initializing  LocalFileDetector is done  ");	                	
	                }else{
	                	if(proxyRequired){
	                		DesiredCapabilities cap = new DesiredCapabilities();
							cap.setCapability(CapabilityType.PROXY, proxy);	
							cap.setCapability(FirefoxDriver.PROFILE, profile);
							driver = new FirefoxDriver(cap);
	                	}else{
//	                		driver = new FirefoxDriver(new FirefoxBinary(new File("C:/Users/hmankala/AppData/Local/Mozilla Firefox/firefox.exe")), profile);
		                	driver = new FirefoxDriver(profile);
	                	}
	                }					
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			break;
			
		case HtmlUnit:
			// Does not take the system proxy settings automatically!
			
			driver = new HtmlUnitDriver();
			
			if (proxyRequired) {
				boolean proxyAuthenticationRequired =
						Boolean.parseBoolean(properties.getProperty("ProxyAuthenticationRequired"));
				if(proxyAuthenticationRequired) {
					// NTLM authentication for proxy supported
					
					driver = new HtmlUnitDriver() {
					@Override
					protected WebClient modifyWebClient(WebClient client) {
						DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
						credentialsProvider.addNTLMCredentials(properties.getProperty("Username"),
																properties.getProperty("Password"),
																properties.getProperty("ProxyHost"),
																Integer.parseInt(properties.getProperty("ProxyPort")),
																"", properties.getProperty("Domain"));
						client.setCredentialsProvider(credentialsProvider);
						return client;
						}
					};
				}
				
				((HtmlUnitDriver) driver).setProxy(properties.getProperty("ProxyHost"),
											Integer.parseInt(properties.getProperty("ProxyPort")));
			}
			
			break;
			
		case InternetExplorer:
			// Takes the system proxy settings automatically
			
			try{
				if(!sauceLabs){
					System.setProperty("webdriver.ie.driver",
							properties.getProperty("InternetExplorerDriverPath"));
					driver = new InternetExplorerDriver();
				}else{						
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setPlatform(envPlatform);
					capabilities.setBrowserName(strEnvBrowser);
					if(strEnvVersion != null){
						capabilities.setVersion(strEnvVersion);		
					}
					//added name capabilities to display test case name in Sauce labs.
					capabilities.setCapability("name", SauceJobName);	
					capabilities.setCapability("seleniumVersion", "2.44.0");
					capabilities.setCapability("idleTimeout", "180");
					capabilities.setCapability("maxDuration", "5400");//Added this to run the test cases upto 1 hour
					// Create the connection to Sauce Labs to run the tests
					driver = new RemoteWebDriver(new URL(url), capabilities);
					((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				}
			}catch(MalformedURLException e){
				e.printStackTrace();
			}		
			
			break;
			
		case Safari:
			// Takes the system proxy settings automatically
			
			driver = new SafariDriver();
			break;
			
		
			
		default:
			throw new FrameworkException("Unhandled browser!");
		}		
		
		driver.manage().window().maximize();
		return driver;
	}
	
	private static DesiredCapabilities getProxyCapabilities()
	{
		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		
		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":" +
									properties.getProperty("ProxyPort");
		
		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
		
		return desiredCapabilities;
	}
	
	/**
	 * Function to return the appropriate {@link WebDriver} object based on the parameters passed
	 * @param browser The {@link Browser} to be used for the test execution
	 * @param remoteUrl The URL of the remote machine to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	public static WebDriver getDriver(Browser browser, String remoteUrl)
	{
		return getDriver(browser, null, null, remoteUrl);
	}
	
	/**
	 * Function to return the appropriate {@link WebDriver} object based on the parameters passed
	 * @param browser The {@link Browser} to be used for the test execution
	 * @param browserVersion The browser version to be used for the test execution
	 * @param platform The {@link Platform} to be used for the test execution
	 * @param remoteUrl The URL of the remote machine to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	public static WebDriver getDriver(Browser browser, String browserVersion,
												Platform platform, String remoteUrl)
	{
		// For running RemoteWebDriver tests in Chrome and IE:
		// The ChromeDriver and IEDriver executables needs to be in the PATH of the remote machine
		// To set the executable path manually, use:
		// java -Dwebdriver.chrome.driver=/path/to/driver -jar selenium-server-standalone.jar
		// java -Dwebdriver.ie.driver=/path/to/driver -jar selenium-server-standalone.jar
		
		properties = Settings.getInstance();
		boolean proxyRequired =
				Boolean.parseBoolean(properties.getProperty("ProxyRequired"));
		
		DesiredCapabilities desiredCapabilities = null;
		
		if ((browser.equals(Browser.HtmlUnit) || browser.equals(Browser.Opera))
																&& proxyRequired) {
			desiredCapabilities = getProxyCapabilities();
		} else {
			desiredCapabilities = new DesiredCapabilities();
		}
				
		desiredCapabilities.setBrowserName(browser.getValue());
		
		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}
		
		desiredCapabilities.setJavascriptEnabled(true);	// Pre-requisite for remote execution
		
		URL url;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FrameworkException("The specified remote URL is malformed");
		}
		
		return new RemoteWebDriver(url, desiredCapabilities);
	}
}