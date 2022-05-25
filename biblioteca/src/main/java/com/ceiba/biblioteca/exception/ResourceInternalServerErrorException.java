package com.ceiba.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceInternalServerErrorException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ResourceInternalServerErrorException (String message) {
		super(message);
	}
}
