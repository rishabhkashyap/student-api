package com.student.exception;

public class ResourceAlreadyExistException extends Exception {

	private static final long serialVersionUID = -5823518811587012972L;
	
	public ResourceAlreadyExistException(String message) {
		super(message);
	}

}
