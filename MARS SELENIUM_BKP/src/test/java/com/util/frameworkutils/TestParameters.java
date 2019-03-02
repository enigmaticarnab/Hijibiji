package com.util.frameworkutils;

/**
 * Class to encapsulate various input parameters required for each test script
 * @author Ravi
 */
public class TestParameters
{
	private final String currentScenario;
	/**
	 * Function to get the current test scenario/module
	 * @return The current test scenario/module
	 */
	public String getCurrentScenario()
	{
		return currentScenario;
	}
	
	private final String currentTestcase;
	/**
	 * Function to get the current test case
	 * @return The current test case
	 */
	public String getCurrentTestcase()
	{
		return currentTestcase;
	}
	
	private String currentTestDescription;
	/**
	 * Function to get the description of the current test case
	 * @return The description of the current test case
	 */
	public String getCurrentTestDescription()
	{
		return currentTestDescription;
	}
	/**
	 * Function to set the description of the current test case
	 * @param currentTestDescription The description of the current test case
	 */
	public void setCurrentTestDescription(String currentTestDescription)
	{
		this.currentTestDescription = currentTestDescription;
	}
	
	private IterationOptions iterationMode;
	/**
	 * Function to get the iteration mode
	 * @return The iteration mode
	 * @see IterationOptions
	 */
	public IterationOptions getIterationMode()
	{
		return iterationMode;
	}
	/**
	 * Function to set the iteration mode
	 * @param iterationMode The iteration mode
	 * @see IterationOptions
	 */
	public void setIterationMode(IterationOptions iterationMode)
	{
		this.iterationMode = iterationMode;
	}
	
	private int startIteration = 1;
	/**
	 * Function to get the start iteration
	 * @return The start iteration
	 */
	public int getStartIteration()
	{
		return startIteration;
	}
	/**
	 * Function to set the start iteration
	 * @param startIteration The start iteration (defaults to 1 if the input is <=0)
	 */
	public void setStartIteration(int startIteration)
	{
		if(startIteration > 0) {
			this.startIteration = startIteration;
		}
	}
	
	private int endIteration = 1;
	/**
	 * Function to get the end iteration
	 * @return The end iteration
	 */
	public int getEndIteration()
	{
		return endIteration;
	}
	/**
	 * Function to set the end iteration
	 * @param endIteration The end iteration (defaults to 1 if the input is <=0)
	 */
	public void setEndIteration(int endIteration)
	{
		if(endIteration > 0) {
			this.endIteration = endIteration;
		}
	}
	
	
	/**
	 * Constructor to initialize the {@link TestParameters} object
	 * @param currentScenario The current test scenario/module
	 * @param currentTestcase The current test case
	 */
	public TestParameters(String currentScenario, String currentTestcase)
	{
		this.currentScenario = currentScenario;
		this.currentTestcase = currentTestcase;
	}
}