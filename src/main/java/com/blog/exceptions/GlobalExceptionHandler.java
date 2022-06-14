package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

@RestControllerAdvice
//used for handling exceptions globally
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse> (apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map <String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map <String, String > errorResp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((e) ->{
			String fieldName=  ((FieldError) e).getField();
			String message = e.getDefaultMessage();
			errorResp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>> (errorResp, HttpStatus.BAD_REQUEST);
	}
}
