package com.example.dicionario.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.example.dicionario.entity.response.ResponseBulkLoad;
import com.example.dicionario.repository.TerminoRepository;
import com.example.dicionario.util.convert.ConvertTermino;

class DiccionarioServiceImplTest {

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
	void setUp() {
		MockitoAnnotations.initMocks(this);
		res = Optional.of(getTermino());
	}

	@AfterEach
	void setDown() {
		responseDTO = null;
	}

	/**
	 * Test: Comprueba que el objeto no es Not Null.
	 */
	@Test
	void getTerminoIdTestIsNotNull() {
		when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(res);
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		responseDTO = diccionarioService.getTerminoId(3);
		assertNotNull(responseDTO, "The object is null");

	}

	/**
	 * Comprueba que el objeto retornado es el mismo que el esperado.
	 */
	@Test
	void getTerimoIdTestIsEquals() {

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
	void getTerminoIdTestError() {
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
	 * 
	 * @throws Exception
	 */
	@Test
	void addTerminoTestResponseNotNull() throws Exception {
		when(comverter.convertDtoToEntity(ArgumentMatchers.any())).thenReturn(getTermino());
		when(repository.save(ArgumentMatchers.any())).thenReturn(getTermino());
		when(comverter.convertEntityToDto(ArgumentMatchers.any())).thenReturn(getTerminoDTO());

		TerminoDTO response = diccionarioService.addTermino(getTerminoDTO());

		assertNotNull(response);
	}

	/**
	 * Comprueba que retorna el objeto esperado.
	 * 
	 * @throws Exception
	 */
	@Test
	void addTerminoTestResponseEsperado() throws Exception {
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
	 * 
	 * @throws Exception
	 */
	@Test
	void addTerminoTestException() throws Exception {
		TerminoDTO request = getTerminoDTO();
		request.setCategoria("");
		try {
			diccionarioService.addTermino(request);
		} catch (IllegalArgumentException e) {
			assertEquals("El parametro Categoria es obligatorio", e.getMessage());
		}
	}

	/**
	 * Comprueba que retorna una Exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void addTerminoTestTerminoAlreadyExistsExcpetion() throws Exception {
		List<Termino> terminos = new ArrayList<>();
		terminos.add(getTermino());
		when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(terminos);
		when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());

		try {
			diccionarioService.addTermino(getTerminoDTO());
		} catch (Exception e) {
			assertEquals("El termino Example ya existe...", e.getMessage());
		}
	}

	/**
	 * Comprueba que el response no sea null.
	 */
	@Test
	void deleteByIdTestResponseNotNull() {

		String response = diccionarioService.deleteById(3);

		assertNotNull(response, "The objecto is null");
	}

	/**
	 * Comprueba que retorna un IllegalArgumentException.
	 */
	@Test
	void deleteByIdTestIllegalArgumentException() {
		DiccionarioServiceImpl serviceImpl = new DiccionarioServiceImpl();

		try {
			serviceImpl.deleteById(1000000);
		} catch (Exception e) {
			assertEquals("El id 1000000 no existe.", e.getMessage());
		}
	}

	/**
	 * Comprueba que el response se el esperado.
	 */
	@Test
	void deleteByIdTestResponseEsperado() {
		String response = diccionarioService.deleteById(3);

		assertEquals("Success ok", response);
	}

	/**
	 * Comprueba que regresa una Exception de tipo IllegalArgumentException.
	 */
	@Test
	void deleteByIdTestExceptionIllegaArgumentsException() {
		try {
			diccionarioService.deleteById(30);
		} catch (IllegalArgumentException e) {
			assertEquals("Message", e.getMessage());
		}

	}

	/**
	 * Comprueba que el objeto retornado no es nulo.
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllTestNotull() throws Exception {
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
	void listAllTestResponseEsperado() throws Exception {
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
	void getTerminoByNombreTestObjectNotNull() {
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
	void getTerminoByNombreTestObjectEsperado() {
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
	void getTErminoByNombreTestResponseEntityNull() {
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
	void updateTerminoTestObjectNotNull() {
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
	void updateTerminoObjectEsperado() {
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
	void updateTerminoTestError() {
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
	void updateTerminoTestExceptionIllegalArgumentException() throws Exception {
		TerminoDTO request = getTerminoDTO();
		request.setCategoria("");
		try {
			diccionarioService.updateTermino(request);
		} catch (IllegalArgumentException e) {
			assertEquals("El parametro Categoria es obligatorio", e.getMessage());
		}

	}

	/**
	 * Comprueba que se ejecuta correctamente el metodo.
	 */
	@Test
	void deletByNameTestOk() {
		when(repository.deleteByNombreTermino(ArgumentMatchers.anyString())).thenReturn(2L);
		try {
			Long response = diccionarioService.deleteByName("Example");
			assertEquals(2, response);
		} catch (Exception e) {
			assert (false);
		}
	}

	/**
	 * Comprueba que el metodo funciona correctamente.
	 */
	@Test
	void bulkLoadTestOk() {
		List<TerminoDTO> terminoDTOs = new ArrayList<>();
		terminoDTOs.add(getTerminoDTO());

		try {
			when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());
			when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());
			when(repository.save(ArgumentMatchers.any(Termino.class))).thenReturn(getTermino());
			when(comverter.convertEntityToDto(ArgumentMatchers.any(Termino.class))).thenReturn(getTerminoDTO());

			ResponseBulkLoad response = diccionarioService.bulkLoad(terminoDTOs, "false");
			assertEquals("Example", response.getLoadedData().get(0).getNombreTermino());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	/**
	 * Comprueba que el metodo funciona con la vandera de rollback.
	 */
	@Test
	void bulkLoadTestConRollBackOk() {
		List<TerminoDTO> terminoDTOs = new ArrayList<>();
		terminoDTOs.add(getTerminoDTO());

		try {
			when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());
			when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());
			when(repository.save(ArgumentMatchers.any(Termino.class))).thenReturn(getTermino());
			when(comverter.convertEntityToDto(ArgumentMatchers.any(Termino.class))).thenReturn(getTerminoDTO());

			ResponseBulkLoad response = diccionarioService.bulkLoad(terminoDTOs, "true");
			assertEquals("Example", response.getLoadedData().get(0).getNombreTermino());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Comprueba que el metodo retorna una Exception en el momento de guardar.
	 */
	@Test
	void bulkLoadTestExceptionSave() {
		List<TerminoDTO> terminoDTOs = new ArrayList<>();
		terminoDTOs.add(getTerminoDTO());

		List<Termino> terminos = new ArrayList<>();
		terminos.add(getTermino());

		try {
			when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());
			when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(terminos);

			ResponseBulkLoad response = diccionarioService.bulkLoad(terminoDTOs, "false");
			assertEquals("Example", response.getDataNoLoaded().get(0).getNombreTermino());
		} catch (Exception e) {
			assert (false);
		}

	}

	/**
	 * Comprueba que el metodo retorna una Exception en el RollBack.
	 */
	@Test
	void bulkLoadTestConRollBackException() {
		List<TerminoDTO> terminoDTOs = new ArrayList<>();
		terminoDTOs.add(getTerminoDTO());

		try {
			when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());
			when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());
			when(repository.save(ArgumentMatchers.any(Termino.class))).thenReturn(getTermino());
			when(comverter.convertEntityToDto(ArgumentMatchers.any(Termino.class))).thenReturn(getTerminoDTO());
			when(repository.deleteByNombreTermino(ArgumentMatchers.anyString())).thenReturn(null);

			diccionarioService.bulkLoad(terminoDTOs, "true");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			assertEquals ("Error: ", e.getMessage());
		}
	}
	
	/**
	 * Comprueba que el metodo funciona con la vandera de rollback y el objeto vacio.
	 */
	@Test
	void bulkLoadTestConRollBackOkObjectEmpty() {
		List<TerminoDTO> terminoDTOs = new ArrayList<>();
		terminoDTOs.add(getTerminoDTO());

		try {
			when(comverter.convertDtoToEntity(ArgumentMatchers.any(TerminoDTO.class))).thenReturn(getTermino());
			when(repository.findByNombreTermino(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());
			when(repository.save(ArgumentMatchers.any(Termino.class))).thenReturn(null);
			when(comverter.convertEntityToDto(ArgumentMatchers.any(Termino.class))).thenReturn(getTerminoDTO());

			ResponseBulkLoad response = diccionarioService.bulkLoad(terminoDTOs, "true");
			assertEquals(1, response.getDataNoLoaded().size());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			assert (false);
		}
	}

	/**
	 * Returns object Termino.
	 * 
	 * @return object Termino
	 */
	Termino getTermino() {
		return new Termino(1, "Example", "Example", "Example", "Example");
	}

	/**
	 * Returns object TerminoDTO.
	 * 
	 * @return object TerminoDTO
	 */
	TerminoDTO getTerminoDTO() {
		return new TerminoDTO("Example", "Exapmle", "Exapmle", "Exapmle");
	}

}
