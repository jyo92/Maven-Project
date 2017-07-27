package com.selenium.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.pages.ContactPage;
import com.selenium.pages.HomePage;

public class ContactPageTest extends BrowserDetails 
{
private WebDriver driver;
private HomePage homePage;
private ContactPage contactPage;

@BeforeClass
public void setUp()
{
	driver = getDriver();
	homePage = new HomePage(driver);
	contactPage = homePage.clickContactUs();
}

@Test
public void testContactPageHeaderText()
{
	Assert.assertTrue(contactPage.verifyContactPageHeaderText(), "Header of contact page is not matched");
}
}
