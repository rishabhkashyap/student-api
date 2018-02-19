package com.student.exception;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(Long id) {
		super("Resource with id  "+id+" not found");
		
	}
	

}
