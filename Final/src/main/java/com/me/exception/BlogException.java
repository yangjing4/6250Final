package com.me.exception;

public class BlogException extends Exception
{
	public BlogException(String message)
	{
		super("AdvertException-"+ message);
	}
	
	public BlogException(String message, Throwable cause)
	{
		super("AdvertException-"+ message,cause);
	}
}