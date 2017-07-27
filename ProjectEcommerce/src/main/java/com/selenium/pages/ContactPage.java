package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage 
{
public WebDriver driver;

private By contactPageHeader = By.xpath(".//*[@id='center_column']/h1");
private WebElement contactPageHeaderElmt;

private String expectedContactPageHeaderText = "CUSTOMER SERVICE - CONTACT US";
private String actualContactPageHeaderText;
private boolean contactPageHeaderResult;

public JavascriptExecutor jse;
public WebDriverWait wait;


public ContactPage(WebDriver driver)
{
	this.driver = driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	jse = (JavascriptExecutor)driver;
}

public String contactPageHeaderText()
{
	WebElement contactPageHeaderElmt = driver.findElement(contactPageHeader);
	return contactPageHeaderElmt.getText();
}

public boolean verifyContactPageHeaderText()
{
	actualContactPageHeaderText = contactPageHeaderText();
	contactPageHeaderResult = actualContactPageHeaderText.contains(expectedContactPageHeaderText);
	return contactPageHeaderResult;
}

}
