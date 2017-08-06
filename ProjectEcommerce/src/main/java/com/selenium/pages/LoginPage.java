package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadExcel;
import com.selenium.common.ReadPropertyFile;

public class LoginPage 
{
	public WebDriver driver;
    public WebDriverWait wait;
    
    private By emailTxtBox = By.id("email");
    private WebElement emailTxtBoxElmt;
    
    private By pwdTxtBox = By.id("passwd");
    private WebElement pwdTxtBoxElmt;
    
    private By submitBtn =  By.id("SubmitLogin");
    private WebElement submitBtnElmt;
    
    private By authenticationHeading = By.xpath(".//*[text()='Authentication']");
    private WebElement authenticationHeadingElmt;
    private boolean authenticationHeadingResult;
    
    private String expecAuthenticationHeading = "Authentication";
    
    private String loginPrtyFilePath = "./src/main/resources/Login.property";
    
    public LoginPage(WebDriver driver)
    {
    	this.driver = driver;
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    public boolean loginPageHeading()
    {
    	authenticationHeadingElmt = driver.findElement(authenticationHeading);
    	return authenticationHeadingElmt.isDisplayed();
    }
    
    public boolean verifyLoginPageHeading()
    {
    	return authenticationHeadingElmt.getText().contains(expecAuthenticationHeading);
    }
    
    public CartPage logIn()
    {
    	enterUserName();
    	enterPwd();
    	submitBtnElmt = driver.findElement(submitBtn);
    	submitBtnElmt.click();
    	return new CartPage(driver);
    }
    
    public void enterUserName()
    {
    	emailTxtBoxElmt = driver.findElement(emailTxtBox);
    	emailTxtBoxElmt.sendKeys(ReadExcel.excelReading(ReadPropertyFile.propertyRead(loginPrtyFilePath, "excelPath" ), ReadPropertyFile.propertyRead(loginPrtyFilePath, "credentialSheet" ) , 1, 0));
    }
    
    public void enterPwd()
    {
    	pwdTxtBoxElmt = driver.findElement(pwdTxtBox);
    	pwdTxtBoxElmt.sendKeys(ReadExcel.excelReading(ReadPropertyFile.propertyRead(loginPrtyFilePath, "excelPath" ), ReadPropertyFile.propertyRead(loginPrtyFilePath, "credentialSheet" ) , 1, 1));
    }
}
