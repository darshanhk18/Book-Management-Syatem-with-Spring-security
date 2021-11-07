package com.hcl.gradedassignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.gradedassignment.beans.ResponseMessage;

@RestControllerAdvice
public class BookessExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseMessage> handleEmployeeIdException(HttpServletRequest request, Exception ex){
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(404);
		return new ResponseEntity<ResponseMessage>(rm,HttpStatus.NOT_FOUND);	
	}


}
