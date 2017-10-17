package com.server.java.logic.build;



public class BuildFactory {

	public static BuildFactory bf = new BuildFactory();

	private BuildFactory() {

	}

	public static BuildFactory getInstance() {
		return bf;
	}

	
}
