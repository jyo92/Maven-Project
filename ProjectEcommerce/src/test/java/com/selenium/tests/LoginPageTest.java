package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.common.WriteExcel;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import com.selenium.pages.MyAccountPage;

@Listeners(com.selenium.common.Listeners.class)
public class LoginPageTest extends BrowserDetails
{
	public WebDriver driver;
	public HomePage homePage;
	public LoginPage loginPage;
	public MyAccountPage myAccPage;
	public String excelPath = "./src/main/resources/testData.xlsx";
	public String sheetName = "LoginPageTCs";
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 0)
	public void testLoginOnlyUname()
	{
		loginPage = homePage.clickMyAccount();
		if(loginPage.onlyUserNameLogin())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 3, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 3, 6, "fail");
		}
	}
	
	@Test(priority = 1)
	public void testLoginOnlyPwd()
	{
		if(loginPage.onlyPwdLogin())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "fail");
		}
	}
	
	@Test(priority = 2)
	public void testLogIn()
	{
		myAccPage = loginPage.logIn();
		if(myAccPage.verifyAcctDbdMsg())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "fail");
		}
	}
	
	@Test(priority = 3)
	public void testSignOut()
	{
		if(myAccPage.verifyLogOut())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "fail");
		}
	}

	@Test(enabled = false)
	public void testNewLogIn() throws Exception
	{
		Thread.sleep(6000);
		loginPage = homePage.clickMyAccount();
		if(loginPage.createNewAccount())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 6, "fail");
		}
	}
}
