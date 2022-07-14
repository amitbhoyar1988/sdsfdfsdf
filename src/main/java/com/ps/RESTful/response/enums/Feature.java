package com.ps.RESTful.response.enums;

public enum Feature {

	Query("query"),
	EMPLOYEE_MASTER("employee"),
	PAYROLL("payroll");
	
	private String feature;
	
	private Feature(String feature) {
		this.feature = feature;
	}
	
	public String getFeature() {
		return feature;
	}
}
