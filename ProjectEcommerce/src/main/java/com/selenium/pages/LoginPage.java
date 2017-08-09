package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import com.selenium.common.ReadExcel;
import com.selenium.common.ReadPropertyFile;

public class LoginPage 
{
	public WebDriver driver;
    public WebDriverWait wait;
    public boolean loginRslt = false;
    
    private String loginPrtyFilePath = "./src/main/resources/Login.property";
    
    public LoginPage(WebDriver driver)
    {
    	this.driver = driver;
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	wait = new WebDriverWait(driver, 10);
    }
    
    public MyAccountPage logIn()
    {
    	enterUserName();
    	enterPwd();
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "logInBtn"))).click();
    	return new MyAccountPage(driver);
    }
    
    public void enterUserName()
    {
    	WebElement emailTxtBoxElmt = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "emilTxtBox")));
    	emailTxtBoxElmt.sendKeys(ReadExcel.excelReading(ReadPropertyFile.propertyRead(loginPrtyFilePath, "excelPath" ), ReadPropertyFile.propertyRead(loginPrtyFilePath, "credentialSheet" ) , 1, 0));
    }
    
    public void enterPwd()
    {
    	WebElement pwdTxtBoxElmt = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "pwdTxtBox")));
    	pwdTxtBoxElmt.sendKeys(ReadExcel.excelReading(ReadPropertyFile.propertyRead(loginPrtyFilePath, "excelPath" ), ReadPropertyFile.propertyRead(loginPrtyFilePath, "credentialSheet" ) , 1, 1));
    }
    
    public boolean onlyUserNameLogin()
    {
    	enterUserName();
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "logInBtn"))).click();
    	try
    	{
    		WebElement LoginErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "loginErrMsg"))));
    		if(LoginErrMsgElmt.isDisplayed())
    		{
    			loginRslt = true;
    			System.out.println("Error message came");
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("no Error message");
    	}
    	return loginRslt;
    }
    
    public boolean onlyPwdLogin()
    {
    	enterPwd();
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "logInBtn"))).click();
    	try
    	{
    		WebElement LoginErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "loginErrMsg"))));
    		if(LoginErrMsgElmt.isDisplayed())
    		{
    			loginRslt = true;
    			System.out.println("Error message came");
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("no Error message");
    	}
    	return loginRslt;
    }
    
}
