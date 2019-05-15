package businesscomponents;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import com.util.frameworkutils.Status;
import org.openqa.selenium.WebElement;
import java.util.Random;
import objectrepositories.Auction;
import objectrepositories.Auction1;
import objectrepositories.GTC;
import objectrepositories.ObjectRepositories;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

public class AuctionAccess1 extends ReusableLibrary {
	Random rand = new Random();  
	WebDriverWait wait = new WebDriverWait(driver, 500);
	public AuctionAccess1(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
	GenericFunctions GF = new GenericFunctions(scriptHelper);
	WebElement element;
	// this keyword helps login to the AA portal with user ID and Password 
	public void loginAA_Auction() throws Exception 
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.TXT_LOGIN_USER_ID)));
			// Entering user id 
			if(GF.isElementPresent(By.xpath(Auction1.TXT_LOGIN_USER_ID)))
				driver.findElement(By.xpath(Auction1.TXT_LOGIN_USER_ID)).sendKeys(uid);
			// entering login password 
			if(GF.isElementPresent(By.xpath(Auction1.TXT_LOGIN_PWD)))
			driver.findElement(By.xpath(Auction1.TXT_LOGIN_PWD)).sendKeys(pwd);
			report.updateTestLog("AA", "Application is opened ", Status.SCREENSHOT);
			
			driver.findElement(By.xpath(Auction1.BTN_LOGIN)).click();
			Thread.sleep(5000);
			
			//selecting role 
 			if (GF.isElementPresent(By.xpath(Auction1.BTN_ROLE_EDIT)));
			driver.findElement(By.xpath(Auction1.BTN_ROLE_DRPDWN)).click();
			driver.findElement(By.xpath(Auction1.BTN_ROLE_SLT)).click();
			
			// selecting company
			/*driver.findElement(By.xpath(Auction.BTN_COMPANY)).click();
			driver.findElement(By.xpath(Auction.BTN_COMPANY_SLT)).click();
			Thread.sleep(5000);*/
			
			driver.findElement(By.xpath(Auction1.BTN_COMPANY)).click();
			Thread.sleep(3000);
			
			List<WebElement> myElements = driver.findElements(By.xpath(Auction1.Company));
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
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.BTN_REFRESH)));
				GF.waitForObject(By.xpath(Auction1.BTN_REFRESH), "Refresh");
				if(GF.isElementPresent(By.xpath(Auction1.BTN_REFRESH))){
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

		
	}
	// This below keyword searching dealer by DBA name 
	
	public void dealerSRH_Auction() throws Exception
    {
        Thread.sleep(10000);
        
      String acctNum = dataTable.getData("General_Data", "ACCOUNT NUMBER");
//     String acctNum =driver.findElement(By.xpath(Auction.TextAfterDealerCreation)).getText();
//      System.out.println(acctNum);          
//       acctNum=(acctNum.substring(7, 14));
//       System.out.println(acctNum);
//        
        String DBAName= dataTable.getData("General_Data", "DBA Name");  
    try 
    {
           driver.findElement(By.xpath(Auction1.BTN_DEALER)).click();
           Thread.sleep(5000);
           System.out.println("Clicked on Dealer");
           driver.findElement(By.xpath(Auction1.BTN_SEARCH)).click();
           Thread.sleep(10000);      
           driver.findElement(By.xpath(Auction1.Search_ID)).sendKeys(acctNum);
           driver.findElement(By.xpath(Auction1.BTN_DLRSRCH)).click();
           Thread.sleep(20000);      
           String DBANameGUI=driver.findElement(By.xpath(Auction1.DBA_TXT)).getText();
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

	public void dealercreate_Auction() throws Exception
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
    	
        driver.findElement(By.xpath(Auction1.BTN_DEALER)).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.BTN_ADD_DEALER)));
        Thread.sleep(500);
        System.out.println("Clicked on Dealer");
        driver.findElement(By.xpath(Auction1.BTN_ADD_DEALER)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.IdentificationWindow)));        
        Thread.sleep(500);
           
        if(driver.findElement(By.xpath(Auction1.IdentificationWindow)).isDisplayed()) 
        {
          report.updateTestLog("AA", "Identification Window is opened ", Status.PASS);          
        }
        else 
        {
          report.updateTestLog("AA", "Identification Window is not opened ", Status.FAIL);
         
        }
        DBAName=ranDom(DBAName);   
        driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).sendKeys(DBAName);
        Thread.sleep(500);   
           //driver.findElement(By.xpath(Auction.Identification_LegalName)).sendKeys(LegalName);
           
        driver.findElement(By.xpath(Auction1.SameAsDBA)).click();
        driver.findElement(By.xpath(Auction1.FederalIdCountry_dropdown)).click();
		Thread.sleep(1000);
			
		List<WebElement> myElements = driver.findElements(By.xpath(Auction1.Federal_Id_Country_WebElements));
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
			
		driver.findElement(By.xpath(Auction1.Federal_Id_Type)).sendKeys(FederalIdType);
		Thread.sleep(1000);
		int rand_int1 = 100000000+rand.nextInt(100000000);
		FederalId=Integer.toString(rand_int1);
		driver.findElement(By.xpath(Auction1.FedaralId)).sendKeys(FederalId);
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction1.FedaralIdConfirm)).sendKeys(FederalId);
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction1.companyType)).sendKeys(CompanyType);
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction1.CompanyTypeText)).click();			
		Thread.sleep(1000);
		driver.findElement(By.xpath(Auction1.BusinessType)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.TXT_BusinessType)));     	
		Thread.sleep(1000);
			
		driver.findElement(By.xpath(Auction1.Add_BusinessType)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(Auction1.DealerType_DropdownArrow)).click();
        Thread.sleep(1000);
			
        List<WebElement> myElements2 = driver.findElements(By.xpath(Auction1.DealerType));
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
			
		//driver.findElement(By.xpath(Auction.NewCheckBox)).click();
		dealerTypeSlection () ;
		Thread.sleep(500);			
		driver.findElement(By.xpath(Auction1.DelearType_Update)).click();
		Thread.sleep(1000);			
		driver.findElement(By.xpath(Auction1.DelearType_Save)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.IdentificationWindow)));  
		Thread.sleep(1000);
		BondCompany=ranDom(BondCompany);
		BondCompany=BondCompany.replace("_", "");	
		driver.findElement(By.xpath(Auction1.BondCompany)).sendKeys(BondCompany);
		Thread.sleep(1000);
		int rand_int2 = 500000000+rand.nextInt(100000000);
		BondNumber=Integer.toString(rand_int2);
		driver.findElement(By.xpath(Auction1.bondNumber)).sendKeys(BondNumber);
		Thread.sleep(2000);			
		report.updateTestLog("AA", "Data entered in all required fields under Identification section ", Status.PASS);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.IdentificationNext)));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.DealerOverride))); 
		Thread.sleep(1000);			
		boolean override1=false;
		override1=driver.findElement(By.xpath(Auction1.DealerOverride)).isDisplayed();
		if(override1==true) 
		{		
	      report.updateTestLog("AA", "Override window is displayed ", Status.SCREENSHOT);
	      driver.findElement(By.xpath(Auction1.DealerOverride)).click();
	      report.updateTestLog("AA", "Clicked on Override button under Override window ", Status.PASS);	            
	      System.out.println("Override is clicked");
	     // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.ContactWindow))); 
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
	
	public void createContact_Auction() throws Exception 
	{		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.ContactWindow)));
		Thread.sleep(1000);
		try 
		{
			if(driver.findElement(By.xpath(Auction1.ContactWindow)).isDisplayed()) 
			{
				report.updateTestLog("AA", "Contact window is opened ", Status.PASS);
			}
			else 
			{
				report.updateTestLog("AA", "Contact window is not opened ", Status.FAIL);
			}
		
		// Setting Lot Line 1

	    String Lot_Line1 = dataTable.getData("General_Data", "Lot_Line1");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction1.LOT_Line1)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction1.LOT_Line1)).sendKeys(Lot_Line1);
	    System.out.println("Lot_Line1 is entered "+Lot_Line1);

	   // Setting Lot Line 2

	    String Lot_Line2 = dataTable.getData("General_Data", "Lot_Line2");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction1.LOT_Line2)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(Auction1.LOT_Line2)).sendKeys(Lot_Line2);
	    Thread.sleep(2000);
	    System.out.println("LOT_Line2 is entered "+Lot_Line2);

	      
	      // LOT_City

	       String LOT_City = dataTable.getData("General_Data", "Lot_City");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.LOT_City)).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.LOT_City)).sendKeys(LOT_City);
	       Thread.sleep(2000);
	       System.out.println("LOT_City is entered "+LOT_City);

	       //Lot_Country
	       String Lot_Country = dataTable.getData("General_Data", "Lot_Country");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.CountryDropdownArrow)).click();
	       Thread.sleep(5000);
	       
	       List<WebElement> myElements2 = driver.findElements(By.xpath(Auction1.Country));
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
	       driver.findElement(By.xpath(Auction1.StateProvinceDropdownArrow)).click();
	       Thread.sleep(4000);
	       
	       List<WebElement> myElements3 = driver.findElements(By.xpath(Auction1.StateProvince));
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
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.LOT_PostalCode)).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.LOT_PostalCode)).sendKeys(Lot_PostalCode);
	       Thread.sleep(2000);
	       System.out.println("Lot_PostalCode is entered "+Lot_PostalCode);

	       //Phone_Country

	      
	       Thread.sleep(2000);
	       String Phone_Country = dataTable.getData("General_Data", "Phone_Country");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.Business_PhoneCountry)).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.Business_PhoneCountry)).sendKeys(Phone_Country);
	       Thread.sleep(2000);
	       System.out.println("Phone_Country is entered "+Phone_Country);

	       //Business_Phone

	       String Business_Phone = dataTable.getData("General_Data", "Business_Phone");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.Business_PhoneNumber)).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.Business_PhoneNumber)).sendKeys(Business_Phone);
	       Thread.sleep(2000);
	       System.out.println("Business_Phone is entered "+Business_Phone);
	       
	       //Fax Business Country
	       String FaxBusinessCountry = dataTable.getData("General_Data", "Fax Business Country");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath(Auction1.Fax_BusinessCountryDropdownArrow)).click();
	       Thread.sleep(4000);
	       
	       List<WebElement> myElements4 = driver.findElements(By.xpath(Auction1.Fax_BusinessCountry));
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
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.BusinessFax)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.BusinessFax)).sendKeys(BusinessFax);
		       Thread.sleep(2000);
		       System.out.println("BusinessFax is entered "+BusinessFax);

		       Thread.sleep(2000);
		       
		       //Select "Mailing Same As Lot" checkbox
		       driver.findElement(By.xpath(Auction1.MailingSameAsLot)).click();
		       Thread.sleep(2000);
		       
		       //Email
		       String ContactEmail = dataTable.getData("General_Data", "ContactEmail");
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.Contact_Email)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.Contact_Email)).sendKeys(ContactEmail);
		       Thread.sleep(2000);
	       
		     //URL
		       String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.WebsiteURL)).click();
		       Thread.sleep(2000);
		       driver.findElement(By.xpath(Auction1.WebsiteURL)).sendKeys(WebsiteURL);
		       Thread.sleep(2000);
	       
		       
	       report.updateTestLog("AA", "Data is entered under Contact window ", Status.PASS);
	       report.updateTestLog("Auction", "Data is entered under Contact window ", Status.SCREENSHOT);
	    	
	       
	      Thread.sleep(15000);
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.IdentificationNext)));
	      
	      
	      /* 
	      // Hitting Next Button
	      boolean Next=false;
	      while(Next==true) {
	      Next=driver.findElement(By.xpath(Auction.IdentificationNext)).isEnabled(); 
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
	      }*/
	      
	      
//	       WebDriverWait wait = new WebDriverWait(driver, 5000);
//	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.DealerOverride)));
//		   GF.waitForObject(By.xpath(Auction1.DealerOverride), "Override");
//		   Thread.sleep(10000);
			
			boolean override2=false;
			override2=driver.findElement(By.xpath(Auction1.DealerOverride)).isDisplayed();
			if(override2==true) {
			    report.updateTestLog("Auction", "Override button is arrived ", Status.SCREENSHOT);	
				driver.findElement(By.xpath(Auction1.DealerOverride)).click();
				report.updateTestLog("Auction", "Override button is Clicked ", Status.SCREENSHOT);
				System.out.println("Override is clicked");
				Thread.sleep(2000);
			}
		}
		catch(Exception e) {
			System.out.println("Error is: "+e);
		}
			
			

	    }
	
	

	public void createLicense_Auction() throws Exception {

		Thread.sleep(5000);		
		try {
			// Setting Dealer Subtypes 
	      
		   SetAttributeValue ("Id Number:","Issued Date:","Exp. Date:","Issuing Country:","Issuing State/Province:");    
	       JavascriptExecutor executor = (JavascriptExecutor)driver;
	       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.DealerCreateFinish)));
	       Thread.sleep(10000);
	       
	       WebDriverWait wait = new WebDriverWait(driver, 5000);
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction1.TextAfterDealerCreation)));
	       GF.waitForObject(By.xpath(Auction1.TextAfterDealerCreation), "Dealer");
		   Thread.sleep(5000);
			
		   ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.TextAfterDealerCreation)));
		   Thread.sleep(3000);
		   report.updateTestLog("AA", "5m Dealer is created successfully ", Status.PASS);
	      // report.updateTestLog("Auction", "Data entered under License window ", Status.SCREENSHOT);
		}catch(Exception e) {
			report.updateTestLog("AA", "5m Dealer is not created successfully ", Status.FAIL);
			System.out.println("Error is: "+e);
		}
   
		
	      
	    }
	
	
	// The key word validate the UI record with database
	public void dbVal_Update() throws Exception {
		
		String acctNum =driver.findElement(By.xpath(Auction1.TextAfterDealerCreation)).getText();
		 System.out.println(acctNum); 
		 acctNum=(acctNum.substring(7, 14));
		System.out.println(acctNum);
		
		// Preparing the connection string 
		Connection conn = null;
		Connection conn2 = null;
		Statement stmt = null;
		Statement stmt2 = null;
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
		
		// Running the SQL Query
		//String dlrAcct = dataTable.getData("General_Data", "ACCOUNT NUMBER");	
		//String sqlPassed="select X.ACCT_DESC,X.ACCT_NUM,X.ACCT_DBA_NM,X.COURIER_ACCT_NUM,X.COURIER_CD,Y.COMP_LEGAL_TYPE,Z.ACCT_IDENT_COUNTRY_CODE,Y.INS_COMP_NM,Y.BOND_COMP_NM,Y.INS_POLICY_NUM,TO_CHAR((Y.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT, BCL.LICENSE_NUM, BCL.LICENSE_EFFECTIVE_DT,BCL.LICENSE_EXPIRY_DT,BCL.LICENSE_TYPE,BCL.LICENSE_PROVINCE_CD,BCL.LICENSE_COUNTRY_CD from CMX_ORS.C_BO_ACCT X,CMX_ORS.C_BO_CUST_COMP_EXT Y,CMX_ORS.C_BO_ACCT_IDENT Z,CMX_ORS.C_BO_ACCT_LICENSE BCL where X.cust_id=Y.cust_id and X.ROWID_OBJECT=Z.acct_id and BCL.acct_id=X.rowid_object  and X.ACCT_NUM ="+"'"+acctNum+"'"; 
		
		String sqlPassed="Select a.ACCT_NUM,a.ACCT_DESC, a.ACCT_DBA_NM,a.COURIER_CD,c.LEGAL_NM,c.CUST_NM,b.ACCT_IDENT_COUNTRY_CODE, b.ACCT_IDENT_TYPE,b.ACCT_IDENT_NUM, d.COMP_LEGAL_TYPE, d.BOND_COMP_NM, d.BOND_NUM, TO_CHAR((d.BOND_EXPIRY_DT), 'MM-DD-YYYY') BOND_EXPIRY_DT , d.INS_COMP_NM, d.INS_POLICY_NUM, TO_CHAR((d.BUS_START_DT), 'MM-DD-YYYY') BUS_START_DT , TO_CHAR((d.INS_POLICY_EXPIRY_DT), 'MM-DD-YYYY') INS_POLICY_EXPIRY_DT, k.CUST_SPECIALTIES_TYPE, k.NEW_IND, l.ADDR_LINE_1 LOT_Addr1, l.ADDR_LINE_2 LOT_Addr2, l.CITY LOT_CITY, l.COUNTRY_CD LOT_COUNTRY, l.PROVINCE_CD LOT_Province, l.POSTAL_CD LOT_PostalCode, l.C_ADDR_LINE_1 Mail_Addr1, l.C_ADDR_LINE_2 Mail_Addr2, l.CLEAN_CITY Mail_City, l.C_COUNTRY_CD Mail_Country, l.CLEAN_PROVINCE Mail_Province, l.CLEAN_POSTAL_CD Mail_Postal, i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT Where a.ACCT_NUM="+"'"+acctNum+"'"+ "and rownum=1";
		
		 // Creating a list to export data in CSV
		 String[] filename =new String[] {"C:\\Users\\237574\\Desktop\\data.xls"};
		 String[] charSep =        new String[] {";"};
		 Boolean intestaFile = true ;
		 File newfilename = new File ("C:\\Users\\237574\\Desktop\\dataBefore.xls");
		 //HIGHLIGHTING THE FILELD
		 ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.DBA_Name_Input_Field)));
		 Thread.sleep(3000);	
		 driver.findElement(By.xpath(Auction1.DBA_Name_Input_Field)).click();
		 Thread.sleep(3000);
		 //Taking the value from UI
		 String DBANameGUI= driver.findElement(By.xpath(Auction1.DBA_Name_Input_Field)).getAttribute("value");
			System.out.println("DBA_Name from Clipboard:" + DBANameGUI); 
		    if(DBANameGUI.equalsIgnoreCase("")) {
		       DBANameGUI="null";
		    }	
			
		    Thread.sleep(2000);	
		    ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Legal_Name)));	
	        driver.findElement(By.xpath(Auction1.Legal_Name)).click();
	        Thread.sleep(2000);	
	        String Legal_Name= driver.findElement(By.xpath(Auction1.Legal_Name)).getAttribute("value");
	        if(Legal_Name.equalsIgnoreCase("")) {
	       	Legal_Name="null";
		    }
	      
	        System.out.println("Legal name : " + Legal_Name);
	        
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Courier_Account)));
	        Thread.sleep(3000);
	        driver.findElement(By.xpath(Auction1.Courier_Account)).click();
	        String Courier_Account= driver.findElement(By.xpath(Auction1.Courier_Account)).getAttribute("value");
	        if(Courier_Account.equalsIgnoreCase("")) {
	       	 Courier_Account="null";
	 	    }
	        System.out.println("Courier_Account : " + Courier_Account);
	        
	       //Federal_ID_Country
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Federal_ID_Country)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.Federal_ID_Country)).click();
	        String Federal_ID_Country= driver.findElement(By.xpath(Auction1.Federal_ID_Country)).getAttribute("value");
	        if(Federal_ID_Country.equalsIgnoreCase("")) {
	       	Federal_ID_Country="null";
	 	    }
	        
	       //Company_Type
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Company_Type)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.Company_Type)).click();
	        String Company_Type= driver.findElement(By.xpath(Auction1.Company_Type)).getAttribute("value");
	        System.out.println("Company_Type from Clipboard:" + Company_Type);
	        
	         if (Company_Type.equalsIgnoreCase("Business Corporation")) {
	       	 Company_Type="BUS";
	       	 System.out.println("Company_Type :" + Company_Type);
	        }
	         
	         if(Company_Type.equalsIgnoreCase("")) {
	       	  Company_Type="null";
	   	    }
	        
	        //Courier
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Courier_UI)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.Courier_UI)).click();
	        String Courier_UI= driver.findElement(By.xpath(Auction1.Courier_UI)).getAttribute("value");
	        if(Courier_UI.equalsIgnoreCase("")) {
	       	Courier_UI="null";
	  	    }
	        System.out.println("Courier UI " + Courier_UI);
	        
	        
	        //Federal_ID
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Courier_UI)));
	        Thread.sleep(2000);
	        
	        String Federal_ID= driver.findElement(By.xpath(Auction1.Federal_ID)).getAttribute("value");
	        if(Federal_ID.equalsIgnoreCase("")) {
	       	Federal_ID="null";
	   	    }
	        System.out.println("Federal_ID :" + Federal_ID);
	        
	      //Bond Company
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.bondingCompany)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.bondingCompany)).click();
	        String bondingCompany= driver.findElement(By.xpath(Auction1.bondingCompany)).getAttribute("value");
	        if(bondingCompany.equalsIgnoreCase("")) {
	       	bondingCompany="null";
	    	    }
	        System.out.println("Federal_ID :" + Federal_ID);
	        
	        //bond Number
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.bondNumber)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.bondNumber)).click();
	        String bondNumber= driver.findElement(By.xpath(Auction1.bondNumber)).getAttribute("value");
	        if(bondNumber.equalsIgnoreCase("")) {
	       	bondNumber="null";
	     	    }
	        System.out.println("bondNumber :" + bondNumber);
	        
	      //insuranceCompany name
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.insuranceCompany)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.insuranceCompany)).click();
	        String insuranceCompany= driver.findElement(By.xpath(Auction1.insuranceCompany)).getAttribute("value");
	        if(insuranceCompany.equalsIgnoreCase("")) {
	       	insuranceCompany="null";
	      	    }
	        System.out.println("insuranceCompany :" + insuranceCompany);
	        
	      //insurancePolicyNumber name
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.insurancePolicyNumber)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.insurancePolicyNumber)).click();
	        String insurancePolicyNumber= driver.findElement(By.xpath(Auction1.insurancePolicyNumber)).getAttribute("value");
	        System.out.println("insurancePolicyNumber :" + insurancePolicyNumber);
	        
	      //insurancePolicyExpirationDate
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.insurancePolicyExpirationDate)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.insurancePolicyExpirationDate)).click();
	        String insurancePolicyExpirationDate= driver.findElement(By.xpath(Auction1.insurancePolicyExpirationDate)).getAttribute("value");
	        System.out.println("insurancePolicyExpirationDate :" + insurancePolicyExpirationDate);
	        
	        if (insurancePolicyExpirationDate.equalsIgnoreCase("")) {
	       	 insurancePolicyExpirationDate="null";
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
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.bondExpirationDate)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.bondExpirationDate)).click();
	        String bondExpirationDate= driver.findElement(By.xpath(Auction1.bondExpirationDate)).getAttribute("value");
	        System.out.println("bondExpirationDate:" + bondExpirationDate);
	        
	        if (bondExpirationDate.equalsIgnoreCase("")) {
	       	 bondExpirationDate="null";
	       	 System.out.println("bondExpirationDate :" + bondExpirationDate);
	        }
	        else {
	        bondExpirationDate=bondExpirationDate.replace("/", "-");
			 System.out.println("bondExpirationDate:" + bondExpirationDate);
	        }
	        
	        
	        //Business Start Date
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction1.Business_Start_UI)));
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(Auction1.Business_Start_UI)).click();
	        String Business_Start_UI= driver.findElement(By.xpath(Auction1.Business_Start_UI)).getAttribute("value");
	        System.out.println("Courier UI from Clipboard:" + Business_Start_UI);
	        
	        if (Business_Start_UI.equalsIgnoreCase("")) {
	       	
	       	Business_Start_UI="null";
	       	 System.out.println("Courier_UI from Clipboard:" + Courier_UI);
	        }else {
	       	 Business_Start_UI=Business_Start_UI.replace("/", "-");
	   		 System.out.println("BUS_Start_Date:" + Business_Start_UI);
	        }
	        
	     
			 // Going to the License Tab and clicking the Link
			 driver.findElement(By.xpath(Auction1.Licences_Link)).click();
			 Thread.sleep(8000);
			 // License type 
			 String License_Type =driver.findElement(By.xpath(Auction1.License_Type_TXT)).getText();
			 
			 if(License_Type.equalsIgnoreCase("Dealer Master Tag")){
				 License_Type="LEGALDLR_LIC_MSTR_TAG";
			 }else if(License_Type.equalsIgnoreCase("Dealer License")) {
				 License_Type="LEGALDLR_LIC";
			 }
			 String License_Number =driver.findElement(By.xpath(Auction1.License_Number_TXT)).getText();
			 Thread.sleep(1000);
			 String License_State =driver.findElement(By.xpath(Auction1.License_State_TXT)).getText();
			 Thread.sleep(1000);
			 if(License_State.equalsIgnoreCase("New York")){
				 License_State="USA-NY";
			 }
			 if (License_State.equalsIgnoreCase("Georgia")){
				 License_State="USA-GA";
			 }
			 String License_Country =driver.findElement(By.xpath(Auction1.License_Country_TXT)).getText();
	        
			 Thread.sleep(1000);
			 
			//fetch Business type data from UI
			 driver.findElement(By.xpath(Auction1.BusinessTypes)).click();
			 Thread.sleep(5000);
			 String DealerType_underBusinessType =driver.findElement(By.xpath(Auction1.DealerType_underBusinessType)).getText();
			 System.out.println("DealerType_underBusinessType:" + DealerType_underBusinessType);
			 
			 if(DealerType_underBusinessType.equalsIgnoreCase("Automobile")) {
				DealerType_underBusinessType="AUT";
			 }
			 driver.findElement(By.xpath(Auction1.DealerType_EditIcon)).click();
			 Thread.sleep(4000);
			 
			 String Types=driver.findElement(By.xpath(Auction1.Types_Under_EditBusinessTypeWindow)).getText();
			 if(Types.equalsIgnoreCase("New")) {
				 Types="Y";
			 }else {
				 Types="N";
			 }
			 
			 driver.findElement(By.xpath(Auction1.CancelButton_Under_EditType_Window)).click();
			 Thread.sleep(2000);
			 //fetch Address fields data from UI 
			 
			 driver.findElement(By.xpath(Auction1.Addresses_Link)).click();
			 Thread.sleep(5000);
			 String Addressline1 =driver.findElement(By.xpath(Auction1.Addressline1_TXT)).getText();
			 System.out.println("Addressline1:" + Addressline1);
			 Thread.sleep(500);
			 String Addressline2 =driver.findElement(By.xpath(Auction1.Addressline2_TXT)).getText();      
			 System.out.println("Addressline2:" + Addressline2);
			 Thread.sleep(500);
			 String AddressesCity =driver.findElement(By.xpath(Auction1.AddressesCity_TXT)).getText();     
			 System.out.println("AddressesCity:" + AddressesCity);
			 Thread.sleep(500);
			 String StateProvince =driver.findElement(By.xpath(Auction1.StateProvince_TXT)).getText();     
			 System.out.println("StateProvince:" + StateProvince);
			 if(StateProvince.equalsIgnoreCase("New York")) {
				StateProvince="USA-NY";
			 }
			 Thread.sleep(500);
			 String AddressPostalCode =driver.findElement(By.xpath(Auction1.PostalCode_TXT)).getText();  
			 System.out.println("AddressPostalCode:" + AddressPostalCode);
			 Thread.sleep(500);
			 String AddressCountry =driver.findElement(By.xpath(Auction1.Country_TXT)).getText();
			 System.out.println("AddressCountry:" + AddressCountry);
			 
			 //Mailing Address field's data fetched from UI
			 String MailAddressline1_TXT =driver.findElement(By.xpath(Auction1.MailAddressline1_TXT)).getText();
			 System.out.println("MailAddressline1_TXT :" + MailAddressline1_TXT);
			 Thread.sleep(500);
			 String MailAddressline2_TXT =driver.findElement(By.xpath(Auction1.MailAddressline2_TXT)).getText();      
			 System.out.println("MailAddressline2_TXT :" + MailAddressline2_TXT);
			 Thread.sleep(500);
			 String MailAddressesCity_TXT =driver.findElement(By.xpath(Auction1.MailAddressesCity_TXT)).getText();     
			 System.out.println("MailAddressesCity_TXT :" + MailAddressesCity_TXT);
			 Thread.sleep(500);
			 String MailStateProvince_TXT =driver.findElement(By.xpath(Auction1.MailStateProvince_TXT)).getText();     
			 System.out.println("MailStateProvince_TXT :" + MailStateProvince_TXT);
			 if(MailStateProvince_TXT.equalsIgnoreCase("New York")){
				 MailStateProvince_TXT="USA-NY";
			 }
			 Thread.sleep(500);
			 String MailPostalCode_TXT =driver.findElement(By.xpath(Auction1.MailPostalCode_TXT)).getText();  
			 System.out.println("MailPostalCode_TXT :" + MailPostalCode_TXT);
			 Thread.sleep(500);
			 String MailCountry_TXT =driver.findElement(By.xpath(Auction1.MailCountry_TXT)).getText();
			 System.out.println("MailCountry_TXT :" + MailCountry_TXT);
		
	        
		 try
			{
				//estabilish connection
					
			 	Class.forName(driverName);
				conn=DriverManager.getConnection(connectionUrl,userName,password);
				conn2=DriverManager.getConnection(connectionUrl,userName,password);
				System.out.println("Connection estabilished with sql server");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Connection is not build successfully");
				
			}	
		 
		 
		 	stmt=conn.createStatement();
		 	stmt2=conn2.createStatement();
			
		 	ResultSet dataSet=stmt.executeQuery(sqlPassed);
		 	ResultSet dataSet2=stmt2.executeQuery(sqlPassed);
					 
					 //code to export data in csv
					 System.out.println("---------------File exist?------------" + filename[0]);
					    File fileTemp2 = new File(filename[0].toString());
					    if (fileTemp2.exists()){ 
					        fileTemp2.renameTo(newfilename);
					        System.out.println("---------------RENAME FILE------------" + filename[0] );
					                }
					    ExportData2CSVXL csv2 = new ExportData2CSVXL();
					     csv2.ExportData2CSVXL(dataSet2,filename[0],intestaFile,charSep[0]);
				         csv2.createFileCsvXL();
				         stmt2.close();
				         conn2.close();
				         
	
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
						
					
						DBA_Name=dataSet.getString("ACCT_DESC");
						ACCT_NUMBER=dataSet.getString("ACCT_NUM");
						ACCT_DBA= dataSet.getString("ACCT_DBA_NM");
						//COURIER_NUM = dataSet.getString("COURIER_ACCT_NUM");
						COMP_LEGAL_TYPE = dataSet.getString("COMP_LEGAL_TYPE");
						System.out.println("Full Name is: "+DBA_Name  +ACCT_NUMBER);
						System.out.println("COMP_LEGAL_TYPE is: "+COMP_LEGAL_TYPE);
						
						//Federal ID Country
						//ACCT_IDENT_COUNTRY_CODE=dataSet.getString("ACCT_IDENT_COUNTRY_CODE");
						//System.out.println("ACCT_IDENT_COUNTRY_CODE is: "+ACCT_IDENT_COUNTRY_CODE);
						
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
						System.out.println("LICENSE_NUM is: "+LICENSE_NUM);
						//Account Description
						 if (DBANameGUI.equalsIgnoreCase(ACCT_DESC) || (DBANameGUI.equalsIgnoreCase("") && ACCT_DESC==null))
							{
								
							 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
							 report.updateTestLog("AA", "Dealer ACCT DESC matched with OVC database value :"+"DB Value: "+ACCT_DESC+"="+" UI Value: "+DBANameGUI, Status.PASS);
							}
							
							else 
							{
								report.updateTestLog("AA", "Dealer ACCT DESC not matched with OVC database"+"DB Value: "+ACCT_DESC+"<>"+" UI Value: "+DBANameGUI, Status.FAIL);
								
							}
						 //Click on Contact Option link
						 
						 
						//Account Number checking
						 
						 if (acctNum.equalsIgnoreCase(ACCT_NUMBER) || (acctNum.equalsIgnoreCase("") && ACCT_NUMBER==null))
							{
								
							 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
							 report.updateTestLog("AA", "Dealer ACCT NUMBER matched with OVC database value :"+"DB Value: "+ACCT_NUMBER+"="+" UI Value: "+acctNum, Status.PASS);
							}
							
							else 
							{
								report.updateTestLog("AA", "Dealer DBA Name not matched with OVC database"+"DB Value: "+ACCT_NUMBER+"<>"+" UI Value: "+acctNum, Status.FAIL);
								
							}
						 
						
						// Checking UI DBA Name and Database DBA value
					 if (DBANameGUI.equalsIgnoreCase(ACCT_DBA) || (DBANameGUI.equalsIgnoreCase("") && ACCT_DBA==null))
						{
							
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer DBA Name matched with OVC database value :"+"DB Value: "+ACCT_DBA+"="+" UI Value: "+DBANameGUI, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA", "Dealer DBA Name not matched with OVC database"+"DB Value: "+ACCT_DBA+"<>"+"UI Value: "+DBANameGUI, Status.FAIL);
							
						}
					 
					  
					 // Checking UI legal name and Database legal name 
					 if (Legal_Name.equalsIgnoreCase(LEGAL_NM) || (Legal_Name.equalsIgnoreCase("") && LEGAL_NM==null))
						{
							
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer legal name matched with OVC database"+"DB Value:" +Legal_Name + "="+"UI Value:"+LEGAL_NM, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA", "Dealer account number not matched with OVC database"+"DB Value:" +Legal_Name + "<>"+"UI Value:"+LEGAL_NM, Status.FAIL);
							
						}
					 
					 		// Checking UI Company Type and database COMPANY LEGAL TYPE 
							 if (Company_Type.equalsIgnoreCase(COMP_LEGAL_TYPE) || (Company_Type.equalsIgnoreCase("") && COMP_LEGAL_TYPE==null))
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
					 if (Federal_ID_Country.equalsIgnoreCase(ACCT_IDENT_COUNTRY_CODE) || (Federal_ID_Country.equalsIgnoreCase("") && ACCT_IDENT_COUNTRY_CODE==null))
						{
							
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer Federal ID Country matched with OVC database"+"DB Value:" +ACCT_IDENT_COUNTRY_CODE + "="+"UI Value:"+Federal_ID_Country, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA","Dealer Federal ID Country not matched with OVC database"+"DB Value:" +ACCT_IDENT_COUNTRY_CODE + "<>"+"UI Value:"+Federal_ID_Country, Status.FAIL);
							
						}
					 
					 //bondingCompany
					 if (bondingCompany.equalsIgnoreCase(BOND_COMP_NM) || (Federal_ID_Country.equalsIgnoreCase("") && ACCT_IDENT_COUNTRY_CODE==null))
						{
							
						 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database"+"DB Value:" +BOND_COMP_NM + "="+"UI Value :"+bondingCompany, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA","Dealer Federal ID Country not matched with OVC database"+"DB Value:" +BOND_COMP_NM + "<>"+"UI Value :"+bondingCompany, Status.FAIL);
							
						}
					 
					//bondNumber
					 if (bondNumber.equalsIgnoreCase(BOND_NUM) || (BOND_NUM.equalsIgnoreCase("") && BOND_NUM==null))
						{
							
						 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", " Bond number matched with OVC database"+"DB Value:" +BOND_NUM + "="+"UI Value :"+bondNumber, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA"," BOND Number not matched with OVC database"+"DB Value:" +BOND_NUM + "<>"+"UI Value :"+bondNumber, Status.FAIL);
							
						}
					 
					 
					 //BOND_EXPIRY_DT
					 if (bondExpirationDate.equalsIgnoreCase(BOND_EXPIRY_DT) || (bondExpirationDate.equalsIgnoreCase("") && BOND_EXPIRY_DT==null))
						{
							
						 //report.updateTestLog("AA", "Dealer Bond Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", " bond Expiration Date matched with OVC database"+"DB Value :" +BOND_EXPIRY_DT + "="+"UI Value :"+bondExpirationDate, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA"," bond Expiration Date not matched with OVC database"+"DB Value :" +BOND_EXPIRY_DT + "<>"+"UI Value :"+bondExpirationDate, Status.FAIL);
							
						}
					 
					//INS_COMP_NM
					 if (insuranceCompany.equalsIgnoreCase(INS_COMP_NM) || (insuranceCompany.equalsIgnoreCase("") && INS_COMP_NM==null))
						{
							
						 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", " insurance Company name matched with OVC database"+"DB Value :" +INS_COMP_NM + "="+"UI Value :"+insuranceCompany, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_COMP_NM + "<>"+"UI Value :"+insuranceCompany, Status.FAIL);
							
						}
					 
					//INS_COMP_NM
					 if (insurancePolicyNumber.equalsIgnoreCase(INS_POLICY_NUM) || (insurancePolicyNumber.equalsIgnoreCase("") && INS_POLICY_NUM==null))
						{
							
						 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", " INSURANCE POLICY NUMBER matched with OVC database"+"DB Value :" +INS_POLICY_NUM + "="+"UI Value :"+insurancePolicyNumber, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_COMP_NM + "<>"+"UI Value :"+insurancePolicyNumber, Status.FAIL);
							
						}
					 
					//INS_POLICY_EXPIRY_DT
					 if (insurancePolicyExpirationDate.equalsIgnoreCase(INS_POLICY_EXPIRY_DT) || (insurancePolicyExpirationDate.equalsIgnoreCase("") && INS_POLICY_EXPIRY_DT==null))
						{
							
						 //report.updateTestLog("AA", "insurance Company name matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", " insurance Policy Expiration Date matched with OVC database"+"DB Value :" +INS_POLICY_EXPIRY_DT + "="+"UI Value :"+insurancePolicyExpirationDate, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA"," insurance Company name not matched with OVC database"+"DB Value :" +INS_POLICY_EXPIRY_DT + "<>"+"UI Value :"+insurancePolicyExpirationDate, Status.FAIL);
							
						}
					 
					 
					 // Checking UI Courier and database Courier_cd 
					 if (Courier_UI.equalsIgnoreCase(COURIER_CD) || (Courier_UI.equalsIgnoreCase("") && COURIER_CD==null))
						{
							
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer Courier matched with OVC database"+"DB Value:" +COURIER_CD + "="+"UI Value:"+Courier_UI, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA","Dealer Courier not matched with OVC database"+"DB Value:" +COURIER_CD + "<>"+"UI Value:"+Courier_UI, Status.FAIL);
							
						}
					 
					 // Checking UI Business Start date 
					 if (Courier_UI.equalsIgnoreCase(COURIER_CD) || (Courier_UI.equalsIgnoreCase("") && COURIER_CD==null))
						{
						 
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer Business Start Date matched with OVC database"+"DB Value:" +BUS_START_DT + "="+"UI Value:"+Business_Start_UI, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA","Dealer Business Start Date not matched with OVC database"+"DB Value:" +BUS_START_DT + "<>"+"UI Value:"+Business_Start_UI, Status.FAIL);
							
						}
					 
					 // Checking UI LICENSE_TYPE  
					 if (License_Type.equalsIgnoreCase(LICENSE_TYPE) || (License_Type.equalsIgnoreCase("") && LICENSE_TYPE==null))
						{
							
						 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
						 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_TYPE + "="+"UI Value:"+License_Type, Status.PASS);
						}
						
						else 
						{
							report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_TYPE + "<>"+"UI Value:"+License_Type, Status.FAIL);
							
						}

					 		// Checking UI License_Number 
							 if (License_Number.equalsIgnoreCase(LICENSE_NUM) || (License_Number.equalsIgnoreCase("") && LICENSE_NUM==null))
								{
									
								 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
								 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_TYPE + "="+"UI Value:"+License_Type, Status.PASS);
								}
								
								else 
								{
									report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_TYPE + "<>"+"UI Value:"+License_Type, Status.FAIL);
									
								}
							// Checking UI License_State 
							 if (License_State.equalsIgnoreCase(LICENSE_PROVINCE_CD) || (License_State.equalsIgnoreCase("") && LICENSE_PROVINCE_CD==null))
								{
									
								 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
								 report.updateTestLog("AA", "Dealer License State matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD + "="+"UI Value:"+License_State, Status.PASS);
								}
								
								else 
								{
									report.updateTestLog("AA","Dealer License State not matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD + "<>"+"UI Value:"+License_State, Status.FAIL);
									
								}
							 
							// Checking UI License_Country  and database BUS_START_DT 
							 if (License_Country.equalsIgnoreCase(LICENSE_COUNTRY_CD) || (License_Country.equalsIgnoreCase("") && LICENSE_COUNTRY_CD==null))
								{
									
								 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
								 report.updateTestLog("AA", "Dealer License Country  matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD + "="+"UI Value:"+License_Country, Status.PASS);
								}
								
								else 
								{
									report.updateTestLog("AA","Dealer License Country not matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD + "<>"+"UI Value:"+License_Country, Status.FAIL);
									
								}
					 
							// Checking UI License_Country  and database BUS_START_DT 
							 if (DealerType_underBusinessType.equalsIgnoreCase(CUST_SPECIALTIES_TYPE) || (DealerType_underBusinessType.equalsIgnoreCase("") && CUST_SPECIALTIES_TYPE==null))
								{
									
								 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
								 report.updateTestLog("AA", "Dealer Type under BusinessType  matched with OVC database"+"DB Value:" +CUST_SPECIALTIES_TYPE + "="+"UI Value:"+DealerType_underBusinessType, Status.PASS);
								}
								
								else 
								{
									report.updateTestLog("AA","Dealer Type under Business Type not matched with OVC database"+"DB Value:" +CUST_SPECIALTIES_TYPE + "<>"+"UI Value:"+DealerType_underBusinessType, Status.FAIL);
									
								}
							 
							// Checking UI License_Country  and database BUS_START_DT 
							 if (Types.equalsIgnoreCase(NEW_IND) || (Types.equalsIgnoreCase("") && NEW_IND==null))
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
			                if (Addressline1.equalsIgnoreCase(LOT_Addr1) || (Addressline1.equalsIgnoreCase("") && LOT_Addr1==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer Addressline1 matched with OVC database"+"DB Value:" +LOT_Addr1 + "="+"UI Value:"+Addressline1, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer Addressline1 not matched with OVC database"+"DB Value:" +LOT_Addr1 + "<>"+"UI Value:"+Addressline1, Status.FAIL);
			                             
			                      } 
			                
			               // Checking UI Addressline2 with database  
			                if (Addressline2.equalsIgnoreCase(LOT_Addr2) || (Addressline2.equalsIgnoreCase("") && LOT_Addr2==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer Addressline2 matched with OVC database"+"DB Value:" +LOT_Addr2 + "="+"UI Value:"+Addressline2, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer Addressline2 not matched with OVC database"+"DB Value:" +LOT_Addr2 + "<>"+"UI Value:"+Addressline2, Status.FAIL);
			                             
			                      } 

			                
			               // Checking UI AddressesCity with database  
			                if (AddressesCity.equalsIgnoreCase(LOT_CITY) || (AddressesCity.equalsIgnoreCase("") && LOT_CITY==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer LOT CITY matched with OVC database"+"DB Value:" +LOT_CITY + "="+"UI Value:"+AddressesCity, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer LOT CITY not matched with OVC database"+"DB Value:" +LOT_CITY + "<>"+"UI Value:"+AddressesCity, Status.FAIL);
			                             
			                      } 
			                //
			               
			               // Checking UI StateProvince with database  
			                if (StateProvince.equalsIgnoreCase(LOT_Province) || (StateProvince.equalsIgnoreCase("") && LOT_Province==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer LOT Province matched with OVC database"+"DB Value:" +LOT_Province + "="+"UI Value:"+StateProvince, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer LOT Province not matched with OVC database"+"DB Value:" +LOT_Province + "<>"+"UI Value:"+StateProvince, Status.FAIL);
			                             
			                      }  
			                
			                
			               // Checking UI PostalCode with database
			               
			                if (AddressPostalCode.equalsIgnoreCase(LOT_PostalCode) || (AddressPostalCode.equalsIgnoreCase("") && LOT_PostalCode==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer LOT PostalCode matched with OVC database"+"DB Value:" +LOT_PostalCode + "="+"UI Value:"+AddressPostalCode, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer LOT PostalCode not matched with OVC database"+"DB Value:" +LOT_PostalCode + "<>"+"UI Value:"+AddressPostalCode, Status.FAIL);
			                             
			                      } 
			                
			                //Checking UI Country with database
			               
			                if (AddressCountry.equalsIgnoreCase(LOT_COUNTRY) || (AddressCountry.equalsIgnoreCase("") && LOT_COUNTRY==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer LOT COUNTRY matched with OVC database"+"DB Value:" +LOT_COUNTRY + "="+"UI Value:"+AddressCountry, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer LOT COUNTRY not matched with OVC database"+"DB Value:" +LOT_COUNTRY + "<>"+"UI Value:"+AddressCountry, Status.FAIL);
			                             
			                      } 
			                
			                
			                //Mailing Address field validation
			                				
			                // Checking UI Mail_Addr1 with database  
			                if (MailAddressline1_TXT.equalsIgnoreCase(Mail_Addr1) || (MailAddressline1_TXT.equalsIgnoreCase("") && Mail_Addr1==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer Mailing Addressline1 matched with OVC database"+"DB Value:" +Mail_Addr1 + "="+"UI Value :"+MailAddressline1_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer Mailing Addressline1 not matched with OVC database"+"DB Value:" +Mail_Addr1 + "<>"+"UI Value :"+MailAddressline1_TXT, Status.FAIL);
			                             
			                      } 
			                
			               // Checking UI Addressline2 with database  
			                if (MailAddressline2_TXT.equalsIgnoreCase(Mail_Addr2) || (MailAddressline2_TXT.equalsIgnoreCase("") && Mail_Addr2==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer Mailing Addressline2 matched with OVC database"+"DB Value:" +Mail_Addr2 + "="+"UI Value :"+MailAddressline2_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer Addressline2 not matched with OVC database"+"DB Value:" +Mail_Addr2 + "<>"+"UI Value :"+MailAddressline2_TXT, Status.FAIL);
			                             
			                      } 

			                
			               // Checking UI mailing AddressesCity with database  
			                if (MailAddressesCity_TXT.equalsIgnoreCase(Mail_CITY) || (MailAddressesCity_TXT.equalsIgnoreCase("") && Mail_CITY==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer MAILING CITY matched with OVC database"+"DB Value:" +Mail_CITY + "="+"UI Value :"+MailAddressesCity_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer MAILING CITY not matched with OVC database"+"DB Value:" +Mail_CITY + "<>"+"UI Value :"+MailAddressesCity_TXT, Status.FAIL);
			                             
			                      } 
			                //
			               
			               // Checking UI mailing StateProvince with database  
			                if (MailStateProvince_TXT.equalsIgnoreCase(Mail_Province) || (MailStateProvince_TXT.equalsIgnoreCase("") && Mail_Province==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer mailing Province matched with OVC database"+"DB Value:" +Mail_Province + "="+"UI Value :"+MailStateProvince_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer mailing Province not matched with OVC database"+"DB Value:" +Mail_Province + "<>"+"UI Value :"+MailStateProvince_TXT, Status.FAIL);
			                             
			                      }  
			                
			                
			               // Checking UI PostalCode with database
			               
			                if (MailPostalCode_TXT.equalsIgnoreCase(Mail_PostalCode) || (MailPostalCode_TXT.equalsIgnoreCase("") && Mail_PostalCode==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer mailing PostalCode matched with OVC database"+"DB Value :" +Mail_PostalCode + "="+"UI Value :"+MailPostalCode_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer mailing PostalCode not matched with OVC database"+"DB Value :" +Mail_PostalCode + "<>"+"UI Value :"+MailPostalCode_TXT, Status.FAIL);
			                             
			                      } 
			                
			                //Checking UI Country with database
			               
			                if (MailCountry_TXT.equalsIgnoreCase(Mail_COUNTRY) || (MailCountry_TXT.equalsIgnoreCase("") && Mail_COUNTRY==null))
			                      {
			                             
			                      //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
			                      report.updateTestLog("AA", "Dealer mailing COUNTRY matched with OVC database"+"DB Value :" +Mail_COUNTRY + "="+"UI Value :"+MailCountry_TXT, Status.PASS);
			                      }
			                      
			                      else 
			                      {
			                             report.updateTestLog("AA","Dealer mailing COUNTRY not matched with OVC database"+"DB Value :" +Mail_COUNTRY + "<>"+"UI Value :"+MailCountry_TXT, Status.FAIL);
			                             
			                      } 
			                Thread.sleep(3000);
			                driver.findElement(By.xpath(Auction1.ContactOptionsLink)).click();
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

						
					}
				
					
				
			}
			
			
	

		public  String extractData(String sql,String colm){
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
					colm=dataSet.getString(1);
					System.out.println("Full Name is: "+colm);				
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
		return colm;
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
        }else {
                        report.updateTestLog("IDD", "Account Number not opened successfully ", Status.SCREENSHOT);
                        System.out.println("Account Number not opened successfully."+ACCOUNT_NUMBER);
        }
        
}

    public void logoutAA() throws Exception {
    	
    	driver.quit();
    }
	
   public void comCreate() throws Exception 
   	{
    try {
    	// entering insurance company 
    	String insuranceCompany = dataTable.getData("General_Data", "insuranceCompany");
    	Thread.sleep(2000);
    	driver.findElement(By.xpath(Auction1.insuranceCompany)).click();
    	driver.findElement(By.xpath(Auction1.insuranceCompany)).clear();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath(Auction1.insuranceCompany)).sendKeys(insuranceCompany);
    	Thread.sleep(2000);
    	// entering insurance expiration date 
    	String PolicyExpirationDate = dataTable.getData("General_Data", "PolicyExpirationDate");
    	Thread.sleep(2000);
    	driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).click();
    	driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).clear();
    	 Thread.sleep(2000);
    	driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).sendKeys(PolicyExpirationDate);
    	 Thread.sleep(2000);
        //// entering bond Expiration Date 
    	String bondExpirationDate = dataTable.getData("General_Data", "bondExpirationDate");
    	driver.findElement(By.xpath(Auction1.bondExpirationDate)).click();
    	driver.findElement(By.xpath(Auction1.bondExpirationDate)).clear();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath(Auction1.bondExpirationDate)).sendKeys(bondExpirationDate);
    	Thread.sleep(2000);
    	//saving the data 
    	driver.findElement(By.xpath(Auction1.Save)).click();
    	Thread.sleep(10000);
    	report.updateTestLog("AA", "Bond and Insurance details entered  ", Status.PASS);
    	report.updateTestLog("AA", "Bond and Insurance details entered  ", Status.SCREENSHOT);
    	
        }	
    	catch(Exception e) 
       {
    		report.updateTestLog("AA", "Bond and Insurance details not entered  ", Status.FAIL);
    		report.updateTestLog("AA", "Bond and Insurance details entered  ", Status.SCREENSHOT);
    		
    	}
    } 

   public void comIdentityUpdate() throws Exception {
	   String CompanyType= dataTable.getData("General_Data", "Company Type"); 
	   String DBAName= dataTable.getData("General_Data", "DBA Name");
       String LegalName= dataTable.getData("General_Data", "Legal Name");
       String FederalIdCountry= dataTable.getData("General_Data", "Update_Federal Id Country"); 
       String FederalIdType= dataTable.getData("General_Data", "Update_Federal Id Type"); 
       String BondCompany= dataTable.getData("General_Data", "Bond Company"); 
       String BondNumber= dataTable.getData("General_Data", "Bond Number"); 
	   
	   try {
		   
//		   String acctNum = "5430531";
//		   driver.findElement(By.xpath(Auction.BTN_DEALER)).click();
//           Thread.sleep(3000);
//           System.out.println("Clicked on Dealer");
//           driver.findElement(By.xpath(Auction.BTN_SEARCH)).click();
//           Thread.sleep(5000);      
//           driver.findElement(By.xpath(Auction.Search_ID)).sendKeys(acctNum);
//           driver.findElement(By.xpath(Auction.BTN_DLRSRCH)).click();
//           Thread.sleep(10000);
		   
           // changing DBA name 
           if(driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).isDisplayed()) {
        	   driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).click();
        	   DBAName = ranDom(DBAName);
        	   driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).clear();
        	   driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).sendKeys(DBAName);
        	   report.updateTestLog("AA", "DBA Value is changed ", Status.PASS);
           } else {
        	   report.updateTestLog("AA", "DBA Value is not changed", Status.FAIL);
           }
           // changing legal name   
           if(driver.findElement(By.xpath(Auction1.Identification_DBA_Name)).isDisplayed()) {
           LegalName = ranDom(LegalName);
    	   driver.findElement(By.xpath(Auction1.Identification_LegalName)).clear();
    	   driver.findElement(By.xpath(Auction1.Identification_LegalName)).sendKeys(LegalName);
    	   report.updateTestLog("AA", "LegalName Value is changed ", Status.PASS);
       } else {
    	   report.updateTestLog("AA", "LegalName Value is not changed", Status.FAIL);
           report.updateTestLog("Auction", "LegalName Value is not changed", Status.SCREENSHOT);
       } 
             
	    	// Changing  company type
           if (driver.findElement(By.xpath(Auction1.companyType)).isDisplayed()){
           
        	   driver.findElement(By.xpath(Auction1.companyType)).clear();
    		   driver.findElement(By.xpath(Auction1.companyType)).sendKeys(CompanyType);
    		   report.updateTestLog("AA", "Company Value is  changed", Status.PASS);
    		   
           }else {
        	   report.updateTestLog("AA", "Company Value is not changed", Status.FAIL);
               report.updateTestLog("Auction", "LegalName Value is not changed", Status.SCREENSHOT);
           } 
           
		  //Federal Country
           if (driver.findElement(By.xpath(Auction1.FederalIdCountry_dropdown_Update)).isEnabled()){
           driver.findElement(By.xpath(Auction1.FederalIdCountry_dropdown_Update)).click();
		   Thread.sleep(5000);
			
			List<WebElement> myElements = driver.findElements(By.xpath(Auction1.Federal_Id_Country_WebElements));
			for(WebElement x : myElements) {
			  String Comp=(x.getText());
			  System.out.println("Company is :"+Comp);
			  if(FederalIdCountry.equalsIgnoreCase(Comp)) {
				  x.click();
				  Thread.sleep(5000);
				  System.out.println("Federal Id Country is selected :"+x);
				  break;
			  }
			  
			}
			 report.updateTestLog("AA", "Federal CountryValue is  changed", Status.PASS);
           }
           else 
           {
        	   report.updateTestLog("AA", "Federal CountryValue is not changed", Status.FAIL);  
        	   
           }
           
           // ********************Federal id to be updated
			// Federal ID Type 
           
//           if (driver.findElement(By.xpath(Auction.Federal_Id_Type)).isEnabled()){
//        	   driver.findElement(By.xpath(Auction.Federal_Id_Type)).clear();
//        	   driver.findElement(By.xpath(Auction.Federal_Id_Type)).click();
//    		   driver.findElement(By.xpath(Auction.Federal_Id_Type)).sendKeys(FederalIdType);
//    		   report.updateTestLog("AA", "Federal_Id_Type Value is  changed", Status.PASS);
//    		   
//           }else {
//        	   report.updateTestLog("AA", "Federal_Id_Type Value is not changed", Status.FAIL);
//               report.updateTestLog("Auction", "Federal_Id_Type Value is not changed", Status.SCREENSHOT);
   //        } 
           //Bond Company
            if (driver.findElement(By.xpath(Auction1.BondCompany)).isEnabled()){
        	   driver.findElement(By.xpath(Auction1.BondCompany)).clear();
        	   BondCompany = ranDom(BondCompany);
    		   driver.findElement(By.xpath(Auction1.BondCompany)).sendKeys(BondCompany);
    		   report.updateTestLog("AA", "BondCompany Value is  changed", Status.PASS);
    		   
           }else {
        	   report.updateTestLog("AA", "BondCompany Value is not changed", Status.FAIL);
               report.updateTestLog("Auction", "BondCompany Value is not changed", Status.SCREENSHOT);
           } 
          //BondNumber
            if (driver.findElement(By.xpath(Auction1.bondNumber)).isDisplayed()){
         	   driver.findElement(By.xpath(Auction1.bondNumber)).clear();
         	  BondNumber = ranDom(BondNumber);
     		   driver.findElement(By.xpath(Auction1.bondNumber)).sendKeys(BondNumber);
     		   report.updateTestLog("AA", "BondNumber Value is  changed", Status.PASS);
     		   
            }else {
         	   report.updateTestLog("AA", "BondNumber Value is not changed", Status.FAIL);
                report.updateTestLog("Auction", "BondNumber Value is not changed", Status.SCREENSHOT);
            }
			
	        	
	   
		// Changing insurance company 
            String insuranceCompany = dataTable.getData("General_Data", "insuranceCompany");
            insuranceCompany = ranDom(insuranceCompany);
            if (driver.findElement(By.xpath(Auction1.insuranceCompany)).isDisplayed()){
          	   driver.findElement(By.xpath(Auction1.insuranceCompany)).clear();
      		   driver.findElement(By.xpath(Auction1.insuranceCompany)).sendKeys(insuranceCompany);
      		   report.updateTestLog("AA", "insuranceCompany Value is changed", Status.PASS);
      		   
             }else {
          	   report.updateTestLog("AA", "PolicyExpirationDate Value is not changed", Status.FAIL);
                 report.updateTestLog("Auction", "PolicyExpirationDate Value is not changed", Status.SCREENSHOT);
             }
		   	// Changing insurance expiration date 
		   	String PolicyExpirationDate = dataTable.getData("General_Data", "PolicyExpirationDate");
            if (driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).isDisplayed()){
          	   driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).clear();
      		   driver.findElement(By.xpath(Auction1.PolicyExpirationDate)).sendKeys(PolicyExpirationDate);
      		   report.updateTestLog("AA", "PolicyExpirationDate Value is  changed", Status.PASS);
      		   
             }else {
          	   report.updateTestLog("AA", "PolicyExpirationDate Value is not changed", Status.FAIL);
                 report.updateTestLog("Auction", "PolicyExpirationDate Value is not changed", Status.SCREENSHOT);
             }
		   	
		       // Changing bond Expiration Date 
        	String bondExpirationDate = dataTable.getData("General_Data", "PolicyExpirationDate");
            if (driver.findElement(By.xpath(Auction1.bondExpirationDate)).isDisplayed()){
          	   driver.findElement(By.xpath(Auction1.bondExpirationDate)).clear();
      		   driver.findElement(By.xpath(Auction1.bondExpirationDate)).sendKeys(bondExpirationDate);
      		   report.updateTestLog("AA", "bondExpirationDate Value is  changed", Status.PASS);
      		   
             }else {
          	   report.updateTestLog("AA", "bondExpirationDate Value is not changed", Status.FAIL);
                 report.updateTestLog("Auction", "bondExpirationDate Value is not changed", Status.SCREENSHOT);
             }
            
          //saving the data 
            if (driver.findElement(By.xpath(Auction1.Save)).isDisplayed()){
           	   driver.findElement(By.xpath(Auction1.Save)).click();
       		   Thread.sleep(10000);
       		   report.updateTestLog("AA", "Save Value button Hit", Status.PASS);
       		   report.updateTestLog("AA", "All data Saved  ", Status.SCREENSHOT);
       		   
              }else {
           	   report.updateTestLog("AA", "Save Value button not Hit", Status.FAIL);
               report.updateTestLog("Auction", "All data not  Saved", Status.SCREENSHOT);
              }
            
            
            
	   }
	   
	   
	    	catch(Exception e) 
	       {
	    		report.updateTestLog("AA", "Bond and Insurance details not entered  ", Status.FAIL);
	    		report.updateTestLog("AA", "Bond and Insurance details entered  ", Status.SCREENSHOT);
	    		
	    	}
	    } 
   public void comContactUpdate() throws Exception{
	   Thread.sleep(2000);
	   driver.findElement(By.xpath(Auction1.Addresses_Link)).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath(Auction1.LotEdit_Button)).click();
	   Thread.sleep(3000);
		// Setting Lot Line 1
       String Lot_Line1 = dataTable.getData("General_Data", "Lot_Line1");
       Lot_Line1 = ranDom(Lot_Line1);
       
       if (driver.findElement(By.xpath(Auction1.LOT_Line1)).isDisplayed())
       {
    	   driver.findElement(By.xpath(Auction1.LOT_Line1)).click();
    	   driver.findElement(By.xpath(Auction1.LOT_Line1)).clear();
    	   driver.findElement(By.xpath(Auction1.LOT_Line1)).sendKeys(Lot_Line1);
    	   report.updateTestLog("AA", "Line 1 Value  updated", Status.PASS);
       }  else {
    	   
    	   report.updateTestLog("AA", "Line 1 Value not entered", Status.FAIL);
           report.updateTestLog("Auction", "Line 1 Value not entered", Status.SCREENSHOT);
       }
       

   // Setting Lot Line 2

      String Lot_Line2 = dataTable.getData("General_Data", "Lot_Line2");
       Thread.sleep(2000);
       Lot_Line2 = ranDom(Lot_Line2);
       
       if (driver.findElement(By.xpath(Auction1.LOT_Line2)).isDisplayed())
       {
    	   driver.findElement(By.xpath(Auction1.LOT_Line2)).click();
    	   driver.findElement(By.xpath(Auction1.LOT_Line2)).clear();
    	   driver.findElement(By.xpath(Auction1.LOT_Line2)).sendKeys(Lot_Line2);
    	   report.updateTestLog("AA", "Line 2 Value  updated", Status.PASS);
       }  else {
    	   
    	   report.updateTestLog("AA", "Line 2 Value not updated", Status.FAIL);
           report.updateTestLog("Auction", "Line 2 Value not updated", Status.SCREENSHOT);
       }

       //Lot_PostalCode
     //String Lot_PostalCode = dataTable.getData("General_Data", "Lot_PostalCode");
       Random rand = new Random(); 
	   int Lot_PostalCode =30000 + rand.nextInt(10000);
	   String PostalCode =Integer.toString(Lot_PostalCode);
       if (driver.findElement(By.xpath(Auction1.LOT_PostalCode)).isDisplayed())
       {
    	   driver.findElement(By.xpath(Auction1.LOT_PostalCode)).click();
    	   driver.findElement(By.xpath(Auction1.LOT_PostalCode)).clear();
    	   driver.findElement(By.xpath(Auction1.LOT_PostalCode)).sendKeys(PostalCode);
    	   report.updateTestLog("AA", "PostalCode Value  updated", Status.PASS);
       }  else {
    	   
    	   report.updateTestLog("AA", "LOT_PostalCode Value not updated", Status.FAIL);
           report.updateTestLog("Auction", "LOT_PostalCode Value not updated", Status.SCREENSHOT);
       }
       
     //saving the data 
       if (driver.findElement(By.xpath(Auction1.Save_ADDR)).isEnabled()){
      	   driver.findElement(By.xpath(Auction1.Save_ADDR)).click();
  		   Thread.sleep(10000);
  		   report.updateTestLog("AA", "Save_ADDR Value button Hit", Status.PASS);
  		   report.updateTestLog("AA", "All data updated  ", Status.SCREENSHOT);
  		   Thread.sleep(5000);
  		   
         }else {
      	   report.updateTestLog("AA", "Save_ADDR Value button not Hit", Status.FAIL);
          report.updateTestLog("Auction", "All data not  updated", Status.SCREENSHOT);
         }
       
       
       
       
     // The below uncommented code to be implemented   
       
    //**********************   
       

       //Phone_Country

      
//       Thread.sleep(2000);
//       String Phone_Country = dataTable.getData("General_Data", "Phone_Country");
//       Thread.sleep(2000);
//       driver.findElement(By.xpath(Auction.Business_PhoneCountry)).click();
//       Thread.sleep(2000);
//       driver.findElement(By.xpath(Auction.Business_PhoneCountry)).sendKeys(Phone_Country);
//       Thread.sleep(2000);
//       System.out.println("Phone_Country is entered "+Phone_Country);

       //Business_Phone

//       String Business_Phone = dataTable.getData("General_Data", "Business_Phone");
//       Thread.sleep(2000);
//       driver.findElement(By.xpath(Auction.Business_PhoneNumber)).click();
//       Thread.sleep(2000);
//       driver.findElement(By.xpath(Auction.Business_PhoneNumber)).sendKeys(Business_Phone);
//       Thread.sleep(2000);
//       System.out.println("Business_Phone is entered "+Business_Phone);
       
       //Fax Business Country
//       String FaxBusinessCountry = dataTable.getData("General_Data", "Fax Business Country");
//       Thread.sleep(2000);
//       driver.findElement(By.xpath(Auction.Fax_BusinessCountryDropdownArrow)).click();
//       Thread.sleep(4000);
//       
//       List<WebElement> myElements4 = driver.findElements(By.xpath(Auction.Fax_BusinessCountry));
//		for(WebElement e : myElements4) {
//		  String Comp=(e.getText());
//		  System.out.println("Fax_BusinessCountry is :"+Comp);
//		  if(FaxBusinessCountry.equalsIgnoreCase(Comp)) {
//			  e.click();
//			  Thread.sleep(5000);
//			  System.out.println("FaxBusinessCountry is selected :"+FaxBusinessCountry);
//			  break;
//		  }
//		  
//		}
       
		//Business Fax
//		   String BusinessFax = dataTable.getData("General_Data", "Business Fax");
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.BusinessFax)).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.BusinessFax)).sendKeys(BusinessFax);
//	       Thread.sleep(2000);
//	       System.out.println("BusinessFax is entered "+BusinessFax);

//	       Thread.sleep(2000);
	       
	       //Select "Mailing Same As Lot" checkbox
       
//	       driver.findElement(By.xpath(Auction.MailingSameAsLot)).click();
//	       Thread.sleep(2000);
	       
	       //Email
       
//	       String ContactEmail = dataTable.getData("General_Data", "ContactEmail");
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.Contact_Email)).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.Contact_Email)).sendKeys(ContactEmail);
//	       Thread.sleep(2000);
       
	     //URL
       
//	       String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.WebsiteURL)).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath(Auction.WebsiteURL)).sendKeys(WebsiteURL);
//	       Thread.sleep(2000);
       
	       
//       report.updateTestLog("AA", "Data is entered under Contact window ", Status.PASS);
//       report.updateTestLog("Auction", "Data is entered under Contact window ", Status.SCREENSHOT);
    	
       
//      Thread.sleep(15000);
//      JavascriptExecutor executor = (JavascriptExecutor)driver;
//      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
      
      
      /* 
      // Hitting Next Button
      boolean Next=false;
      while(Next==true) {
      Next=driver.findElement(By.xpath(Auction.IdentificationNext)).isEnabled(); 
      JavascriptExecutor executor = (JavascriptExecutor)driver;
       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.IdentificationNext)));
      }*/
      
//       WebDriverWait wait = new WebDriverWait(driver, 5000);
//       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.DealerOverride)));
//	   GF.waitForObject(By.xpath(Auction.DealerOverride), "Override");
//	   Thread.sleep(10000);
//		
//		boolean override2=false;
//		override2=driver.findElement(By.xpath(Auction.DealerOverride)).isDisplayed();
//		if(override2==true) {
//		    report.updateTestLog("Auction", "Override button is arrived ", Status.SCREENSHOT);	
//			driver.findElement(By.xpath(Auction.DealerOverride)).click();
//			report.updateTestLog("Auction", "Override button is Clicked ", Status.SCREENSHOT);
//			System.out.println("Override is clicked");
//			Thread.sleep(2000);
	//	}
	
	   
   }
   
	public void updateLicense() throws Exception {
		Thread.sleep(2000);
		   driver.findElement(By.xpath(Auction1.Licences_Link)).click();
		   Thread.sleep(2000);
		   
		   if(driver.findElement(By.xpath(Auction1.LicenseEdit_Button)).isDisplayed()) {
			   driver.findElement(By.xpath(Auction1.LicenseEdit_Button)).click();
				report.updateTestLog("AA", "License edit is opened ", Status.PASS);
		        report.updateTestLog("Auction", "License edit is opened ", Status.SCREENSHOT);
		    	
			}else {
				report.updateTestLog("AA", "License edit is not opened ", Status.FAIL);
		        report.updateTestLog("Auction", "License edit is not opened ", Status.SCREENSHOT);
		    	
			}
		   
		   
		   
		   
		if(driver.findElement(By.xpath(Auction1.LicenseWindow_Edit)).isDisplayed()) {
			report.updateTestLog("AA", "License window is opened ", Status.PASS);
	        report.updateTestLog("Auction", "License window is opened ", Status.SCREENSHOT);
	    	
		}else {
			report.updateTestLog("AA", "License window is not opened ", Status.FAIL);
	        report.updateTestLog("Auction", "License window is not opened ", Status.SCREENSHOT);
	    	
		}  
		
		try {
		// Setting LicenseNumber

	     //  String LicenseNumber = dataTable.getData("General_Data", "LicenseNumber");
	       Random rand = new Random(); 
		   int rand_int21 =10000000+ rand.nextInt(90000000);
		   String LicenseNumber = Integer.toString(rand_int21);
	       driver.findElement(By.xpath(Auction1.LicenseNumber)).click();
	       driver.findElement(By.xpath(Auction1.LicenseNumber)).clear();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction1.LicenseNumber)).sendKeys(LicenseNumber);
	       System.out.println("LicenseNumber entered");

	       // issuedDate

	       String issuedDate = dataTable.getData("General_Data", "issuedDate");
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction1.issuedDate)).click();
	       driver.findElement(By.xpath(Auction1.issuedDate)).clear();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction1.issuedDate)).sendKeys(issuedDate);
	       System.out.println("issuedDate entered");

	       //expiration Date
	       String expirationDate = dataTable.getData("General_Data", "expirationDate");
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction1.expirationDate)).click();
	       driver.findElement(By.xpath(Auction1.expirationDate)).clear();
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(Auction1.expirationDate)).sendKeys(expirationDate);
	       Thread.sleep(3000);
	       System.out.println("expirationDate entered");
	       
	  // this code to be implemented later      
	 //  **********************************    
	       // issuingCountry
//	       String issuingCountry = dataTable.getData("General_Data", "issuingCountry");
//	       Thread.sleep(3000);
//	       driver.findElement(By.xpath(Auction.issuingCountryDropdownArrow)).click();
//	       Thread.sleep(4000);
//	       System.out.println("issuingCountryDropdownArrow clicked");
//	       
//	       List<WebElement> myElements4 = driver.findElements(By.xpath(Auction.issuingCountry));
//	       Thread.sleep(2000);
//			for(WebElement e : myElements4) {
//			  String Comp=(e.getText());
//			  if(issuingCountry.equalsIgnoreCase(Comp)) {
//				  Thread.sleep(3000);
//				  JavascriptExecutor executor = (JavascriptExecutor)driver;
//			       executor.executeScript("arguments[0].click();", e);
			        
//				  //e.click();
//				  Thread.sleep(5000);
//				  System.out.println("issuing Country is selected :"+issuingCountry);
//				  break;
//			  }
//			  
//			}
	       
	       
//			 Thread.sleep(3000);
	       //issuingStateOrProvince

//	       String issuingStateOrProvince = dataTable.getData("General_Data", "issuingStateOrProvince");
//	       Thread.sleep(3000);
//	       driver.findElement(By.xpath(Auction.issuingStateOrProvinceArrowDropdown)).click();
//	       Thread.sleep(4000);
//	       
//	       List<WebElement> myElements5 = driver.findElements(By.xpath(Auction.issuingStateOrProvince));
//	       Thread.sleep(3000);
//			for(WebElement e : myElements5) {
//			  String Comp=(e.getText());
//			  if(issuingStateOrProvince.equalsIgnoreCase(Comp)) {
//				  Thread.sleep(3000);
//				  e.click();
//				  Thread.sleep(5000);
//				  System.out.println("issuing State Or Province is selected :"+issuingStateOrProvince);
//				  break;
//			  }
//			  
//			}
			
//			Thread.sleep(3000);
//			report.updateTestLog("AA", "Data entered under License window ", Status.PASS);
//	        report.updateTestLog("Auction", "Data entered under License window ", Status.SCREENSHOT);
//    
//	       Thread.sleep(10000);
//	              
//	       JavascriptExecutor executor = (JavascriptExecutor)driver;
//	       executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction.DealerCreateFinish)));
//	      
//	       
//	       WebDriverWait wait = new WebDriverWait(driver, 5000);
//	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Auction.TextAfterDealerCreation)));
//	       GF.waitForObject(By.xpath(Auction.TextAfterDealerCreation), "Dealer");
//		   Thread.sleep(5000);
//			
//		   ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", driver.findElement(By.xpath(Auction.TextAfterDealerCreation)));
//		   Thread.sleep(3000);
//		   report.updateTestLog("AA", "5m Dealer is created successfully ", Status.PASS);
//	       report.updateTestLog("Auction", "Data entered under License window ", Status.SCREENSHOT);
	       //saving the data 
	       if (driver.findElement(By.xpath(Auction1.Save_ADDR)).isEnabled()){
	      	   driver.findElement(By.xpath(Auction1.Save_ADDR)).click();
	  		   Thread.sleep(10000);
	  		   report.updateTestLog("AA", "Save licences Value button Hit", Status.PASS);
	  		   report.updateTestLog("AA", "All data updated  ", Status.SCREENSHOT);
	  		   Thread.sleep(5000); 
	  		   
	       }
		}catch(Exception e) {
			report.updateTestLog("AA", "5m Dealer is not updated  successfully ", Status.FAIL);
			System.out.println("Error is: "+e);
		}
   
	      
	    }
	  
   
   public String ranDom(String fieldname)  {
	   Random rand = new Random(); 
	   int rand_int1 = rand.nextInt(100000);
	   fieldname = rand_int1+"_"+fieldname ;
	   return fieldname ;
	   
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
   		String CompanyType= dataTable.getData("General_Data", "Company Type"); 
   	}
   	return attributeValue;    	
   	
   }
   
   public void dealerTypeSlection ()
   {
   //	String attributeValue="";
   	String Dealer_New= dataTable.getData("License","Dealer_New"); 
   	String Dealer_Used= dataTable.getData("License","Dealer_Used");
	String Dealer_Lease= dataTable.getData("License","Dealer_Lease"); 
   	String Dealer_WholeSale= dataTable.getData("License","Dealer_WholeSale");
 	String Dealer_Export= dataTable.getData("License","Dealer_Export"); 
   	String Dealer_Parts= dataTable.getData("License","Dealer_Parts");
	String Dealer_Salvage= dataTable.getData("License","Dealer_Salvage"); 
   	String Dealer_Rental= dataTable.getData("License","Dealer_Rental");
   	try
   	{
   		 if (Dealer_New.equalsIgnoreCase("Yes")){
   			 
   		//	driver.findElement(By.xpath(Auction.NewCheckBox)).click();
   		  JavascriptExecutor executor = (JavascriptExecutor)driver;
	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.NewCheckBox)));
   			Thread.sleep(2000);
   		 }  
   		 
   		  if  (Dealer_Used.equalsIgnoreCase("Yes")){
   			 
   		//	driver.findElement(By.xpath(Auction.UsedCheckBox)).click();
   			JavascriptExecutor executor = (JavascriptExecutor)driver;
  	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.UsedCheckBox)));
   			Thread.sleep(2000);
   		 }  
   		  
   		  
   		 if (Dealer_Lease.equalsIgnoreCase("Yes")){
   			 
    		//	driver.findElement(By.xpath(Auction.LeaseCheckBox)).click();
   			JavascriptExecutor executor = (JavascriptExecutor)driver;
    	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.LeaseCheckBox)));
    			Thread.sleep(2000);
    		 }  
    		 
    	  if  (Dealer_WholeSale.equalsIgnoreCase("Yes")){
    			 
    		//	driver.findElement(By.xpath(Auction.WholesaleCheckBox)).click();
    			JavascriptExecutor executor = (JavascriptExecutor)driver;
      	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.WholesaleCheckBox)));
    			Thread.sleep(2000);
    		 }  
    		  
    	   if (Dealer_Export.equalsIgnoreCase("Yes")){
    	   			 
    	   			//driver.findElement(By.xpath(Auction.ExportCheckBox)).click();
    		   JavascriptExecutor executor = (JavascriptExecutor)driver;
       	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.ExportCheckBox)));
    	   			Thread.sleep(2000);
    	   		 }  
    	   		 
    	    if  (Dealer_Parts.equalsIgnoreCase("Yes")){
    	   			 
    	   		//	driver.findElement(By.xpath(Auction.PartsCheckBox)).click();
    	   		   JavascriptExecutor executor = (JavascriptExecutor)driver;
    	       	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.PartsCheckBox)));
    	   			Thread.sleep(2000);
    	   		 }  
    	   		  
    	   		  
    	   	 if (Dealer_Salvage.equalsIgnoreCase("Yes")){
    	   			 
    	    		//	driver.findElement(By.xpath(Auction.SalvageCheckBox)).click();
    	    			  JavascriptExecutor executor = (JavascriptExecutor)driver;
    	           	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.SalvageCheckBox)));
    	    			Thread.sleep(2000);
    	    		 }  
    	    		 
    	    if  (Dealer_Rental.equalsIgnoreCase("Yes")){
    	    			 
    	    		//	driver.findElement(By.xpath(Auction.RentalCheckBox)).click();
    	    			JavascriptExecutor executor = (JavascriptExecutor)driver;
  	           	      executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(Auction1.RentalCheckBox)));
    	    			Thread.sleep(2000);
    	    		 }  
    	    		  
    	    		  report.updateTestLog("AA", "dealer type value selected", Status.PASS);
    		  		   report.updateTestLog("AA", "dealer type value selected  ", Status.SCREENSHOT); 		  
   		  
   	}
   	catch (Exception e)
   	{
   		
   	}
   	//return attributeValue;    	
   	
   }
  
   
   public void SetAttributeValue (String Attributename1,String Attributename2,String Attributename3,String Attributename4,String Attributename5)
   {
	 String issuingCountry = dataTable.getData("General_Data", "issuingCountry");
	 String issuingStateOrProvince = dataTable.getData("General_Data", "issuingStateOrProvince");
   	Random rand = new Random(); 
   	try
   	{
   		List<WebElement>myElements1 = driver.findElements(By.xpath("//*[text()='"+Attributename1+"']//parent::td//following-sibling::td//input"));
   		List<WebElement>myElements2 = driver.findElements(By.xpath("//*[text()='"+Attributename2+"']//parent::td//following-sibling::td//input"));
   		List<WebElement>myElements3 = driver.findElements(By.xpath("//*[text()='"+Attributename3+"']/parent::td//following-sibling::td//input"));
   		List<WebElement>myElements4 = driver.findElements(By.xpath("//*[text()='"+Attributename4+"']//parent::td//following-sibling::td//input"));
   		List<WebElement>myElements5 = driver.findElements(By.xpath("//*[text()='"+Attributename5+"']//parent::td//following-sibling::td//input"));
   		if (Attributename1.equalsIgnoreCase("Id Number:"))
   		{
   			
   			for(int i=0;i<myElements1.size();i++)
			 {
			    int rand_int1 = 100000000+rand.nextInt(100000000);
				String num=Integer.toString(rand_int1);
				myElements1.get(i).sendKeys(num);
				
			 }
   			System.out.println("Id Number for license entered");
   		}
   		 if (Attributename2.equalsIgnoreCase("Issued Date:"))
	   		{
				 for(int j=0;j<myElements2.size();j++)
				 {
					
						LocalDateTime ldt = LocalDateTime.now();
						System.out.println(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH).format(ldt));
						String d=DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH).format(ldt);					
						myElements2.get(j).sendKeys(d);
					
				 }
				 System.out.println("Issued Date for license entered");
	   		}
   		  
   		if (Attributename3.equalsIgnoreCase("Exp. Date:"))
		 {
				 for(int i=0;i<myElements3.size();i++)
				 {
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDate localDate = LocalDate.now();
						localDate=localDate.plusYears(2+i);
						String abc =DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH).format(localDate);
						//String localDate2 =dtf.format(localDate);
						myElements3.get(i).sendKeys(abc);						
					
				 }
				 System.out.println("Exp Date for license entered");
		 }
   		if (Attributename4.equalsIgnoreCase("Issuing Country:"))
		 {
				 
   			int j=0;
   			
   			for(int i=0;i<myElements4.size();i++)
				 {
					 
					  
					  String IssueingCountryXpath=getxpath(17+j);
					  String issuingCountryvalue =getxpathCountry(6+i);
					 driver.findElement(By.xpath(IssueingCountryXpath)).click();
					 j= j+6 ;
				     Thread.sleep(4000);
				     System.out.println("issuingCountryDropdownArrow clicked"); 
					 List<WebElement> myElements6 = driver.findElements(By.xpath(issuingCountryvalue));
				       Thread.sleep(2000);
						for(WebElement e : myElements6) {
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
					
				      
					 
				 }
   			
   		 System.out.println("All Country is selected :"+issuingCountry);
		 }
		  
   		if (Attributename5.equalsIgnoreCase("Issuing State/Province:"))
		 {
   			int j=0;	 
   			for(int i=0;i<myElements5.size();i++)
				 {
					  
					  String IssueingProvinceXpath=getxpathProvince(1+i);
					       driver.findElement(By.xpath(IssueingProvinceXpath)).click();	
					       driver.findElement(By.xpath(IssueingProvinceXpath)).sendKeys(issuingStateOrProvince);
					       Thread.sleep(3000);
					       driver.findElement(By.xpath(Auction1.Licences_Link)).click();
					       System.out.println("issuing Province is selected :"+issuingStateOrProvince);	
						} 						  
					 
				 }
  			
   		// driver.findElement(By.xpath(Auction.Addwidow)).click();
  		 System.out.println("All Country is selected :"+issuingCountry);   		         					 		 
   	}
   	catch (Exception e)
   	{
   		System.out.println("Error is: "+e);
   	}
   	//return attributeValue;    	
   	
   }
   
   public String getxpath(int num){
	   String vatrxpath="";
	   try{
	     vatrxpath =  "(//*[@class='x-trigger-cell'])"+"["+num+"]" ;
	   }
	   catch(Exception e){
		   
	   }
	   return vatrxpath; 
   }
   
   public String getxpathCountry(int num1){
	   String vatrxpath1="";
	   try{
		   //vatrxpath1="(//*[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default'])"+"["+num1+"]"+"//ul//li";
	    
		   vatrxpath1= "(//*[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default')])"+"["+num1+"]"+"//ul//li";
	   }
	   catch(Exception e){
		   
	   }
	   return vatrxpath1; 
   }
  
   public String getxpathProvince(int num1){
	   String vatrxpath1="";
	   try{
		   //vatrxpath1="(//*[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default'])"+"["+num1+"]"+"//ul//li";
	    
		   //vatrxpath1= "(//*[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default')])"+"["+num1+"]"+"//ul//li";
		   vatrxpath1= "(//*[@name='issuingStateOrProvince'])"+"["+num1+"]" ;
	   }
	   catch(Exception e){
		   
	   }
	   return vatrxpath1; 
   }
   
   
   public void dbVal_License() throws Exception {

		// Preparing the connection string 
		Connection conn = null;
		Statement stmt = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
	   driver.findElement(By.xpath(Auction1.Licences_Link)).click();
	   System.out.println("Clicked License link"); 
	   Thread.sleep(2000);
	   List<WebElement>myElements1 = driver.findElements(By.xpath("//*[@data-qtip='Edit']"));
	   for(int i=0;i<myElements1.size();i++)
		 {
		  // List<String> listOfDBValues = new ArrayList<String>();
		   String license_number=getxpathLicense(2+i);
		   license_number= driver.findElement(By.xpath(license_number)).getText();
		   System.out.println("License Number:"+license_number);
		   Class.forName(driverName);
			conn=DriverManager.getConnection(connectionUrl,userName,password);
		   String sqlPassed="Select i.LICENSE_TYPE, i.LICENSE_NUM, TO_CHAR((i.LICENSE_EFFECTIVE_DT), 'MM-DD-YYYY') LICENSE_EFFECTIVE_DT , TO_CHAR((i.LICENSE_EXPIRY_DT), 'MM-DD-YYYY') LICENSE_EXPIRY_DT, i.LICENSE_COUNTRY_CD, i.LICENSE_PROVINCE_CD from CMX_ORS.C_BO_ACCT a left outer join CMX_ORS.C_BO_ACCT_IDENT b on a.ROWID_OBJECT=b.ACCT_ID left outer join CMX_ORS.C_BO_CUST c on a.CUST_ID=c.ROWID_OBJECT  left outer join CMX_ORS.C_BO_CUST_COMP_EXT d on  c.ROWID_OBJECT = d.CUST_ID left outer join CMX_ORS.C_BO_ACCT_POSTADDR e on e.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_TELECOMNUM f on f.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_ACCT_ELECADDR h on h.ACCT_ID = a.ROWID_OBJECT  left outer join CMX_ORS.C_BO_ACCT_LICENSE i on i.ACCT_ID = a.ROWID_OBJECT left outer join CMX_ORS.C_BO_CUST_SPECIALTIES k on k.CUST_ID = c.ROWID_OBJECT left outer join CMX_ORS.C_BO_POST_ADDR l on e.POST_ADDR_ID = l.ROWID_OBJECT  Where i.LICENSE_NUM="+"'"+license_number+"'"+ "and rownum=1";
		   stmt=conn.createStatement();
		   System.out.println("Connection estabilished with sql server");
		   ResultSet resultSet =stmt.executeQuery(sqlPassed);
		   while (resultSet.next())
		   {
		    String 	LICENSE_TYPE_DB=resultSet.getString("LICENSE_TYPE");
			String 	LICENSE_PROVINCE_CD_DB=resultSet.getString("LICENSE_PROVINCE_CD");
			String LICENSE_COUNTRY_CD_DB=resultSet.getString("LICENSE_COUNTRY_CD");
			String LICENSE_NUM_DB=resultSet.getString("LICENSE_NUM");
			String LICENSE_EFFECTIVE_DT_DB=resultSet.getString("LICENSE_EFFECTIVE_DT");
			String LICENSE_EXPIRY_DT_DB=resultSet.getString("LICENSE_EXPIRY_DT");
			
		   String LICENSE_TYPE= getxpathLicenseTable(2+i,2);
		   LICENSE_TYPE = driver.findElement(By.xpath(LICENSE_TYPE)).getText();
		   System.out.println("License Number:"+LICENSE_TYPE);
		   // License Number from UI 
		   String LICENSE_NUM= getxpathLicenseTable(2+i,3);
		   LICENSE_NUM = driver.findElement(By.xpath(LICENSE_NUM)).getText();
		   System.out.println("License Number:"+LICENSE_NUM);
		   //Eff date 
		   String LICENSE_EFFECTIVE_DT= getxpathLicenseTable(2+i,4);
		   LICENSE_EFFECTIVE_DT = driver.findElement(By.xpath(LICENSE_EFFECTIVE_DT)).getText();
		   System.out.println("License Number:"+LICENSE_EFFECTIVE_DT);
		   //Exp Date 
		   String LICENSE_EXPIRY_DT= getxpathLicenseTable(2+i,5);
		   LICENSE_EXPIRY_DT = driver.findElement(By.xpath(LICENSE_EXPIRY_DT)).getText();
		   System.out.println("License Number:"+LICENSE_EXPIRY_DT);
		   //Country
		   String LICENSE_COUNTRY_CD= getxpathLicenseTable(2+i,8);
		   LICENSE_COUNTRY_CD = driver.findElement(By.xpath(LICENSE_COUNTRY_CD)).getText();
		   System.out.println("License Number:"+LICENSE_COUNTRY_CD);
		   // State 
		   String LICENSE_PROVINCE_CD= getxpathLicenseTable(2+i,7);
		   LICENSE_PROVINCE_CD = driver.findElement(By.xpath(LICENSE_PROVINCE_CD)).getText();
			 // Checking UI LICENSE_TYPE  
			 if (LICENSE_TYPE.equalsIgnoreCase(LICENSE_TYPE_DB) || (LICENSE_TYPE.equalsIgnoreCase("") && LICENSE_TYPE_DB==null))
				{
			
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_TYPE_DB + "="+"UI Value:"+LICENSE_TYPE, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_TYPE_DB + "<>"+"UI Value:"+LICENSE_TYPE, Status.FAIL);
					
				}
			// Checking UI License_Number 
			 if (LICENSE_NUM.equalsIgnoreCase(LICENSE_NUM_DB) || (LICENSE_NUM.equalsIgnoreCase("") && LICENSE_NUM_DB==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer LICENSE TYPE matched with OVC database"+"DB Value:" +LICENSE_NUM_DB + "="+"UI Value:"+LICENSE_NUM, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer LICENSE TYPE not matched with OVC database"+"DB Value:" +LICENSE_NUM_DB + "<>"+"UI Value:"+LICENSE_NUM, Status.FAIL);
					
				}
			// Checking UI License_State 
			 if (LICENSE_PROVINCE_CD.equalsIgnoreCase(LICENSE_PROVINCE_CD_DB) || (LICENSE_PROVINCE_CD.equalsIgnoreCase("") && LICENSE_PROVINCE_CD_DB==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer License State matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD_DB + "="+"UI Value:"+LICENSE_COUNTRY_CD, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer License State not matched with OVC database"+"DB Value:" +LICENSE_PROVINCE_CD_DB + "<>"+"UI Value:"+LICENSE_PROVINCE_CD_DB, Status.FAIL);
					
				}
			 
			// Checking UI License_Country 
			 if (LICENSE_COUNTRY_CD.equalsIgnoreCase(LICENSE_COUNTRY_CD_DB) || (LICENSE_COUNTRY_CD.equalsIgnoreCase("") && LICENSE_COUNTRY_CD_DB==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer License Country  matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD_DB + "="+"UI Value:"+LICENSE_COUNTRY_CD, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer License Country not matched with OVC database"+"DB Value:" +LICENSE_COUNTRY_CD_DB + "<>"+"UI Value:"+LICENSE_COUNTRY_CD, Status.FAIL);
					
				}
	 
			// Checking UI License_Effective
			 LICENSE_EFFECTIVE_DT =dateconverter(LICENSE_EFFECTIVE_DT);
			 if (LICENSE_EFFECTIVE_DT.equalsIgnoreCase(LICENSE_EFFECTIVE_DT_DB) || (LICENSE_EFFECTIVE_DT.equalsIgnoreCase("") && LICENSE_EFFECTIVE_DT_DB==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer License Country  matched with OVC database"+"DB Value:" +LICENSE_EFFECTIVE_DT_DB + "="+"UI Value:"+LICENSE_EFFECTIVE_DT, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer License Country not matched with OVC database"+"DB Value:" +LICENSE_EFFECTIVE_DT_DB + "<>"+"UI Value:"+LICENSE_EFFECTIVE_DT, Status.FAIL);
					
				}
		   
			// Checking UI License_Expiry
			 LICENSE_EXPIRY_DT =dateconverter(LICENSE_EXPIRY_DT);
			 if (LICENSE_EXPIRY_DT.equalsIgnoreCase(LICENSE_EXPIRY_DT_DB) || (LICENSE_EXPIRY_DT.equalsIgnoreCase("") && LICENSE_EXPIRY_DT_DB==null))
				{
					
				 //report.updateTestLog("AA", "Dealer value matched with OVC database", Status.PASS);
				 report.updateTestLog("AA", "Dealer License Country  matched with OVC database"+"DB Value:" +LICENSE_EXPIRY_DT_DB + "="+"UI Value:"+LICENSE_EXPIRY_DT, Status.PASS);
				}
				
				else 
				{
					report.updateTestLog("AA","Dealer License Country not matched with OVC database"+"DB Value:" +LICENSE_EXPIRY_DT_DB + "<>"+"UI Value:"+LICENSE_EXPIRY_DT, Status.FAIL);
					
				}
			 
		 }
		 }
	   
   }
   
   
   public ArrayList<String>executeSQLQuery_List(String sqlPassed) throws Exception
   {
		
	   ArrayList<String> resultValue = new ArrayList<String>();
		// Preparing the connection string 
		Connection conn = null;
		//Connection conn2 = null;
		Statement stmt = null;
		//Statement stmt2 = null;
		String driverName="oracle.jdbc.OracleDriver";
		String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q1OVC_OLTP1";
		String userName="amondal";
		String password="amald3v3";
		

		 try
			{
				//estabilish connection
					
			 	Class.forName(driverName);
				conn=DriverManager.getConnection(connectionUrl,userName,password);
				//conn2=DriverManager.getConnection(connectionUrl,userName,password);
				System.out.println("Connection estabilished with sql server");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Connection is not build successfully");
				
			}	
		 
		 
		 	stmt=conn.createStatement();
		 	//stmt2=conn2.createStatement();
			
		 	ResultSet resultSet =stmt.executeQuery(sqlPassed);
		 //	ResultSet dataSet2=stmt2.executeQuery(sqlPassed);
		 		try
		 		{
					while (resultSet.next()) {
						int columnCount = resultSet.getMetaData().getColumnCount();
	                    StringBuilder stringBuilder = new StringBuilder();
	                    for(int iCounter=1;iCounter<=columnCount; iCounter++)
	                    {
	                    	String s =resultSet.getString(iCounter);
	                    	if (resultSet.wasNull())
	                    	{
	                    		s = "";
	                    	}
	                        stringBuilder.append(s.trim()+" ");
	                    }
	                    String reqValue = stringBuilder.substring(0, stringBuilder.length()-1);
	                    resultValue.add(reqValue);					
						
					}
              }
		 		catch (SQLException e)
		 		{
	                e.printStackTrace();
	            }
	            
		 		return resultValue  ;
			}
			
   public String getxpathLicense(int num1){
	   String vatrxpath1="";
	   try{
		   vatrxpath1="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//"+"tr["+num1+"]"+"//td[3]//div";
	    
		   //vatrxpath1= "(//*[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default')])"+"["+num1+"]"+"//ul//li";
		 //  vatrxpath1= "(//*[@name='issuingStateOrProvince'])"+"["+num1+"]" ;
	   }
	   catch(Exception e){
		   
	   }
	   return vatrxpath1; 
   }			
   public String getxpathLicenseTable(int num1,int num2)
   {
	   String vatrxpath1="";
	   try{
		   vatrxpath1="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//"+"tr["+num1+"]"+"//"+ "td["+num2+"]"+ "//div";
	    
		   //vatrxpath1= "(//*[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default')])"+"["+num1+"]"+"//ul//li";
		 //  vatrxpath1= "(//*[@name='issuingStateOrProvince'])"+"["+num1+"]" ;
	   }
	   catch(Exception e){
		   
	   }
	   return vatrxpath1; 
   }	
   
   public String dateconverter(String S1)
   {
	   
	   try{
		
		    S1=S1.replace("/", "-");
	   }
	   catch(Exception e){
		   
	   }
	   return S1 ;
	   
   }
   
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
   public void loginGmailarnab() throws Exception {
		String uid = dataTable.getData("General_Data", "UserID");	
		String pwd = dataTable.getData("General_Data", "Password");	
		
		try {
			driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);		
			String strAppURL = "https://www.gmail.com";
			driver.get(strAppURL);			
			Thread.sleep(10000);
		//driver.findElement(By.xpath("//a[text()='Gmail']")).click();
		//Thread.sleep(10000);
		System.out.println("clicked on the gmail link");
		// providing the user name 
		driver.findElement(By.xpath("//input[@type='email']")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(uid);
		System.out.println("entered username");
		//Hitting Next
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		System.out.println("clicked next");
		Thread.sleep(5000);
		//Providing password
		driver.findElement(By.xpath("//input[@type='password']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pwd);
		System.out.println("entered password");
		//Hitting Next
			driver.findElement(By.xpath("//*[text()='Next']")).click();
			Thread.sleep(20000);
			System.out.println("in the login page");
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
   
   



