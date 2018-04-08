package com.student.constants;

public enum StudentStatusCode {
	RESOURCE_CREATED("201");
	private String code;
	
	StudentStatusCode(String code){
		this.code=code;
	}
	public String  getValue(){
		return this.code;
	}

}
