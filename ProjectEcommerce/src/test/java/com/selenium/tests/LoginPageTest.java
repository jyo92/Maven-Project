package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import com.selenium.pages.MyAccountPage;

@Listeners(com.selenium.common.Listeners.class)
public class LoginPageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private MyAccountPage myAccPage;
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(enabled = false)
	public void testLoginOnlyUname()
	{
		loginPage = homePage.clickMyAccount();
		Assert.assertTrue(loginPage.onlyUserNameLogin(), "no error message");
	}
	
	@Test(enabled = false)
	public void testLoginOnlyPwd()
	{
		loginPage = homePage.clickMyAccount();
		Assert.assertTrue(loginPage.onlyPwdLogin(), "no error message");
	}
	
	@Test(enabled = false)
	public void testLogIn()
	{
		loginPage = homePage.clickMyAccount();
		myAccPage = loginPage.logIn();
		Assert.assertTrue(myAccPage.verifyAcctDbdMsg(), "login failed");
	}

	@Test
	public void testNewLogIn()
	{
		loginPage = homePage.clickMyAccount();
		Assert.assertTrue(loginPage.createNewAccount());
	}
}
