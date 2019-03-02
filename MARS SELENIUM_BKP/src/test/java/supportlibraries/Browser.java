package supportlibraries;

/**
 * Enumeration to represent the browser to be used for execution
 * @author Ravi
 */
public enum Browser
{
	//Android("android"),
	Chrome("chrome"),
	Firefox("firefox"),
	HtmlUnit("htmlunit"),
	InternetExplorer("internet explorer"),
	//iPhone("iPhone"),
	//iPad("iPad"),
	Opera("opera"),
	Safari("safari"),
	PhantomJS("phantomjs");
	
	private String value;
	
	Browser(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value1)
	{
		this.value = value1;		
	}
	
	/*@Override
	public String toString()
	{
		return value;
	}*/
}