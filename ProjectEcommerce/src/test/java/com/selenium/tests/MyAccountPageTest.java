package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.common.WriteExcel;
import com.selenium.pages.MyAccountPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
@Listeners(com.selenium.common.Listeners.class)
public class MyAccountPageTest extends BrowserDetails 
{
public WebDriver driver;
public HomePage homePage;
public LoginPage loginPage;
public MyAccountPage myAccPage;
public String excelPath = "./src/main/resources/testData.xlsx";
public String sheetName = "AccountPageTCs";

@BeforeClass
public void setUp()
{
	baseSetUp();
	driver = getDriver();
	homePage = new HomePage(driver);
}

@Test(priority = 0)
public void testWshListShre()
{
	loginPage = homePage.clickMyAccount();
	myAccPage = loginPage.logIn();
	if(myAccPage.addToWishList())
	{
		WriteExcel.excelWriting(excelPath, sheetName, 3, 6, "pass");
	}
	else
	{
		WriteExcel.excelWriting(excelPath, sheetName, 3, 6, "fail");
	}
}

@Test(priority = 1)
public void testItemTotalPrice()
{
	if(myAccPage.chkQtyAndPrice())
	{
		WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "pass");
	}
	else
	{
		WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "fail");
	}
}

@Test(priority = 2)
public void testSuccOrderGen() throws Exception
{
	if(myAccPage.vfySuccOrderGen())
	{
		WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "pass");
	}
	else
	{
		WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "fail");
	}
}

@Test(enabled = false)
public void testChangePwd()
{
	loginPage = homePage.clickMyAccount();
	myAccPage = loginPage.logIn();
	if(myAccPage.changePwd())
	{
		WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "pass");
	}
	else
	{
		WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "fail");
	}
}

@AfterClass
public void closeBrowser()
{
	browserClose();
}
}
