package com.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.RandomNumber;
import com.selenium.common.ReadExcel;
import com.selenium.common.ReadPropertyFile;
import com.selenium.common.UploadFile;

public class MyAccountPage 
{
public WebDriver driver;

public String excelPath = "./src/main/resources/testData.xlsx";
public String sheetName = "UserCredentials";
private String accountPrtyFilePath = "./src/main/resources/Account.property";

public JavascriptExecutor jse;
public WebDriverWait wait;

public boolean dashBrdIsPresent = false;
public String shreListSuccMsg = "Your Wishlist has been shared";
public boolean shareResult = false;
public boolean checkTotalPrice = false;
public Logger log;
public String expSuccOdrMsg = "YOUR ORDER HAS BEEN RECEIVED.";
public boolean odrRslt = false;
boolean pwdChgRslt = false;

public MyAccountPage(WebDriver driver)
{
	this.driver = driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10);
	jse = (JavascriptExecutor)driver;
	log = Logger.getLogger(MyAccountPage.class);
}

public boolean verifyAcctDbdMsg()
{
	try
	{
		WebElement LoginSuccDbd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "logInDashBrd"))));
	    if(LoginSuccDbd.isDisplayed())
	    {
	    	dashBrdIsPresent = true;
	    	System.out.println("Login Successful");
	    }
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("login failed");
	}
	return dashBrdIsPresent;
}

public void addTvToWishList()
{
	WebElement tvElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "tvLink")));
	tvElmt.click();
	log.info("TV Link clicked........");
	try
	{
		WebElement wishLGElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishLG")));
		wishLGElmt.click();
		log.info("Add to wishlist clicked");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

public void addMobToWishList()
{
	WebElement MobElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "mobLink")));
	MobElmt.click();
	log.info("Mobile Link clicked........");
	try
	{
		WebElement wishIphElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishIph")));
		wishIphElmt.click();
		log.info("Add to wishlist clicked");
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}
public boolean addToWishList()
{
	addMobToWishList();//adding mobile to wish list
	driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "shreWshBtn"))).click();
	log.info("share btn clicked");
	WebElement emailBox = driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "emailBox")));
	emailBox.sendKeys(ReadPropertyFile.propertyRead(accountPrtyFilePath, "email"));
	log.info("email typed");
	WebElement msgBox = driver.findElement(By.cssSelector(ReadPropertyFile.propertyRead(accountPrtyFilePath, "msgBox")));
	msgBox.sendKeys(ReadPropertyFile.propertyRead(accountPrtyFilePath, "message"));
	log.info("message typed");
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "shareBtn"))).click();
	try
	{
		WebElement succMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "succMsg"))));
		String succMsg = succMsgElmt.getText();
		if(succMsg.contains(shreListSuccMsg))
		{
			System.out.println(succMsg);
			shareResult = true;
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return shareResult;
}
 

public boolean vfyWishItmIsPresent()
{
	WebElement wishListLink = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishLink")));
	wishListLink.click();
	log.info("my wishlist link clicked");
	boolean WishItemPresent = false;
	try
	{
	WebElement wishLstEtyMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "wishLstEtyMsg"))));	
	if(wishLstEtyMsg.isDisplayed())
	{
		System.out.println("Wish list is empty");
	}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		WishItemPresent = true;
	}
	return WishItemPresent;
}

public boolean chkQtyAndPrice()
{

		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "addCartBtn"))).click();
		log.info("Add to cart button clicked");
		WebElement qtyBox = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "qtyBox")));
		qtyBox.clear();
		int qty = RandomNumber.randomGen(10, 1);
		qtyBox.sendKeys(Integer.toString(qty));
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "updateBtn")))).click();
			log.info("update button clicked");
			checkTotalPrice = vfySubtotal(qty);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	return checkTotalPrice;
}

public boolean vfySubtotal(int qty)
{
	String priceString = (driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "price"))).getText()).replaceAll("[,]", "");
	String totalpriceString = (driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "totalPrice"))).getText()).replaceAll("[,]", "");
	double price = Double.parseDouble(priceString.substring(1));
	double actTotal = price * qty;
	double expTotal = Double.parseDouble(totalpriceString.substring(1));
	boolean checkTotal = false;
	if(actTotal == expTotal)
	{
		checkTotal = true;
	}
	return checkTotal;
}

public boolean vfySuccOrderGen() throws Exception
{
		try
		{
				Select select = new Select(driver.findElement(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "regIDdd"))));
				select.selectByVisibleText(ReadPropertyFile.propertyRead(accountPrtyFilePath, "ny"));
				log.info("state dd selected");
				driver.findElement(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "postCode"))).sendKeys("542222");
				log.info("postal code given");
				driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "estimateLnk"))).click();
				log.info("estimate link clicked");
				driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "checkout"))).click();
			    log.info("proceed to checkout clicked");
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "continueBtn")))).click();
			    log.info("shipping address is confirmed");
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "shipgMtdCont")))).click();
			    log.info("shipping method is selected");
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "monyOdrRadio")))).click();
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "payInfoCont")))).click();
			    log.info("payment information is given");
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "plceOrdBtn")))).click();
			    log.info("place order clicked");
			    odrRslt = succOrderMsgRslt();//checking successful order message is generated.
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return odrRslt;
}

public boolean succOrderMsgRslt()
{
	try
	{
	String actuSuccOdrMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "succOdrMsg")))).getText();
	odrRslt = actuSuccOdrMsg.contains(expSuccOdrMsg);
	System.out.println(actuSuccOdrMsg);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return odrRslt;
}

public boolean changePwd()
{
	try
	{
		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "chgPwd"))).click();
		log.info("change pwd link clicked");
		WebElement currPwd = driver.findElement(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "currPwd")));
		currPwd.sendKeys(ReadExcel.excelReading(excelPath, sheetName , 1, 1).toString());
		log.info("current pwd given");
		WebElement newPwd = driver.findElement(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "nwPwd")));
		newPwd.sendKeys(ReadExcel.excelReading(excelPath, sheetName , 1, 2).toString());
		log.info("new pwd given");
		WebElement confPwd = driver.findElement(By.id(ReadPropertyFile.propertyRead(accountPrtyFilePath, "confNewPwd")));
		confPwd.sendKeys(ReadExcel.excelReading(excelPath, sheetName , 1, 2).toString());
		log.info("new pwd given again");
		driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "saveBtn"))).click();
		log.info("save btn clicked");
		WebElement chgPwdSuccMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "chgPwdSuccMsg"))));
		if(chgPwdSuccMsg.isDisplayed())
		{
			pwdChgRslt = true;
			log.info("pwd change success msg came");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return pwdChgRslt;
}

public boolean verifyLogOut()
{
	boolean logOutRslt = false; //get result of logout test 
	try
	{
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "accountLink"))).click();
	log.info("my account clicked");
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "logOutLink"))).click();
	log.info("log out clicked");
	String logOutActMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(accountPrtyFilePath, "logOutMsg")))).getText();
	log.info("successful log out message");
	String logOutExpMsg = "You are now logged out";
	System.out.println(logOutActMsg);
	System.out.println(logOutExpMsg.toUpperCase());
	logOutRslt = logOutActMsg.contains(logOutExpMsg.toUpperCase());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return logOutRslt;
}
}
