package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadExcel;
import com.selenium.common.ReadPropertyFile;

public class LoginPage 
{
	public WebDriver driver;
    public WebDriverWait wait;
    public Logger log;
    public boolean loginRslt = false;
    
    public String excelPath = "./src/main/resources/testData.xlsx";
    public String sheetName = "UserCredentials";
    private String loginPrtyFilePath = "./src/main/resources/Login.property";
    
    public LoginPage(WebDriver driver)
    {
    	this.driver = driver;
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	wait = new WebDriverWait(driver, 10);
    	log = Logger.getLogger(LoginPage.class);
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
    	emailTxtBoxElmt.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 1, 0).toString());
    }
    
    public void enterPwd()
    {
    	WebElement pwdTxtBoxElmt = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "pwdTxtBox")));
    	pwdTxtBoxElmt.sendKeys(ReadExcel.excelReading(excelPath, sheetName , 1, 1).toString());
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
    			driver.navigate().refresh();
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("no Error message");
    		driver.navigate().refresh();
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
    			driver.navigate().refresh();
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("no Error message");
    		driver.navigate().refresh();
    	}
    	return loginRslt;
    }
    
    public boolean createNewAccount()
    {
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "crateAnAcct"))).click();
    	log.info("create account button clicked");
    	WebElement fname = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "fName")));
    	fname.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 5, 0).toString());
    	log.info("fname given");
    	WebElement lname = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "lName")));
    	lname.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 5, 1).toString());
    	log.info("lname given");
    	WebElement email = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "email")));
    	email.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 5, 2).toString());
    	log.info("email given");
    	WebElement pwd = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "pwd")));
    	pwd.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 5, 3).toString());
    	log.info("pwd given");
    	WebElement rePwd = driver.findElement(By.id(ReadPropertyFile.propertyRead(loginPrtyFilePath, "rePwd")));
    	rePwd.sendKeys(ReadExcel.excelReading(excelPath, sheetName, 5, 3).toString());
    	log.info("pwd given again");
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "register"))).click();
    	log.info("register button clicked");
    	boolean newRgRslt = false;
    	try
    	{
    		WebElement succRegMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(loginPrtyFilePath, "succRegMsg"))));
    		if(succRegMsg.isDisplayed())
    		{
    			newRgRslt = true;
    			log.info("reg success msg came");
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return newRgRslt;
    }
}
