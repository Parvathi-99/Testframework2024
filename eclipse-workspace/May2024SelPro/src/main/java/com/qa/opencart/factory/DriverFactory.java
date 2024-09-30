package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory 
{
	private WebDriver driver;
	Properties prop;
	public WebDriver intiatedriver(Properties prop)	
	{
		String browsername=prop.getProperty("browser");
		System.out.println("Browser name is : " + browsername);
		
		switch(browsername.toLowerCase().trim())
		{
			case "chrome":
				driver=new ChromeDriver();
				break;
			case "firefox":
				driver=new FirefoxDriver();
				break;
			case "edge":
				driver=new EdgeDriver();
				break;
			case "safari":
				driver=new SafariDriver();
				break;
			default:
				System.out.println(browsername + "It is a invalid. Please provide valid browser");
			//	throw new BrowserException("invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver;
		
	}
	
	/* this method initialize the property 
	 * to use config .properties file */

	public Properties  intiateprop()
	{
		prop=new Properties();
		FileInputStream fi;
		try {
			fi = new FileInputStream("./src/test/resource/config/config.properties");
					prop.load(fi);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	

}
