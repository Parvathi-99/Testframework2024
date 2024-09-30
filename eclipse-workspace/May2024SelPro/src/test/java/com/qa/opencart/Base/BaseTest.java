package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;

public class BaseTest{
	
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginpage;
	protected Properties prop;
	
	@BeforeTest
	
	public void setup()
	{
		df= new DriverFactory();
	
		prop=df.intiateprop();
		driver=df.intiatedriver(prop);
		loginpage=new LoginPage(driver);
	
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	

}
