package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.HomePage;

public class HomePageTest extends BrowserDetails
{
	private WebDriver driver;
	private HomePage homePage;
	
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}
	
	@Test
	public void getHomePageTitle()
	{
	   homePage = new HomePage(driver);
	   homePage.homePageTitle();
	}
	
}
