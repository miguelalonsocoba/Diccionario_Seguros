package com.example.dicionario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

class DiccionarioControllerTest {

	@InjectMocks
	private DiccionarioController diccionarioController;

	@Mock
	private DiccionarioServiceImpl service;

	@Mock
	private ObjectMapper oMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Prueba que retorna lo esperado.
	 * 
	 * @throws Exception
	 */
	@Test
	void getTerminoIDTestOk() throws Exception {
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
	void getTerminoIdTestError() {
		try {
			when(service.getTerminoId(6)).thenReturn(getResponse());
			when(oMapper.writeValueAsString(ArgumentMatchers.any())).thenThrow(new JsonProcessingException("Error") {
			});
			diccionarioController.getTerminoID(6);
		} catch (Exception e) {
			assertEquals(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, "Error"), e.getMessage());
		}

	}

	/**
	 * Comprueba que retorna un error de tipo ProxyException.
	 */
	@Test
	void getTerminoIDTestErrorProxyException() {
		when(service.getTerminoId(6)).thenThrow(new ProxyException("Error"));

		try {
			diccionarioController.getTerminoID(6);
		} catch (ProxyException e) {
			assertEquals(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, "Error"), e.getMessage());
		}
	}

	/**
	 * Cpmprueba que el objeto de respuesta no es nulo y que es igual a lo esperado.
	 */
	@Test
	void addTerminoTestOk() throws Exception {
		ObjectMapper oMapperWorker = new ObjectMapper();
		when(service.addTermino(ArgumentMatchers.any())).thenReturn(getResponse());
		when(oMapper.writeValueAsString(ArgumentMatchers.any()))
				.thenReturn(oMapperWorker.writeValueAsString(getResponse()));

		String response = diccionarioController.addTermino(getResponse());

		assertNotNull(response);
		assertEquals(oMapperWorker.writeValueAsString(getResponse()), response, "No son iguales los objetos");
	}

	@Test
	void addTerminoTestErrorJsonProcesingException() {
		try {
			when(service.addTermino(ArgumentMatchers.any())).thenReturn(getResponse());
			when(oMapper.writeValueAsString(ArgumentMatchers.any())).thenThrow(new JsonProcessingException("Error") {
			});
			diccionarioController.addTermino(getResponse());
		} catch (Exception e) {
			assertEquals(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, "Error"), e.getMessage());
		}
	}

	/**
	 * Comprueba que regresa una Exception de tipo NullPointerException.
	 */
	@Test
	void getTerminoIdTestNullPointerException() {
		try {
			diccionarioController.getTerminoID(null);
		} catch (NullPointerException e) {
			assertEquals("The param idTermino es null: ", e.getMessage());
		}

	}

	/**
	 * Comprueba que el metodo retorna lo esperado y que no sea null.
	 */
	@Test
	void deletTerminoTestOk() {
		Integer idTermino = 9;

		try {
			when(service.deleteById(idTermino)).thenReturn("Success ok");
			String response = diccionarioController.deletTermino(idTermino);
			assertEquals("Termino con id: " + idTermino + " eliminado correctamente: Success ok", response);
			assertNotNull(response);
		} catch (Exception e) {
			assertFalse(true);
		}

	}

	/**
	 * Verifica si se ejecuta el servicio.
	 */
	@Test
	void deletTerminoTestExecuteService() throws Exception {
		Integer idTermino = 9;

		String response = diccionarioController.deletTermino(idTermino);
		verify(service).deleteById(idTermino);

	}

	/**
	 * Comprueba que retorna una respuesta esperada si ocurre un error de tipo
	 * Exception.
	 */
	@Test
	void deletTerminoTestErrorException() throws Exception {
		Integer idTermino = 9;
		try {
			when(service.deleteById(idTermino)).thenThrow(new IllegalArgumentException("Error"));
			diccionarioController.deletTermino(idTermino);
		} catch (Exception e) {
			assertEquals("Controller: Error", e.getMessage());
		}

	}

	/**
	 * Comprueba que regresa una Exception de tipo NullPointerException.
	 * 
	 * @throws Exception
	 */
	@Test
	void deletTerminoTestExceptionNullPointerException() throws Exception {
		try {
			diccionarioController.deletTermino(null);
		} catch (Exception e) {
			assertEquals("Controller: El parametro id es null", e.getMessage());
		}
	}

	/**
	 * Comprueba que retorna una lista de tipo TerminoDTO.
	 * 
	 * @throws Exception
	 */
	@Test
	void getAllTestOk() throws Exception {
		List<TerminoDTO> responseList = new ArrayList<TerminoDTO>();
		responseList.add(getResponse());
		responseList.add(getResponse());
		when(service.listAll()).thenReturn(responseList);

		List<TerminoDTO> response = diccionarioController.getAll();

		assertNotNull(response, "The list is null");
	}

	/**
	 * Comprueba que retorna una Exception.
	 */
	@Test
	void getAllTestException() {
		try {
			when(service.listAll()).thenThrow(new Exception("Error"));
			diccionarioController.getAll();
		} catch (Exception e) {
			assertEquals("Error", e.getMessage());
		}

	}

	/**
	 * Comprueba que el metodo se ejecute correctamente.
	 */
	@Test
	void getTerminoByNameTestOk() {
		List<TerminoDTO> responseList = new ArrayList<>();
		responseList.add(getResponse());
		responseList.add(getResponse());
		when(service.getTerminoByNombre(ArgumentMatchers.anyString())).thenReturn(responseList);
		List<TerminoDTO> resposne = diccionarioController.getTerminoByName("GNP");
		assertNotNull(resposne, "The list is null");
	}

	/**
	 * Comprueba que regresa una Exception de tipo NulPointerException.
	 */
	@Test
	void getTerminoByNameTestExceptionNullPointerException() {
		try {
			diccionarioController.getTerminoByName("");
		} catch (NullPointerException e) {
			assertEquals("EL parametro termino tienen un valor nulo", e.getMessage());
		}

	}

	/**
	 * Comprueba que el metodo retorna lo esperado.
	 * 
	 * @throws Exception
	 */
	@Test
	void updateTerminoTestOk() throws Exception {
		ObjectMapper oMapperWorker = new ObjectMapper();
		when(service.updateTermino(getResponse())).thenReturn(getResponse());
		when(oMapper.writeValueAsString(ArgumentMatchers.any()))
				.thenReturn(oMapperWorker.writeValueAsString(getResponse()));

		String response = diccionarioController.updateTermino(getResponse());

		assertNotNull(response, "The object is null");

	}

	/**
	 * Metodo que retorna un objeto de Tipo TerminoDTO.
	 * 
	 * @return
	 */
	private TerminoDTO getResponse() {
		return new TerminoDTO("Example", "Example", "Example", "Example");
	}

}
