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

@Test(priority = 0)
public void testContactPageHeaderText()
{
	Assert.assertTrue(contactPage.verifyContactPageHeaderText(), "Header of contact page is not matched");
}

@Test(priority = 1)
public void testsubjectDownList()
{
	Assert.assertEquals(contactPage.actualDdownList, contactPage.expectedDdownList, "Subject drop down list is not correct");
}

@Test(enabled=false)
public void testEmailErrorMsg()
{
	Assert.assertTrue(contactPage.verifyErrMsgAllBlank(),"Either error messages are not correct or Error message is not displayed");
}

@Test(enabled=false)
public void testMessageBoxErrorMsg()
{
	Assert.assertTrue(contactPage.verifyErrIfMsgBoxBlank(),"Either error messages are not correct or Error message is not displayed");
}

@Test(enabled=false)
public void testsubjectddErrorMsg()
{
	Assert.assertTrue(contactPage.verifyErrIfDdBlank(),"Unwanted Error message is displayed");
}

@Test(priority = 2)
public void testFileUpload()
{
	contactPage.verifyFileUpload();
}
}
