package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage 
{
	public WebDriver driver;
	
	private By proceedToChkOutBtn = By.xpath(".//*[text()='Proceed to checkout']");
	private WebElement proceedToChkOutBtnElmt;
	
	private By cartText = By.xpath(".//*[text()='Your shopping cart is empty.']");
	private WebElement cartTextElmt; 
	
	private By orderpgAddressHeading = By.xpath(".//h1[text()='Addresses']"); 
	private WebElement orderpgAddressHeadingElmt;
	
	public String cartExpectedResult = "Your shopping cart is empty.";
	public String cartActualResult;
	public boolean cartResult;
	
	public JavascriptExecutor jse;
	
    public WebDriverWait wait;
	
	public OrderPage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jse = (JavascriptExecutor)driver;
	}
	
    public boolean VerifyCartIsEmpty()
	{
	    cartTextElmt = driver.findElement(cartText);
	    cartActualResult = cartTextElmt.getText();
	    System.out.println(cartActualResult);
	    cartResult = cartActualResult.contains(cartExpectedResult);
	    return cartResult;
    }
	
	public LoginPage checkOutBtnClick()
	{
		proceedToChkOutBtnElmt = driver.findElement(proceedToChkOutBtn);
		proceedToChkOutBtnElmt.click();
		return new LoginPage(driver);
	}
	
	public boolean isAddressHdrDisplayed()
	{
		orderpgAddressHeadingElmt = driver.findElement(orderpgAddressHeading);
		return orderpgAddressHeadingElmt.isDisplayed();
	}
	
}
