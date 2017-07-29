package com.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.common.ReadPropertyFile;
import com.selenium.common.UploadFile;

public class ContactPage 
{
public WebDriver driver;
private String contactPropertyFilePath = "./src/main/resources/Contact.property";

private By contactPageHeader = By.xpath(".//*[@id='center_column']/h1");
private WebElement contactPageHeaderElmt;

private By subjectDropDown = By.xpath(".//*[@id='id_contact']");
private WebElement subjectDropDownElmt;

private By sendBtn = By.id("submitMessage");
private WebElement sendBtnElmt;

private By genErrMsg = By.xpath("//*[text()='There is 1 error']");
private WebElement genErrMsgElmt;

private By emailErrMsg = By.xpath("//*[text()='Invalid email address.']");
private WebElement emailErrMsgElmt;

private By messageBoxErr = By.xpath("//*[text()='The message cannot be blank.']");
private WebElement messageBoxErrElmt;

private By subjectddError = By.xpath("//*[text()='Please select a subject from the list provided. '] ");
private WebElement subjectddErrorElmt;

private By chooseFileBtn = By.id("fileUpload");
private WebElement chooseFileBtnElmt;

private By emailTextBox = By.id("email");
private WebElement emailTextBoxElmt;
private String emailTextBoxContent;

private By messageBox = By.id("message");
private WebElement messageBoxElmt;
private String messageBoxContent;

private String expectedContactPageHeaderText = "CUSTOMER SERVICE - CONTACT US";
private String actualContactPageHeaderText;
private boolean contactPageHeaderResult;

private String expectedGenErrMsg = "There is 1 error";
private boolean GenErrMsgResult;

private String expectedEmailErrMsg = "Invalid email address.";
private boolean EmailErrMsgResult;

private String expectedMsgBoxErr = "The message cannot be blank.";
private boolean MsgBoxErrResult;

private String expectedSubjectddErr = "Please select a subject from the list provided.";
private boolean SubjectddErrResult;

public Select select;
public List<WebElement> subjectDownList;

public List<String> expectedDdownList;
public List<String> actualDdownList;

private String[] subjectDDownArray = null;

public JavascriptExecutor jse;
public WebDriverWait wait;

public boolean allMsgResult;


public ContactPage(WebDriver driver)
{
	this.driver = driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10);
	jse = (JavascriptExecutor)driver;
}

public String contactPageHeaderText()
{
	WebElement contactPageHeaderElmt = driver.findElement(contactPageHeader);
	return contactPageHeaderElmt.getText();
}

public boolean verifyContactPageHeaderText()
{
	actualContactPageHeaderText = contactPageHeaderText();
	contactPageHeaderResult = actualContactPageHeaderText.contains(expectedContactPageHeaderText);
	return contactPageHeaderResult;
}

public void subjectDropDownItems()
{
	subjectDropDownElmt = driver.findElement(subjectDropDown);
	if(subjectDropDownElmt.isDisplayed())
	{
	select = new Select(subjectDropDownElmt);
	subjectDownList = select.getOptions();
	}
	else
	{
	System.out.println("subject dropDownList not found");
	}
}

public void createActualDdownList()
{
	actualDdownList = new ArrayList<String>();
	for(WebElement option : subjectDownList)
	{
		actualDdownList.add(option.getText());
	}
}

public void createExpectedDdownList()
{
	expectedDdownList = new ArrayList<String>();
	subjectDDownArray = new String[] {"-- Choose --", "Customer service", "Webmaster"};
	for(String element : subjectDDownArray)
	{
		expectedDdownList.add(element);
	}
}

public void clkSend()
{
	sendBtnElmt = driver.findElement(sendBtn);
	if(sendBtnElmt.isDisplayed())
	{
	sendBtnElmt.click();
	}
	else
	{
		System.out.println("send Button not found");
	}
}

public boolean verifyErrMsgAllBlank()
{
	clkSend();
	try
	{
		emailErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrMsg));
		genErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(genErrMsg));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(emailErrMsgElmt.isDisplayed() && genErrMsgElmt.isDisplayed())
	{
	EmailErrMsgResult = resultOfTextOfEmailErrMsg();
	GenErrMsgResult = resultOfTextOfGenErrMsg();
		if(EmailErrMsgResult && GenErrMsgResult)
		{
			allMsgResult =  true;
		}
		else
		{
			allMsgResult =  false;
		}
	}
	else
	{
		allMsgResult =  false;
	}
	return allMsgResult;
}

public boolean verifyErrIfMsgBoxBlank()
{
	emailTextBoxElmt = driver.findElement(emailTextBox);
	emailTextBoxElmt.sendKeys(ReadPropertyFile.propertyRead(contactPropertyFilePath, "email"));
	clkSend();
	try
	{
		messageBoxErrElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(messageBoxErr));
		genErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(genErrMsg));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(messageBoxErrElmt.isDisplayed()&& genErrMsgElmt.isDisplayed())
	{
		MsgBoxErrResult = resultOfTextOfMsgBoxErr();
		GenErrMsgResult = resultOfTextOfGenErrMsg();
		if(MsgBoxErrResult && GenErrMsgResult)
		{
			allMsgResult =  true;
		}
		else
		{
			allMsgResult =  false;
		}
	}
	else
	{
		allMsgResult =  false;
	}
	return allMsgResult;
}

public boolean verifyErrIfDdBlank()
{
	emailTextBoxElmt = driver.findElement(emailTextBox);
	emailTextBoxElmt.sendKeys(ReadPropertyFile.propertyRead(contactPropertyFilePath, "email"));
	
	messageBoxElmt = driver.findElement(messageBox);
	messageBoxElmt.sendKeys(ReadPropertyFile.propertyRead(contactPropertyFilePath, "message"));
	
	clkSend();
	try
	{
		genErrMsgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(genErrMsg));
		subjectddErrorElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(subjectddError));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(genErrMsgElmt.isDisplayed() && subjectddErrorElmt.isDisplayed())
	{
		SubjectddErrResult = resultOfSubjectddErrMsg();
		GenErrMsgResult = resultOfTextOfGenErrMsg();
		if(SubjectddErrResult && GenErrMsgResult)
		{
			allMsgResult =  true;
		}
		else
		{
			allMsgResult =  false;
		}
	}
	else
	{
		allMsgResult =  false;
	}
	return allMsgResult;
}

public boolean resultOfTextOfEmailErrMsg()
{
	return emailErrMsgElmt.getText().contains(expectedEmailErrMsg);
}

public boolean resultOfTextOfMsgBoxErr()
{
	return messageBoxErrElmt.getText().contains(expectedMsgBoxErr);
}

public boolean resultOfTextOfGenErrMsg()
{
	return genErrMsgElmt.getText().contains(expectedGenErrMsg);
}

public boolean resultOfSubjectddErrMsg()
{
	return subjectddErrorElmt.getText().contains(expectedSubjectddErr);
}

public void verifyFileUpload()
{
	chooseFileBtnElmt = driver.findElement(chooseFileBtn);
	chooseFileBtnElmt.click();
	String uploadFilePath = ReadPropertyFile.propertyRead(contactPropertyFilePath, "uploadFilePath");
	UploadFile.fileUpload(uploadFilePath);
}

}
