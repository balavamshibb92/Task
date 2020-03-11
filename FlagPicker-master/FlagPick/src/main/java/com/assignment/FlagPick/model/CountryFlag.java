package com.assignment.FlagPick.model;

public class CountryFlag {
	
	private String name;
	
	private String flag;
	
	public CountryFlag(String name, String flag) {
		super();
		this.name = name;
		this.flag = flag;
	}

	public CountryFlag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
