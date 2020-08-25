package com.example.dicionario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception que se regresa si un parámetro de entrada no es valido por format
 * o longitud.
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class ParameterException extends RuntimeException {

	/**
	 * long.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new parameter exception.
	 *
	 * @param message Mensaje
	 */
	public ParameterException(final String message) {
		super(message);
	}

	/**
	 * Remove StackTrace.
	 */
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}
