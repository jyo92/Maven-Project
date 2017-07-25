package com.selenium.common;

public class RandomNumber 
{
	public static int randonGen(int max, int min)
	{
	    int randomInteger = (int)(Math.random() * ((max - min) + 1)) + min;
	    return randomInteger;
	}
}
