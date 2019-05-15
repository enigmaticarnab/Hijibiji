package com.util.frameworkutils;

import java.io.File;
import java.util.Properties;


/**
 * Singleton class which manages the creation of timestamped result folders for every test batch execution
 * @author Ravi
 */
public class TimeStamp
{
	private static volatile String path;
	
	private TimeStamp()
	{
		// To prevent external instantiation of this class
	}
	
	/**
	 * Function to return the timestamped result folder path
	 * @return The timestamped result folder path
	 */
	public static String getInstance()
	{
		if(path == null) {
			synchronized (TimeStamp.class) {
				if(path == null) {	// Double-checked locking
					FrameworkParameters frameworkParameters =
											FrameworkParameters.getInstance();
					
					if(frameworkParameters.getRelativePath() == null) {
						throw new FrameworkException("FrameworkParameters.relativePath is not set!");
					}
					if(frameworkParameters.getRunConfiguration() == null) {
						throw new FrameworkException("FrameworkParameters.runConfiguration is not set!");
					}
					
					Properties properties = Settings.getInstance();
					path = frameworkParameters.getRunConfiguration() + Util.getFileSeparator() + "Run_" +
							Util.getCurrentFormattedTime(properties.getProperty("DateFormatString")).replace(" ", "_").replace(":", "-");
					
					String strResultsCreationRelativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
					if(strResultsCreationRelativePath.contains("supportlibraries")) {						
						strResultsCreationRelativePath = new File(System.getProperty("user.dir")).getParent();
					}
					strResultsCreationRelativePath = strResultsCreationRelativePath + Util.getFileSeparator() + "target";
					
					String reportPathWithTimeStamp =
							strResultsCreationRelativePath +
									Util.getFileSeparator() + "Results" +
									Util.getFileSeparator() + path;
					
//					strResultsCreationRelativePath = strResultsCreationRelativePath + "\\target";
//					
//					String reportPathWithTimeStamp =
//							strResultsCreationRelativePath +									
//									Util.getFileSeparator() + path;
		            
		            new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}
		
		return path;
	}
	
	/**
	 * Function to set the path of the timestamp folder (relative to the Results folder)
	 * @param myPath The path of the timestamp folder (relative to the Results folder)
	 */
	public static void setPath(String myPath)
	{
		if(path == null) {
			synchronized (TimeStamp.class) {
				if(path == null) {	// Double-checked locking
					FrameworkParameters frameworkParameters =
											FrameworkParameters.getInstance();
					
					if(frameworkParameters.getRelativePath() == null) {
						throw new FrameworkException("FrameworkParameters.relativePath is not set!");
					}
					
					path = myPath;
					
					String reportPathWithTimeStamp =
									frameworkParameters.getRelativePath() +
									Util.getFileSeparator() + "Results" +
									Util.getFileSeparator() + path;
					
					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		} else {
			throw new FrameworkException("The timestamp path is already initialized!");
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}