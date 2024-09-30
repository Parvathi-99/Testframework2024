package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void LoginPageTitleTest()
	{
		String title=loginpage.getLoginPageTitle();
		Assert.assertTrue(title.contains(AppConstants.LOGIN_PAGE_TITLE));
		
	}
	
	@Test(priority = Integer.MIN_VALUE)
	public void LoginPageURLTest()
	{
		String url=loginpage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
		
	}
	@Test

	public void forgotpasswordlinkTest()
	{
		boolean forgotpwdlink=loginpage.isforgotpasswordlinkexist();
		Assert.assertTrue(forgotpwdlink);
		
	}
	@Test

	public void loginpagelogoTest()
	{
		boolean loginpagelogo=loginpage.isLogoExist();
		Assert.assertTrue(loginpagelogo);
		
	}

	@Test(priority = Integer.MAX_VALUE)
	public void loginTest()
	{
		String Accounttitle=loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(Accounttitle.contains(AppConstants.ACCOUNT_PAGE_TITLE));

	}
	
	
	
}
