package com.selenium.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.RandomNumber;

public class HomePage 
{
public WebDriver driver;
	
	private By cart = By.xpath("//a[@title='View my shopping cart']");
	private WebElement cartElmt;
	
	private By addToCartBtn = By.xpath("//span[text()='Add to cart']");
	private String addToCartBtnTxt = "Add to cart";
	public List<WebElement> addToCartBtns;
	
	private By proceedToChkOut = By.xpath("//*[contains(text(),'Proceed to checkout')]");
	
	private By contactBtn = By.xpath(".//*[@id='contact-link']/a");
	private WebElement contactBtnElmt;
	
	private By signInBtn = By.xpath(".//*[@class='login']");
	private WebElement signInBtnElmt;
	
	private By fadesdShortImg = By.xpath(".//*[@id='homefeatured']/li[1]/div/div[2]");
	private WebElement fadesdShortImgElmt;
	
	private By blouseImg = By.xpath(".//*[@id='homefeatured']/li[2]/div/div[2]");
	private WebElement blouseImgElmt;
	
	private By printedDress1Img = By.xpath(".//*[@id='homefeatured']/li[3]/div/div[2]");
	private WebElement printedDress1ImgElmt;
	
	private By printedDress2Img = By.xpath(".//*[@id='homefeatured']/li[4]/div/div[2]");
	private WebElement printedDress2ImgElmt;
	
	private By printedSumm1Img = By.xpath(".//*[@id='homefeatured']/li[5]/div/div[2]");
	private WebElement printedSumm1ImgElmt;
	
	private By printedSumm2Img = By.xpath(".//*[@id='homefeatured']/li[6]/div/div[2]");
	private WebElement printedSumm2ImgElmt;
	
	private By chiffonImg = By.xpath(".//*[@id='homefeatured']/li[7]/div/div[2]");
	private WebElement chiffonImgElmt;
	
	public String ExpectedHomePageTitle = "My Store";
	public String ActualHomePageTitle;
	public boolean homeTitleResult;
	
	public HashMap<Integer, By> hashMap;
	public Set<String> setOfDresses;
	public HashMap<Integer, String> MapOfProductName ;
	
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
	
	public List<WebElement> getAllItems()
	{
		List<WebElement> items = driver.findElements(By.className("product-name"));
		return items;
	}
	
	public OrderPage hoverAndAddToCart()
	{
		jse.executeScript("window.scroll(0,850)");
		try
		{
		fadesdShortImgElmt = driver.findElement(fadesdShortImg);
		Actions actions = new Actions(driver);
		By dressImg = getElmtFrmMap();
		actions.moveToElement(driver.findElement(dressImg)).build().perform();
		clickAddToCartBtn();
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToChkOut)).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new OrderPage(driver);
	}
	
	public void clickAddToCartBtn()
	{
		addToCartBtns = driver.findElements(addToCartBtn);
		for(WebElement addToCartBtnElmt : addToCartBtns)
		{
			if(addToCartBtnElmt.getText().contains(addToCartBtnTxt))
			{
				addToCartBtnElmt.click();
				break;
			}
		}
	}
	
	public void mapOfDresses()
	{
		hashMap = new HashMap<Integer,By>();
		hashMap.put(1, fadesdShortImg);
		hashMap.put(2, blouseImg);
		hashMap.put(3, printedDress1Img);
		hashMap.put(4, printedDress2Img);
		hashMap.put(5, printedSumm1Img);
		hashMap.put(6, printedSumm2Img);
		hashMap.put(7, chiffonImg);
	}
	
	public void createMapOfProductName()
	{
		MapOfProductName = new HashMap<Integer, String>();
	}
	
	public By getElmtFrmMap()
	{
		mapOfDresses();
		int rand = RandomNumber.randomGen(1, 7);
		getsetOfDresses(rand);
		return hashMap.get(rand);
	}
	
	public void getsetOfDresses(int rand)
	{
		
	}
	
}
