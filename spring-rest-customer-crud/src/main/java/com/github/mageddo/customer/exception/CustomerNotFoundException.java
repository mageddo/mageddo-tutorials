package com.github.mageddo.customer.exception;

public class CustomerNotFoundException extends ValidationException {
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
