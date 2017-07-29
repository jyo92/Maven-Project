package com.selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadPropertyFile 
{
	public static File file;
	public static FileInputStream fis;
	public static Properties prop;
	public static String value;
	 
	public static String propertyRead(String propertyFileLocation, String key)
	{
		try
		{
			file = new File(propertyFileLocation);
			try 
			{
				fis = new FileInputStream(file);
				prop = new Properties();
				prop.load(fis);
				value = prop.getProperty(key);
				fis.close();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return value;
	}
}
