package com.selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel 
{
	public static File file;
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static void excelWriting(String excelLocation, String sheetName, int rowNum, int colNum, String data)
	{
		try
		{
			file = new File(excelLocation);
			try 
			{
				fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheet(sheetName);
				sheet.getRow(rowNum).createCell(colNum).setCellValue(data);
				fos = new FileOutputStream(file);
				workbook.write(fos);
				fis.close();
				fos.close();
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
	}
	
}
