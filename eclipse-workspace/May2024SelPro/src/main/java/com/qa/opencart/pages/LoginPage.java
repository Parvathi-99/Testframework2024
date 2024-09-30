package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.Elementutil;

public class LoginPage {
	private WebDriver driver;
	//private by locator
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By forgotpwdlink=By.linkText("Forgotten Password");
	private By logo=By.cssSelector("a img.img-responsive");
	Elementutil eleutil;
	
	//public page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		 eleutil=new Elementutil(driver);
	}
	
	//public page actions/Method

	public String getLoginPageTitle()
	{
		String title=eleutil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
		//		driver.getTitle();
		return title;
	}
	

	public String getLoginPageURL()
	{
		String url=eleutil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL,AppConstants.DEFAULT_SHORT_TIME_OUT);

		return url;
	}
	public boolean isforgotpasswordlinkexist()
	{
		return eleutil.IsElementDisplayed(forgotpwdlink);
	}
	public boolean isLogoExist()
	{
		return eleutil.IsElementDisplayed(logo);
	}
	public String dologin(String user,String pwd)
		{
		
		eleutil.waitforElementVisible(username,AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(user);
		//driver.findElement(password).sendKeys(pwd);
		eleutil.dosendkey(password,pwd);;
		eleutil.doclick(loginbtn);
		//driver.findElement(loginbtn).click();
		String AcctTitle=eleutil.waitForURLContainsAndReturn(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIME_OUT);
		return AcctTitle;
	}
}
