package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.common.WriteExcel;
import com.selenium.pages.CartPage;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;
//@Listeners(com.selenium.common.Listeners.class)
public class CartPageTest extends BrowserDetails
{
	public WebDriver driver;
	public HomePage homePage;
	public CartPage cartPage;
	public MobilePage mobilePage;
	public String excelPath = "./src/main/resources/testData.xlsx";
	public String sheetName = "CartPageTCs";
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 0)
	public void testSuccCoupMsg()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		if(cartPage.verifySuccCoupMsg())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 6, "fail");
		}
	}
	
	@Test(priority = 1)
	public void testMaxQtyErrMsg()
	{
		if(cartPage.verifyMaxQtyErrMsg())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 6, "fail");
		}
	}

	@Test(priority = 2)
	public void testItemRemoveFrmCart()
	{
		if(cartPage.RemoveFrmCart())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 6, "fail");
		}
	}
	
	@Test(priority = 3)
	public void testEmtyCartMsg()
	{
		if(cartPage.VerifyEmptyCrtMsg())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 6, "fail");
		}
		cartPage.backToHomePage();//going back to home page
	}
	
	@Test(priority = 4)
	public void testContinueShopp()
	{
		mobilePage = homePage.clickOnMobile();
		cartPage = mobilePage.addToCart();
		if(cartPage.verifyContShopp())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 8, 6, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 8, 6, "fail");
		}
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
	
	
}
