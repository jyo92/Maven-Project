package com.selenium.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.CartPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;

@Listeners(com.selenium.common.Listeners.class)
public class HomePageTest extends BrowserDetails
{
	public WebDriver driver;
	public HomePage homePage;
	public MobilePage Mobpage;
	public CartPage cartPage;
	
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
	    Assert.assertTrue(homePage.verifyHomePageTitle());
	}
	
	@Test(priority = 1)
	public void testMobPgeHdr()
	{
		Mobpage = homePage.clickOnMobile();
		Assert.assertTrue(Mobpage.verifyMobPgeHdr());
	}
	
	@Test(priority = 2)
	public void testEmptyCartMsg()
	{
		Assert.assertTrue(homePage.verifyEmptyCrtMsg());
	}
	
	@Test(priority = 3)
	public void testItemRemoveFromCart()
	{
		Mobpage.addToCart();
		Assert.assertTrue(homePage.removeItemFrmCart());
	}
	
	@Test(priority = 4)
	public void testAccOptions()
	{
		Assert.assertTrue(homePage.verifyAccOptions());
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
}
