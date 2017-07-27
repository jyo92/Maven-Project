package com.selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadPropertyFile 
{
	private static String propertyFileLocation = "./src/main/resources/DriverLocation.property";
	public static File file;
	public static FileInputStream fis;
	public static Properties prop;
	 
	public static String propertyRead(String key)
	{
		try
		{
			file = new File(propertyFileLocation);
			try 
			{
				fis = new FileInputStream(file);
				prop = new Properties();
				prop.load(fis);
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
		return prop.getProperty(key);
	}
}
