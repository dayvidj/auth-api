package com.dayvid.authentication_api.exception;

public class DuplicateEntityException extends RuntimeException {
	public DuplicateEntityException(String message) {
		super(message);
	}
}
