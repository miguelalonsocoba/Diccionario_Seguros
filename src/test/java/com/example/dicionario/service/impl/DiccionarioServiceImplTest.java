package com.example.dicionario.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.Termino;
import com.example.dicionario.repository.TerminoRepository;
import com.example.dicionario.util.convert.ConvertTermino;

public class DiccionarioServiceImplTest {

	@InjectMocks
	private DiccionarioServiceImpl diccionarioService;

	@Mock
	private TerminoRepository repository;

	@Mock
	private ConvertTermino comverter;

	/** Object Optional de tipo Termino. */
	Optional<Termino> res;

	TerminoDTO responseDTO;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		res = Optional.of(getTermino());
	}

	@AfterEach
	public void setDown() {
		responseDTO = null;
	}

	/**
	 * Test: Comprueba que el objeto no es Not Null.
	 */
	@Test
	public void getTerminoIdTestIsNotNull() {
		when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(res);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		responseDTO = diccionarioService.getTerminoId(3);
		assertNotNull(responseDTO, "The object is null");

	}

	/**
	 * Comprueba que el objeto retornado es el mismo que el esperado.
	 */
	@Test
	public void getTerimoIdTestIsEquals() {

		when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(res);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		responseDTO = diccionarioService.getTerminoId(3);

		assertEquals(getTerminoDTO().getNombreTermino(), responseDTO.getNombreTermino(), "The attributes are not same");
		assertEquals(getTerminoDTO().getDescripcion(), responseDTO.getDescripcion(), "The attributes are not same");
		assertEquals(getTerminoDTO().getEjemplo(), responseDTO.getEjemplo(), "The attributes are not same");
		assertEquals(getTerminoDTO().getCategoria(), responseDTO.getCategoria(), "The attributes are not same");
	}

	/**
	 * Throws a exception of type NoSuchElementException.
	 */
	@Test
	public void getTerminoIdTestError() {
		Integer idTermino = 3;
		String message = "Item not found.";

		when(repository.findById(ArgumentMatchers.anyInt())).thenThrow(new NoSuchElementException(message));

		try {
			diccionarioService.getTerminoId(idTermino);
		} catch (IllegalArgumentException e) {
			assertEquals("No se encontro el termino con el id " + idTermino + " " + message, e.getMessage());
		}

	}

	/**
	 * Returns object Termino.
	 * 
	 * @return object Termino
	 */
	public Termino getTermino() {
		return new Termino(1, "Example", "Example", "Example", "Example");
	}

	/**
	 * Returns object TerminoDTO.
	 * 
	 * @return object TerminoDTO
	 */
	public TerminoDTO getTerminoDTO() {
		return new TerminoDTO("Example", "Exapmle", "Exapmle", "Exapmle");
	}

}
