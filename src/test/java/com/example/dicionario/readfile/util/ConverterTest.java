package com.example.dicionario.readfile.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dicionario.dto.TerminoDTO;

class ConverterTest {

	private List<List<String>> listaL;

	private Converter converter;

	@BeforeEach
	void setUp() {

		converter = new Converter();

		listaL = new ArrayList<>();
		List<String> listaS = new ArrayList<>();
		listaS.add("Example1");
		listaS.add("Example2");
		listaS.add("Example3");
		listaS.add("Example4");
		listaL.add(listaS);
	}

	/**
	 * Comprueba que no retorna un valor nullo.
	 */
	@Test
	void converterListStringToTerminoDTOTestNotBull() {
		List<TerminoDTO> response = converter.converterListStringToTerminoDTO(listaL);
		assertNotNull(response);

	}

	/**
	 * Comprueba que retorna el valor esperado.
	 */
	@Test
	void converterListStringToTerminoDTOTestValorEsperado() {
		List<TerminoDTO> response = converter.converterListStringToTerminoDTO(listaL);
		assertEquals("Example1", response.get(0).getNombreTermino());

	}

	/**
	 * Comprueba que obtenermos una excpetion de tipo NullPointerException cuando le pasamos un
	 * valor nulo al metodo.
	 */
	@Test
	void converterListStringToTerminoDTOTestExceptionNotNull() {
		try {
			converter.converterListStringToTerminoDTO(null);
		} catch (NullPointerException e) {
			assertEquals("Atributo con valor Null.", e.getMessage());
		}
	}

	/**
	 * Comprueba que obtenemos una exception de tipo NullPointerException cuando recibe una lista
	 * vacia.
	 */
	@Test
	void converterListStringToTerminoDTOTestExceptioListEmpty() {
		try {
			converter.converterListStringToTerminoDTO(new ArrayList<List<String>>());
		} catch (NullPointerException e) {
			System.err.println("NullPointerException: " + e.getMessage());
			assertEquals("Atributo con valor Null", e.getMessage());
		}
	}

}
