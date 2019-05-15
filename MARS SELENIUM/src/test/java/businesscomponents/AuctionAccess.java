package businesscomponents;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.frameworkutils.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import objectrepositories.Auction;
import objectrepositories.GTC;
import objectrepositories.ObjectRepositories;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

public class AuctionAccess extends ReusableLibrary {
	Random rand = new Random(); 
	WebDriverWait wait = new WebDriverWait(driver, 500);
	public AuctionAccess(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
	GenericFunctions GF = new GenericFunctions(scriptHelper);
	WebElement element;
	static String dealerId="";
	// this keyword helps login to the AA portal with user ID and Password 
	public void loginAA() throws Exception 
	{
		driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);		
		String strAppURL = properties.getProperty("AuctionAApplicationURL");
		String uid = dataTable.getData("General_Data", "UserID");	
		String pwd = dataTable.getData("General_Data", "Password");	
		String role = dataTable.getData("General_Data", "Role");
		String cmp = dataTable.getData("General_Data", "Company");
		driver.get(strAppURL);
		
		Thread.sleep(20000);
//		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 500);
		try
		{
			// The script is waiting for the LOGIN USER ID field to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.TXT_LOGIN_USER_ID)));
			// Entering user id 
			if(GF.isElementPresent(By.xpath(Auction.TXT_LOGIN_USER_ID)))
				driver.findElement(By.xpath(Auction.TXT_LOGIN_USER_ID)).sendKeys(uid);
			// entering login password 
			if(GF.isElementPresent(By.xpath(Auction.TXT_LOGIN_PWD)))
			driver.findElement(By.xpath(Auction.TXT_LOGIN_PWD)).sendKeys(pwd);
			report.updateTestLog("AA", "Application is opened ", Status.SCREENSHOT);
			
			driver.findElement(By.xpath(Auction.BTN_LOGIN)).click();
			Thread.sleep(5000);
			
			//selecting role 
 			if (GF.isElementPresent(By.xpath(Auction.BTN_ROLE_EDIT)));
			driver.findElement(By.xpath(Auction.BTN_ROLE_DRPDWN)).click();
			driver.findElement(By.xpath(Auction.BTN_ROLE_SLT)).click();
			
			// selecting company
			/*driver.findElement(By.xpath(Auction.BTN_COMPANY)).click();
			driver.findElement(By.xpath(Auction.BTN_COMPANY_SLT)).click();
			Thread.sleep(5000);*/
			
			driver.findElement(By.xpath(Auction.BTN_COMPANY)).click();
			Thread.sleep(3000);
			
			List<WebElement> myElements = driver.findElements(By.xpath(Auction.Company));
			for(WebElement e : myElements) {
			  String Comp=(e.getText());
			  System.out.println("Company is :"+Comp);
			  if(cmp.equalsIgnoreCase(Comp)) {
				  e.click();
				  Thread.sleep(5000);
				  System.out.println("Company is clicked :"+e);
				  break;
			  }
			  
			}
			
			
			// selecting company
			/*driver.findElement(By.xpath(Auction.BTN_COMPANY)).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(Auction.BTN_COMPANY_SLT_QA1)).click();*/
			
					
			// Checking that in landing page we are in
			try
			{
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.BTN_REFRESH)));
				GF.waitForObject(By.xpath(Auction.BTN_REFRESH), "Refresh");
				if(GF.isElementPresent(By.xpath(Auction.BTN_REFRESH))){
					report.updateTestLog("AA", "Login Successful ", Status.PASS);
					report.updateTestLog("Auction", "We are in landing page ", Status.SCREENSHOT);
					//GF.removehighlight(driver, element);
				}
			}
			catch(Exception e)
			{
				report.updateTestLog("AA", "Login Success But Page are taking longer time to load ", Status.FAIL);
			}

		}
			catch(Exception e)
		{
			report.updateTestLog("AA", "Login Failed due to Login Screen Load Delay", Status.FAIL);
		}

		//dealercreate();
		//Thread.sleep(5000);
		//createContact();
		//Thread.sleep(5000);
		//createLicense();
		//driver.quit();
		
	}
	
	// This below keyword searching dealer by DBA name 
	
	public void dealerSRH() throws Exception
    {
        Thread.sleep(10000);
    
        String DBAName= dataTable.getData("General_Data", "DBA Name");  
    try 
    {
    	 String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		 System.out.println(acctNum);
		 
		 acctNum=(acctNum.substring(7, 14));
		 System.out.println(acctNum);   
    	
		   driver.findElement(By.xpath(Auction.BTN_DEALER)).click();
           Thread.sleep(5000);
           System.out.println("Clicked on Dealer");
           driver.findElement(By.xpath(Auction.BTN_SEARCH)).click();
           Thread.sleep(10000);      
           driver.findElement(By.xpath(Auction.Search_ID)).sendKeys(acctNum);
           driver.findElement(By.xpath(Auction.BTN_DLRSRCH)).click();
           Thread.sleep(20000);      
           String DBANameGUI=driver.findElement(By.xpath(Auction.DBA_TXT)).getText();
           // Matching the UI vale with the excel value 
                  if (DBANameGUI.equalsIgnoreCase(DBAName))
                  {
                        report.updateTestLog("AA", "Dealer value matched ", Status.PASS);
                        report.updateTestLog("Auction", "We are in Search page ", Status.SCREENSHOT);
                  }
                  
                  else 
                  {
                        report.updateTestLog("AA", "Dealer value not matched", Status.FAIL);
                        
                  }
                  
           
    }
    
    catch (Exception e)
    {
           report.updateTestLog("AA", "Dealer search", Status.FAIL);
    }
    }
	
	public void clickonContackDetailsLink() {
		try 
		{
		if(driver.findElement(By.xpath(Auction.ContactOptionsLink)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.ContactOptionsLink)).click();
		Thread.sleep(2000);
		report.updateTestLog("AA", "Contact details dection is opened ", Status.PASS);
		}
		else
		{
			report.updateTestLog("AA", "Contact details dection is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Contact details dection link not found ", Status.FAIL);
		}
	}
	
	public void addContactDetails() throws InterruptedException {
		
		 String VisibilityGlobal = dataTable.getData("General_Data", "Visibility Global");
	        
	     String VisibilityGroup = dataTable.getData("General_Data", "Visibility Group");
	        
	     String VisibilityAuction = dataTable.getData("General_Data", "Visibility Auction");
	        
	     String AddressTypeBilling = dataTable.getData("General_Data", "AddressType Billing");
	        
	     String AddressTypePayments = dataTable.getData("General_Data", "AddressType Payments");
	        
	     String AddressTypeLot = dataTable.getData("General_Data", "AddressType Lot");
	        
	     String AddressTypeMailing = dataTable.getData("General_Data", "AddressType Mailing");
	        
	     String Location = dataTable.getData("General_Data", "Location");
	        
	     String LocationLot = dataTable.getData("General_Data", "LocationLot");
	 	
	     clickonContackDetailsLink();
	     Thread.sleep(2000);
	     if(VisibilityGlobal.equalsIgnoreCase("Y") && (Location.equalsIgnoreCase("Corporate Office"))) {
			
			selectVisibility("Global","Corporate Office");
			Thread.sleep(2000);
			addAllContactOptionDetailsForFaxType();
			Thread.sleep(1000);
			selectVisibility("Global","Corporate Office");
			Thread.sleep(2000);
			addAllContactOptionDetailsForTollFreeType();
			Thread.sleep(1000);
			selectVisibility("Global","Corporate Office");
			Thread.sleep(2000);
			addAllContactOptionDetailsForCellType();
			
			}
		
		if(VisibilityGlobal.equalsIgnoreCase("Y") && (LocationLot.equalsIgnoreCase("Y"))) {
			
			
			/*selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForFaxType();*/
			
			selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForCellType();
			
			selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForTollFreeType();
	
			selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForAlternateType();
			
			/*selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForWebSiteType();*/
			
			/*selectVisibility("Global","Lot");
			Thread.sleep(2000);
			addAllContactOptionDetailsForEmailAddressType();*/
			}
		
	}
	
	
	public void addAllContactOptionDetailsForAlternateType() throws InterruptedException 
	{
		
		
		String Type1= dataTable.getData("General_Data", "Type1");
		String Country= dataTable.getData("General_Data", "Lot_Country");
		String PhoneNumber= dataTable.getData("General_Data", "Phone Number");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElement10 = driver.findElements(By.xpath(Auction.TypeValue));
		for(WebElement x : myElement10) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type1.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type1);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		
		//Country value selection from country dropdown
		try {
			
			Thread.sleep(2000);	
			if(driver.findElement(By.xpath(Auction.Countrydropdownarrow)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.Countrydropdownarrow)).click();
			Thread.sleep(2000);
			}
			else
			{
				report.updateTestLog("AA", "Country dropdown arrow  arrow is not enabled ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Country dropdown arrow is not found ", Status.FAIL);
			}
			
			try {
			List<WebElement> myElement11 = driver.findElements(By.xpath(Auction.Countrydropdownvalue));
			for(WebElement x : myElement11) 
			{
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(Country.equalsIgnoreCase(Comp)) 
			  {
				  x.click();
				  Thread.sleep(1000);
				  System.out.println("Country Value is selected from Country dropdown :"+ Country);
				  report.updateTestLog("AA", "Country Value is selected from Country dropdown ", Status.PASS);
				  break;
			  }
			}
			}
			catch(Exception e)
			{
				report.updateTestLog("AA", "Country dropdown value is not found ", Status.FAIL);
			}
		
			//enter Phone Number
			try 
			{
			if(driver.findElement(By.xpath(Auction.PhoneNumber)).isDisplayed())
			{
			driver.findElement(By.xpath(Auction.PhoneNumber)).sendKeys(PhoneNumber);;
			Thread.sleep(1000);
			System.out.println("Phone Number is entered under Phone Number :"+PhoneNumber);
			}
			else
			{
				report.updateTestLog("AA", "Phone Number text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Phone Number field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	public void addAllContactOptionDetailsForWebSiteType() throws InterruptedException 
	{
		Thread.sleep(1000);
		
		
		Thread.sleep(1000);
		String Type5= dataTable.getData("General_Data", "Type5");
		System.out.println(Type5);
		//String Country= dataTable.getData("General_Data", "Lot_Country");
		String WebSite= dataTable.getData("General_Data", "WebSite");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElement11 = driver.findElements(By.xpath(Auction.TypeValue));
		Thread.sleep(1000);	
		for(WebElement x : myElement11) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type5.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type5);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		Thread.sleep(1000);	
			//enter Web Site
			try 
			{
			if(driver.findElement(By.xpath(Auction.WebSite)).isDisplayed())
			{
			Thread.sleep(1000);
			driver.findElement(By.xpath(Auction.WebSite)).sendKeys(WebSite);
			Thread.sleep(1000);
			System.out.println("Web Site is entered under WebSite :"+WebSite);
			}
			else
			{
				report.updateTestLog("AA", "Web Site text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Web Site field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	public void addAllContactOptionDetailsForEmailAddressType() throws InterruptedException 
	{
		Thread.sleep(1000);
		
		
		Thread.sleep(1000);
		String Type6= dataTable.getData("General_Data", "Type6");

		String EmailAddress= dataTable.getData("General_Data", "EmailAddress");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.TypeValue));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type6.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type6);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		
			//enter Web Site
			try 
			{
			if(driver.findElement(By.xpath(Auction.WebSite)).isDisplayed())
			{
			driver.findElement(By.xpath(Auction.WebSite)).sendKeys(EmailAddress);;
			Thread.sleep(1000);
			System.out.println("Email Address is entered under WebSite :"+EmailAddress);
			}
			else
			{
				report.updateTestLog("AA", "Email Address text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Email Address field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	 
	public void addAllContactOptionDetailsForCellType() throws InterruptedException 
	{
		
		
		Thread.sleep(1000);
		String Type2= dataTable.getData("General_Data", "Type2");
		String Country= dataTable.getData("General_Data", "Lot_Country");
		String CellPhoneNumber= dataTable.getData("General_Data", "CellPhoneNumber");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.TypeValue));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type2.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type2);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		
		//Country value selection from country dropdown
		try {
			
			Thread.sleep(2000);	
			if(driver.findElement(By.xpath(Auction.Countrydropdownarrow)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.Countrydropdownarrow)).click();
			Thread.sleep(2000);
			}
			else
			{
				report.updateTestLog("AA", "Country dropdown arrow  arrow is not enabled ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Country dropdown arrow is not found ", Status.FAIL);
			}
			
			try {
			List<WebElement> myElements = driver.findElements(By.xpath(Auction.Countrydropdownvalue));
			for(WebElement x : myElements) 
			{
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(Country.equalsIgnoreCase(Comp)) 
			  {
				  x.click();
				  Thread.sleep(1000);
				  System.out.println("Country Value is selected from Country dropdown :"+ Country);
				  report.updateTestLog("AA", "Country Value is selected from Country dropdown ", Status.PASS);
				  break;
			  }
			}
			}
			catch(Exception e)
			{
				report.updateTestLog("AA", "Country dropdown value is not found ", Status.FAIL);
			}
		
			//enter Phone Number
			try 
			{
			if(driver.findElement(By.xpath(Auction.PhoneNumber)).isDisplayed())
			{
			driver.findElement(By.xpath(Auction.PhoneNumber)).sendKeys(CellPhoneNumber);;
			Thread.sleep(1000);
			System.out.println("Phone Number is entered under Phone Number :"+CellPhoneNumber);
			}
			else
			{
				report.updateTestLog("AA", "Phone Number text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Phone Number field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	public void addAllContactOptionDetailsForFaxType() throws InterruptedException 
	{
		
		Thread.sleep(1000);	
		String Type3= dataTable.getData("General_Data", "Type3");
		String Country= dataTable.getData("General_Data", "Lot_Country");
		String FaxPhoneNumber= dataTable.getData("General_Data", "FaxPhoneNumber");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.TypeValue));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type3.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type3);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		
		//Country value selection from country dropdown
		try {
			
			Thread.sleep(2000);	
			if(driver.findElement(By.xpath(Auction.Countrydropdownarrow)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.Countrydropdownarrow)).click();
			Thread.sleep(1000);
			}
			else
			{
				report.updateTestLog("AA", "Country dropdown arrow  arrow is not enabled ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Country dropdown arrow is not found ", Status.FAIL);
			}
			
			try {
			List<WebElement> myElements = driver.findElements(By.xpath(Auction.Countrydropdownvalue));
			for(WebElement x : myElements) 
			{
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(Country.equalsIgnoreCase(Comp)) 
			  {
				  x.click();
				  Thread.sleep(1000);
				  System.out.println("Country Value is selected from Country dropdown :"+ Country);
				  report.updateTestLog("AA", "Country Value is selected from Country dropdown ", Status.PASS);
				  break;
			  }
			}
			}
			catch(Exception e)
			{
				report.updateTestLog("AA", "Country dropdown value is not found ", Status.FAIL);
			}
		
			//enter Phone Number
			try 
			{
			if(driver.findElement(By.xpath(Auction.PhoneNumber)).isDisplayed())
			{
			driver.findElement(By.xpath(Auction.PhoneNumber)).sendKeys(FaxPhoneNumber);;
			Thread.sleep(1000);
			System.out.println("Phone Number is entered under Phone Number :"+FaxPhoneNumber);
			}
			else
			{
				report.updateTestLog("AA", "Phone Number text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Phone Number field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	
	public void addAllContactOptionDetailsForTollFreeType() throws InterruptedException 
	{
		
		
		String Type4= dataTable.getData("General_Data", "Type4");
		String Country= dataTable.getData("General_Data", "Lot_Country");
		String TollFreePhoneNumber= dataTable.getData("General_Data", "TollFreePhoneNumber");
		
		// alternate value selected from Type dropdown
		try {
		
		Thread.sleep(1000);	
		if(driver.findElement(By.xpath(Auction.Typedropdownarrow)).isEnabled())
		{
		driver.findElement(By.xpath(Auction.Typedropdownarrow)).click();
		Thread.sleep(2000);
		}
		else
		{
			report.updateTestLog("AA", "Type dropdown arrow is not enabled ", Status.FAIL);
		}
		}
		catch(Exception e) 
		{
			report.updateTestLog("AA", "Type dropdown arrow is not found ", Status.FAIL);
		}
		
		try {
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.TypeValue));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(Type4.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("Type Value is selected from Type dropdown :"+ Type4);
			  report.updateTestLog("AA", "Type Value is selected from Type dropdown ", Status.PASS);
			  break;
		  }
		}
		}
		catch(Exception e)
		{
			report.updateTestLog("AA", "Type dropdown value is not found ", Status.FAIL);
		}
		
		//Country value selection from country dropdown
		try {
			
			Thread.sleep(2000);	
			if(driver.findElement(By.xpath(Auction.Countrydropdownarrow)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.Countrydropdownarrow)).click();
			Thread.sleep(2000);
			}
			else
			{
				report.updateTestLog("AA", "Country dropdown arrow  arrow is not enabled ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Country dropdown arrow is not found ", Status.FAIL);
			}
			
			try {
			List<WebElement> myElements = driver.findElements(By.xpath(Auction.Countrydropdownvalue));
			for(WebElement x : myElements) 
			{
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(Country.equalsIgnoreCase(Comp)) 
			  {
				  x.click();
				  Thread.sleep(1000);
				  System.out.println("Country Value is selected from Country dropdown :"+ Country);
				  report.updateTestLog("AA", "Country Value is selected from Country dropdown ", Status.PASS);
				  break;
			  }
			}
			}
			catch(Exception e)
			{
				report.updateTestLog("AA", "Country dropdown value is not found ", Status.FAIL);
			}
		
			//enter Phone Number
			try 
			{
			if(driver.findElement(By.xpath(Auction.PhoneNumber)).isDisplayed())
			{
			driver.findElement(By.xpath(Auction.PhoneNumber)).sendKeys(TollFreePhoneNumber);;
			Thread.sleep(1000);
			System.out.println("Phone Number is entered under Phone Number :"+TollFreePhoneNumber);
			}
			else
			{
				report.updateTestLog("AA", "Phone Number text box is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Phone Number field is not found ", Status.FAIL);
			}
			
			Thread.sleep(2000);
			//Click on save
			try 
			{
			if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled())
			{
			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
			Thread.sleep(2000);
			System.out.println("Save Button is clicked Under Add Contact Option ");
			}
			else
			{
				report.updateTestLog("AA", "Save Button Under Add Contact Option is not displayed ", Status.FAIL);
			}
			}
			catch(Exception e) 
			{
				report.updateTestLog("AA", "Save field is not found ", Status.FAIL);
			}
			
			
	}
	
	public void selectVisibility(String VisibilityValue,String LocationValue) throws InterruptedException
	{
		try {
		
		Thread.sleep(2000);	
			
		Thread.sleep(1000);
		if(driver.findElement(By.xpath(Auction.AddButton_ContactDetails)).isDisplayed() && driver.findElement(By.xpath(Auction.AddButton_ContactDetails)).isEnabled())
		{	
		driver.findElement(By.xpath(Auction.AddButton_ContactDetails)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.AddContactOption)));
		}
		else
		{
			report.updateTestLog("AA", "AddButton under ContactDetails is not displayed ", Status.FAIL);
		}
		
		if(driver.findElement(By.xpath(Auction.visibilitydropdownarrow)).isDisplayed() && driver.findElement(By.xpath(Auction.visibilitydropdownarrow)).isEnabled())
		{	
		driver.findElement(By.xpath(Auction.visibilitydropdownarrow)).click();
		
		Thread.sleep(1000);
		}
		else
		{
			report.updateTestLog("AA", "AddButton under ContactDetails is not displayed or not enabled ", Status.FAIL);
		}
		Thread.sleep(1000);
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.visibilityValue));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(VisibilityValue.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(1000);
			  System.out.println("VisibilityValue is:"+x);
			  break;
		  }
		} 
		Thread.sleep(2000);
		driver.findElement(By.xpath(Auction.Locationdropdownarrow)).click();
		Thread.sleep(1000);
		//String LocationValue= dataTable.getData("General_Data", "Location");
		List<WebElement> myElements1 = driver.findElements(By.xpath(Auction.locationValue));
		for(WebElement x : myElements1) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(LocationValue.equalsIgnoreCase(Comp)) 
		  {
			  x.click();
			  Thread.sleep(2000);
			  System.out.println("LocationValue is:"+x);
			  break;
		  }
		}
	}
		catch(Exception e) {
			System.out.println(e);
			report.updateTestLog("AA", "Problem occured in Web Element path in Select visibility section", Status.FAIL);
			report.updateTestLog("Auction", "Problem occured in Web Element path ", Status.SCREENSHOT);
		}
		
	}
	
	public void dealerSRHnew() throws Exception
    {
        Thread.sleep(5000);
       
        String acctNum="5430799";
        String DBAName= dataTable.getData("General_Data", "DBA Name"); 
        dealerId=acctNum;
    try 
    {
    	 /*String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		 System.out.println(acctNum);
		 
		 acctNum=(acctNum.substring(7, 14));
		 System.out.println(acctNum);  */ 
    	
		   driver.findElement(By.xpath(Auction.BTN_DEALER)).click();
           Thread.sleep(5000);
           System.out.println("Clicked on Dealer");
           driver.findElement(By.xpath(Auction.BTN_SEARCH)).click();
           Thread.sleep(5000);      
           //driver.findElement(By.xpath(Auction.TXT_DBA)).sendKeys(DBAName);
           driver.findElement(By.xpath(Auction.Search_ID)).sendKeys(acctNum);
           driver.findElement(By.xpath(Auction.BTN_DLRSRCH)).click();
           Thread.sleep(10000);      
           String DBANameGUI=driver.findElement(By.xpath(Auction.DBA_TXT)).getText();
           // Matching the UI vale with the excel value 
                  if (DBANameGUI.equalsIgnoreCase(DBAName))
                  {
                        report.updateTestLog("AA", "Dealer value matched ", Status.PASS);
                        report.updateTestLog("Auction", "We are in Search page ", Status.SCREENSHOT);
                  }
                  
                  else 
                  {
                        report.updateTestLog("AA", "Dealer value not matched", Status.FAIL);
                        
                  }
                  
                 /* for(int i=0;i<1;i++) {
                  clickOnAddressLink();
                  Thread.sleep(500);
                  getAddressvalue();
                  //break;
                  }*/
           
    }
    
    catch (Exception e)
    {
        System.out.println(e);   
    	report.updateTestLog("AA", "Dealer search", Status.FAIL);
    }
    }
	
	public void clickOnAdd() throws InterruptedException {
		
		if(driver.findElement(By.xpath(Auction.AddressAdd)).isEnabled() && driver.findElement(By.xpath(Auction.AddressAdd)).isDisplayed()) 
		{
		driver.findElement(By.xpath(Auction.AddressAdd)).click();
		report.updateTestLog("AA", "Add button is clicked ", Status.PASS);
		Thread.sleep(2000);
		}else {
			report.updateTestLog("AA", "Add button either not enabled or not displayed ", Status.FAIL);
		}
	}
	
	public void clickOnAddressLink() throws InterruptedException {
		
		if(driver.findElement(By.xpath(Auction.Addresses_Link)).isEnabled() && driver.findElement(By.xpath(Auction.Addresses_Link)).isDisplayed()) 
		{
		driver.findElement(By.xpath(Auction.Addresses_Link)).click();
		report.updateTestLog("AA", "Addresses Link is clicked ", Status.PASS);
		Thread.sleep(3000);
		}else {
			report.updateTestLog("AA", "Addresses Link either not enabled or not displayed ", Status.FAIL);
		}
	}

	public void dealercreate() throws Exception
    {
		System.out.println("I am at 2nd");
           String DBAName= dataTable.getData("General_Data", "DBA Name");
           String LegalName= dataTable.getData("General_Data", "Legal Name");
           String FederalIdCountry= dataTable.getData("General_Data", "Federal Id Country"); 
           String FederalIdType= dataTable.getData("General_Data", "Federal Id Type"); 
           String FederalId= dataTable.getData("General_Data", "Federal Id"); 
           String Confirm= dataTable.getData("General_Data", "Confirm"); 
           String CompanyType= dataTable.getData("General_Data", "Company Type"); 
           String DelearType= dataTable.getData("General_Data", "Delear Type");
           String BondCompany= dataTable.getData("General_Data", "Bond Company"); 
           String BondNumber= dataTable.getData("General_Data", "Bond Number"); 
    try 
    {
    	
        driver.findElement(By.xpath(Auction.BTN_DEALER)).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.BTN_ADD_DEALER)));
        Thread.sleep(500);
        System.out.println("Clicked on Dealer");
        driver.findElement(By.xpath(Auction.BTN_ADD_DEALER)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.IdentificationWindow)));        
        Thread.sleep(500);
          
        if(driver.findElement(By.xpath(Auction.IdentificationWindow)).isDisplayed()) 
        {
          report.updateTestLog("AA", "Identification Window is opened ", Status.PASS);          
        }
        else 
        {
          report.updateTestLog("AA", "Identification Window is not opened ", Status.FAIL);
         
        }
        
        DBAName=ranDom(DBAName);
        driver.findElement(By.xpath(Auction.Identification_DBA_Name)).sendKeys(DBAName);
        Thread.sleep(500);   
           //driver.findElement(By.xpath(Auction.Identification_LegalName)).sendKeys(LegalName);
           
        driver.findElement(By.xpath(Auction.SameAsDBA)).click();
        driver.findElement(By.xpath(Auction.FederalIdCountry_dropdown)).click();
		Thread.sleep(1000);
			
		List<WebElement> myElements = driver.findElements(By.xpath(Auction.Federal_Id_Country_WebElements));
		for(WebElement x : myElements) 
		{
		  String Comp=(x.getText());
		  System.out.println("Company is :"+Comp);
		  if(FederalIdCountry.equalsIgnoreCase(Comp))  
		  {
			 x.click();
			 Thread.sleep(1000);
			 System.out.println("Federal Id Country is selected :"+x);
			 break;
		  }
			  
		}          
			
		driver.findElement(By.xpath(Auction.Federal_Id_Type)).sendKeys(FederalIdType);
		Thread.sleep(1000);
		//random
		int rand_int1 = 100000000+rand.nextInt(100000000);
		FederalId=Integer.toString(rand_int1);
		 
		driver.findElement(By.xpath(Auction.FedaralId)).sendKeys(FederalId);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(Auction.FedaralIdConfirm)).sendKeys(FederalId);
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction.companyType)).sendKeys(CompanyType);
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction.CompanyTypeText)).click();			
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction.BusinessType)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.TXT_BusinessType)));     	
		Thread.sleep(1000);
			
		driver.findElement(By.xpath(Auction.Add_BusinessType)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(Auction.DealerType_DropdownArrow)).click();
        Thread.sleep(1000);
			
        List<WebElement> myElements2 = driver.findElements(By.xpath(Auction.DealerType));
		for(WebElement e : myElements2) 
		{
			String Comp=(e.getText());
			System.out.println("Delear Type is :"+Comp);
			if(DelearType.equalsIgnoreCase(Comp)) 
			{
			  e.click();
			  Thread.sleep(5000);
			  System.out.println("Delear Type is selected :"+e);
			  break;
			 }
			  
		}
			
		driver.findElement(By.xpath(Auction.NewCheckBox)).click();
		Thread.sleep(500);			
		driver.findElement(By.xpath(Auction.DelearType_Update)).click();
		Thread.sleep(1000);			
		driver.findElement(By.xpath(Auction.DelearType_Save)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.IdentificationWindow)));  
		Thread.sleep(1000);
		//random
		BondCompany=ranDom(BondCompany);
		BondCompany=BondCompany.replace("_", "");
		driver.findElement(By.xpath(Auction.BondCompany)).sendKeys(BondCompany);
		Thread.sleep(2000);
		//random
		int rand_int2 = 500000000+rand.nextInt(100000000);
		BondNumber=Integer.toString(rand_int2);
		
		driver.findElement(By.xpath(Auction.BondNumber)).sendKeys(BondNumber);
		Thread.sleep(2000);			
		report.updateTestLog("AA", "Data entered in all required fields under Identification section ", Status.PASS);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
		Thread.sleep(10000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.DealerOverride))); 
		
		boolean override1=false;
		override1=driver.findElement(By.xpath(Auction.DealerOverride)).isDisplayed();
		if(override1==true) 
		{		
	      report.updateTestLog("AA", "Override window is displayed ", Status.SCREENSHOT);
	      driver.findElement(By.xpath(Auction.DealerOverride)).click();
	      report.updateTestLog("AA", "Clicked on Override button under Override window ", Status.PASS);	            
	      System.out.println("Override is clicked");
	      //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.ContactWindow))); 
	      Thread.sleep(1000);
		}
			
    }
    
    catch (Exception e)
    {
    	report.updateTestLog("AA", "Data is not entered properly under Identification section ", Status.FAIL);       
    	report.updateTestLog("AA", "Dealer creation", Status.FAIL);
    	System.out.println(e);
    }
    
    
    }
	
	
	public void createContact() throws Exception 
	{		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.ContactWindow)));
		Thread.sleep(3000);
		try 
		{
			if(driver.findElement(By.xpath(Auction.ContactWindow)).isDisplayed()) 
			{
				report.updateTestLog("AA", "Contact window is opened ", Status.PASS);
			}
			else 
			{
				report.updateTestLog("AA", "Contact window is not opened ", Status.FAIL);
			}
		
		// Setting Lot Line 1

	    String Lot_Line1 = dataTable.getData("General_Data", "Lot_Line1");
	    Lot_Line1=ranDom(Lot_Line1);
	    Lot_Line1=Lot_Line1.replace("_", "");
		
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction.LOT_Line1)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction.LOT_Line1)).sendKeys(Lot_Line1);
	    System.out.println("Lot_Line1 is entered "+Lot_Line1);

	   // Setting Lot Line 2

	    String Lot_Line2 = dataTable.getData("General_Data", "Lot_Line2");
	    Lot_Line2=ranDom(Lot_Line2);
	    Lot_Line2=Lot_Line2.replace("_", "");
	    
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction.LOT_Line2)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction.LOT_Line2)).sendKeys(Lot_Line2);
	    Thread.sleep(2000);
	    System.out.println("LOT_Line2 is entered "+Lot_Line2);

	      
	      // LOT_City

	       String LOT_City = dataTable.getData("General_Data", "Lot_City");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction.LOT_City)).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction.LOT_City)).sendKeys(LOT_City);
	       Thread.sleep(2000);
	       System.out.println("LOT_City is entered "+LOT_City);

	       //Lot_Country
	       String Lot_Country = dataTable.getData("General_Data", "Lot_Country");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction.CountryDropdownArrow)).click();
	       Thread.sleep(5000);
	       
	       List<WebElement> myElements2 = driver.findElements(By.xpath(Auction.Country));
			for(WebElement e : myElements2) {
			  String Comp=(e.getText());
			  System.out.println("Country is :"+Comp);
			  if(Lot_Country.equalsIgnoreCase(Comp)) {
				  e.click();
				  Thread.sleep(5000);
				  System.out.println("Lot Country is selected :"+e);
				  break;
			  }
			  
			}

			 Thread.sleep(2000);
	       // Lot_State

	       String Lot_State = dataTable.getData("General_Data", "Lot_State");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction.StateProvinceDropdownArrow)).click();
	       Thread.sleep(4000);
	       
	       List<WebElement> myElements3 = driver.findElements(By.xpath(Auction.StateProvince));
			for(WebElement e : myElements3) {
			  String Comp=(e.getText());
			  System.out.println("StateProvince is :"+Comp);
			  if(Lot_State.equalsIgnoreCase(Comp)) {
				  e.click();
				  Thread.sleep(5000);
				  System.out.println("Lot State is selected :"+e);
				  break;
			  }
			  
			}
			 Thread.sleep(2000);
	       //Lot_PostalCode

	       String Lot_PostalCode = dataTable.getData("General_Data", "Lot_PostalCode");
	      
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.LOT_PostalCode)).click();
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.LOT_PostalCode)).sendKeys(Lot_PostalCode);
	       Thread.sleep(1000);
	       System.out.println("Lot_PostalCode is entered "+Lot_PostalCode);

	       //Phone_Country

	      
	       Thread.sleep(1000);
	       String Phone_Country = dataTable.getData("General_Data", "Phone_Country");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.Business_PhoneCountry)).click();
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.Business_PhoneCountry)).sendKeys(Phone_Country);
	       Thread.sleep(1000);
	       System.out.println("Phone_Country is entered "+Phone_Country);

	       //Business_Phone

	       //String Business_Phone = dataTable.getData("General_Data", "Business_Phone");
	       Random rand = new Random();
	       int rand_int1 = 1000000000+rand.nextInt(1000000000);
	       String Business_Phone=Integer.toString(rand_int1);
		   
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.Business_PhoneNumber)).click();
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.Business_PhoneNumber)).sendKeys(Business_Phone);
	       Thread.sleep(1000);
	       System.out.println("Business_Phone is entered "+Business_Phone);
	       
	       //Fax Business Country
	       String FaxBusinessCountry = dataTable.getData("General_Data", "Fax Business Country");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.Fax_BusinessCountryDropdownArrow)).click();
	       Thread.sleep(4000);
	       
	       List<WebElement> myElements4 = driver.findElements(By.xpath(Auction.Fax_BusinessCountry));
			for(WebElement e : myElements4) {
			  String Comp=(e.getText());
			  System.out.println("Fax_BusinessCountry is :"+Comp);
			  if(FaxBusinessCountry.equalsIgnoreCase(Comp)) {
				  e.click();
				  Thread.sleep(5000);
				  System.out.println("FaxBusinessCountry is selected :"+FaxBusinessCountry);
				  break;
			  }
			  
			}
	       
			//Business Fax
			   String BusinessFax = dataTable.getData("General_Data", "Business Fax");
			   int rand_int3 = rand.nextInt(1000);
			   BusinessFax = rand_int3+BusinessFax ;
			   
			   Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.BusinessFax)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.BusinessFax)).sendKeys(BusinessFax);
		       Thread.sleep(2000);
		       System.out.println("BusinessFax is entered "+BusinessFax);

		       Thread.sleep(2000);
		       
		       //Select "Mailing Same As Lot" checkbox
		       driver.findElement(By.xpath(Auction.MailingSameAsLot)).click();
		       Thread.sleep(2000);
		       
		       //Email
		       String ContactEmail = dataTable.getData("General_Data", "ContactEmail");
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.Contact_Email)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.Contact_Email)).sendKeys(ContactEmail);
		       Thread.sleep(2000);
	       
		     //URL
		       String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.WebsiteURL)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction.WebsiteURL)).sendKeys(WebsiteURL);
		       Thread.sleep(3000);
	       
		       
	       report.updateTestLog("AA", "Data is entered under Contact window ", Status.PASS);
	       
	      Thread.sleep(15000);
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
	      
	      
	      /* 
	      // Hitting Next Button
	      boolean Next=false;
	      while(Next==true) {
	      Next=driver.findElement(By.xpath(Auction.IdentificationNext)).isEnabled(); 
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
	      }*/
	      
	      // WebDriverWait wait = new WebDriverWait(driver, 5000);
	       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.DealerOverride)));
		   
		   Thread.sleep(10000);
			
			boolean override2=false;
			override2=driver.findElement(By.xpath(Auction.DealerOverride)).isDisplayed();
			if(override2==true) {
			    report.updateTestLog("Auction", "Override button is arrived ", Status.SCREENSHOT);	
				driver.findElement(By.xpath(Auction.DealerOverride)).click();
				report.updateTestLog("Auction", "Override button is Clicked ", Status.SCREENSHOT);
				System.out.println("Override is clicked");
				Thread.sleep(2000);
			}
		}
		catch(Exception e) {
			System.out.println("Error is: "+e);
		}
			
			

	    }
	
	public void createLicense() throws Exception {

		Thread.sleep(5000);
		if(driver.findElement(By.xpath(Auction.LicenseWindow)).isDisplayed()) {
			report.updateTestLog("AA", "License window is opened ", Status.PASS);
	        report.updateTestLog("Auction", "License window is opened ", Status.SCREENSHOT);
	    	
		}else {
			report.updateTestLog("AA", "License window is not opened ", Status.FAIL);
	        report.updateTestLog("Auction", "License window is not opened ", Status.SCREENSHOT);
	    	
		}  
		
		try {
		// Setting LicenseNumber
			Random rand = new Random();
			int rand_int1 = 100000000+rand.nextInt(100000000);
			String LicenseNumber=Integer.toString(rand_int1);
			 
	       //String LicenseNumber = dataTable.getData("General_Data", "LicenseNumber");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.LicenseNumber)).click();
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.LicenseNumber)).sendKeys(LicenseNumber);
	       System.out.println("LicenseNumber entered");

	       // issuedDate

	       String issuedDate = dataTable.getData("General_Data", "issuedDate");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.issuedDate)).click();
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.issuedDate)).sendKeys(issuedDate);
	       System.out.println("issuedDate entered");

	       //expiration Date
	       String expirationDate = dataTable.getData("General_Data", "expirationDate");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath(Auction.expirationDate)).click();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction.expirationDate)).sendKeys(expirationDate);
	       Thread.sleep(3000);
	       System.out.println("expirationDate entered");
	       
	       // issuingCountry
	       String issuingCountry = dataTable.getData("General_Data", "issuingCountry");
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction.issuingCountryDropdownArrow)).click();
	       Thread.sleep(3000);
	       System.out.println("issuingCountryDropdownArrow clicked");
	       
	       List<WebElement> myElements4 = driver.findElements(By.xpath(Auction.issuingCountry));
	       Thread.sleep(2000);
			for(WebElement e : myElements4) {
			  String Comp=(e.getText());
			  if(issuingCountry.equalsIgnoreCase(Comp)) {
				  Thread.sleep(3000);
				  JavascriptExecutor executor = (JavascriptExecutor)driver;
			       executor.executeScript("arguments[0].click();", e);
			        
				  //e.click();
				  Thread.sleep(5000);
				  System.out.println("issuing Country is selected :"+issuingCountry);
				  break;
			  }
			  
			}
	       
	       
			 Thread.sleep(1000);
	       //issuingStateOrProvince

	       String issuingStateOrProvince = dataTable.getData("General_Data", "issuingStateOrProvince");
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction.issuingStateOrProvinceArrowDropdown)).click();
	       Thread.sleep(3000);
	       
	       List<WebElement> myElements5 = driver.findElements(By.xpath(Auction.issuingStateOrProvince));
	       Thread.sleep(1000);
			for(WebElement e : myElements5) {
			  String Comp=(e.getText());
			  if(issuingStateOrProvince.equalsIgnoreCase(Comp)) {
				  Thread.sleep(1000);
				  e.click();
				  Thread.sleep(2000);
				  System.out.println("issuing State Or Province is selected :"+issuingStateOrProvince);
				  break;
			  }
			  
			}
			
			Thread.sleep(1000);
			report.updateTestLog("AA", "Data entered under License window ", Status.PASS);
	        report.updateTestLog("Auction", "Data entered under License window ", Status.SCREENSHOT);
    
	       Thread.sleep(10000);
	              
	       JavascriptExecutor executor = (JavascriptExecutor)driver;
	       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.DealerCreateFinish)));
	      
	       
	       WebDriverWait wait = new WebDriverWait(driver, 5000);
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.TextAfterDealerCreation)));
	       GF.waitForObject(By.xpath(Auction.TextAfterDealerCreation), "Dealer");
		   Thread.sleep(5000);
			
		   ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.TextAfterDealerCreation)));
		   Thread.sleep(3000);
		   report.updateTestLog("AA", "5m Dealer is created successfully ", Status.PASS);
	       report.updateTestLog("Auction", "Data entered under License window ", Status.SCREENSHOT);
		}catch(Exception e) {
			report.updateTestLog("AA", "5m Dealer is not created successfully ", Status.FAIL);
			System.out.println("Error is: "+e);
		}
		 Thread.sleep(2000);
		 String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		 System.out.println(acctNum);
		 
		acctNum=(acctNum.substring(7, 14));
		System.out.println(acctNum);  
		dealerId=acctNum;
		
	    }
	
	
	// The key word validate the UI record with database
	
	public void dbVal() throws Exception {
		
		 String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		 System.out.println(acctNum);
		 
		acctNum=(acctNum.substring(7, 14));
		System.out.println(acctNum);
		
		// Preparing the connection string 
		Connection conn = null;
		Statement stmt = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
		String DBA_Name = "";
		String ACCT_NUMBER = "";
		String ACCT_DBA = "";
		String COURIER_NUM = "";
		String COMP_LEGAL_TYPE ="";
		String ACCT_IDENT_COUNTRY_CODE="";
		String INS_COMP_NM="";
		String BOND_COMP_NM="";
		String INS_POLICY_NUM="";
		String COURIER_CD="";
		String BUS_START_DT="";
		String LICENSE_TYPE="";
		String LICENSE_PROVINCE_CD="";
		String LICENSE_COUNTRY_CD="";
		String LICENSE_NUM="";
		
		String ACCT_DESC="";
		String LEGAL_NM="";
		String CUST_NM="";
		String ACCT_IDENT_TYPE="";
		String ACCT_IDENT_NUM="";
		String BOND_NUM="";
		String BOND_EXPIRY_DT="";
		String INS_POLICY_EXPIRY_DT="";
		String CUST_SPECIALTIES_TYPE="";
		String NEW_IND="";
		String LOT_Addr1="";
		String ADDR_LINE_2="";
		String LOT_Addr2="";
		String LOT_CITY="";
		String LOT_COUNTRY="";
		String LOT_Province="";
		String LOT_PostalCode="";
		
		String Mail_Addr1="";
		String Mail_Addr2="";
		String Mail_CITY="";
		String Mail_COUNTRY="";
		String Mail_Province="";
		String Mail_PostalCode="";
		String sqlPassed="";
		String LAST_ROWID_SYSTEM = dataTable.getData("General_Data", "LAST ROWID SYSTEM");
		
		// Running the SQL Query
		//String dlrAcct = dataTable.getData("General_Data", "ACCOUNT NUMBER");	
		//String sqlPassed="select X.ACCT_DESC,X.ACCT_NUM,X.ACCT_DBA_NM,X.COURIER_ACCT_NUM,X.COURIER_CD,Y.COMP_LEGAL_TYPE,Z.ACCT_IDENT_COUNTRY_CODE,Y.INS_COMP_NM,Y.BOND_COMP_NM,Y.INS_POLICY_NUM,TO_CHAR((Y.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT, BCL.LICENSE_NUM, BCL.LICENSE_EFFECTIVE_DT,BCL.LICENSE_EXPIRY_DT,BCL.LICENSE_TYPE,BCL.LICENSE_PROVINCE_CD,BCL.LICENSE_COUNTRY_CD from CMX_ORS.C_BO_ACCT X,CMX_ORS.C_BO_CUST_COMP_EXT Y,CMX_ORS.C_BO_ACCT_IDENT Z,CMX_ORS.C_BO_ACCT_LICENSE BCL where X.cust_id=Y.cust_id and X.ROWID_OBJECT=Z.acct_id and BCL.acct_id=X.rowid_object  and X.ACCT_NUM ="+"'"+acctNum+"'"; 
		
		//String sqlPassed="Select a.ACCT_NUM,a.ACCT_DESC, a.ACCT_DBA_NM,a.COURIER_CD,c.LEGAL_NM,c.CUST_NM,b.ACCT_IDENT_COUNTRY_CODE, b.ACCT_IDENT_TYPE,b.ACCT_IDENT_NUM, d.COMP_LEGAL_TYPE, d.BOND_COMP_NM, d.BOND_NUM, TO_CHAR((d.BOND_EXPIRY_DT), 'MM-DD-YYYY') BOND_EXPIRY_DT , d.INS_COMP_NM, d.INS_POLICY_NUM, TO_CHAR((d.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT , TO_CHAR((d.INS_POLICY_EXPIRY_DT), 'MM-DD-YYYY') INS_POLICY_EXPIRY_DT, k.CUST_SPECIALTIES_TYPE, k.NEW_IND, l.ADDR_LINE_1 LOT_Addr1, l.ADDR_LINE_2 LOT_Addr2, l.CITY LOT_CITY, l.COUNTRY_CD LOT_COUNTRY, l.PROVINCE_CD LOT_Province, l.POSTAL_CD LOT_PostalCode, l.C_ADDR_LINE_1 Mail_Addr1, l.C_ADDR_LINE_2 Mail_Addr2, l.CLEAN_CITY Mail_City, l.C_COUNTRY_CD Mail_Country, l.CLEAN_PROVINCE Mail_Province, l.CLEAN_POSTAL_CD Mail_Postal, i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT Where a.ACCT_NUM="+"'"+acctNum+"'"+ "and rownum=1";
		if (LAST_ROWID_SYSTEM.equalsIgnoreCase("VIK")) {
			 sqlPassed="Select a.ACCT_NUM,a.ACCT_DESC, a.ACCT_DBA_NM,a.COURIER_CD,c.LEGAL_NM,c.CUST_NM,b.ACCT_IDENT_COUNTRY_CODE, b.ACCT_IDENT_TYPE,b.ACCT_IDENT_NUM, d.COMP_LEGAL_TYPE, d.BOND_COMP_NM, d.BOND_NUM, TO_CHAR((d.BOND_EXPIRY_DT), 'MM-DD-YYYY') BOND_EXPIRY_DT , d.INS_COMP_NM, d.INS_POLICY_NUM, TO_CHAR((d.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT , TO_CHAR((d.INS_POLICY_EXPIRY_DT), 'MM-DD-YYYY') INS_POLICY_EXPIRY_DT, k.CUST_SPECIALTIES_TYPE, k.NEW_IND, l.ADDR_LINE_1 LOT_Addr1, l.ADDR_LINE_2 LOT_Addr2, l.CITY LOT_CITY, l.COUNTRY_CD LOT_COUNTRY, l.PROVINCE_CD LOT_Province, l.POSTAL_CD LOT_PostalCode, l.C_ADDR_LINE_1 Mail_Addr1, l.C_ADDR_LINE_2 Mail_Addr2, l.CLEAN_CITY Mail_City, l.C_COUNTRY_CD Mail_Country, l.CLEAN_PROVINCE Mail_Province, l.CLEAN_POSTAL_CD Mail_Postal, i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT left outer join CMX_ORS.C_BO_TELECOM_NUM g on g.ROWID_OBJECT=f.TELECOM_NUM_ID left outer join CMX_ORS.C_BO_ELEC_ADDR m on m.ROWID_OBJECT=h.ELEC_ADDR_ID left outer join CMX_ORS.C_BO_CUST_POSTADDR n on n.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR l on l.POST_ADDR_ID = n.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_TELECOMNUM q on q.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_TELECOM_NUM r on r.ROWID_OBJECT=q.TELECOM_NUM_ID Where a.ACCT_NUM="+"'"+acctNum+"'"+ " and a.LAST_ROWID_SYSTEM='VIK' and rownum=1";	
		}else if (LAST_ROWID_SYSTEM.equalsIgnoreCase("AA")) {
			 sqlPassed="Select a.ACCT_NUM,a.ACCT_DESC, a.ACCT_DBA_NM,a.COURIER_CD,c.LEGAL_NM,c.CUST_NM,b.ACCT_IDENT_COUNTRY_CODE, b.ACCT_IDENT_TYPE,b.ACCT_IDENT_NUM, d.COMP_LEGAL_TYPE, d.BOND_COMP_NM, d.BOND_NUM, TO_CHAR((d.BOND_EXPIRY_DT), 'MM-DD-YYYY') BOND_EXPIRY_DT , d.INS_COMP_NM, d.INS_POLICY_NUM, TO_CHAR((d.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT , TO_CHAR((d.INS_POLICY_EXPIRY_DT), 'MM-DD-YYYY') INS_POLICY_EXPIRY_DT, k.CUST_SPECIALTIES_TYPE, k.NEW_IND, l.ADDR_LINE_1 LOT_Addr1, l.ADDR_LINE_2 LOT_Addr2, l.CITY LOT_CITY, l.COUNTRY_CD LOT_COUNTRY, l.PROVINCE_CD LOT_Province, l.POSTAL_CD LOT_PostalCode, l.C_ADDR_LINE_1 Mail_Addr1, l.C_ADDR_LINE_2 Mail_Addr2, l.CLEAN_CITY Mail_City, l.C_COUNTRY_CD Mail_Country, l.CLEAN_PROVINCE Mail_Province, l.CLEAN_POSTAL_CD Mail_Postal, i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT left outer join CMX_ORS.C_BO_TELECOM_NUM g on g.ROWID_OBJECT=f.TELECOM_NUM_ID left outer join CMX_ORS.C_BO_ELEC_ADDR m on m.ROWID_OBJECT=h.ELEC_ADDR_ID left outer join CMX_ORS.C_BO_CUST_POSTADDR n on n.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR l on l.POST_ADDR_ID = n.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_TELECOMNUM q on q.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_TELECOM_NUM r on r.ROWID_OBJECT=q.TELECOM_NUM_ID Where a.ACCT_NUM="+"'"+acctNum+"'"+ " and rownum=1";
		}else {
			sqlPassed="Select a.ACCT_NUM,a.ACCT_DESC, a.ACCT_DBA_NM,a.COURIER_CD,c.LEGAL_NM,c.CUST_NM,b.ACCT_IDENT_COUNTRY_CODE, b.ACCT_IDENT_TYPE,b.ACCT_IDENT_NUM, d.COMP_LEGAL_TYPE, d.BOND_COMP_NM, d.BOND_NUM, TO_CHAR((d.BOND_EXPIRY_DT), 'MM-DD-YYYY') BOND_EXPIRY_DT , d.INS_COMP_NM, d.INS_POLICY_NUM, TO_CHAR((d.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT , TO_CHAR((d.INS_POLICY_EXPIRY_DT), 'MM-DD-YYYY') INS_POLICY_EXPIRY_DT, k.CUST_SPECIALTIES_TYPE, k.NEW_IND, l.ADDR_LINE_1 LOT_Addr1, l.ADDR_LINE_2 LOT_Addr2, l.CITY LOT_CITY, l.COUNTRY_CD LOT_COUNTRY, l.PROVINCE_CD LOT_Province, l.POSTAL_CD LOT_PostalCode, l.C_ADDR_LINE_1 Mail_Addr1, l.C_ADDR_LINE_2 Mail_Addr2, l.CLEAN_CITY Mail_City, l.C_COUNTRY_CD Mail_Country, l.CLEAN_PROVINCE Mail_Province, l.CLEAN_POSTAL_CD Mail_Postal, i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT left outer join CMX_ORS.C_BO_TELECOM_NUM g on g.ROWID_OBJECT=f.TELECOM_NUM_ID left outer join CMX_ORS.C_BO_ELEC_ADDR m on m.ROWID_OBJECT=h.ELEC_ADDR_ID left outer join CMX_ORS.C_BO_CUST_POSTADDR n on n.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR l on l.POST_ADDR_ID = n.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_TELECOMNUM q on q.CUST_ID = a.CUST_ID left outer join CMX_ORS.C_BO_TELECOM_NUM r on r.ROWID_OBJECT=q.TELECOM_NUM_ID Where a.ACCT_NUM="+"'"+acctNum+"'"+ " and a.LAST_ROWID_SYSTEM='MSC' and rownum=1";
		}
		
		
		//String sqlPassed="select X.ACCT_DESC,X.ACCT_NUM,X.ACCT_DBA_NM,X.COURIER_ACCT_NUM,X.COURIER_CD,Y.COMP_LEGAL_TYPE,Z.ACCT_IDENT_COUNTRY_CODE,Y.INS_COMP_NM,Y.BOND_COMP_NM,Y.INS_POLICY_NUM,TO_CHAR((Y.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT from CMX_ORS.C_BO_ACCT X,CMX_ORS.C_BO_CUST_COMP_EXT Y,CMX_ORS.C_BO_ACCT_IDENT Z where X.cust_id=Y.cust_id and X.ROWID_OBJECT=Z.acct_id and X.ACCT_NUM ='acctNum'";
		 //sqlPassed=sqlPassed.replace("acctNum", dlrAcct);
		 // Highlighting the UI value 
		 ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.DBA_Name_Input_Field)));
		 Thread.sleep(3000);	
		 driver.findElement(By.xpath(Auction.DBA_Name_Input_Field)).click();
		 Thread.sleep(3000);	
		 // The HTML is area enabled so copying the value and pasting it against the clip board 
		 // and storing it in a variable 
         		 
		String DBANameGUI= driver.findElement(By.xpath(Auction.DBA_Name_Input_Field)).getAttribute("value");
		System.out.println("DBA_Name from Clipboard:" + DBANameGUI); 
	    Thread.sleep(2000);	
	    ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Legal_Name)));	
	 
	    // The HTML is area enabled so copying the value and pasting it against the clip board 
	 		 // and storing it in a varible 
         driver.findElement(By.xpath(Auction.Legal_Name)).click();
         Thread.sleep(2000);	
         String Legal_Name= driver.findElement(By.xpath(Auction.Legal_Name)).getAttribute("value");
         System.out.println("Legal name : " + Legal_Name);	
         
        // toolkit.getDefaultToolkit().getSystemClipboard().setContents(null, null);
      // The HTML is area enabled so copying the value and pasting it against the clip board 
      		 // and storing it in a varible 
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Courier_Account)));
         Thread.sleep(3000);
         driver.findElement(By.xpath(Auction.Courier_Account)).click();
         String Courier_Account= driver.findElement(By.xpath(Auction.Courier_Account)).getAttribute("value");
         System.out.println("Courier_Account : " + Courier_Account);
         
        //Federal_ID_Country
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Federal_ID_Country)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.Federal_ID_Country)).click();
         String Federal_ID_Country= driver.findElement(By.xpath(Auction.Federal_ID_Country)).getAttribute("value");
        
        //Company_Type
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Company_Type)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.Company_Type)).click();
         String Company_Type= driver.findElement(By.xpath(Auction.Company_Type)).getAttribute("value");
         System.out.println("Company_Type from Clipboard:" + Company_Type);
         
          if (Company_Type.equalsIgnoreCase("Business Corporation")) {
        	 Company_Type="BUS";
        	 System.out.println("Company_Type :" + Company_Type);
         }
         //Courier
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Courier_UI)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.Courier_UI)).click();
         String Courier_UI= driver.findElement(By.xpath(Auction.Courier_UI)).getAttribute("value");
         System.out.println("Courier UI " + Courier_UI);
         
         
         //Federal_ID
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Courier_UI)));
         Thread.sleep(2000);
         
         String Federal_ID= driver.findElement(By.xpath(Auction.Federal_ID)).getAttribute("value");
         System.out.println("Federal_ID :" + Federal_ID);
         
       //Bond Company
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.bondingCompany)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.bondingCompany)).click();
         String bondingCompany= driver.findElement(By.xpath(Auction.bondingCompany)).getAttribute("value");
         
         System.out.println("Federal_ID :" + Federal_ID);
         
         //bond Number
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.bondNumber)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.bondNumber)).click();
         String bondNumber= driver.findElement(By.xpath(Auction.bondNumber)).getAttribute("value");
         
         System.out.println("bondNumber :" + bondNumber);
         
       //insuranceCompany name
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.insuranceCompany)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.insuranceCompany)).click();
         String insuranceCompany= driver.findElement(By.xpath(Auction.insuranceCompany)).getAttribute("value");
         
         System.out.println("insuranceCompany :" + insuranceCompany);
         
       //insurancePolicyNumber name
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.insurancePolicyNumber)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.insurancePolicyNumber)).click();
         String insurancePolicyNumber= driver.findElement(By.xpath(Auction.insurancePolicyNumber)).getAttribute("value");
         System.out.println("insurancePolicyNumber :" + insurancePolicyNumber);
         
       //insurancePolicyExpirationDate
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.insurancePolicyExpirationDate)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.insurancePolicyExpirationDate)).click();
         String insurancePolicyExpirationDate= driver.findElement(By.xpath(Auction.insurancePolicyExpirationDate)).getAttribute("value");
         System.out.println("insurancePolicyExpirationDate :" + insurancePolicyExpirationDate);
         
         if (insurancePolicyExpirationDate.equalsIgnoreCase("")) {
        	 
        	 System.out.println("insurancePolicyExpirationDate :" + insurancePolicyExpirationDate);
         } else {
         
         insurancePolicyExpirationDate=insurancePolicyExpirationDate.replace("/", "-");
		 System.out.println("insurancePolicyExpirationDate:" + insurancePolicyExpirationDate);
         }
         
         
         if (Courier_UI.equalsIgnoreCase("Federal Express")) {
        	 Courier_UI="FEDEX";
        	 System.out.println("Courier_UI from Clipboard:" + Courier_UI);
         }
         
         if (Courier_UI.equalsIgnoreCase("No Courier")) {
        	 Courier_UI="NA";
        	 System.out.println("Courier_UI from Clipboard:" + Courier_UI);
         }
         
         
       //bondExpirationDate
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.bondExpirationDate)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.bondExpirationDate)).click();
         String bondExpirationDate= driver.findElement(By.xpath(Auction.bondExpirationDate)).getAttribute("value");
         System.out.println("bondExpirationDate:" + bondExpirationDate);
         
         if (bondExpirationDate.equalsIgnoreCase("")) {	 
        	 System.out.println("bondExpirationDate :" + bondExpirationDate);
         }
         else {
         bondExpirationDate=bondExpirationDate.replace("/", "-");
		 System.out.println("bondExpirationDate:" + bondExpirationDate);
         }
         
         
         //Business Start Date
         ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.Business_Start_UI)));
         Thread.sleep(2000);
         driver.findElement(By.xpath(Auction.Business_Start_UI)).click();
         String Business_Start_UI= driver.findElement(By.xpath(Auction.Business_Start_UI)).getAttribute("value");
         System.out.println("Courier UI from Clipboard:" + Business_Start_UI);
         
         if (Business_Start_UI.equalsIgnoreCase("")) {
     	 System.out.println("Courier_UI from Clipboard:" + Courier_UI);
         }else {
        	 Business_Start_UI=Business_Start_UI.replace("/", "-");
    		 System.out.println("BUS_Start_Date:" + Business_Start_UI);
         }
         
         /*Thread.sleep(3000);
         driver.findElement(By.xpath(Auction.Business_Start_UI)).sendKeys(Keys.CONTROL+"a");
         driver.findElement(By.xpath(Auction.Business_Start_UI)).sendKeys(Keys.CONTROL+"c");
         driver.findElement(By.xpath(Auction.Business_Start_UI)).sendKeys(Keys.CONTROL+"v");
                
         String Business_Start_UI = (String) clipboard.getData(DataFlavor.stringFlavor);
         System.out.println("Business Start UI from Clipboard:" + Business_Start_UI);*/
      
		 // Going to the License Tab and clicking the Link
		 driver.findElement(By.xpath(Auction.Licences_Link)).click();
		 Thread.sleep(5000);
		 // License type 
		 String License_Type =driver.findElement(By.xpath(Auction.License_Type_TXT)).getText();
		 
		 if(License_Type.equalsIgnoreCase("Dealer Master Tag")){
			 License_Type="LEGALDLR_LIC_MSTR_TAG";
		 }else if(License_Type.equalsIgnoreCase("Dealer License")) {
			 License_Type="LEGALDLR_LIC";
		 }
		 String License_Number =driver.findElement(By.xpath(Auction.License_Number_TXT)).getText();
		 Thread.sleep(1000);
		 String License_State =driver.findElement(By.xpath(Auction.License_State_TXT)).getText();
		 Thread.sleep(1000);
		 if(License_State.equalsIgnoreCase("New York")){
			 License_State="USA-NY";
		 }
		 if (License_State.equalsIgnoreCase("Georgia")){
			 License_State="USA-GA";
		 }
		 String License_Country =driver.findElement(By.xpath(Auction.License_Country_TXT)).getText();
         
		 Thread.sleep(1000);
		 
		//fetch Business type data from UI
		 driver.findElement(By.xpath(Auction.BusinessTypes)).click();
		 Thread.sleep(5000);
		 String DealerType_underBusinessType =driver.findElement(By.xpath(Auction.DealerType_underBusinessType)).getText();
		 System.out.println("DealerType_underBusinessType:" + DealerType_underBusinessType);
		 
		 if(DealerType_underBusinessType.equalsIgnoreCase("Automobile")) {
			DealerType_underBusinessType="AUT";
		 }
		 driver.findElement(By.xpath(Auction.DealerType_EditIcon)).click();
		 Thread.sleep(4000);
		 
		 String Types=driver.findElement(By.xpath(Auction.Types_Under_EditBusinessTypeWindow)).getText();
		 if(Types.equalsIgnoreCase("New")) {
			 Types="Y";
		 }else {
			 Types="N";
		 }
		 
		 driver.findElement(By.xpath(Auction.CancelButton_Under_EditType_Window)).click();
		 Thread.sleep(2000);
		 //fetch Address fields data from UI 
		 
		 driver.findElement(By.xpath(Auction.Addresses_Link)).click();
		 Thread.sleep(5000);
		 String Addressline1 =driver.findElement(By.xpath(Auction.Addressline1_TXT)).getText();
		 System.out.println("Addressline1:" + Addressline1);
		 Thread.sleep(500);
		 String Addressline2 =driver.findElement(By.xpath(Auction.Addressline2_TXT)).getText();      
		 System.out.println("Addressline2:" + Addressline2);
		 Thread.sleep(500);
		 String AddressesCity =driver.findElement(By.xpath(Auction.AddressesCity_TXT)).getText();     
		 System.out.println("AddressesCity:" + AddressesCity);
		 Thread.sleep(500);
		 String StateProvince =driver.findElement(By.xpath(Auction.StateProvince_TXT)).getText();     
		 System.out.println("StateProvince:" + StateProvince);
		 if(StateProvince.equalsIgnoreCase("New York")) {
			StateProvince="USA-NY";
		 }else if(StateProvince.equalsIgnoreCase("Georgia"))
		 {
		    StateProvince="USA-GA";
		 }
		 Thread.sleep(500);
		 String AddressPostalCode =driver.findElement(By.xpath(Auction.PostalCode_TXT)).getText();  
		 System.out.println("AddressPostalCode:" + AddressPostalCode);
		 Thread.sleep(500);
		 String AddressCountry =driver.findElement(By.xpath(Auction.Country_TXT)).getText();
		 System.out.println("AddressCountry:" + AddressCountry);
		 
		 //Mailing Address field's data fetched from UI
		 String MailAddressline1_TXT =driver.findElement(By.xpath(Auction.MailAddressline1_TXT)).getText();
		 System.out.println("MailAddressline1_TXT :" + MailAddressline1_TXT);
		 Thread.sleep(500);
		 String MailAddressline2_TXT =driver.findElement(By.xpath(Auction.MailAddressline2_TXT)).getText();      
		 System.out.println("MailAddressline2_TXT :" + MailAddressline2_TXT);
		 Thread.sleep(500);
		 String MailAddressesCity_TXT =driver.findElement(By.xpath(Auction.MailAddressesCity_TXT)).getText();     
		 System.out.println("MailAddressesCity_TXT :" + MailAddressesCity_TXT);
		 Thread.sleep(500);
		 String MailStateProvince_TXT =driver.findElement(By.xpath(Auction.MailStateProvince_TXT)).getText();     
		 System.out.println("MailStateProvince_TXT :" + MailStateProvince_TXT);
		 if(MailStateProvince_TXT.equalsIgnoreCase("New York")){
			 MailStateProvince_TXT="USA-NY";
		 }
		 Thread.sleep(500);
		 String MailPostalCode_TXT =driver.findElement(By.xpath(Auction.MailPostalCode_TXT)).getText();  
		 System.out.println("MailPostalCode_TXT :" + MailPostalCode_TXT);
		 Thread.sleep(500);
		 String MailCountry_TXT =driver.findElement(By.xpath(Auction.MailCountry_TXT)).getText();
		 System.out.println("MailCountry_TXT :" + MailCountry_TXT);
		 
		 // Creating a list to export data in CSV
         String filename ="C:\\Users\\237574\\Desktop\\data.csv";
			try{
				Class.forName(driverName);
				conn=DriverManager.getConnection(connectionUrl,userName,password);
				System.out.println("inside the metod" +conn);
				if(conn!=null){
				stmt = conn.createStatement();
					ResultSet dataSet=stmt.executeQuery(sqlPassed);
					 Thread.sleep(10000);
					//ResultSetMetaData datatSetMetaData = dataSet.getMetaData();
					while (dataSet.next()) {
						
						//1--done
						ACCT_DESC=dataSet.getString("ACCT_DESC");
						System.out.println("ACCT_DESC is: "+ACCT_DESC);
						
						//2--done
						ACCT_NUMBER=dataSet.getString("ACCT_NUM");
						System.out.println("ACCT_NUMBER is: "+ACCT_NUMBER);
						
						//3 DBA Name-- done
						ACCT_DBA= dataSet.getString("ACCT_DBA_NM");
						System.out.println("ACCT_DBA is: "+ACCT_DBA);
						
						//4 done
						LEGAL_NM=dataSet.getString("LEGAL_NM");
						System.out.println("LEGAL_NM is: "+LEGAL_NM);
						
						//5--not yet done have doubt
						CUST_NM=dataSet.getString("CUST_NM");
						System.out.println("CUST_NM is: "+CUST_NM);
						
						//6 Company Type done
						COMP_LEGAL_TYPE=dataSet.getString("COMP_LEGAL_TYPE");
						System.out.println("COMP_LEGAL_TYPE is: "+COMP_LEGAL_TYPE);
						
						//7 have doubt
						ACCT_IDENT_TYPE=dataSet.getString("ACCT_IDENT_TYPE");
						System.out.println("ACCT_IDENT_TYPE is: "+ACCT_IDENT_TYPE);
						
						//8 not done
						ACCT_IDENT_NUM=dataSet.getString("ACCT_IDENT_NUM");
						System.out.println("ACCT_IDENT_NUM is: "+ACCT_IDENT_NUM);
						
						//9 Bond Company done
						BOND_COMP_NM=dataSet.getString("BOND_COMP_NM");
						System.out.println("BOND_COMP_NM is: "+BOND_COMP_NM);
						
						//10 Bond Number done
						BOND_NUM=dataSet.getString("BOND_NUM");
						System.out.println("BOND_NUM is: "+BOND_NUM);
						
						//11 BOND_EXPIRY_DT done
						BOND_EXPIRY_DT=dataSet.getString("BOND_EXPIRY_DT");
						System.out.println("BOND_EXPIRY_DT is: "+BOND_EXPIRY_DT);
						
						//12 done
						INS_COMP_NM=dataSet.getString("INS_COMP_NM");
						System.out.println("INS_COMP_NM is: "+INS_COMP_NM);
						
						//13 done
						INS_POLICY_NUM=dataSet.getString("INS_POLICY_NUM");
						System.out.println("INS_POLICY_NUM is: "+INS_POLICY_NUM);
						
						//14 done
						BUS_START_DT=dataSet.getString("BUS_START_DT");
						System.out.println("BUS_START_DT is: "+BUS_START_DT);
						
						// 15 done
						INS_POLICY_EXPIRY_DT=dataSet.getString("INS_POLICY_EXPIRY_DT");
						System.out.println("INS_POLICY_EXPIRY_DT is: "+INS_POLICY_EXPIRY_DT);
						
						CUST_SPECIALTIES_TYPE=dataSet.getString("CUST_SPECIALTIES_TYPE");
						System.out.println("CUST_SPECIALTIES_TYPE is: "+CUST_SPECIALTIES_TYPE);
						
						NEW_IND=dataSet.getString("NEW_IND");
						System.out.println("NEW_IND is: "+NEW_IND);
						
						LOT_Addr1=dataSet.getString("LOT_Addr1");
						System.out.println("LOT_Addr1 is: "+LOT_Addr1);
						
						LOT_Addr2=dataSet.getString("LOT_Addr2");
						System.out.println("LOT_Addr2 is: "+LOT_Addr2);
						
						LOT_CITY=dataSet.getString("LOT_CITY");
						System.out.println("LOT_CITY is: "+LOT_CITY);
						
						LOT_COUNTRY=dataSet.getString("LOT_COUNTRY");
						System.out.println("LOT_COUNTRY is: "+LOT_COUNTRY);
						
						LOT_Province=dataSet.getString("LOT_Province");
						System.out.println("LOT_Province is: "+LOT_Province);
						
						LOT_PostalCode=dataSet.getString("LOT_PostalCode");
						System.out.println("LOT_PostalCode is: "+LOT_PostalCode);
						
						
						
						LICENSE_TYPE=dataSet.getString("LICENSE_TYPE");
						System.out.println("LICENSE_TYPE is: "+LICENSE_TYPE);
						
						LICENSE_PROVINCE_CD=dataSet.getString("LICENSE_PROVINCE_CD");
						System.out.println("LICENSE_PROVINCE_CD is: "+LICENSE_PROVINCE_CD);
						
						LICENSE_COUNTRY_CD=dataSet.getString("LICENSE_COUNTRY_CD");
						System.out.println("LICENSE_COUNTRY_CD is: "+LICENSE_COUNTRY_CD);
						
						LICENSE_NUM=dataSet.getString("LICENSE_NUM");
						System.out.println("LICENSE_NUM is: "+LICENSE_NUM);
						
						//Federal ID Country
						ACCT_IDENT_COUNTRY_CODE=dataSet.getString("ACCT_IDENT_COUNTRY_CODE");
						System.out.println("ACCT_IDENT_COUNTRY_CODE is: "+ACCT_IDENT_COUNTRY_CODE);
						
						//Courier
						COURIER_CD=dataSet.getString("COURIER_CD");
						System.out.println("COURIER_CD is: "+COURIER_CD);
						
						//Mail_Addr1
						Mail_Addr1=dataSet.getString("Mail_Addr1");
						System.out.println("Mail_Addr1 is: "+Mail_Addr1);
						
						//Mail_Addr2
						Mail_Addr2=dataSet.getString("Mail_Addr2");
						System.out.println("Mail_Addr2 is: "+Mail_Addr2);
						
						//Mail_CITY
						Mail_CITY=dataSet.getString("Mail_City");
						System.out.println("Mail_CITY is: "+Mail_CITY);
						
						//Mail_COUNTRY
						Mail_COUNTRY=dataSet.getString("Mail_Country");
						System.out.println("Mail_COUNTRY is: "+Mail_COUNTRY);
						
						//Mail_Province
						Mail_Province=dataSet.getString("Mail_Province");
						System.out.println("Mail_Province is: "+Mail_Province);
						
						//Mail_PostalCode
						Mail_PostalCode=dataSet.getString("Mail_Postal");
						System.out.println("Mail_PostalCode is: "+Mail_PostalCode);
						
					
						/*DBA_Name=dataSet.getString("ACCT_DESC");
						ACCT_NUMBER=dataSet.getString("ACCT_NUM");
						ACCT_DBA= dataSet.getString("ACCT_DBA_NM");
						COURIER_NUM = dataSet.getString("COURIER_ACCT_NUM");
						COMP_LEGAL_TYPE = dataSet.getString("COMP_LEGAL_TYPE");
						System.out.println("Full Name is: "+DBA_Name  +ACCT_NUMBER);
						System.out.println("COMP_LEGAL_TYPE is: "+COMP_LEGAL_TYPE);
						
						//Federal ID Country
						ACCT_IDENT_COUNTRY_CODE=dataSet.getString("ACCT_IDENT_COUNTRY_CODE");
						System.out.println("ACCT_IDENT_COUNTRY_CODE is: "+ACCT_IDENT_COUNTRY_CODE);
						
						INS_COMP_NM=dataSet.getString("INS_COMP_NM");
						System.out.println("INS_COMP_NM is: "+INS_COMP_NM);
						
						BOND_COMP_NM=dataSet.getString("BOND_COMP_NM");
						System.out.println("BOND_COMP_NM is: "+BOND_COMP_NM);
						
						INS_POLICY_NUM=dataSet.getString("INS_POLICY_NUM");
						System.out.println("INS_POLICY_NUM is: "+INS_POLICY_NUM);
						
						COURIER_CD=dataSet.getString("COURIER_CD");
						System.out.println("COURIER_CD is: "+COURIER_CD);
						
						BUS_START_DT=dataSet.getString("BUS_START_DT");
						System.out.println("BUS_START_DT is: "+BUS_START_DT);
						
						LICENSE_TYPE=dataSet.getString("LICENSE_TYPE");
						System.out.println("LICENSE_TYPE is: "+LICENSE_TYPE);
						
						LICENSE_PROVINCE_CD=dataSet.getString("LICENSE_PROVINCE_CD");
						System.out.println("LICENSE_PROVINCE_CD is: "+LICENSE_PROVINCE_CD);
						
						LICENSE_COUNTRY_CD=dataSet.getString("LICENSE_COUNTRY_CD");
						System.out.println("LICENSE_COUNTRY_CD is: "+LICENSE_COUNTRY_CD);
						
						LICENSE_NUM=dataSet.getString("LICENSE_NUM");
						System.out.println("LICENSE_NUM is: "+LICENSE_NUM);*/
						
						
						FileWriter fw = new FileWriter(filename);
						fw.append(dataSet.getString("ACCT_DESC"));
		                fw.append(',');
		                fw.append(dataSet.getString("ACCT_NUM"));
		                fw.append(',');
		                fw.append(dataSet.getString("ACCT_DBA_NM"));
		                fw.append(',');
		                fw.append(dataSet.getString("COURIER_ACCT_NUM"));
		                fw.append(',');
		                fw.append(dataSet.getString("COMP_LEGAL_TYPE"));
		                fw.append(',');
		                fw.append(dataSet.getString("LICENSE_COUNTRY_CD"));
		                fw.append(',');
		                fw.append(dataSet.getString("LICENSE_NUM"));
		                fw.append(',');
		                fw.append(dataSet.getString("LICENSE_PROVINCE_CD"));
		                fw.append('\n');
		                fw.flush();
			            fw.close();
		               
				
//						int ncols = dataSet.getMetaData().getColumnCount();  
//
//			            System.out.println("ColumnCout"+ncols);  
//			            FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\237574\\Desktop\\data.csv"),false);  
//			            fos.flush();
//			            Writer out = new OutputStreamWriter(new BufferedOutputStream(fos),"UTF_8");      
//
//			            for (int j=1; j<(ncols+1); j++) {     
//			            out.append(dataSet.getMetaData().getColumnName (j));       
//			            if (j<ncols) out.append(","); else out.append("\r\n");      
//			            }   
//			            int m =1;    
//
//			            while (dataSet.next()) {   
//
//			            for (int k=1; k<(ncols+1); k++) {   
//
//			            out.append(dataSet.getString(k));    
//
//			            if (k<ncols) out.append(","); else out.append("\r\n");    
//			            }   
//			            //System.out.println("No of rows"+m);   
//			            m++;   
//			            }
//			            fos.close();
//						FileWriter fstream = new FileWriter("C:\\Users\\237574\\Desktop\\data.csv");
//						 BufferedWriter out = new BufferedWriter(fstream);
//						 out.flush();
//						 out.write(DBA_Name);
//						 out.write(ACCT_NUMBER);
//						 out.write(ACCT_DBA);
//						 out.write(COURIER_NUM);
//						 out.close();
					
						
					}
				}
					
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		
			//Account Description
			 if (DBANameGUI.equalsIgnoreCase(ACCT_DESC) || (DBANameGUI.equalsIgnoreCase(" ") && ACCT_DESC==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer ACCT DESC matched with OVC database value :"+"DB Value: "+ACCT_DESC+"="+" UI Value: "+DBANameGUI, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Dealer ACCT DESC not matched with OVC database"+"DB Value: "+ACCT_DESC+"<>"+" UI Value: "+DBANameGUI, Status.FAIL);
					
				}
			 
			 //CUST_NM in CUST table
			 if (DBANameGUI.equalsIgnoreCase(CUST_NM) || (DBANameGUI.equalsIgnoreCase(" ") && CUST_NM==null))
				{
					
				 //report.updateTestLog("AA", "Customer name value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Customer name value matched with OVC database value in CUST table :"+"DB Value: "+CUST_NM+"="+" UI Value: "+DBANameGUI, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Customer name value not matched with OVC database in CUST table "+"DB Value: "+CUST_NM+"<>"+" UI Value: "+DBANameGUI, Status.FAIL);
					
				}
			 
			 if (Legal_Name.equalsIgnoreCase(LEGAL_NM) || (DBANameGUI.equalsIgnoreCase(" ") && LEGAL_NM==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer LEGAL NAME matched with OVC database value in CUST table:"+"DB Value: "+LEGAL_NM+"="+" UI Value: "+Legal_Name, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Dealer LEGAL NAME is not matched with OVC database"+"DB Value: "+LEGAL_NM+"<>"+" UI Value: "+Legal_Name, Status.FAIL);
					
				}
			 
			 
			//Account Number checking
			 
			 if (acctNum.equalsIgnoreCase(ACCT_NUMBER) || (acctNum.equalsIgnoreCase(" ") && ACCT_NUMBER==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer ACCT NUMBER matched with OVC database value :"+"DB Value: "+ACCT_NUMBER+"="+" UI Value: "+acctNum, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Dealer DBA Name not matched with OVC database"+"DB Value: "+ACCT_NUMBER+"<>"+" UI Value: "+acctNum, Status.FAIL);
					
				}
			 
			
			// Checking UI DBA Name and Database DBA value
		 if (DBANameGUI.equalsIgnoreCase(ACCT_DBA) || (DBANameGUI.equalsIgnoreCase(" ") && ACCT_DBA==null))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer DBA Name matched with OVC database value :"+"DB Value: "+ACCT_DBA+"="+" UI Value: "+DBANameGUI, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA", "Dealer DBA Name not matched with OVC database"+"DB Value: "+ACCT_DBA+"<>"+"UI Value: "+DBANameGUI, Status.FAIL);
				
			}
		 
		  
		 // Checking UI legal name and Database legal name 
		 if (Legal_Name.equalsIgnoreCase(LEGAL_NM) || (Legal_Name.equalsIgnoreCase(" ") && LEGAL_NM==null))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer legal name matched with OVC database"+"DB Value:" +Legal_Name + "="+"UI Value:"+LEGAL_NM, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA", "Dealer account number not matched with OVC database"+"DB Value:" +Legal_Name + "<>"+"UI Value:"+LEGAL_NM, Status.FAIL);
				
			}
		 
		 		// Checking UI Company Type and database COMPANY LEGAL TYPE 
				 if (Company_Type.equalsIgnoreCase(COMP_LEGAL_TYPE) || (Company_Type.equalsIgnoreCase(" ") && COMP_LEGAL_TYPE==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer Company Type matched with OVC database"+"DB Value:" +COMP_LEGAL_TYPE + "="+"UI Value:"+Company_Type, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer Company Type not matched with OVC database"+"DB Value:" +COMP_LEGAL_TYPE + "<>"+"UI Value:"+Company_Type, Status.FAIL);
						
					}
		
		 /*// Checking UI courier number and database courier number 
		 if (Courier_Account.equalsIgnoreCase(COURIER_NUM))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer Courier name matched with OVC database"+"DB Value:" +COURIER_NUM + "="+"UI Value:"+Courier_Account, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA","Dealer Courier name not matched with OVC database"+"DB Value:" +COURIER_NUM + "<>"+"UI Value:"+Courier_Account, Status.FAIL);
				
			}*/

		 // Checking UI Federal_ID_Country and database Federal_ID_Country 
		 if (Federal_ID_Country.equalsIgnoreCase(ACCT_IDENT_COUNTRY_CODE) || (Federal_ID_Country.equalsIgnoreCase(" ") && ACCT_IDENT_COUNTRY_CODE==null))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer Federal ID Country matched with OVC database"+"DB Value:" +ACCT_IDENT_COUNTRY_CODE + "="+"UI Value:"+Federal_ID_Country, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA","Dealer Federal ID Country not matched with OVC database"+"DB Value:" +ACCT_IDENT_COUNTRY_CODE + "<>"+"UI Value:"+Federal_ID_Country, Status.FAIL);
				
			}
		 
		 //bondingCompany
		 if (bondingCompany.equalsIgnoreCase(BOND_COMP_NM) || (bondingCompany.equalsIgnoreCase(" ") && BOND_COMP_NM==null))
			{
				
			 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database"+"DB Value:" +BOND_COMP_NM + "="+"UI Value :"+bondingCompany, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA","Dealer Federal ID Country not matched with OVC database"+"DB Value:" +BOND_COMP_NM + "<>"+"UI Value :"+bondingCompany, Status.FAIL);
				
			}
		 
		//bondNumber
		 if (bondNumber.equalsIgnoreCase(BOND_NUM) || (bondNumber.equalsIgnoreCase(" ") && BOND_NUM==null))
			{
				
			 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", " Bond number matched with OVC database"+"DB Value:" +BOND_NUM + "="+"UI Value :"+bondNumber, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA"," BOND Number not matched with OVC database"+"DB Value:" +BOND_NUM + "<>"+"UI Value :"+bondNumber, Status.FAIL);
				
			}
		 
		 
		 //BOND_EXPIRY_DT
		 if (bondExpirationDate.equalsIgnoreCase(BOND_EXPIRY_DT) || (bondExpirationDate.equalsIgnoreCase(" ") && BOND_EXPIRY_DT==null))
			{
				
			 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", " bond Expiration Date matched with OVC database"+"DB Value :" +BOND_EXPIRY_DT + "="+"UI Value :"+bondExpirationDate, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA"," bond Expiration Date not matched with OVC database"+"DB Value :" +BOND_EXPIRY_DT + "<>"+"UI Value :"+bondExpirationDate, Status.FAIL);
				
			}
		 
		//INS_COMP_NM
		 if (insuranceCompany.equalsIgnoreCase(INS_COMP_NM) || (insuranceCompany.equalsIgnoreCase(" ") && INS_COMP_NM==null))
			{
				
			 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", " insurance Company name matched with OVC database"+"DB Value :" +INS_COMP_NM + "="+"UI Value :"+insuranceCompany, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_COMP_NM + "<>"+"UI Value :"+insuranceCompany, Status.FAIL);
				
			}
		 
		//INS_COMP_NM
		 if (insurancePolicyNumber.equalsIgnoreCase(INS_POLICY_NUM) || (insurancePolicyNumber.equalsIgnoreCase(" ") && INS_POLICY_NUM==null))
			{
				
			 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", " INSURANCE POLICY NUMBER matched with OVC database"+"DB Value :" +INS_POLICY_NUM + "="+"UI Value :"+insurancePolicyNumber, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_COMP_NM + "<>"+"UI Value :"+insurancePolicyNumber, Status.FAIL);
				
			}
		 
		//INS_POLICY_EXPIRY_DT
		 if (insurancePolicyExpirationDate.equalsIgnoreCase(INS_POLICY_EXPIRY_DT) || (insurancePolicyExpirationDate.equalsIgnoreCase(" ") && INS_POLICY_EXPIRY_DT==null))
			{
				
			 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", " insurance Policy Expiration Date matched with OVC database"+"DB Value :" +INS_POLICY_EXPIRY_DT + "="+"UI Value :"+insurancePolicyExpirationDate, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_POLICY_EXPIRY_DT + "<>"+"UI Value :"+insurancePolicyExpirationDate, Status.FAIL);
				
			}
		 
		 
		 // Checking UI Courier and database Courier_cd 
		 if (Courier_UI.equalsIgnoreCase(COURIER_CD) || (Courier_UI.equalsIgnoreCase(" ") && COURIER_CD==null))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer Courier matched with OVC database"+"DB Value:" +COURIER_CD + "="+"UI Value:"+Courier_UI, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA","Dealer Courier not matched with OVC database"+"DB Value:" +COURIER_CD + "<>"+"UI Value:"+Courier_UI, Status.FAIL);
				
			}
		 
		 
		 // Checking UI LICENSE_TYPE  
		 if (License_Type.equalsIgnoreCase(LICENSE_TYPE) || (License_Type.equalsIgnoreCase(" ") && LICENSE_TYPE==null))
			{
				
			 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_TYPE + "="+"UI Value:"+License_Type, Status.PASS);
			}
			
			else 
			{
				report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_TYPE + "<>"+"UI Value:"+License_Type, Status.FAIL);
				
			}

		 		// Checking UI License_Number 
				 if (License_Number.equalsIgnoreCase(LICENSE_NUM) || (License_Number.equalsIgnoreCase(" ") && LICENSE_NUM==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_TYPE + "="+"UI Value:"+License_Type, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_TYPE + "<>"+"UI Value:"+License_Type, Status.FAIL);
						
					}
				// Checking UI License_State 
				 if (License_State.equalsIgnoreCase(LICENSE_PROVINCE_CD) || (License_State.equalsIgnoreCase(" ") && LICENSE_PROVINCE_CD==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer License State matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD + "="+"UI Value:"+License_State, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer License State not matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD + "<>"+"UI Value:"+License_State, Status.FAIL);
						
					}
				 
				// Checking UI License_Country  and database BUS_START_DT 
				 if (License_Country.equalsIgnoreCase(LICENSE_COUNTRY_CD) || (License_Country.equalsIgnoreCase(" ") && LICENSE_COUNTRY_CD==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer License Country  matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD + "="+"UI Value:"+License_Country, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer License Country not matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD + "<>"+"UI Value:"+License_Country, Status.FAIL);
						
					}
		 
				// Checking UI License_Country  and database BUS_START_DT 
				 if (DealerType_underBusinessType.equalsIgnoreCase(CUST_SPECIALTIES_TYPE) || (DealerType_underBusinessType.equalsIgnoreCase(" ") && CUST_SPECIALTIES_TYPE==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer Type under BusinessType  matched with OVC database"+"DB Value:" +CUST_SPECIALTIES_TYPE + "="+"UI Value:"+DealerType_underBusinessType, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer Type under Business Type not matched with OVC database"+"DB Value:" +CUST_SPECIALTIES_TYPE + "<>"+"UI Value:"+DealerType_underBusinessType, Status.FAIL);
						
					}
				 
				// Checking UI License_Country  and database BUS_START_DT 
				 if (Types.equalsIgnoreCase(NEW_IND) || (Types.equalsIgnoreCase(" ") && NEW_IND==null))
					{
						
					 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
					 report.updateTestLog("AA", "Dealer Type under BusinessType  matched with OVC database"+"DB Value:" +NEW_IND + "="+"UI Value:"+Types, Status.PASS);
					}
					
					else 
					{
						report.updateTestLog("AA","Dealer Type under Business Type not matched with OVC database"+"DB Value:" +NEW_IND + "<>"+"UI Value:"+Types, Status.FAIL);
						
					}
				 
				 
				 //Address field-----------------ADDRESS ATTRIBUTE
				
				 // Checking UI Addressline1 with database  
                 if (Addressline1.equalsIgnoreCase(LOT_Addr1) || (Addressline1.equalsIgnoreCase(" ") && LOT_Addr1==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer Addressline1 matched with OVC database"+"DB Value:" +LOT_Addr1 + "="+"UI Value:"+Addressline1, Status.PASS);
                       report.updateTestLog("AA", "Dealer Addressline1 matched with OVC database in C_BO_CUST_POSTADDR table"+"DB Value:" +LOT_Addr1 + "="+"UI Value:"+Addressline1, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer Addressline1 not matched with OVC database"+"DB Value:" +LOT_Addr1 + "<>"+"UI Value:"+Addressline1, Status.FAIL);
                              
                       } 
                 
                // Checking UI Addressline2 with database  
                 if ((Addressline2.equalsIgnoreCase(LOT_Addr2) || (Addressline2.equalsIgnoreCase(" ") && LOT_Addr2==null)))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer Addressline2 matched with OVC database"+"DB Value:" +LOT_Addr2 + "="+"UI Value:"+Addressline2, Status.PASS);
                       report.updateTestLog("AA", "Dealer Addressline2 matched with OVC database C_BO_CUST_POSTADDR table "+"DB Value:" +LOT_Addr2 + "="+"UI Value:"+Addressline2, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer Addressline2 not matched with OVC database"+"DB Value:" +LOT_Addr2 + "<>"+"UI Value:"+Addressline2, Status.FAIL);
                              
                       } 

                 
                // Checking UI AddressesCity with database  
                 if (AddressesCity.equalsIgnoreCase(LOT_CITY) || (AddressesCity.equalsIgnoreCase(" ") && LOT_CITY==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT CITY matched with OVC database"+"DB Value:" +LOT_CITY + "="+"UI Value:"+AddressesCity, Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT CITY matched with OVC database in C_BO_CUST_POSTADDR table"+"DB Value:" +LOT_CITY + "="+"UI Value:"+AddressesCity, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer LOT CITY not matched with OVC database"+"DB Value:" +LOT_CITY + "<>"+"UI Value:"+AddressesCity, Status.FAIL);
                              
                       } 
                 //
                
                // Checking UI StateProvince with database  
                 if (StateProvince.equalsIgnoreCase(LOT_Province) || (StateProvince.equalsIgnoreCase(" ") && LOT_Province==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT Province matched with OVC database"+"DB Value:" +LOT_Province + "="+"UI Value:"+StateProvince, Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT Province matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +LOT_Province + "="+"UI Value:"+StateProvince, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer LOT Province not matched with OVC database"+"DB Value:" +LOT_Province + "<>"+"UI Value:"+StateProvince, Status.FAIL);
                              
                       }  
                 
                 
                // Checking UI PostalCode with database
                
                 if (AddressPostalCode.equalsIgnoreCase(LOT_PostalCode) || (AddressPostalCode.equalsIgnoreCase(" ") && LOT_PostalCode==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT PostalCode matched with OVC database"+"DB Value:" +LOT_PostalCode + "="+"UI Value:"+AddressPostalCode, Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT PostalCode matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +LOT_PostalCode + "="+"UI Value:"+AddressPostalCode, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer LOT PostalCode not matched with OVC database"+"DB Value:" +LOT_PostalCode + "<>"+"UI Value:"+AddressPostalCode, Status.FAIL);
                              
                       } 
                 
                 //Checking UI Country with database
                
                 if (AddressCountry.equalsIgnoreCase(LOT_COUNTRY) || (AddressCountry.equalsIgnoreCase(" ") && LOT_COUNTRY==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT COUNTRY matched with OVC database"+"DB Value:" +LOT_COUNTRY + "="+"UI Value:"+AddressCountry, Status.PASS);
                       report.updateTestLog("AA", "Dealer LOT COUNTRY matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +LOT_COUNTRY + "="+"UI Value:"+AddressCountry, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer LOT COUNTRY not matched with OVC database"+"DB Value:" +LOT_COUNTRY + "<>"+"UI Value:"+AddressCountry, Status.FAIL);
                              
                       } 
                 
                 
                 //Mailing Address field validation
                 				
                 // Checking UI Mail_Addr1 with database  
                 if (MailAddressline1_TXT.equalsIgnoreCase(Mail_Addr1) || (MailAddressline1_TXT.equalsIgnoreCase(" ") && Mail_Addr1==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer Mailing Addressline1 matched with OVC database"+"DB Value:" +Mail_Addr1 + "="+"UI Value :"+MailAddressline1_TXT, Status.PASS);
                       report.updateTestLog("AA", "Dealer Mailing Addressline1 matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +Mail_Addr1 + "="+"UI Value :"+MailAddressline1_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer Mailing Addressline1 not matched with OVC database"+"DB Value:" +Mail_Addr1 + "<>"+"UI Value :"+MailAddressline1_TXT, Status.FAIL);
                              
                       } 
                 
                // Checking UI Addressline2 with database  
                 if (MailAddressline2_TXT.equalsIgnoreCase(Mail_Addr2) || (MailAddressline2_TXT.equalsIgnoreCase(" ") && Mail_Addr2==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer Mailing Addressline2 matched with OVC database"+"DB Value:" +Mail_Addr2 + "="+"UI Value :"+MailAddressline2_TXT, Status.PASS);
                       report.updateTestLog("AA", "Dealer Mailing Addressline2 matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +Mail_Addr2 + "="+"UI Value :"+MailAddressline2_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer Addressline2 not matched with OVC database"+"DB Value:" +Mail_Addr2 + "<>"+"UI Value :"+MailAddressline2_TXT, Status.FAIL);
                              
                       } 

                 
                // Checking UI mailing AddressesCity with database  
                 if (MailAddressesCity_TXT.equalsIgnoreCase(Mail_CITY) || (MailAddressesCity_TXT.equalsIgnoreCase(" ") && Mail_CITY==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer MAILING CITY matched with OVC database"+"DB Value:" +Mail_CITY + "="+"UI Value :"+MailAddressesCity_TXT, Status.PASS);
                       report.updateTestLog("AA", "Dealer MAILING CITY matched with OVC database in C_BO_CUST_POSTADDR table "+"DB Value:" +Mail_CITY + "="+"UI Value :"+MailAddressesCity_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer MAILING CITY not matched with OVC database"+"DB Value:" +Mail_CITY + "<>"+"UI Value :"+MailAddressesCity_TXT, Status.FAIL);
                              
                       } 
                 //
                
                // Checking UI mailing StateProvince with database  
                 if (MailStateProvince_TXT.equalsIgnoreCase(Mail_Province) || (MailStateProvince_TXT.equalsIgnoreCase(" ") && Mail_Province==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer mailing Province matched with OVC database"+"DB Value:" +Mail_Province + "="+"UI Value :"+MailStateProvince_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer mailing Province not matched with OVC database"+"DB Value:" +Mail_Province + "<>"+"UI Value :"+MailStateProvince_TXT, Status.FAIL);
                              
                       }  
                 
                 
                // Checking UI PostalCode with database
                
                 if (MailPostalCode_TXT.equalsIgnoreCase(Mail_PostalCode) || (MailPostalCode_TXT.equalsIgnoreCase(" ") && Mail_PostalCode==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer mailing PostalCode matched with OVC database"+"DB Value :" +Mail_PostalCode + "="+"UI Value :"+MailPostalCode_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer mailing PostalCode not matched with OVC database"+"DB Value :" +Mail_PostalCode + "<>"+"UI Value :"+MailPostalCode_TXT, Status.FAIL);
                              
                       } 
                 
                 //Checking UI Country with database
                
                 if (MailCountry_TXT.equalsIgnoreCase(Mail_COUNTRY) || (MailCountry_TXT.equalsIgnoreCase(" ") && Mail_COUNTRY==null))
                       {
                              
                       //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
                       report.updateTestLog("AA", "Dealer mailing COUNTRY matched with OVC database"+"DB Value :" +Mail_COUNTRY + "="+"UI Value :"+MailCountry_TXT, Status.PASS);
                       }
                       
                       else 
                       {
                              report.updateTestLog("AA","Dealer mailing COUNTRY not matched with OVC database"+"DB Value :" +Mail_COUNTRY + "<>"+"UI Value :"+MailCountry_TXT, Status.FAIL);
                              
                       } 
                 
                
                 Thread.sleep(3000);
                 driver.findElement(By.xpath(Auction.ContactOptionsLink)).click();
    			 Thread.sleep(4000);
    			 
    			 String EmailAddress_Val=getAttributeValue("Email Address");
    			 String Fax_Val=getAttributeValue("Fax");
    			 String MainPhone_Val=getAttributeValue("Main Phone");
    			 String WebSite_Val=getAttributeValue("Web Site");
    			 
    			 String Business_Phone = dataTable.getData("General_Data", "Business_Phone");

    			 String BusinessFax = dataTable.getData("General_Data", "Business Fax");

    			 String ContactEmail = dataTable.getData("General_Data", "ContactEmail");

    			 String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");
    			 
    			 if (EmailAddress_Val.equalsIgnoreCase(ContactEmail) && Fax_Val.equalsIgnoreCase(BusinessFax) && MainPhone_Val.equalsIgnoreCase(Business_Phone) && WebSite_Val.equalsIgnoreCase(WebsiteURL)) {
    				 
    				 report.updateTestLog("AA", "Dealer EmailAddress Business Fax URL Phone number matched with OVC database value :"+"Business_Phone DB Value : "+Business_Phone+","+"BusinessFax value: "+BusinessFax+","+"ContactEmail value: "+ContactEmail+","+"WebsiteURL : "+WebsiteURL+"="+"EmailAddress UI Value: "+EmailAddress_Val+","+"Fax UI Value: "+Fax_Val+","+"Main Phone UI Value"+MainPhone_Val+","+"WebSite_Val: "+WebSite_Val, Status.PASS);
    			 
    			 }else {
    				 report.updateTestLog("AA", "Dealer EmailAddress Business Fax URL Phone number not matched with OVC database value :"+"Business_Phone DB Value : "+Business_Phone+","+"BusinessFax value: "+BusinessFax+","+"ContactEmail value: "+ContactEmail+","+"WebsiteURL : "+WebsiteURL+"<Not equal>"+"EmailAddress UI Value: "+EmailAddress_Val+","+"Fax UI Value: "+Fax_Val+","+"Main Phone UI Value"+MainPhone_Val+","+"WebSite_Val: "+WebSite_Val, Status.PASS);
    			 }

	
//		 System.out.println("idd Login enter");
//		 loginIDD();
//		 System.out.println("call searchformIDD");
//		 searchformIDD();
	
}
	

	
	public  String extractData(String sql){
		Connection conn = null;
		Statement stmt = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
		String ACCT_NUM = "";

		try{
			Class.forName(driverName);
			conn=DriverManager.getConnection(connectionUrl,userName,password);
			System.out.println("inside the metod" +conn);
			if(conn!=null){
				stmt = conn.createStatement();
				ResultSet dataSet=stmt.executeQuery(sql);
				//ResultSetMetaData datatSetMetaData = dataSet.getMetaData();
				while (dataSet.next()) {
					ACCT_NUM=dataSet.getString("ACCT_DESC");
					System.out.println("Full Name is: "+ACCT_NUM);				
				}
			}
				
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ACCT_NUM;
	}

	public void loginIDD() throws Exception
	{

        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        String strAppURL = properties.getProperty("IDDApplicationURL");
        String uid = dataTable.getData("General_Data", "UserIDidd");
        String pwd = dataTable.getData("General_Data", "PasswordIDD");

        driver.get(strAppURL);
        Thread.sleep(10000);

        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_TXT_LOGIN_USER_ID)).sendKeys(uid);
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_TXT_LOGIN_PWD)).sendKeys(pwd);
        report.updateTestLog("IDD", "Application is opened ", Status.SCREENSHOT);
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_BTN_LOGIN)).click();
        
        Thread.sleep(20000);
        report.updateTestLog("IDD", "Login Successful ", Status.PASS);
        System.out.println("IDD Login Successful");                         
        
   }
    public void searchformIDD() throws Exception
    {
        
        System.out.println("Enter SearchForm");
        String ACCOUNT_NUMBER = dataTable.getData("General_Data", "ACCOUNT NUMBER");
        String ACCOUNT_NAME = dataTable.getData("General_Data", "ACCOUNT NAME");
        
        Thread.sleep(10000);
        //Click on Queries
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_Queries)).click();
        Thread.sleep(5000);
        
        driver.switchTo().frame("data");
        Thread.sleep(5000);
        //Select Account Number from Account
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_SearchForm_AccountNumber)).click();
        
        Thread.sleep(5000);
        //Click on Open Queries
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_OpenQueries)).click();
        Thread.sleep(5000);
        
        //Enter Account Number under ACCOUNT NUMBER TEXT FIELD
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_ACCOUNT_NUMBER_TextField_Under_Search)).click();
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_ACCOUNT_NUMBER_TextField_Under_Search)).sendKeys(ACCOUNT_NUMBER);
        
        //Click On RUN SEARCH button
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_RunSearch)).click();
        Thread.sleep(10000);
        
        //Open any record from Search Result
        driver.findElement(By.xpath(ObjectRepositories.IDD_UI_Open)).click();
        Thread.sleep(8000);
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(ObjectRepositories.IDD_UI_AccountNumber_From_DataTab)));
        
        String Fetch_AccountNumber_From_DataTab=driver.findElement(By.xpath(ObjectRepositories.IDD_UI_AccountNumber_From_DataTab)).getText();
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(ObjectRepositories.IDD_UI_ACCOUNT_DESCRIPTION_From_DataTab)));
        
        String Fetch_ACCOUNT_DESCRIPTION_From_DataTab=driver.findElement(By.xpath(ObjectRepositories.IDD_UI_ACCOUNT_DESCRIPTION_From_DataTab)).getText();
        
        
        if ((ACCOUNT_NUMBER.equalsIgnoreCase(Fetch_AccountNumber_From_DataTab)) && (ACCOUNT_NAME.equalsIgnoreCase(Fetch_ACCOUNT_DESCRIPTION_From_DataTab))){
                        report.updateTestLog("IDD", "Account Number opened successfully and account name verified", Status.SCREENSHOT);
                        System.out.println("Account Number opened successfully."+ACCOUNT_NUMBER);
        }
        else 
        {
                        report.updateTestLog("IDD", "Account Number not opened successfully ", Status.SCREENSHOT);
                        System.out.println("Account Number not opened successfully."+ACCOUNT_NUMBER);
        }
        
}

    public void logoutAA() throws Exception {
    	
    	driver.quit();
    }
    
    public String getAttributeValue(String Attributename)
    {
    	String attributeValue="";
    	
    	try
    	{
    		attributeValue=driver.findElement(By.xpath("//div[text()='"+Attributename+"']/parent::td/following-sibling::td/div")).getText();    		
    	}
    	catch (Exception e)
    	{
    		
    	}
    	//return Attributename,attributeValue;
    	return attributeValue;    	
    	
    }
    
    
    public String getXpathforAddAddressAttribute(String Attributename)
    {
    	String varxpath="";
    	
    	try
    	{
    		varxpath="//*[text()='"+Attributename+"']//parent::td//following-sibling::td//input"; 
    		System.out.println(varxpath);
    	}
    	catch (Exception e)
    	{
    		
    	}
    	//return Attributename,attributeValue;
    	return varxpath; 
     	
    }
    
    public String getXpathforContactOptionsAttribute(String index)
    {
    	String varxpath="";
    	
    	try
    	{
    		varxpath="(//*[@class='x-grid-table x-grid-table-resizer'])["+index+"]//td[3]"; 
    		System.out.println(varxpath);
    	}
    	catch (Exception e)
    	{
    		
    	}
    	//return Attributename,attributeValue;
    	return varxpath; 
     	
    }
    
    public void dbValForContactOptions() throws Exception
    {
    	 	
        	String CorporateOfficeCell_Xpath=getXpathforContactOptionsAttribute("2");
        	String CorporateOfficeFax_Xpath=getXpathforContactOptionsAttribute("3");
        	String CorporateOfficeTollFree_Xpath=getXpathforContactOptionsAttribute("4");
        	String LotAlternate_Xpath=getXpathforContactOptionsAttribute("5");
        	String LotCell_Xpath=getXpathforContactOptionsAttribute("7");
        	String LotEmailAddress_Xpath=getXpathforContactOptionsAttribute("8");
        	String LotFax_Xpath=getXpathforContactOptionsAttribute("9");
        	String LotMainPhone_Xpath=getXpathforContactOptionsAttribute("9");
        	String LotTollFree_Xpath=getXpathforContactOptionsAttribute("10");
        	String LotWebSite_Xpath=getXpathforContactOptionsAttribute("11");
        	
        	String CorporateOfficeCell=driver.findElement(By.xpath(CorporateOfficeCell_Xpath)).getText();
        	String CorporateOfficeFax=driver.findElement(By.xpath(CorporateOfficeFax_Xpath)).getText();
        	String CorporateOfficeTollFree=driver.findElement(By.xpath(CorporateOfficeTollFree_Xpath)).getText();
        	String LotAlternate=driver.findElement(By.xpath(LotAlternate_Xpath)).getText();
        	String LotCell=driver.findElement(By.xpath(LotCell_Xpath)).getText();
        	String LotEmailAddress=driver.findElement(By.xpath(LotEmailAddress_Xpath)).getText();
        	String LotFax=driver.findElement(By.xpath(LotFax_Xpath)).getText();
        	String LotMainPhone=driver.findElement(By.xpath(LotMainPhone_Xpath)).getText();
        	String LotTollFree=driver.findElement(By.xpath(LotTollFree_Xpath)).getText();
        	String LotWebSite=driver.findElement(By.xpath(LotWebSite_Xpath)).getText();
        	
    }
    
public void dbValContactOptions(String Value) throws Exception {
		
    	
    	String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		System.out.println(acctNum);
		 
		acctNum=(acctNum.substring(7, 14));
		System.out.println(acctNum);
		
		// Preparing the connection string 
		Connection conn = null;
		Statement stmt = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
		String sqlPassed="";
		
		String C_COUNTRY_CD="";
		String CLEAN_POSTAL_CD="";
		String CLEAN_PROVINCE="";
		String CLEAN_CITY="";
		String C_ADDR_LINE_1="";
		
		
		// Running the SQL Query
		sqlPassed="";
				//"SELECT     custxref.PKEY_SRC_OBJECT,     custxref.ROWID_OBJECT,     cust.ROWID_OBJECT,     acct.CUST_ID,     acct.ROWID_OBJECT,     acctpo.ACCT_ID,     acctpo.POST_ADDR_ID,     po.ROWID_OBJECT,     custxref.ROWID_SYSTEM,     cust.CUST_TYPE,     cust.CUST_NM,     cust.LEGAL_NM,     cust.FIRST_NM,     cust.MIDDLE_NM,     cust.LAST_NM,     cust.NO_SOLICIT_PHONE_IND,     cust.NO_SOLICIT_EMAIL_IND,     cust.NO_SOLICIT_PADDR_IND,     cust.KO_BOOK_IND,     cust.KO_BOOK_EXCEPT_IND,     cust.KO_BOOK_OVER_IND,     cust.ACTIVITY_CD,     cust.PAPERLESS_IND,     cust.SRC_DELETE_IND,     cust.CONSOLIDATION_IND,     cust.HUB_STATE_IND,     cust.LAST_ROWID_SYSTEM,     acct.ACCT_TYPE,     acct.ACCT_NUM,     acct.ACCT_DESC,     acct.ACCT_STATUS_CD,     acct.SRC_DELETE_IND,     acct.ACCT_DBA_NM,     acct.ACCT_SUBTYPE,     acct.ACCT_CLOSED_IND,     acct.ACCT_CLOSED_DT,     acct.PRIMARY_ACCT_IND,     acct.BUS_TYPE,     acct.BUS_SUBTYPE,     acct.CONSOLIDATION_IND,     acct.LAST_ROWID_SYSTEM,     acct.HUB_STATE_IND,     acct.CREATE_DATE,     acct.LAST_UPDATE_DATE,     acctpo.POST_ADDR_TYPE,     acctpo.PRIMARY_IND,     acctpo.SRC_DELETE_IND,     acctpo.HUB_STATE_IND,     acctpo.CONSOLIDATION_IND,     acctpo.CREATE_DATE,     acctpo.LAST_UPDATE_DATE,     po.LAST_UPDATE_DATE,     po.CONSOLIDATION_IND,     po.HUB_STATE_IND,     po.ADDR_LINE_1,     po.ADDR_LINE_2,     po.CITY,     po.PROVINCE_CD,     po.COUNTRY_CD,     po.LATITUDE,     po.LONGITUDE,     po.POSTAL_CD,     po.C_ADDR_LINE_1,     po.C_ADDR_LINE_2,     po.CLEAN_CITY,     po.CLEAN_PROVINCE,     po.CLEAN_POSTAL_CD,     po.C_POSTAL_CD_EXT,     po.C_COUNTRY_CD,     po.C_LATITUDE,     po.C_LONGITUDE,     po.C_MAILABILITY_SCORE,     po.SRC_DELETE_IND FROM     CMX_ORS.C_BO_CUST cust INNER JOIN     CMX_ORS.C_BO_ACCT acct ON     (         cust.ROWID_OBJECT = acct.CUST_ID) INNER JOIN     CMX_ORS.C_BO_ACCT_POSTADDR acctpo ON     (         acct.ROWID_OBJECT = acctpo.ACCT_ID) INNER JOIN     CMX_ORS.C_BO_POST_ADDR po ON     (         acctpo.POST_ADDR_ID = po.ROWID_OBJECT) INNER JOIN     CMX_ORS.C_BO_CUST_XREF custxref ON     (         cust.ROWID_OBJECT = custxref.ROWID_OBJECT) WHERE     cust.CONSOLIDATION_IND = 1 AND cust.HUB_STATE_IND = 1 AND cust.SRC_DELETE_IND = 'N' AND acct.HUB_STATE_IND = 1 AND acct.SRC_DELETE_IND = 'N' AND acctpo.HUB_STATE_IND = 1 AND acctpo.SRC_DELETE_IND = 'N' AND po.HUB_STATE_IND = 1 AND po.SRC_DELETE_IND = 'N' AND custxref.PKEY_SRC_OBJECT ="+ "'"+5430703+"'"+ "AND custxref.ROWID_SYSTEM = 'AA' AND acctpo.POST_ADDR_TYPE="+"'"+AddressType+"'";
		 
		
			try{
				Class.forName(driverName);
				conn=DriverManager.getConnection(connectionUrl,userName,password);
				System.out.println("inside the metod" +conn);
				if(conn!=null){
				stmt = conn.createStatement();
					ResultSet dataSet=stmt.executeQuery(sqlPassed);
					 Thread.sleep(10000);
					//ResultSetMetaData datatSetMetaData = dataSet.getMetaData();
					while (dataSet.next()) {
						
						//1--done
						C_COUNTRY_CD=dataSet.getString("C_COUNTRY_CD");
						System.out.println("C_COUNTRY_CD is: "+C_COUNTRY_CD);
						
						//2--done
						CLEAN_POSTAL_CD=dataSet.getString("CLEAN_POSTAL_CD");
						System.out.println("CLEAN_POSTAL_CD is: "+CLEAN_POSTAL_CD);
						
						//3 DBA Name-- done
						CLEAN_PROVINCE= dataSet.getString("CLEAN_PROVINCE");
						System.out.println("CLEAN_PROVINCE is: "+CLEAN_PROVINCE);
						
						//4 done
						CLEAN_CITY=dataSet.getString("CLEAN_CITY");
						System.out.println("CLEAN_CITY is: "+CLEAN_CITY);
						
						//5--not yet done have doubt
						C_ADDR_LINE_1=dataSet.getString("C_ADDR_LINE_1");
						System.out.println("C_ADDR_LINE_1 is: "+C_ADDR_LINE_1);
						
						
					}
				}
					
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
}
    
    
    
    public void dbValAdressesForGlobal(String AddressType,String Street1, String City, String State, String postalCode,String Country) throws Exception {
		
    	
    	String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
		System.out.println(acctNum);
		 
		acctNum=(acctNum.substring(7, 14));
		System.out.println(acctNum);
		
		// Preparing the connection string 
		Connection conn = null;
		Statement stmt = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
		String sqlPassed="";
		
		String C_COUNTRY_CD="";
		String CLEAN_POSTAL_CD="";
		String CLEAN_PROVINCE="";
		String CLEAN_CITY="";
		String C_ADDR_LINE_1="";
		
		
		// Running the SQL Query
		sqlPassed="SELECT     custxref.PKEY_SRC_OBJECT,     custxref.ROWID_OBJECT,     cust.ROWID_OBJECT,     acct.CUST_ID,     acct.ROWID_OBJECT,     acctpo.ACCT_ID,     acctpo.POST_ADDR_ID,     po.ROWID_OBJECT,     custxref.ROWID_SYSTEM,     cust.CUST_TYPE,     cust.CUST_NM,     cust.LEGAL_NM,     cust.FIRST_NM,     cust.MIDDLE_NM,     cust.LAST_NM,     cust.NO_SOLICIT_PHONE_IND,     cust.NO_SOLICIT_EMAIL_IND,     cust.NO_SOLICIT_PADDR_IND,     cust.KO_BOOK_IND,     cust.KO_BOOK_EXCEPT_IND,     cust.KO_BOOK_OVER_IND,     cust.ACTIVITY_CD,     cust.PAPERLESS_IND,     cust.SRC_DELETE_IND,     cust.CONSOLIDATION_IND,     cust.HUB_STATE_IND,     cust.LAST_ROWID_SYSTEM,     acct.ACCT_TYPE,     acct.ACCT_NUM,     acct.ACCT_DESC,     acct.ACCT_STATUS_CD,     acct.SRC_DELETE_IND,     acct.ACCT_DBA_NM,     acct.ACCT_SUBTYPE,     acct.ACCT_CLOSED_IND,     acct.ACCT_CLOSED_DT,     acct.PRIMARY_ACCT_IND,     acct.BUS_TYPE,     acct.BUS_SUBTYPE,     acct.CONSOLIDATION_IND,     acct.LAST_ROWID_SYSTEM,     acct.HUB_STATE_IND,     acct.CREATE_DATE,     acct.LAST_UPDATE_DATE,     acctpo.POST_ADDR_TYPE,     acctpo.PRIMARY_IND,     acctpo.SRC_DELETE_IND,     acctpo.HUB_STATE_IND,     acctpo.CONSOLIDATION_IND,     acctpo.CREATE_DATE,     acctpo.LAST_UPDATE_DATE,     po.LAST_UPDATE_DATE,     po.CONSOLIDATION_IND,     po.HUB_STATE_IND,     po.ADDR_LINE_1,     po.ADDR_LINE_2,     po.CITY,     po.PROVINCE_CD,     po.COUNTRY_CD,     po.LATITUDE,     po.LONGITUDE,     po.POSTAL_CD,     po.C_ADDR_LINE_1,     po.C_ADDR_LINE_2,     po.CLEAN_CITY,     po.CLEAN_PROVINCE,     po.CLEAN_POSTAL_CD,     po.C_POSTAL_CD_EXT,     po.C_COUNTRY_CD,     po.C_LATITUDE,     po.C_LONGITUDE,     po.C_MAILABILITY_SCORE,     po.SRC_DELETE_IND FROM     CMX_ORS.C_BO_CUST cust INNER JOIN     CMX_ORS.C_BO_ACCT acct ON     (         cust.ROWID_OBJECT = acct.CUST_ID) INNER JOIN     CMX_ORS.C_BO_ACCT_POSTADDR acctpo ON     (         acct.ROWID_OBJECT = acctpo.ACCT_ID) INNER JOIN     CMX_ORS.C_BO_POST_ADDR po ON     (         acctpo.POST_ADDR_ID = po.ROWID_OBJECT) INNER JOIN     CMX_ORS.C_BO_CUST_XREF custxref ON     (         cust.ROWID_OBJECT = custxref.ROWID_OBJECT) WHERE     cust.CONSOLIDATION_IND = 1 AND cust.HUB_STATE_IND = 1 AND cust.SRC_DELETE_IND = 'N' AND acct.HUB_STATE_IND = 1 AND acct.SRC_DELETE_IND = 'N' AND acctpo.HUB_STATE_IND = 1 AND acctpo.SRC_DELETE_IND = 'N' AND po.HUB_STATE_IND = 1 AND po.SRC_DELETE_IND = 'N' AND custxref.PKEY_SRC_OBJECT ="+ "'"+5430703+"'"+ "AND custxref.ROWID_SYSTEM = 'AA' AND acctpo.POST_ADDR_TYPE="+"'"+AddressType+"'";
		 
		
			try{
				Class.forName(driverName);
				conn=DriverManager.getConnection(connectionUrl,userName,password);
				System.out.println("inside the metod" +conn);
				if(conn!=null){
				stmt = conn.createStatement();
					ResultSet dataSet=stmt.executeQuery(sqlPassed);
					 Thread.sleep(10000);
					//ResultSetMetaData datatSetMetaData = dataSet.getMetaData();
					while (dataSet.next()) {
						
						//1--done
						C_COUNTRY_CD=dataSet.getString("C_COUNTRY_CD");
						System.out.println("C_COUNTRY_CD is: "+C_COUNTRY_CD);
						
						//2--done
						CLEAN_POSTAL_CD=dataSet.getString("CLEAN_POSTAL_CD");
						System.out.println("CLEAN_POSTAL_CD is: "+CLEAN_POSTAL_CD);
						
						//3 DBA Name-- done
						CLEAN_PROVINCE= dataSet.getString("CLEAN_PROVINCE");
						System.out.println("CLEAN_PROVINCE is: "+CLEAN_PROVINCE);
						
						//4 done
						CLEAN_CITY=dataSet.getString("CLEAN_CITY");
						System.out.println("CLEAN_CITY is: "+CLEAN_CITY);
						
						//5--not yet done have doubt
						C_ADDR_LINE_1=dataSet.getString("C_ADDR_LINE_1");
						System.out.println("C_ADDR_LINE_1 is: "+C_ADDR_LINE_1);
						
						
					}
				}
					
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		
			//STREET
			 if (Street1.equalsIgnoreCase(C_ADDR_LINE_1) || (Street1.equalsIgnoreCase(" ") && C_ADDR_LINE_1==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Street1 value in UI under Addresses section for Address Type "+ AddressType + "is : "+Street1+"="+" Street1 DB value is : "+C_ADDR_LINE_1, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Street1 value in UI under Addresses section for Address Type "+ AddressType + "is : "+Street1+" NOT EQUAL TO "+" Street1 DB value is : "+C_ADDR_LINE_1, Status.FAIL);
					
				}
			 
			//CITY
			 if (City.equalsIgnoreCase(CLEAN_CITY) || (City.equalsIgnoreCase(" ") && CLEAN_CITY==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "CITY value in UI under Addresses section for Address Type "+ AddressType + "is : "+City+"="+" CITY DB value is : "+CLEAN_CITY, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "CITY value in UI under Addresses section for Address Type "+ AddressType + "is : "+City+" NOT EQUAL TO "+" CITY DB value is : "+CLEAN_CITY, Status.FAIL);
					
				}
			 
			//State
			 if (State.equalsIgnoreCase(CLEAN_PROVINCE) || (State.equalsIgnoreCase(" ") && CLEAN_PROVINCE==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "STATE value in UI under Addresses section for Address Type "+ AddressType + "is : "+State+"="+" STATEPROVINCE DB value is : "+CLEAN_PROVINCE, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "STATE value in UI under Addresses section for Address Type "+ AddressType + "is : "+State+" NOT EQUAL TO "+" STATEPROVINCE DB value is : "+CLEAN_PROVINCE, Status.FAIL);
					
				}
			 
			//postalCode
			 if (postalCode.equalsIgnoreCase(CLEAN_POSTAL_CD) || (postalCode.equalsIgnoreCase(" ") && CLEAN_POSTAL_CD==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Postal Code value in UI under Addresses section for Address Type "+ AddressType + "is : "+postalCode+"="+" POSTAL CODE DB value is : "+CLEAN_POSTAL_CD, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "Postal Code value in UI under Addresses section for Address Type "+ AddressType + "is : "+postalCode+" NOT EQUAL TO "+" POSTAL CODE DB value is : "+CLEAN_POSTAL_CD, Status.FAIL);
					
				}
			 
			//postalCode
			 if (Country.equalsIgnoreCase(C_COUNTRY_CD) || (Country.equalsIgnoreCase(" ") && C_COUNTRY_CD==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "COUNTRY value in UI under Addresses section for Address Type "+ AddressType + "is : "+Country+"="+" COUNTRY DB value is : "+C_COUNTRY_CD, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA", "COUNTRY value in UI under Addresses section for Address Type "+ AddressType + "is : "+Country+" NOT EQUAL TO "+" COUNTRY DB value is : "+C_COUNTRY_CD, Status.FAIL);
					
				}
		
    	}
    
  
    public void getAddressvalue() throws Exception
    {
    	 String VisibilityGlobal = dataTable.getData("General_Data", "Visibility Global");
         
         String VisibilityGroup = dataTable.getData("General_Data", "Visibility Group");
         
         String VisibilityAuction = dataTable.getData("General_Data", "Visibility Auction");
         
         String AddressTypeBilling = dataTable.getData("General_Data", "AddressType Billing");
         
         String AddressTypePayments = dataTable.getData("General_Data", "AddressType Payments");
         
         String AddressTypeLot = dataTable.getData("General_Data", "AddressType Lot");
         
         String AddressTypeMailing = dataTable.getData("General_Data", "AddressType Mailing");
         
         
         if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypeBilling.equalsIgnoreCase("Y"))) {
         	
        	String Street1XpathBilling=getXpathforAddAddressAttributeGlobal("2","2","2");
        	String CityXpathBilling=getXpathforAddAddressAttributeGlobal("2","2","4");
        	String StateProvinceXpathBilling=getXpathforAddAddressAttributeGlobal("2","2","6");
        	String PostalCodeXpathBilling=getXpathforAddAddressAttributeGlobal("2","2","7");
        	String CountryXpathBilling=getXpathforAddAddressAttributeGlobal("2","2","8");
         	
        	String Street1_ValueBilling=driver.findElement(By.xpath(Street1XpathBilling)).getText();
        	Thread.sleep(500);
        	String City_ValueBilling=driver.findElement(By.xpath(CityXpathBilling)).getText();
        	Thread.sleep(500);
        	String StateProvince_ValueBilling=driver.findElement(By.xpath(StateProvinceXpathBilling)).getText();
        	Thread.sleep(500);
        	if(StateProvince_ValueBilling.equalsIgnoreCase("New York")) 
        	{
        		StateProvince_ValueBilling="USA-NY";
        	}else if(StateProvince_ValueBilling.equalsIgnoreCase("Georgia"))
        	{
        		StateProvince_ValueBilling="USA-GA";
        	}
        	String PostalCode_ValueBilling=driver.findElement(By.xpath(PostalCodeXpathBilling)).getText();
        	Thread.sleep(500);
        	String Country_ValueBilling=driver.findElement(By.xpath(CountryXpathBilling)).getText();
         	Thread.sleep(500);
        	dbValAdressesForGlobal("LEGALBILLING",Street1_ValueBilling, City_ValueBilling, StateProvince_ValueBilling, PostalCode_ValueBilling,Country_ValueBilling);
         }
         
         if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypeLot.equalsIgnoreCase("Y"))) {
            	
           	String Street1XpathLot=getXpathforAddAddressAttributeGlobal("3","2","2");
           	String CityXpathLot=getXpathforAddAddressAttributeGlobal("3","2","4");
           	String StateProvinceXpathLot=getXpathforAddAddressAttributeGlobal("3","2","6");
           	String PostalCodeXpathLot=getXpathforAddAddressAttributeGlobal("3","2","7");
           	String CountryXpathLot=getXpathforAddAddressAttributeGlobal("3","2","8");
            	
           	String Street1_Value_Lot=driver.findElement(By.xpath(Street1XpathLot)).getText();
           	Thread.sleep(500);
           	String City_Value_Lot=driver.findElement(By.xpath(CityXpathLot)).getText();
           	Thread.sleep(500);
           	String StateProvince_Value_Lot=driver.findElement(By.xpath(StateProvinceXpathLot)).getText();
           	Thread.sleep(500);
           	if(StateProvince_Value_Lot.equalsIgnoreCase("New York")) {
           		StateProvince_Value_Lot="USA-NY";
         	}else if(StateProvince_Value_Lot.equalsIgnoreCase("Georgia"))
        	{
         		StateProvince_Value_Lot="USA-GA";
        	}
           	String PostalCode_Value_Lot=driver.findElement(By.xpath(PostalCodeXpathLot)).getText();
           	Thread.sleep(500);
           	String Country_Value_Lot=driver.findElement(By.xpath(CountryXpathLot)).getText();
           	Thread.sleep(500);
           	dbValAdressesForGlobal("LEGALPHYSICAL",Street1_Value_Lot, City_Value_Lot, StateProvince_Value_Lot, PostalCode_Value_Lot,Country_Value_Lot);
            }
         
         if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypeMailing.equalsIgnoreCase("Y"))) {
            	
           	String Street1XpathMailing=getXpathforAddAddressAttributeGlobal("4","2","2");
           	String CityXpathMailing=getXpathforAddAddressAttributeGlobal("4","2","4");
           	String StateProvinceXpathMailing=getXpathforAddAddressAttributeGlobal("4","2","6");
           	String PostalCodeXpathMailing=getXpathforAddAddressAttributeGlobal("4","2","7");
           	String CountryXpathMailing=getXpathforAddAddressAttributeGlobal("4","2","8");
            	
           	String Street1_Value_Mailing=driver.findElement(By.xpath(Street1XpathMailing)).getText();
           	Thread.sleep(500);
           	String City_Value_Mailing=driver.findElement(By.xpath(CityXpathMailing)).getText();
           	Thread.sleep(500);
           	String StateProvince_Value_Mailing=driver.findElement(By.xpath(StateProvinceXpathMailing)).getText();
           	Thread.sleep(500);
           	if(StateProvince_Value_Mailing.equalsIgnoreCase("New York")) {
           		StateProvince_Value_Mailing="USA-NY";
         	}else if(StateProvince_Value_Mailing.equalsIgnoreCase("Georgia"))
        	{
         		StateProvince_Value_Mailing="USA-GA";
        	}
           	String PostalCode_Value_Mailing=driver.findElement(By.xpath(PostalCodeXpathMailing)).getText();
           	Thread.sleep(500);
           	String Country_Value_Mailing=driver.findElement(By.xpath(CountryXpathMailing)).getText();
           	Thread.sleep(500);
           	dbValAdressesForGlobal("LEGALMAIL",Street1_Value_Mailing, City_Value_Mailing, StateProvince_Value_Mailing, PostalCode_Value_Mailing,Country_Value_Mailing);
            }
         
         if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypePayments.equalsIgnoreCase("Y"))) {
          	
         	String Street1XpathPayments=getXpathforAddAddressAttributeGlobal("5","2","2");
         	String CityXpathPayments=getXpathforAddAddressAttributeGlobal("5","2","4");
         	String StateProvinceXpathPayments=getXpathforAddAddressAttributeGlobal("5","2","6");
         	String PostalCodeXpathPayments=getXpathforAddAddressAttributeGlobal("5","2","7");
         	String CountryXpathPayments=getXpathforAddAddressAttributeGlobal("5","2","8");
          	
         	String Street1_Value_Payments=driver.findElement(By.xpath(Street1XpathPayments)).getText();
         	Thread.sleep(500);
         	String City_Value_Payments=driver.findElement(By.xpath(CityXpathPayments)).getText();
         	Thread.sleep(500);
         	String StateProvince_Value_Payments=driver.findElement(By.xpath(StateProvinceXpathPayments)).getText();
         	Thread.sleep(500);
         	if(StateProvince_Value_Payments.equalsIgnoreCase("New York")) {
         		StateProvince_Value_Payments="USA-NY";
        	}else if(StateProvince_Value_Payments.equalsIgnoreCase("Georgia"))
        	{
        		StateProvince_Value_Payments="USA-GA";
        	}
         	String PostalCode_Value_Payments=driver.findElement(By.xpath(PostalCodeXpathPayments)).getText();
         	Thread.sleep(500);
         	String Country_Value_Payments=driver.findElement(By.xpath(CountryXpathPayments)).getText();
          	
         	dbValAdressesForGlobal("LEGALBILLING",Street1_Value_Payments, City_Value_Payments, StateProvince_Value_Payments, PostalCode_Value_Payments,Country_Value_Payments);
          }
        
    }
    
    
    public String getXpathforAddAddressAttributeGlobal(String Table,String TR,String TD)
    {
    	String varxpath="";
    	
    	try
    	{
    		
    		varxpath="(//table[@class='x-grid-table x-grid-table-resizer'])["+Table+"]//tbody//tr["+TR+"]//td["+TD+"]"; 
    		System.out.println(varxpath);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	//return Attributename,attributeValue;
    	return varxpath; 
     	
    }
    
    public String ranDom(String fieldname)  
    {
 	   Random rand = new Random(); 
 	   int rand_int1 = rand.nextInt(100000);
 	   fieldname = rand_int1+"_"+fieldname ;
 	   return fieldname ;
    }
    
    public void addAddressAttribute() throws InterruptedException
    {
      
        String VisibilityGlobal = dataTable.getData("General_Data", "Visibility Global");
        
        String VisibilityGroup = dataTable.getData("General_Data", "Visibility Group");
        
        String VisibilityAuction = dataTable.getData("General_Data", "Visibility Auction");
        
        String AddressTypeBilling = dataTable.getData("General_Data", "AddressType Billing");
        
        String AddressTypePayments = dataTable.getData("General_Data", "AddressType Payments");
        
        String AddressTypeLot = dataTable.getData("General_Data", "AddressType Lot");
        
        String AddressTypeMailing = dataTable.getData("General_Data", "AddressType Mailing");
        
        
        if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypeBilling.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Global","Billing");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
        }
        Thread.sleep(500);
        
        if((VisibilityGlobal.equalsIgnoreCase("Y")) && (AddressTypePayments.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Global","Payments");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityGroup.equalsIgnoreCase("Y")) && (AddressTypeLot.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Group","Lot");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityGroup.equalsIgnoreCase("Y")) && (AddressTypeMailing.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Group","Mailing");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityGroup.equalsIgnoreCase("Y")) && (AddressTypeBilling.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Group","Billing");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityGroup.equalsIgnoreCase("Y")) && (AddressTypePayments.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Group","Payments");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityAuction.equalsIgnoreCase("Y")) && (AddressTypeLot.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Auction","Lot");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityAuction.equalsIgnoreCase("Y")) && (AddressTypeMailing.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Auction","Mailing");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        
        Thread.sleep(500);
        if((VisibilityAuction.equalsIgnoreCase("Y")) && (AddressTypeBilling.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Auction","Billing");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
        
        if((VisibilityAuction.equalsIgnoreCase("Y")) && (AddressTypePayments.equalsIgnoreCase("Y"))) {
        	Thread.sleep(1000);
            clickOnAdd();
            Thread.sleep(1000);
        	enterdatainVisibilityANDAddressType("Auction","Payments");
        	Thread.sleep(500);
        	addOtherAddressAttribute();
        	Thread.sleep(500);
            }
        Thread.sleep(500);
       
    	}
    	
    
    public void addOtherAddressAttribute() throws InterruptedException {
    	
    	String Lot_Line1 = dataTable.getData("General_Data", "Lot_Line1");
  		String Lot_City = dataTable.getData("General_Data", "Lot_City");
  		String Lot_Country = dataTable.getData("General_Data", "Lot_Country");
  		String Lot_State = dataTable.getData("General_Data", "Lot_State");
  		String Lot_PostalCode = dataTable.getData("General_Data", "Lot_PostalCode");
  		
  		ranDom("Lot_Line1");
  		
  		String Street=getXpathforAddAddressAttribute("Street:");
  		String City=getXpathforAddAddressAttribute("City:");
  		String Country=getXpathforAddAddressAttribute("Country:");
  		String StateProvince=getXpathforAddAddressAttribute("State/Province:");
  		String PostalCode=getXpathforAddAddressAttribute("Postal Code:");
  		
  		driver.findElement(By.xpath(Street)).sendKeys(Lot_Line1);
  		 Thread.sleep(500);
  		driver.findElement(By.xpath(City)).sendKeys(Lot_City);
  		Thread.sleep(500);
  		driver.findElement(By.xpath(Country)).sendKeys(Lot_Country);
  		Thread.sleep(500);
  		driver.findElement(By.xpath(StateProvince)).sendKeys(Lot_State);
  		Thread.sleep(500);
  		driver.findElement(By.xpath(PostalCode)).sendKeys(Lot_PostalCode);
  		Thread.sleep(1000);
  		
  		if(driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).isEnabled()) {
  			driver.findElement(By.xpath(Auction.SaveButtonUnderAddContactOption)).click();
  			Thread.sleep(3000);
  		}
  		else
  		{
  			driver.findElement(By.xpath(Auction.CancelButton_Under_EditType_Window)).click();
  			System.out.println("Something went wrong Save button is not enabled");	
  		}
  		
  		try {
  		if(driver.findElement(By.xpath(Auction.Error)).isDisplayed()) {
  		   driver.findElement(By.xpath(Auction.Ok)).click();
  		   Thread.sleep(2000);
  		   driver.findElement(By.xpath(Auction.CancelButton_Under_EditType_Window)).click();
  		 Thread.sleep(1000);
  		}
  		}
  		catch(Exception e)
  		{
  			System.out.println("No Error window occured :"+e);
  		}
  		 
  		report.updateTestLog("AA", "Data is entered successfully under Add Address window ", Status.PASS);
    }
    
    public void enterdatainVisibilityANDAddressType(String visibility,String AddressType) {
    	try
    	{
    driver.findElement(By.xpath(Auction.visibilitydropdownarrow)).click();
    Thread.sleep(1000);
    
    List<WebElement> myElements1 = driver.findElements(By.xpath(Auction.visibilityValue));
		for(WebElement e : myElements1) {
		  String Comp=(e.getText());
		  System.out.println("Country is :"+Comp);
		  if(visibility.equalsIgnoreCase(Comp)) {
			  e.click();
			  Thread.sleep(2000);
			  System.out.println("Value is selected from Visibility dropdown :"+visibility);
			  break;
		  }
		  
		}
		
	   driver.findElement(By.xpath(Auction.AddressTypeDropdownArrow)).click();
     Thread.sleep(1000);
     
     List<WebElement> myElements2 = driver.findElements(By.xpath(Auction.AddressTypeValue));
	   for(WebElement e : myElements2) {
		  String Comp=(e.getText());
		  System.out.println("Country is :"+Comp);
		  if(AddressType.equalsIgnoreCase(Comp)) {
			  System.out.println(AddressType+" Value is found in AddressType dropdown");
			  e.click();
			  Thread.sleep(1000);
			  System.out.println("Value is selected from AddressType dropdown :"+AddressType);
			  break;
		  }
		  else
		  {
			System.out.println(AddressType+" Value is not found in AddressType dropdown");
		  }
		  
		}
	}
	   catch(Exception e)
	   {
		   System.out.println("Something went wrong :"+e);  
	   }
}


	
    public void quicksearchAA() throws InterruptedException
    {
    	Thread.sleep(1000); 
    	try
    	{
    		/*String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
   		 
    		acctNum=(acctNum.substring(7, 14));
    		System.out.println(acctNum);*/
    		
    		if(driver.findElement(By.xpath(Auction.QuickSearch)).isDisplayed())
    		{
    			driver.findElement(By.xpath(Auction.QuickSearch)).sendKeys(dealerId);
    		Thread.sleep(500);
    		}
    		Actions action = new Actions(driver);
    		action.sendKeys(Keys.ENTER);
    		action.perform();
    		Thread.sleep(2000);
    		if(driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).isDisplayed()) {
    		report.updateTestLog("AA", "Dealer Information window displayed after Quick Search", Status.PASS);
    		}
    		else
    		{
    		report.updateTestLog("AA", "Dealer Information window is not displayed after Quick Search", Status.FAIL);
    		}
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    }
    
    public void changeRole() throws InterruptedException
    {
    	Thread.sleep(1000); 
    	try
    	{
    		String ChangeRole= dataTable.getData("General_Data", "Change Role"); 
    		 
    		if(driver.findElement(By.xpath(Auction.changeRoleDropdown)).isEnabled()) {
    		driver.findElement(By.xpath(Auction.changeRoleDropdown)).click();
    		Thread.sleep(1000);
    		driver.findElement(By.xpath(Auction.changeRole)).click();
    		Thread.sleep(1000);
    		}
    		else
    		{
    		report.updateTestLog("AA", "change role button is not enabled", Status.FAIL);
    		}
    		
    		driver.findElement(By.xpath(Auction.HomeCompanyDropdown)).click();
    		Thread.sleep(500);
    		
    		List<WebElement> myElement1 = driver.findElements(By.xpath(Auction.HomeCompanyValue));
			for(WebElement x : myElement1) 
			{
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(ChangeRole.equalsIgnoreCase(Comp)) 
			  {
				  x.click();
				  Thread.sleep(1000);
				  System.out.println("Role is changed to :"+ ChangeRole);
				  report.updateTestLog("AA", "Role is changes successfully ", Status.PASS);
				  break;
			  }
			}
			
			driver.findElement(By.xpath(Auction.Ok)).click();
			Thread.sleep(1000);
 	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    }
    
    public void registerAA() throws InterruptedException
    {
    	Thread.sleep(1000); 
    	try
    	{
    		
    		if(driver.findElement(By.xpath(Auction.Register)).isEnabled()) {
    		driver.findElement(By.xpath(Auction.Register)).click();
    		Thread.sleep(1000);
    		report.updateTestLog("AA", "Dealer is registered successfully ", Status.PASS);
    		
    		}
    		else
    		{
    		report.updateTestLog("AA", "Register button is not enabled ", Status.FAIL);
    		}
    		
 	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    }
    
   
}
