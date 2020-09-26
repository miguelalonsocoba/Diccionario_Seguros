package com.example.dicionario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dicionario.constants.Constants;
import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.exceptions.ProxyException;
import com.example.dicionario.service.impl.DiccionarioServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiccionarioControllerTest {

	@InjectMocks
	private DiccionarioController diccionarioController;

	@Mock
	private DiccionarioServiceImpl service;

	@Mock
	private ObjectMapper oMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Prueba que retorna lo esperado.
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void getTerminoIDTestOk() throws JsonProcessingException {
		ObjectMapper oMapperWorker = new ObjectMapper();
		when(service.getTerminoId(6)).thenReturn(getResponse());
		when(oMapper.writeValueAsString(ArgumentMatchers.any()))
				.thenReturn(oMapperWorker.writeValueAsString(getResponse()));
		String response = diccionarioController.getTerminoID(6);

		assertEquals(oMapperWorker.writeValueAsString(getResponse()), response, "Los objetos no son iguales");

	}

	/**
	 * Lanza Exception JsonProcessingException.
	 */
	@Test
	public void getTerminoIdTestError() {
		try {
			when(service.getTerminoId(6)).thenReturn(getResponse());
			when(oMapper.writeValueAsString(ArgumentMatchers.any())).thenThrow(new JsonProcessingException("Error") {
			});
			diccionarioController.getTerminoID(6);
		} catch (ProxyException e) {
			assertEquals(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, "Error"), e.getMessage());
		} catch (JsonProcessingException e) {
			assertTrue(false);
		}

	}

	private TerminoDTO getResponse() {
		return new TerminoDTO("Example", "Example", "Example", "Example");
	}

}
