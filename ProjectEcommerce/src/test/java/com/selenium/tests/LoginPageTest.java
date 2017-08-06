package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;

public class LoginPageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
		loginPage = homePage.clickOnSignIn();
	}
	
	@Test(priority = 0)
	public void testSignIn() throws Exception
	{
		loginPage.logIn();
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
}
