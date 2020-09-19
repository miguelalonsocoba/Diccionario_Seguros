package com.example.dicionario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.service.IDiccionarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiccionarioControllerTest {

	@InjectMocks
	private DiccionarioController diccionarioController;

	@Mock
	private IDiccionarioService service;

	@Mock
	private ObjectMapper oMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTerminoIDTestOk() throws JsonProcessingException {

		when(service.getTerminoId(3)).thenReturn(new TerminoDTO("GNP", "Example", "NA", "Termino"));
		when(oMapper.writeValueAsString(ArgumentMatchers.<String>any())).thenReturn("Hola");

		System.out.println(diccionarioController.getTerminoID(3));

		assertEquals("Hola", diccionarioController.getTerminoID(3));

	}

}
