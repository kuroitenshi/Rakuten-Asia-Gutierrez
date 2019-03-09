package com.rakuten.fullstackrecruitmenttest.exception;

public class EmployeeDataIsInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeDataIsInvalidException(String message) {
		super(message);
	}
}
