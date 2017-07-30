package com.selenium.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.OrderPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;

public class OrderPageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private OrderPage orderPage;
	private LoginPage loginPage;
	
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(enabled=false)
	public void testCartIsEmpty()
	{
		orderPage = homePage.clickOnCart();
		Assert.assertTrue(orderPage.VerifyCartIsEmpty(), "Cart is not showing empty");
	}
	
	@Test(priority = 0)
	public void testSelectedPdctAndChkOut()
	{
		orderPage = homePage.hoverAndAddToCart();
		loginPage = orderPage.checkOutBtnClick();
		Assert.assertTrue(loginPage.loginPageHeading(), "login page has not come");
	}
	
	@Test(priority = 1)
	public void logIn()
	{
		orderPage = loginPage.logIn();
		Assert.assertTrue(orderPage.isAddressHdrDisplayed(),"login failed");
	}
	
}
