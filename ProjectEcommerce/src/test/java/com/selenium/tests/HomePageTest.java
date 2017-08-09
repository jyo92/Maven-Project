package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;

@Listeners(com.selenium.common.Listeners.class)
public class HomePageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private MobilePage Mobpage;
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 0)
	public void testHomePgeTitle() throws Exception
	{
	    Assert.assertTrue(homePage.verifyHomePageTitle(), "Home page title does not match");
	}
	
	@Test(priority = 1)
	public void testMobPgeHdr()
	{
		Mobpage = homePage.clickOnMobile();
		Assert.assertTrue(Mobpage.verifyMobPgeHdr(), "Mobile page header is not matched");
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
}
