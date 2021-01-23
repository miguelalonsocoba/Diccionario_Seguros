package com.example.dicionario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Termino.
 */
@Entity
@Table(name = "terminos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Termino implements Serializable {

	/**
	 * serial.
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

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
