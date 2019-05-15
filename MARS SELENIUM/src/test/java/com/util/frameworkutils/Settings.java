package com.util.frameworkutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Singleton class that encapsulates the user settings specified in the properties file of the framework
 * @author Ravi
 */
public class Settings
{
	private static Properties properties = loadFromPropertiesFile();
	/*Properties instance for test data
	 * @author: TCS
	 */
	private static Properties testDataProperties=loadTestDataFromPropertiesFile();
	
	private Settings()
	{
		// To prevent external instantiation of this class
	}
	
	/* Load test data properties file
	 * @author: TCS
	 */
	private static Properties loadTestDataFromPropertiesFile() {
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
				
				if(frameworkParameters.getRelativePath() == null) {
					throw new FrameworkException("FrameworkParameters.relativePath is not set!");
				}
				
				Properties properties = new Properties();
				
				try {
					
					String strPropertiesRelativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
					if(strPropertiesRelativePath.contains("supportlibraries")) {
						strPropertiesRelativePath = new File(System.getProperty("user.dir")).getParent();
					}
					String strPathToResources = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "resources";
					strPropertiesRelativePath = strPropertiesRelativePath + strPathToResources;
					
					properties.load(new FileInputStream(strPropertiesRelativePath +
											Util.getFileSeparator() + "TestData.properties"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new FrameworkException("FileNotFoundException while loading the Global Settings file");
				} catch (IOException e) {
					e.printStackTrace();
					throw new FrameworkException("IOException while loading the Global Settings file");
				}
				
				return properties;
			}
	
	/**
	 * Function to return the singleton instance of the {@link Properties} object
	 * @return Instance of the {@link Properties} object
	 */
	public static Properties getInstance()
	{
		return properties;
	}
	
	/*Return properties instance
	 * @ author : TCS
	 */
	
	public static Properties getTestDataInstance()
	{
		return testDataProperties;
	}
	
	private static Properties loadFromPropertiesFile()
	{
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		
		if(frameworkParameters.getRelativePath() == null) {
			throw new FrameworkException("FrameworkParameters.relativePath is not set!");
		}
		
		Properties properties = new Properties();
		
		try {
			
			String strPropertiesRelativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
			if(strPropertiesRelativePath.contains("supportlibraries")) {
				strPropertiesRelativePath = new File(System.getProperty("user.dir")).getParent();
			}
			String strPathToResources = Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"  + Util.getFileSeparator() + "resources";
			strPropertiesRelativePath = strPropertiesRelativePath + strPathToResources;
			
			properties.load(new FileInputStream(strPropertiesRelativePath +
									Util.getFileSeparator() + "Global Settings.properties"));
			
			//Properties sysProperties = System.getProperties();
			
			//for(Object key:sysProperties.keySet())	{
			//	properties.put(key, sysProperties.get(key));
			//}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("FileNotFoundException while loading the Global Settings file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("IOException while loading the Global Settings file");
		}
		
		return properties;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}