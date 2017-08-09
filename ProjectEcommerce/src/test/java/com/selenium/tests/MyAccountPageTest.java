package com.selenium.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.MyAccountPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
@Listeners(com.selenium.common.Listeners.class)
public class MyAccountPageTest extends BrowserDetails 
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

//@Test(priority = 0)
//public void testWshListShre()
//{
//	loginPage = homePage.clickMyAccount();
//	myAccPage = loginPage.logIn();
//	Assert.assertTrue(myAccPage.addToWishList(),"wish list is not share successfully") ;
//}

@Test(priority = 1)
public void testSuccOrderPlacing()
{
	loginPage = homePage.clickMyAccount();
	myAccPage = loginPage.logIn();
	myAccPage.verifyOrderPlacing();
	
}

//@AfterClass
//public void closeBrowser()
//{
//	browserClose();
//}
}
