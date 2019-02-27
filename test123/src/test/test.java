package test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.chrome.ChromeDriver;

public class test {
//For git hub demo
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//String exePath = "C:\\Users\\ARNAB\\Downloads\\chromedriver.exe";
		//System.setProperty("webdriver.chrome.driver", exePath);
		//ChromeDriver driver = new ChromeDriver();
		//driver.get("https://www.google.co.in");
		//Arnab
		String url = "jdbc:sqlserver://DESKTOP-RGJVMQJ\\SQLEXPRESS;databaseName=arnab;integratedSecurity=true";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		try {
			
			Connection conn = DriverManager.getConnection(url);
			System.out.println("The connection string is : " +conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
