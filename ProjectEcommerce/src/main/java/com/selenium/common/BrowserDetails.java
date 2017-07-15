package com.selenium.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BrowserDetails 
{
	private WebDriver driver;
	public String excelLocation = "./src/main/resources/EcommerceProject.xlsx";
	
	public WebDriver getDriver()
	{
		return driver;
	}

	private void setDriver(String browserType, String appURL)
	{
		switch(browserType)
		{
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "ie":
			driver = initIeDriver(appURL);
			break;
		default:
			driver = initChromeDriver(appURL);
		
		}
		
	}


	private WebDriver initChromeDriver(String URL)
	{
		System.out.println("Initializing chrome browser");
		System.setProperty("webdriver.chrome.driver",ReadExcel.excelReading(excelLocation,"Drivers",1,1));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;
	}

	private WebDriver initFirefoxDriver(String URL)
	{
		System.out.println("Initializing firefox browser");
		WebDriver driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver initIeDriver(String URL) 
	{
		System.out.println("Initializing ie browser");
		System.setProperty("webdriver.chrome.driver",ReadExcel.excelReading(excelLocation,"Drivers",3,1));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;
		
	}
	
	
	@Parameters({"browserType","appURL"})
	@BeforeClass
	public void baseSetUp(String browserType, String appURL)
	{
		try
		{
			setDriver(browserType, appURL);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
