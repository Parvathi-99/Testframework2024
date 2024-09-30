package com.qa.opencart.exceptions;

public class BrowserException extends RuntimeException{
	public BrowserException(String msg)
	{
		RuntimeException r=new RuntimeException(msg);
		r.printStackTrace();
	}

}
