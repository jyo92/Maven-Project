package com.selenium.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadPropertyFile;

public class HomePage 
{
	public WebDriver driver;
    public WebElement homePgTitle;
	
	public Actions actions;
	public JavascriptExecutor jse;
	public WebDriverWait wait;
	public Logger log;
	public String removeSuccMsg;
	public boolean removeRslt = false;
	public Alert alert;
	
	private String homePrptyPath = "./src/main/resources/Home.property";
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor)driver;
		log = Logger.getLogger(HomePage.class);
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
	
	public boolean verifyEmptyCrtMsg()
	{
		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "cart"))).click();
		log.info("click on empty cart");
		String ExpMsg = "You have no items in your shopping cart.";
		return getActEmtyCrtMsg().contains(ExpMsg);
	}
	
	public String getActEmtyCrtMsg()
	{
		String actuMsg = null;
		try
		{
			actuMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "emptyMsg")))).getText();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "close")))).click();
			log.info("close empty cart msg");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return actuMsg;
	}
	
	public boolean removeItemFrmCart()
	{
		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "cart"))).click();
		log.info("click on cart");
		try
		{
			List<WebElement> removeList = driver.findElements(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "removeLink")));
			for(WebElement remove : removeList)
			{
				remove.click();
				alert = driver.switchTo().alert();
				alert.accept();
			}
			removeSuccMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReadPropertyFile.propertyRead(homePrptyPath, "removeSuccMsg")))).getText();
		    System.out.println(removeSuccMsg);
			log.info("Remove successful msg came");
			String expMsg = "Item was removed successfully.";
			removeRslt = expMsg.contains(removeSuccMsg);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "close")))).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return removeRslt;
	}	
	
	public List<String> getExpAccOptsList()
	{
		List<String> expList = new ArrayList<String>(Arrays.asList("My Account", "My Wishlist", "My Cart", "Checkout", "Register","Log In"));//Converting array to list
		return expList;		
	}
	
	public boolean verifyAccOptions()
	{
		WebElement accLink = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "accountLink")));
		accLink.click();
		log.info("Account link clicked");
		boolean getRslt = false;
		try
		{
			List<WebElement> accOptList = driver.findElements(By.xpath(ReadPropertyFile.propertyRead(homePrptyPath, "acctOpt")));
			log.info("Account related items displayed");
			List<String> actList = new ArrayList<String>();
			for(WebElement element : accOptList)
			{
				actList.add(element.getText());
			}
			getRslt = compareLists(getExpAccOptsList(), actList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getRslt;
	}
	
	public boolean compareLists(List<String>expList, List<String>actList)
	{
		boolean rslt = false;
		expList.removeAll(actList);
		if(expList.isEmpty())
		{
			rslt = true; 
		}
		else
		{
			rslt = false;
		}
		return rslt;
	}
	
	
}
