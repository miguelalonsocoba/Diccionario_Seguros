package com.example.dicionario.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
	 * Comprueba que el objeto no es null.
	 */
	@Test
	public void addTerminoTestResponseNotNull() {
		when(comverter.convertDtoToEntity(ArgumentMatchers.any())).thenReturn(getTermino());
		when(repository.save(ArgumentMatchers.any())).thenReturn(getTermino());
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		TerminoDTO response = diccionarioService.addTermino(getTerminoDTO());

		assertNotNull(response);
	}

	/**
	 * Comprueba que retorna el objeto esperado.
	 */
	@Test
	public void addTerminoTestResponseEsperado() {
		when(comverter.convertDtoToEntity(ArgumentMatchers.any())).thenReturn(getTermino());
		when(repository.save(ArgumentMatchers.any())).thenReturn(getTermino());
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		TerminoDTO response = diccionarioService.addTermino(getTerminoDTO());

		assertEquals(getTerminoDTO().getCategoria(), response.getCategoria(), "The params Categoria are not same");
		assertEquals(getTerminoDTO().getDescripcion(), response.getDescripcion(),
				"The params Descripcion are not same");
		assertEquals(getTerminoDTO().getEjemplo(), response.getEjemplo(), "The params Ejemplo are not same");
		assertEquals(getTerminoDTO().getNombreTermino(), response.getNombreTermino(),
				"The params NombreTermino are not same");
	}

	/**
	 * Comprueba que lanza una Exception de tipo IllegalArgumentException.
	 */
	@Test
	public void addTerminoTestException() {
		TerminoDTO request = getTerminoDTO();
		request.setCategoria("");
		try {
			diccionarioService.addTermino(request);
		} catch (IllegalArgumentException e) {
			assertEquals("El parametro Categoria es obligatorio", e.getMessage());
		}
	}

	/**
	 * Comprueba que el response no sea nulo.
	 */
	@Test
	public void deleteByIdTestResponseNotNull() {

		String response = diccionarioService.deleteById(3);

		assertNotNull(response, "The objecto is null");
	}

	/**
	 * Comprueba que el response se el esperado.
	 */
	@Test
	public void deleteByIdTestResponseEsperado() {
		String response = diccionarioService.deleteById(3);

		assertEquals("Success ok", response);
	}

	/**
	 * Comprueba que regresa una Exception de tipo IllegalArgumentException.
	 */
	@Test
	public void deleteByIdTestExceptionIllegaArgumentsException() {
		try {
			diccionarioService.deleteById(30);
		} catch (IllegalArgumentException e) {
			assertEquals("Message", e.getMessage());
		}

	}

	/**
	 * Comprueba que retorna una exception de tipo IllegalArgumentException.
	 */
//	@Test
//	public void deleteByIdTestExceptionIllegalArgumentException() {
//		
//	}

	/**
	 * Comprueba que el objeto retornado no es nulo.
	 * 
	 * @throws Exception
	 */
	@Test
	public void listAllTestNotull() throws Exception {
		List<Termino> response = new ArrayList<>();
		response.add(getTermino());
		response.add(getTermino());

		when(repository.findAll()).thenReturn(response);
		when(comverter.convertEntityToDto(getTermino())).thenReturn(getTerminoDTO());

		List<TerminoDTO> responseList = diccionarioService.listAll();

		assertNotNull(responseList);
	}

	/**
	 * Comprueba que el respinse es el esperado.
	 * 
	 * @throws Exception
	 */
	@Test
	public void listAllTestResponseEsperado() throws Exception {
		List<Termino> response = new ArrayList<>();
		response.add(getTermino());

		when(repository.findAll()).thenReturn(response);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		List<TerminoDTO> responseList = diccionarioService.listAll();

		assertEquals(getTerminoDTO().getCategoria(), responseList.get(0).getCategoria());
		assertEquals(getTerminoDTO().getDescripcion(), responseList.get(0).getDescripcion());
		assertEquals(getTerminoDTO().getEjemplo(), responseList.get(0).getEjemplo());
		assertEquals(getTerminoDTO().getNombreTermino(), responseList.get(0).getNombreTermino());
	}

	/**
	 * Comprueba que el response no es nulo.
	 */
	@Test
	public void getTerminoByNombreTestObjectNotNull() {
		List<Termino> response = new ArrayList<>();
		response.add(getTermino());
		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(response);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		List<TerminoDTO> responseList = diccionarioService.getTerminoByNombre(ArgumentMatchers.anyString());

		assertNotNull(responseList, "The object is null");
	}

	/**
	 * Comprueba que el response es el esperado.
	 */
	@Test
	public void getTerminoByNombreTestObjectEsperado() {
		List<Termino> response = new ArrayList<>();
		response.add(getTermino());
		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(response);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		List<TerminoDTO> responseList = diccionarioService.getTerminoByNombre(ArgumentMatchers.anyString());

		assertEquals(getTerminoDTO().getCategoria(), responseList.get(0).getCategoria());
		assertEquals(getTerminoDTO().getDescripcion(), responseList.get(0).getDescripcion());
		assertEquals(getTerminoDTO().getEjemplo(), responseList.get(0).getEjemplo());
		assertEquals(getTerminoDTO().getNombreTermino(), responseList.get(0).getNombreTermino());
	}

	/**
	 * Comprueba que regresa una Exception de termino NullPointerException.
	 */
	@Test
	public void getTErminoByNombreTestResponseEntityNull() {
		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());

		try {
			diccionarioService.getTerminoByNombre("Paulo");
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			assertEquals("El termino Paulo no existe en la base de datos.", e.getMessage());
		}
	}

	/**
	 * Comprueba que el response no es nulo.
	 */
	@Test
	public void updateTerminoTestObjectNotNull() {
		List<Termino> list = new ArrayList<>();
		list.add(getTermino());

		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(list);
		when(comverter.convertDtoToEntity(getTerminoDTO())).thenReturn(getTermino());
		when(repository.save(ArgumentMatchers.any())).thenReturn(getTermino());
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		try {
			TerminoDTO response = diccionarioService.updateTermino(getTerminoDTO());
			assertNotNull(response, "The object is null");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Comprueba que el objeto es el esperado.
	 */
	@Test
	public void updateTerminoObjectEsperado() {
		List<Termino> list = new ArrayList<>();
		list.add(getTermino());

		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(list);
		when(comverter.convertDtoToEntity(ArgumentMatchers.any())).thenReturn(getTermino());
		when(repository.save(ArgumentMatchers.any())).thenReturn(getTermino());
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		try {
			TerminoDTO response = diccionarioService.updateTermino(getTerminoDTO());
			assertEquals(getTerminoDTO().getCategoria(), response.getCategoria(), "The objects are not same");
			assertEquals(getTerminoDTO().getDescripcion(), response.getDescripcion(), "The objects are not same");
			assertEquals(getTerminoDTO().getEjemplo(), response.getEjemplo(), "The objects are not same");
			assertEquals(getTerminoDTO().getNombreTermino(), response.getNombreTermino(), "The objects are not same");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Comprueba que lanza una exception de tipo Exception.
	 */
	@Test
	public void updateTerminoTestError() {
		List<Termino> list = new ArrayList<>();

		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(list);

		try {
			diccionarioService.updateTermino(getTerminoDTO());
		} catch (Exception e) {
			assertEquals("El termino Example no existe.", e.getMessage());
		}

	}

	/**
	 * Comprueba que retorna una Exception de tipo IllegalArgumentException.
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateTerminoTestExceptionIllegalArgumentException() throws Exception {
		TerminoDTO request = getTerminoDTO();
		request.setCategoria("");
		try {
			diccionarioService.updateTermino(request);
		} catch (IllegalArgumentException e) {
			assertEquals("El parametro Categoria es obligatorio", e.getMessage());
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
