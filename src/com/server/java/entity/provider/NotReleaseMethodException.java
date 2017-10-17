package com.server.java.entity.provider;
/**
 * 未实现某个方法的异常
 * */
public class NotReleaseMethodException extends Exception{
	
	private static final long serialVersionUID = 5112066237161357335L;

	public NotReleaseMethodException(String msg) {
		super(msg);
	}
	
}
