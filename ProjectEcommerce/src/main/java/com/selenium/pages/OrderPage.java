package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage 
{
	private By cartText = By.xpath(".//*[text()='Your shopping cart is empty.']");
	private WebElement cartTextElmt; 
	public WebDriver driver;
	public String cartExpectedResult;
	public String cartActualResult;
	public boolean cartResult;
	
	public OrderPage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
    public boolean VerifyCartIsEmpty()
    {
    	cartExpectedResult = "Your shopping cart is empty.";
    	cartTextElmt = driver.findElement(cartText);
    	cartActualResult = cartTextElmt.getText();
    	System.out.println(cartActualResult);
    	cartResult = cartActualResult.contains(cartExpectedResult);
    	return cartResult;
    }
}
