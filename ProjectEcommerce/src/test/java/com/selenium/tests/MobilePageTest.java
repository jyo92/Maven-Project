package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.common.BrowserDetails;
import com.selenium.common.ReadPropertyFile;
import com.selenium.common.WriteExcel;
import com.selenium.pages.HomePage;
import com.selenium.pages.MobilePage;
@Listeners(com.selenium.common.Listeners.class)
public class MobilePageTest extends BrowserDetails
{
	public WebDriver driver;
	public HomePage homePage;
	public MobilePage mobPage;
	public String excelPath = "./src/main/resources/testData.xlsx";
	public String sheetName = "MobilePageTCs";
	
	@BeforeClass
	public void setUp()
	{
		baseSetUp();
		driver = getDriver();
		homePage = new HomePage(driver);
	}
	
	
	@Test(priority = 0)
	public void testSortByName()
	{
		mobPage = homePage.clickOnMobile();
		if(mobPage.compareTwoMobLists())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 4, 5, "fail");
		}
	}
	
	@Test(priority = 1)
	public void testCompPgeHdr()
	{
		if(mobPage.verifyCompPgeHdr())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 5, 5, "fail");
		}
	}
	
	@Test(priority = 2)
	public void testAddToCompItms()
	{
		if(mobPage.compSetsOfMobile())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 6, 5, "fail");
		}
	}
	
	@Test(priority = 3)
	public void testBackToHomePge()
	{
		if(mobPage.backToHomePage())
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 5, "pass");
		}
		else
		{
			WriteExcel.excelWriting(excelPath, sheetName, 7, 5, "fail");
		}
	}
	
	@Test(priority = 4)
	public void testXpPrice()
	{
		mobPage = homePage.clickOnMobile();
		if(mobPage.verifyXperiaPrice())
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
