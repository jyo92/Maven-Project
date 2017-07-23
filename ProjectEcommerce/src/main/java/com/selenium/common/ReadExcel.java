package com.selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class ReadExcel 
{	
public static File file;
public static FileInputStream fis;
public static XSSFWorkbook workbook;
public static XSSFSheet sheet;
public static String result;
public static String excelReading(String excelLocation, String sheetName, int rowNum, int colNum)
{
	try
	{
		file = new File(excelLocation);
		try 
		{
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			result = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			fis.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return result;
}
}
