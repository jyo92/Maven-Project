package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;

public class MobilePageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private MobilePage mobPage;
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test()
	public void testXpPrice()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.verifyXperiaPrice(), "Xperia price is not correct");
	}
	
	@Test(enabled = false)
	public void testSortByName()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.compareTwoMobLists(), "Mobile list is not sorted");
	}
	
	@Test(enabled = false)
	public void testAddToCompItms()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.compSetsOfMobile(), "Mobile comparison list is not correct");
	}
	
	@Test(enabled = false)
	public void testCompPgeHdr()
	{
		Assert.assertTrue(mobPage.verifyCompPgeHdr(), "Compare page header is not correct");
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
}
