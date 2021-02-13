package com.example.dicionario.readfile.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HandlingFilesTest {

	@InjectMocks
	private HandlingFiles handlingFiles;

	@Mock
	private OPCPackage opcPachage;
	
	@Mock
	private Cell cell;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Comprueba que no retorna un valor null.
	 */
	@Test
	void openFileTestReturnValueNotNull() {
		Workbook response;
		try {
			response = handlingFiles.openFile("Diccionario-Aseguradora.xlsx");
			assertNotNull(response);
		} catch (FileNotFoundException e) {
			assertFalse(true);
		}
	}

	/**
	 * Verifica que retorna un valor nulo cuando recibe un parametro nulo.
	 */
	@Test
	void openFileTestParamNull() {
		try {
			handlingFiles.openFile(null);
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	/**
	 * Comprueba que devuelve un exception de tipo FileNotFoundException.
	 */
	@Test
	void openFileTestFileNotFoundException() {
		try {
			handlingFiles.openFile("ruta ejemplo");
		} catch (FileNotFoundException e) {
			assertEquals("No se encontro el archivo en la ruta especificada: ruta ejemplo", e.getMessage());
		}
	}
	
	/**
	 * Comprueba que el valor que retorna no es nulo.
	 * @throws Exception 
	 */
	@Test
	void retriverFirstSheetTestReturnNotNull() throws Exception {
		Workbook responseW = null;
		try {
			responseW = handlingFiles.openFile("Diccionario-Aseguradora.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(responseW);
		
		assertNotNull(response);
		
	}
	
	/**
	 * Comprueba que recupera el valor de la Key esperado.
	 * @throws Exception 
	 */
	@Test
	void retriverFirstSheetTestValorEsperadoKey() throws Exception {
		Workbook responseW = null;
		try {
			responseW = handlingFiles.openFile("Diccionario-Aseguradora.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(responseW);
		assertTrue(response.containsKey(1));
	}
	
	/**
	 * Comprueba que recupera el valor esperado.
	 * @throws Exception 
	 */
	@Test
	void retriverFirstSheetTestValorEsperadoValue() throws Exception {
		Workbook responseW = null;
		try {
			responseW = handlingFiles.openFile("Diccionario-Aseguradora.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(responseW);
		assertEquals("Ejemplo1", response.get(1).get(0));
	}
	
	/**
	 * Comprueba que entra en el default del case cuando la celda no es de tipo String.
	 * @throws Exception 
	 */
	@Test
	void restriverFirstSheetTestValorCeldaInt() throws Exception {
		Workbook responseW = null;
		try {
			responseW = handlingFiles.openFile("Diccionario-Aseguradora2.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(responseW);
		assertNotNull(response);
	}
	
	/**
	 * Comprueba que entra en el default del case cuando la celda no es de tipo String.
	 * @throws Exception 
	 */
	@Test
	void restriverFirstSheetTestValorCeldaNull() throws Exception {
		Workbook responseW = null;
		try {
			responseW = handlingFiles.openFile("Diccionario-Aseguradora3Vacio.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(responseW);
		assertNotNull(response);
	}
}