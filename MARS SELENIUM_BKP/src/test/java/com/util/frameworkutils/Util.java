package com.util.frameworkutils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Class to encapsulate utility functions of the framework
 * @author Ravi
 */
public class Util
{
	private Util()
	{
		// To prevent external instantiation of this class
	}
	
	/**
	 * Function to get the separator string to be used for directories and files based on the current OS
	 * @return The file separator string
	 */
	public static String getFileSeparator()
	{
		return System.getProperty("file.separator");
	}
	
	/**
	 * Function to return the current time
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime()
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	/**
	 * Function to return the current time, formatted as per the DateFormatString setting
	 * @param dateFormatString The date format string to be applied
	 * @return The current time, formatted as per the date format string specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString)
	{
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * Function to format the given time variable as specified by the DateFormatString setting
	 * @param time The date/time variable to be formatted
	 * @param dateFormatString The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString)
	{
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}
	
	/**
	 * Function to get the time difference between 2 Date variables in minutes/seconds format
	 * @param startTime The start time
	 * @param endTime The end time
	 * @return Time difference in minutes/seconds format
	 */
	public static String getTimeDifference(Date startTime, Date endTime)
	{
	
		return (Integer.parseInt(getTimeDifferenceInSeconds(startTime,endTime)) / 60) + " minute(s), "
				+ (Integer.parseInt(getTimeDifferenceInSeconds(startTime,endTime)) % 60) + " seconds";
	}
	
	
	/*
	 * Calculating the time difference in seconds
	 */
	
	public static String getTimeDifferenceInSeconds(Date startTime, Date endTime){
		
		long timeDifference = endTime.getTime() - startTime.getTime();
		return Long.toString(timeDifference /= 1000);
			
		
	}

	/**
	 * Build a file path from segments in a cross-platform manner
	 * @param segments Sequence of arbitrarily-many path segments
	 * @return Time difference in minutes/seconds format
	 */
//	public static String pathJoin(String...segments) {
//		return String.join(File.separator, segments);
//	}
}