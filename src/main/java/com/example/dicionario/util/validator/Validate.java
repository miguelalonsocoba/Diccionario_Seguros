package com.example.dicionario.util.validator;

import org.springframework.util.StringUtils;

import com.example.dicionario.constants.Constants;
import com.example.dicionario.dto.TerminoDTO;

/**
 * The class Validate.
 */
public class Validate {

	/**
	 * Valida atributos del objeto no sean nulos.
	 * 
	 * @param termino the termino
	 */
	public void validateTerminoNull(TerminoDTO termino) {
		checkOrElseThrow(StringUtils.isEmpty(termino.getCategoria()),
				String.format(Constants.MESSAGE_ELEMENT_REQUIRED, "Categoria"));
		checkOrElseThrow(StringUtils.isEmpty(termino.getDescripcion()),
				String.format(Constants.MESSAGE_ELEMENT_REQUIRED, "Descripcion"));
		checkOrElseThrow(StringUtils.isEmpty(termino.getEjemplo()),
				String.format("El parametrp %s es obligatorio", "Ejemplo"));
		checkOrElseThrow(StringUtils.isEmpty(termino.getNombreTermino()),
				String.format(Constants.MESSAGE_ELEMENT_REQUIRED, "NombreTermino"));
	}

	/**
	 * Check or else throw.
	 *
	 * @param condition        Condicion que cumplir, si no, se dispara
	 * @param exceptionMessage Mensaje de error
	 */
	protected void checkOrElseThrow(final boolean condition, final String exceptionMessage) {
		if (condition) {
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

}
