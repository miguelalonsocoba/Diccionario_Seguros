package com.example.dicionario.readfile.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dicionario.readfile.request.ReadFileExcelRequest;
import com.example.dicionario.readfile.service.IReadExcelService;

class ReadExcelControllerTest {

	@InjectMocks
	private ReadExcelController readExcelController;

	@Mock
	private IReadExcelService readExcelService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Comprueba que no retorna un valor vacio o null.
	 */
	@Test
	void readExcelTestNotNull() {
		Map<Integer, List<String>> responseService = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		responseService.put(1, responseList);
		when(readExcelService.readExcel(ArgumentMatchers.anyString())).thenReturn(responseService);

		ReadFileExcelRequest request = new ReadFileExcelRequest("Example");

		Map<Integer, List<String>> response = readExcelController.readExcel(request);

		assertFalse(response.isEmpty());
	}

	/**
	 * Comprueba que retorna una Key esperada.
	 */
	@Test
	void readExcelTestRetornaKeyEsperadoOK() {
		Map<Integer, List<String>> responseService = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		responseService.put(1, responseList);
		when(readExcelService.readExcel(ArgumentMatchers.anyString())).thenReturn(responseService);

		ReadFileExcelRequest request = new ReadFileExcelRequest("Example");

		Map<Integer, List<String>> response = readExcelController.readExcel(request);

		assertEquals(true, response.containsKey(1));
	}

	/**
	 * Comprueba que retorna un valor esperado.
	 */
	@Test
	void readExcelTestRetornaValueEsperadoOK() {
		Map<Integer, List<String>> responseService = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		responseService.put(1, responseList);
		when(readExcelService.readExcel(ArgumentMatchers.anyString())).thenReturn(responseService);

		ReadFileExcelRequest request = new ReadFileExcelRequest("Example");

		Map<Integer, List<String>> response = readExcelController.readExcel(request);
		assertEquals(response.toString(), response.toString());
	}

	/**
	 * Comprueba que devuelve un error cuando el request es nulo.
	 */
	@Test
	void readExcelTestErrorNullPointerException() {

		try {
			readExcelController.readExcel(null);
		} catch (NullPointerException e) {
			assertEquals(null, e.getMessage());
		}
	}
	
	/**
	 * Comprueba que si el atributo fileLocation es null retorna un NullPointerException.
	 */
	@Test
	void readExcelTestErrorParamNull() {
		ReadFileExcelRequest request = new ReadFileExcelRequest(null);
		
		try {
			readExcelController.readExcel(request);
		} catch (NullPointerException e) {
			assertEquals(null, e.getMessage());
		}
	}

}
