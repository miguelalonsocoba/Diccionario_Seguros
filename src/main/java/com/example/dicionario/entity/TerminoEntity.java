package com.example.dicionario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity Termino.
 */
@Entity(name = "terminos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerminoEntity {
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	/** The nombre del termino. */
	@Column(name = "NOMBRE_TERMINO")
	private String nombreTermino;
	
	/** The descripcion. */
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	/** The ejemplo. */
	@Column(name = "EJEMPLO")
	private String ejemplo;
	
	/** The categoria. */
	@Column(name = "CATEGORIA")
	private String categoria;
	
	

}
