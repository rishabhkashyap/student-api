package com.student.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.student.exception.ResourceAlreadyExistException;
import com.student.exception.ResourceNotFoundException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException exception) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	protected ResponseEntity<Object> handleResourceAlreadyExist(ResourceAlreadyExistException exception) {
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, exception.getMessage(), exception);
		return buildResponseEntity(apiError);
	}
	
	 
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
//		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
//		return buildResponseEntity(apiError);
//	}


	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}

}
