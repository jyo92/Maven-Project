package com.selenium.common;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter
{
	public WebDriver driver;
	BrowserDetails browrDtls = new BrowserDetails();
	
	@Override
	public void onFinish(ITestContext arg0) 
	{
		
	}

	@Override
	public void onStart(ITestContext arg0) 
	{
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) 
	{
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		Object currentClass = result.getInstance();
		WebDriver driver = ((BrowserDetails) currentClass).getDriver();
        TakesScreenshot ss = (TakesScreenshot)driver;
        File file = ss.getScreenshotAs(OutputType.FILE);
        try 
        {
			org.apache.commons.io.FileUtils.copyFile(file,new File("./TakeScreenShot/"+result.getName()+".png"));
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        
	}

	@Override
	public void onTestSkipped(ITestResult arg0) 
	{
		
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println(result.getName()+" started.");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println(result.getName()+" passed.");
	}
	
}
