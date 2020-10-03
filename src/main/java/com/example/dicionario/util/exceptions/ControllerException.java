package com.example.dicionario.util.exceptions;

/**
 * The Class ControllerException.
 */
public class ControllerException extends Exception {

	/**
	 * Log.
	 */
	private static final long serialVersionUID = 8342741884237299598L;

	/** The descirpcion. */
	private final String descripcion;

	/** The estatus. */
	private final String estatus;

	/** The codigo de error. */
	private final int codigoError;

	/**
	 * Intancia de un nuevp ControllerExcpetion.
	 * 
	 * @param mensaje the mensaje
	 * @param causa   the causa
	 */
	public ControllerException(final String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.descripcion = mensaje;
		this.codigoError = 0;
		this.estatus = "";
	}

	/**
	 * Intancia de un nuevp ControllerExcpetion.
	 * 
	 * @param mensaje
	 * @param causa
	 * @param codigo
	 */
	public ControllerException(String mensaje, Throwable causa, int codigo) {
		super(mensaje, causa);
		this.descripcion = mensaje;
		this.codigoError = codigo;
		this.estatus = "";
	}

	/**
	 * Intancia de un nuevp ControllerExcpetion.
	 * 
	 * @param estatus the estatus
	 * @param mensaje the mensaje
	 * @param codigo  the codigo
	 */
	public ControllerException(String estatus, String mensaje, int codigo) {
		super(mensaje);
		this.descripcion = mensaje;
		this.codigoError = codigo;
		this.estatus = estatus;
	}

	/**
	 * Gets the descirpcion.
	 * 
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Gets the estatus.
	 * 
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * Gets the codigo error.
	 * 
	 * @return the codigo error
	 */
	public int getCodigoError() {
		return codigoError;
	}

	/**
	 * Print the attributs.
	 */
	@Override
	public String toString() {
		return "Caused by : descripcion :" + descripcion + ", estatus:" + estatus + ", codigoError :" + codigoError;
	}
}
