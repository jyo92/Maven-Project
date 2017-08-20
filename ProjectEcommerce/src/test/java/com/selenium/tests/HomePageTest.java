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
public class HomePageTest extends BrowserDetails
{
	public WebDriver driver;
	public HomePage homePage;
	public MobilePage Mobpage;
	public CartPage cartPage;
	public String excelPath = "./src/main/resources/testData.xlsx";
	public String sheetName = "HomePageTCs";
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 0)
	public void testHomePgeTitle() throws Exception
	{
	    if(homePage.verifyHomePageTitle())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 5, "fail");
		}
	}
	
	@Test(priority = 1)
	public void testEmptyCartMsg()
	{
		if(homePage.verifyEmptyCrtMsg())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 5, "fail");
		}
	}
	
	@Test(priority = 2)
	public void testAccOptions()
	{
		if(homePage.verifyAccOptions())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 5, "fail");
		}
	}
	
	@Test(priority = 3)
	public void testMobPgeHdr()
	{
		Mobpage = homePage.clickOnMobile();
		if(Mobpage.verifyMobPgeHdr())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 5, "fail");
		}
	}
	
	@Test(priority = 4)
	public void testItemRemoveFromCart()
	{
		Mobpage.addToCart();
		if(homePage.removeItemFrmCart())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 8, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 8, 5, "fail");
		}
	}
	
	@AfterClass
	public void closeBrowser()
	{
		browserClose();
	}
}
