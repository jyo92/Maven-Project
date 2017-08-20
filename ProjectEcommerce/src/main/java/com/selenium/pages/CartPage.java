package com.selenium.pages;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.common.ReadPropertyFile;

public class CartPage 
{
	public WebDriver driver;
	public JavascriptExecutor jse;
	public Logger log;
	
    public WebDriverWait wait;
    public WebElement actuQtyErrMsgElmt;
    public WebElement updateBtn;
    public String expcErrMsg = "Some of the products cannot be ordered in requested quantity";
    public boolean maxQtyErrVisible = false;
    public boolean emtyCrtMsgVisible = false;
    
    private String cartPrptyPath = "./src/main/resources/Cart.property";
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		jse = (JavascriptExecutor)driver;
		log = Logger.getLogger(CartPage.class);
	}
	
    public void genMaxQtyErrMsg()
	{
	   WebElement qtyBox = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "qtyBox")));
	   qtyBox.clear();
	   qtyBox.sendKeys("1000");
	   try
	   {
	   updateBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "updateBtn"))));
	   updateBtn.click();
	   actuQtyErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "qtyErrMsg"))));
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   System.out.println("Either update Btn is not visible or no err msg came");
	   }
    }
    
    public boolean verifyMaxQtyErrMsg()
    {
    	genMaxQtyErrMsg();
    	if(actuQtyErrMsgElmt.isDisplayed())
    	{
    	String actuErrMsg = actuQtyErrMsgElmt.getText();
    	maxQtyErrVisible =  actuErrMsg.contains(expcErrMsg);
    	}
    	return maxQtyErrVisible;
    }
    
    public boolean VerifyEmptyCrtMsg()
    {
    	WebElement emptyCrtLink = driver.findElement(By.id(ReadPropertyFile.propertyRead(cartPrptyPath, "emptyCrtLnk")));
    	emptyCrtLink.click();
    	try
    	{
    		WebElement emptyCrtActualMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "emptyCrtMsg"))));
    	    String emptyCrtActualMsg = emptyCrtActualMsgElmt.getText();
    	    String emptyCrtExpMsg = "SHOPPING CART IS EMPTY";
    	    emtyCrtMsgVisible = emptyCrtActualMsg.contains(emptyCrtExpMsg);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("Empty cart error message has not come");
    	}
    	return emtyCrtMsgVisible;
    }
    
    public boolean RemoveFrmCart()
    {
    	List<WebElement> bfrRemovList = driver.findElements(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "pdctName")));
    	for(WebElement ele : bfrRemovList)
    	{
    		System.out.println(ele.getText());
    	}
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "removeElmt"))).click();
    	log.info("remove Elmt clicked");
    	List<WebElement> afterRemovList = driver.findElements(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "pdctName")));
    	for(WebElement ele : afterRemovList)
    	{
    		System.out.println(ele.getText());
    	}
    	bfrRemovList.removeAll(afterRemovList);
    	boolean rslt;
    	if(bfrRemovList.isEmpty())
    	{
    		rslt = false;
    	}
    	else
    	{
    		rslt = true;
    	}
    	return rslt;
    }
    
    public boolean verifyContShopp()
    {
    	String tvPageTitle = "TV";
    	String mobPageTitle = "Mobile";
    	boolean rslt = false;
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "contShopp"))).click(); //clicking on continue shopping
    	if(driver.getTitle().equals(mobPageTitle)|| driver.getTitle().equals(tvPageTitle))
    	{
    		rslt = true;
    	}
    	return rslt;
    }
    
    public boolean verifySuccCoupMsg()
    {
    	WebElement discountTxtBox = driver.findElement(By.id(ReadPropertyFile.propertyRead(cartPrptyPath, "couTxtBox")));
    	discountTxtBox.sendKeys("GURU5");
    	log.info("code given in txt box");
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "applyLink"))).click();
    	log.info("apply link clicked");
    	String actMsg = null;
    	String expMsg = "Coupon code \"GURU50\" was applied.";
    	boolean rslt = false;
    	try
    	{
    		actMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "succCoupMsg")))).getText();
    		log.info("discount msg came");
    		if(actMsg.contains(expMsg))
    		{
    			rslt = true;
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return rslt;
    }
    
    public void backToHomePage()
    {
    	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "logo"))).click();
    }
}
