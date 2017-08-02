package com.selenium.common;

public class RandomNumber 
{
	public static int randomGen(int max, int min)
	{
	    int randomInteger = (int)(Math.random() * ((max - min) + 1)) + min;
	    return randomInteger;
	}
}
