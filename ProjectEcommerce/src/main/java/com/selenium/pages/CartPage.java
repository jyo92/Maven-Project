package com.selenium.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import com.selenium.common.ReadPropertyFile;

public class CartPage 
{
	public WebDriver driver;
	public JavascriptExecutor jse;
	
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
	}
	
    public void genMaxQtyErrMsg()
	{
	   WebElement qtyBox = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(cartPrptyPath, "qtyBox")));
	   qtyBox.sendKeys("000");
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
}
