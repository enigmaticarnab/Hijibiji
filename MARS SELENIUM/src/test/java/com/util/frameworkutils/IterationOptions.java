package com.util.frameworkutils;

/**
 * Enumeration to represent the various options for iteration mode
 * @author Ravi
 */
public enum IterationOptions
{
	/**
	 * Run all iterations specified in the test data sheet
	 */
	RunAllIterations,
	/**
	 * Run only the first iteration specified in the test data sheet
	 */
	RunOneIterationOnly,
	/**
	 * Run the range of iterations specified by StartIteration and EndIteration
	 */
	RunRangeOfIterations;
}