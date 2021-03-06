package com.example.dicionario.readfile.service.impl;

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

import com.example.dicionario.readfile.util.HandlingFiles;

class ReadExcelServiceImplTest {

	@InjectMocks
	private ReadExcelServiceImpl service;

	@Mock
	private HandlingFiles handlingFiles;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Comprueba que no retorna un valor nulo.
	 * @throws Exception 
	 */
	@Test
	void readExcelTestReturnNoNull() throws Exception {
		Map<Integer, List<String>> response = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		response.put(1, responseList);

		when(handlingFiles.openFile("fileLocation")).thenReturn(ArgumentMatchers.any());
		when(handlingFiles.retrieveFirstSheet(null)).thenReturn(response);

		Map<Integer, List<String>> result = service.readExcel("fileLocation");

		assertFalse(result.isEmpty());
	}

	/**
	 * Comprueba que retorna una key esperaco.
	 * @throws Exception 
	 */
	@Test
	void readExcelTestRetornaValorEsperadoKey() throws Exception {
		Map<Integer, List<String>> response = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		response.put(1, responseList);

		when(handlingFiles.openFile("fileLocation")).thenReturn(ArgumentMatchers.any());
		when(handlingFiles.retrieveFirstSheet(null)).thenReturn(response);

		Map<Integer, List<String>> result = service.readExcel("fileLocation");

		assertEquals(true, result.containsKey(1));
	}

	/**
	 * Comprueba que retorna un valor esperado.
	 * @throws Exception 
	 */
	@Test
	void readExcelTestRetornaValorEsperado() throws Exception {
		List<String> valorEsperado = new ArrayList<>();
		valorEsperado.add("Example");
		Map<Integer, List<String>> response = new HashedMap<>();
		List<String> responseList = new ArrayList<>();
		responseList.add("Example");
		response.put(1, responseList);

		when(handlingFiles.openFile("fileLocation")).thenReturn(ArgumentMatchers.any());
		when(handlingFiles.retrieveFirstSheet(null)).thenReturn(response);

		Map<Integer, List<String>> result = service.readExcel("fileLocation");
		System.out.println(result.toString());
		assertEquals(true, result.containsValue(valorEsperado));
	}

	/**
	 * Comprueba que retorna un error de NullPointerException cuando se pasa como
	 * parametro un valor nulo.
	 */
	@Test
	void readExcelTestParamNull() {
		try {
			service.readExcel(null);
		} catch (Exception e) {
			assertEquals("Param fileLocation es null", e.getMessage());
		}
	}

}
