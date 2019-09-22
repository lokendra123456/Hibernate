package com.learning.java8plus;

import java.util.List;

public class Department {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	private List<SubDepartMent> sunDepartments;

	public List<SubDepartMent> getSunDepartments() {
		return sunDepartments;
	}

	public void setSunDepartments(List<SubDepartMent> sunDepartments) {
		this.sunDepartments = sunDepartments;
	}

}
