package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadPropertyFile;

public class HomePage 
{
	public WebDriver driver;
    public WebElement homePgTitle;
	
	public Actions actions;
	public JavascriptExecutor jse;
	public WebDriverWait wait;
	
	private String homePrptyPath = "./src/main/resources/Home.property";
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor)driver;
	}
	
	public String homePageTitle()
	{
		homePgTitle = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "title")));
		String ActualHomePageTitle = homePgTitle.getText();
		System.out.println(ActualHomePageTitle);
		return ActualHomePageTitle;
	}
	
	public boolean verifyHomePageTitle()
	{
		String ExpectedHomePageTitle = "THIS IS DEMO SITE";
		return homePageTitle().contains(ExpectedHomePageTitle);
	}
	
	public MobilePage clickOnMobile()
	{
		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "mobile"))).click();
		return new MobilePage(driver);
	}
	
	public LoginPage clickMyAccount()
	{
		WebElement accLink = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "accountLink")));
		accLink.click();
		WebElement myAccLink = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "myAccount")));
		myAccLink.click();
		return new LoginPage(driver);
	}
		
}
