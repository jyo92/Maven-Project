package com.selenium.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.common.ReadPropertyFile;

public class MobilePage 
{
public WebDriver driver;
public WebElement mobPgeHdr;
public String actCompPageHdr;

public WebElement sonyXPcartBtn;

public List<WebElement> MobList;
public List<String> unsortdMobList;
public List<String> expSortdMobList;
public List<WebElement> srtByNameList;
public List<String> actuSortdMobList;

public WebDriverWait wait;
public Logger log;

public Set<String> expCompItemSet;
public Set<String> actCompItemSet;
public Set<String> allWindowHndle;

public JavascriptExecutor jse;

public String MobPrtyLoc = "./src/main/resources/Mobile.property";

public MobilePage(WebDriver driver)
{
	this.driver = driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10);
    jse = (JavascriptExecutor)driver;
    log = Logger.getLogger(MobilePage.class);
}

public String getMobPgeHdr()
{
	mobPgeHdr = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "mobHdr")));
	System.out.println(mobPgeHdr.getText());
	return mobPgeHdr.getText();
}

public boolean verifyMobPgeHdr()
{
	String expHdr = "MOBILE";
	return getMobPgeHdr().equals(expHdr);
}

public void sortByName()
{
	jse.executeScript("window.scroll(0, 400)");
	Select select = new Select(driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "srtByDd"))));
	select.selectByVisibleText("Name");
}

public List<String> getActuSrtedList()
{
	sortByName();
	srtByNameList = driver.findElements(By.className(ReadPropertyFile.propertyRead(MobPrtyLoc, "prdctList")));
	actuSortdMobList = new ArrayList<String>();
	for(WebElement item : srtByNameList)
	{
		actuSortdMobList.add(item.getText());
	}
	return actuSortdMobList;
}

public List<String> unsortedList()
{
	MobList = driver.findElements(By.className(ReadPropertyFile.propertyRead(MobPrtyLoc, "prdctList")));
	unsortdMobList = new ArrayList<String>();
	for(WebElement item : MobList)
	{
		unsortdMobList.add(item.getText());
	}
	return unsortdMobList;
}

public List<String> getExpSrtedList()
{
	expSortdMobList = getSortedListAsc(unsortedList());
	return expSortdMobList;
}

public List<String> getSortedListAsc(List<String> list)
{
	Collections.sort(list);
	return list;
}

public boolean compareTwoMobLists()
{
	boolean sortedResult = false;
	getExpSrtedList().removeAll(getActuSrtedList());
	if(expSortdMobList.isEmpty())
	{
		sortedResult = true;
	}
	return sortedResult;
}

public CartPage addToCart()
{
	sonyXPcartBtn = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "cartXp")));
	sonyXPcartBtn.click();
	log.info("sony XPeria cartBtn clicked");
	driver.navigate().back();
	WebElement galaxyCartBtn = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "cartGalaxy")));
	galaxyCartBtn.click();
	log.info("galaxy cartBtn clicked");
	return new CartPage(driver); 
}

public Set<String> getAddToCompExpSet()
{
	WebElement compXpElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "compXp")));
	compXpElmt.click();
	expCompItemSet = new HashSet<String>();
	expCompItemSet.add(ReadPropertyFile.propertyRead(MobPrtyLoc, "XpText"));
	WebElement compIPhnElmt = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "compIPhn")));
	compIPhnElmt.click();
	expCompItemSet.add(ReadPropertyFile.propertyRead(MobPrtyLoc, "IPhnText"));
	return expCompItemSet;
}

public Set<String> getAddToCompActSet()
{
	//switching to new window after clicking compare button
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "compBtn"))).click();
	String parentWindowHndle = driver.getWindowHandle();
	allWindowHndle = driver.getWindowHandles();
	try
	{
		for(String window : allWindowHndle)
		{
			if(!window.equalsIgnoreCase(parentWindowHndle))
			{
			driver.switchTo().window(window);
			}
		}
		actCompPageHdr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "cmpPgHdr")))).getText();
		System.out.println(actCompPageHdr);
		List<WebElement> comPgeList = driver.findElements(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "cmpPrdct")));
		actCompItemSet = new HashSet<String>();
		for(WebElement element : comPgeList)
		{
			actCompItemSet.add(element.getAttribute("innerHTML"));
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	driver.close();
	driver.switchTo().window(parentWindowHndle);//coming back to parent window
	return actCompItemSet;
}
public boolean verifyCompPgeHdr()
{
	String expCompPgeHdr = "COMPARE PRODUCTS";
	return actCompPageHdr.contains(expCompPgeHdr);
}

public boolean compSetsOfMobile()
{
	boolean compResult = false;
	Set<String> expSet = getAddToCompExpSet();
	Set<String> actSet = getAddToCompActSet();
	expSet.removeAll(actSet);
	if(expSet.isEmpty())
	{
		compResult = true;
	}
	return compResult;
}
public boolean verifyXperiaPrice()
{
	String expPrice = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "XpExpPrceElmt"))).getText();
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "XpElmt"))).click();
	String actPrice = driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "XpActPrceElmt"))).getText();
	return expPrice.equals(actPrice);
}

public boolean backToHomePage()
{
	driver.findElement(By.xpath(ReadPropertyFile.propertyRead(MobPrtyLoc, "logo"))).click();
	return (driver.getTitle().equals("Home page"));
}

}
