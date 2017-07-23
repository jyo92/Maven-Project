package com.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;
import com.selenium.pages.OrderPage;

public class OrderPageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	private OrderPage orderPage;
	
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
		orderPage = homePage.clickOnCart();
	}
	
	@Test
	public void testCartIsEmpty()
	{
		Assert.assertTrue(orderPage.VerifyCartIsEmpty(), "Cart is not showing empty");
	}
}
