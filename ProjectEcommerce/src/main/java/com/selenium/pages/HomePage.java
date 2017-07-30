package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	private By proceedToChkOut = By.xpath(".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span");
	
	private By contactBtn = By.xpath(".//*[@id='contact-link']/a");
	private WebElement contactBtnElmt;
	
	private By signInBtn = By.xpath(".//*[@class='login']");
	private WebElement signInBtnElmt;
	
	
	public String ExpectedHomePageTitle = "My Store";
	public String ActualHomePageTitle;
	public boolean homeTitleResult;
	
	public Actions actions;
	public JavascriptExecutor jse;
	public WebDriverWait wait;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jse = (JavascriptExecutor)driver;
	}
	
	public String homePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyHomePageTitle()
	{
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
	
	public ContactPage clickContactUs()
	{
		contactBtnElmt = driver.findElement(contactBtn);
		contactBtnElmt.click();
		return new ContactPage(driver);
	}
	
	public LoginPage clickOnSignIn()
	{
		signInBtnElmt = driver.findElement(signInBtn);
		signInBtnElmt.click();
		return new LoginPage(driver);
	}
	
	
	
	public OrderPage hoverAndAddToCart()
	{
		jse.executeScript("window.scroll(0,850)");
		try
		{
		fadesdShortImgElmt = driver.findElement(fadesdShortImg);
		Actions actions = new Actions(driver);
		actions.moveToElement(fadesdShortImgElmt).build().perform();
		addToCartBtnElmt = driver.findElement(addToCartBtn);
		addToCartBtnElmt.click();
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToChkOut)).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new OrderPage(driver);
	}
}
