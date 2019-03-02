package businesscomponents;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.xml.sax.SAXException;

import com.util.frameworkutils.Status;

import objectrepositories.GTC;
import objectrepositories.UKConstantsIF;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
public class COX extends ReusableLibrary

{
	

static Properties prop1 = new Properties();
public static  String Caseval1;
public static int propno=1;
Properties prop = new Properties();


public COX(ScriptHelper scriptHelper) 
{
	super(scriptHelper);
}

GenericFunctions GF = new GenericFunctions(scriptHelper);
WebElement element;	
public static String sapName;
public static String productDesc;
public static String record; 

boolean success1 ;
WebElement sap;
WebElement vot;
WebDriverWait wait = new WebDriverWait(driver, 500);



public void loginUpdate() throws Exception {

		 String sqlPassed="select CUST_NM,LEGAL_NM from CMX_ORS.C_S_CUST_MSC where ROWNUM<10";
		 String ACCT_NUM=extractData(sqlPassed);
		 System.out.println("difference is  :"+ACCT_NUM);
		 
		 report.updateTestLog("MDM", "difference is : "+ACCT_NUM, Status.PASS);
	
	//String strAppURL = "https://www.google.com";
	//String uid = dataTable.getData("General_Data", "UserID");	
	//String pwd = dataTable.getData("General_Data", "Password");
	//driver.get(strAppURL);	
		 
}



public static String extractData(String sql){
Connection conn = null;
Statement stmt = null;
String driverName="oracle.jdbc.OracleDriver";
String connectionUrl="jdbc:oracle:thin:@dataservices3.imanheim.com:1521/Q4OVC_OLTP1";
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
			ACCT_NUM=dataSet.getString("CUST_NM");
			String Legal_NM=dataSet.getString("LEGAL_NM");
//			String mName=dataSet.getString("MIDDLE_NAME");
//			String lName=dataSet.getString("LAST_NAME");
			System.out.println("Full Name is: "+ACCT_NUM);
			System.out.println("Full Name is: "+Legal_NM);
			
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
	
	
}


