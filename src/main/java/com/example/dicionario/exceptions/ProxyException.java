package com.example.dicionario.exceptions;

/**
 * Class ParameterException.
 */
public class ProxyException extends RuntimeException {

	/**
	 * long.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new parameter exception.
	 *
	 * @param message Mensaje
	 */
	public ProxyException(final String message) {
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
