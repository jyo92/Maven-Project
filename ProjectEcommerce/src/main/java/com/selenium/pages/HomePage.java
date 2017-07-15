package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
	public WebDriver driver;
	
	private By cart = By.xpath(".//*[@id='header']/div[3]/div/div/div[3]/div/a");
	private WebElement cartElmt;
	
	private By fadesdShortImg = By.xpath(".//*[@id='homefeatured']/li[1]/div/div[2]");
	private WebElement fadesdShortImgElmt;
	
	private By addToCartBtn = By.xpath(".//*[@id='homefeatured']/li[1]/div/div[2]/div[2]/a[1]/span");
	private WebElement addToCartBtnElmt;
	
	public String ExpectedHomePageTitle;
	public String ActualHomePageTitle;
	public boolean homeTitleResult;
	public Actions actions;
	public JavascriptExecutor jse;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		jse = (JavascriptExecutor)driver;
	}
	
	public String homePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyHomePageTitle()
	{
		ExpectedHomePageTitle = "My Store";
		ActualHomePageTitle = homePageTitle();
		homeTitleResult = ActualHomePageTitle.equalsIgnoreCase(ExpectedHomePageTitle);
		return homeTitleResult;
	}
	
	public OrderPage clickOnCart()
	{
		cartElmt = driver.findElement(cart);
		cartElmt.click();
		return new OrderPage(driver);
	}
	
	public void hoverAndAddToCart()
	{
		jse.executeScript("window.scroll(0,850)");
		fadesdShortImgElmt = driver.findElement(fadesdShortImg);
		Actions actions = new Actions(driver);
		actions.moveToElement(fadesdShortImgElmt).build().perform();
		addToCartBtnElmt = driver.findElement(addToCartBtn);
		addToCartBtnElmt.click();
	}
}
