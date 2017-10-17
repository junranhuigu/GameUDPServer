package com.server.java.logic.build;



public class DecomposeFactory {
	private static DecomposeFactory instance;
	
	private DecomposeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static DecomposeFactory getInstance() {
		if(instance == null){
			instance = new DecomposeFactory();
		}
		return instance;
	}

}
