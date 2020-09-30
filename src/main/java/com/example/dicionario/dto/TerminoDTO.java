package com.example.dicionario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class TerminoDTO.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerminoDTO {

	/** The nombre del termino. */
	private String nombreTermino;

	/** The descripcion */
	private String descripcion;

	/** The ejemplo. */
	private String ejemplo;

	/** The categoria. */

	private String categoria;

}
