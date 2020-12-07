package com.banking.transaction.exceptions;

public class ValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String missingField) {
        super(String.format("Field is %d not found in Request", missingField));

	}
}
