package com.selenium.tests;

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
public class CartPageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private CartPage cartPage;
	public MobilePage mobilePage;
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 0)
	public void testMaxQtyErrMsg()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		Assert.assertTrue(cartPage.verifyMaxQtyErrMsg(), "no qty error message came");
	}

	@Test(priority = 1)
	public void testEmtyCartMsg()
	{
		Assert.assertTrue(cartPage.VerifyEmptyCrtMsg(), "no empty cart error message came");
	}
	
	@Test(priority = 2)
	public void testItemRemoveFrmCart()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		Assert.assertTrue(cartPage.RemoveFrmCart());
	}
	
	@Test(priority = 3)
	public void testContinueShopp()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		Assert.assertTrue(cartPage.verifyContShopp());
	}
	
	@Test(priority = 4)
	public void testSuccCoupMsg()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		Assert.assertTrue(cartPage.verifySuccCoupMsg());
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
	
	
}
