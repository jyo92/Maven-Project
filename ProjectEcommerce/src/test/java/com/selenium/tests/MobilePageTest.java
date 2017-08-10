package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.common.ReadPropertyFile;
import com.selenium.common.WriteExcel;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;
@Listeners(com.selenium.common.Listeners.class)
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
	
	@Test(enabled = false)
	public void testXpPrice()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.verifyXperiaPrice(), "Xperia price is not correct");
	}
	
	@Test(priority = 0)
	public void testSortByName()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.compareTwoMobLists(), "Mobile list is not sorted");
	}
	
	@Test(priority = 1)
	public void testAddToCompItms()
	{
		mobPage = homePage.clickOnMobile();
		Assert.assertTrue(mobPage.compSetsOfMobile(), "Mobile comparison list is not correct");
	}
	
	@Test(priority = 2)
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
