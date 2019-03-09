package com.rakuten.fullstackrecruitmenttest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = EmployeeDataIsInvalidException.class)
	   public ResponseEntity<Object> exception(EmployeeDataIsInvalidException exception) {
	      return new ResponseEntity<>("Employee data is invalid", HttpStatus.NOT_FOUND);
	   }

}
