package com.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadPropertyFile;
import com.selenium.common.UploadFile;

public class MyAccountPage 
{
public WebDriver driver;
private String accountPrtyFilePath = "./src/main/resources/Account.property";

public JavascriptExecutor jse;
public WebDriverWait wait;

public boolean dashBrdIsPresent = false;
public String shreListSuccMsg = "Your Wishlist has been shared";
public boolean shareResult = false;
public Logger log;


public MyAccountPage(WebDriver driver)
{
	this.driver = driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10);
	jse = (JavascriptExecutor)driver;
	log = Logger.getLogger(MyAccountPage.class);
}

public boolean verifyAcctDbdMsg()
{
	try
	{
		WebElement LoginSuccDbd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "logInDashBrd"))));
	    if(LoginSuccDbd.isDisplayed())
	    {
	    	dashBrdIsPresent = true;
	    	System.out.println("Login Successful");
	    }
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("login failed");
	}
	return dashBrdIsPresent;
}

public boolean addToWishList()
{
	WebElement tvElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "tvLink")));
	tvElmt.click();
	log.info("TV clicked........");
	WebElement wishLGElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishLG")));
	wishLGElmt.click();
	driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "shreWshBtn"))).click();
	WebElement emailBox = driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "emailBox")));
	emailBox.sendKeys(ReadPropertyFile.propertyRead(accountPrtyFilePath, "email"));
	WebElement msgBox = driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "msgBox")));
	msgBox.sendKeys(ReadPropertyFile.propertyRead(accountPrtyFilePath, "message"));
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "shareBtn"))).click();
	try
	{
		WebElement succMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "succMsg"))));
		String succMsg = succMsgElmt.getText();
		if(succMsg.contains(shreListSuccMsg))
		{
			shareResult = true;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return shareResult;
}

public void verifyOrderPlacing()
{
	WebElement wishListLink = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishLink")));
	wishListLink.click();
	log.info("my wishlist link clicked");
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "addCartBtn"))).click();
	log.info("Add to cart button clicked");
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "checkout"))).click();
	log.info("proceed to checkout clicked");
}

}
