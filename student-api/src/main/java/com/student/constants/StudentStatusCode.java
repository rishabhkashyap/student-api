package com.student.constants;

public enum StudentStatusCode {
	RESOURCE_CREATED("201"),
	RESOURCE_UPDATED("200");
	
	private String code;
	
	StudentStatusCode(String code){
		this.code=code;
	}
	public String  getValue(){
		return this.code;
	}

}
