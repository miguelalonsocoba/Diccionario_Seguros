package com.example.dicionario.util.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.Termino;

/**
 * Class ConverterTerminoTest.
 *
 */
class ConvertTerminoTest {

	/** The converter. */
	private ConvertTermino convert;

	/**
	 * Inicializa lo que se necesita para los test.
	 */
	@BeforeEach
	public void setUp() {
		convert = new ConvertTermino();
	}

	/**
	 * Inicializa valores.
	 */
	@AfterEach
	public void setDown() {
		convert = null;
	}

	/**
	 * Comprueba que el objeto no es nulo.
	 */
	@Test
	void convertDtoToEntityTestIsNotNull() {
		Termino termino = convert.convertDtoToEntity(getTerminoDTO());
		assertNotNull(termino);
	}

	/**
	 * Comprueba que el objeto es el esperado.
	 */
	@Test
	void convertDtoToEntotyTestObjectEsperado() {
		Termino termino = convert.convertDtoToEntity(getTerminoDTO());

		assertEquals(getTerminoDTO().getCategoria(), termino.getCategoria());
	}

	/**
	 * Comprueba que trata el error cuando recibe un objeto nulo lanzando una
	 * exception de tipo NullPointerException..
	 */
	@Test
	void convertDtoToEntityObjectNull() {
		try {
			convert.convertDtoToEntity(new TerminoDTO());
		} catch (NullPointerException e) {
			assertEquals("Some attribute have null value.", e.getMessage());
		}

	}

	/**
	 * Comprueba que lanza una Exception de tipo NullPointerException cuando se le
	 * manda un parametro nulo,
	 */
	@Test
	void convertDtoToEntityParamNombreTerminoNull() {
		TerminoDTO termminoDto = new TerminoDTO();
		termminoDto.setCategoria("Categoria");
		termminoDto.setDescripcion("Descripcion");
		termminoDto.setEjemplo("Ejemplo");
		try {
			convert.convertDtoToEntity(termminoDto);
		} catch (NullPointerException e) {
			assertEquals("Some attribute have null value.", e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza una Exception de tipo NullPointerException cuando se le
	 * manda un parametro nulo,
	 */
	@Test
	void convertDtoToEntityParamDescripcionNull() {
		TerminoDTO termminoDto = new TerminoDTO();
		termminoDto.setCategoria("Categoria");
		termminoDto.setEjemplo("Ejemplo");
		termminoDto.setNombreTermino("NombreTermino");
		try {
			convert.convertDtoToEntity(termminoDto);
		} catch (NullPointerException e) {
			assertEquals("Some attribute have null value.", e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza una Exception de tipo NullPointerException cuando se le
	 * manda un parametro nulo,
	 */
	@Test
	void convertDtoToEntityParamEjemploNull() {
		TerminoDTO termminoDto = new TerminoDTO();
		termminoDto.setCategoria("Categoria");
		termminoDto.setDescripcion("Descripcion");
		termminoDto.setNombreTermino("NombreTermino");
		try {
			convert.convertDtoToEntity(termminoDto);
		} catch (NullPointerException e) {
			assertEquals("Some attribute have null value.", e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza una Exception de tipo NullPointerException cuando se le
	 * manda un parametro nulo,
	 */
	@Test
	void convertDtoToEntityParamCategoriaNull() {
		TerminoDTO termminoDto = new TerminoDTO();
		termminoDto.setNombreTermino("NombreTermino");
		termminoDto.setDescripcion("Descripcion");
		termminoDto.setEjemplo("Ejemplo");
		try {
			convert.convertDtoToEntity(termminoDto);
		} catch (NullPointerException e) {
			assertEquals("Some attribute have null value.", e.getMessage());
		}
	}

	/**
	 * Comprueba que el objeto de retorno no es null.
	 */
	@Test
	void convertEntityToDtoTestReturnNotNull() {
		TerminoDTO response = convert.convertEntityToDto(getTermino());

		assertNotNull(response);
	}

	/**
	 * Comprueba que el objeto de retorno es el esperado.
	 */
	@Test
	void convertEntityToDtoTestReturnEsperado() {
		TerminoDTO response = convert.convertEntityToDto(getTermino());

		assertEquals(getTerminoDTO().getCategoria(), response.getCategoria(), "The objects are not smae.");
		assertEquals(getTerminoDTO().getDescripcion(), response.getDescripcion(), "The objects are not same.");
		assertEquals(getTerminoDTO().getEjemplo(), response.getEjemplo(), "The objects are not same.");
		assertEquals(getTerminoDTO().getNombreTermino(), response.getNombreTermino(), "The objects are not same.");
	}

	/**
	 * Comprueba que retorna una Exception de tipo NullPointerException.
	 */
	@Test
	void convertEntityToDtoTestError() {
		try {
			convert.convertEntityToDto(new Termino());
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza exception de tipo NullPointerException cuando un
	 * parametro es nulo.
	 */
	@Test
	void convertEntityToDtoTestParamIdNull() {
		Termino termino = new Termino();
		termino.setCategoria("Categoria");
		termino.setDescripcion("Descripcion");
		termino.setEjemplo("Ejemplo");
		termino.setNombreTermino("NombreTermino");
		try {
			convert.convertEntityToDto(termino);
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza exception de tipo NullPointerException cuando un
	 * parametro es nulo.
	 */
	@Test
	void convertEntityToDtoTestParamNombreTerminoNull() {
		Termino termino = new Termino();
		termino.setCategoria("Categoria");
		termino.setDescripcion("Descripcion");
		termino.setEjemplo("Ejemplo");
		termino.setId(2);
		try {
			convert.convertEntityToDto(termino);
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza exception de tipo NullPointerException cuando un
	 * parametro es nulo.
	 */
	@Test
	void convertEntityToDtoTestParamDescripcionNull() {
		Termino termino = new Termino();
		termino.setCategoria("Categoria");
		termino.setId(3);
		termino.setEjemplo("Ejemplo");
		termino.setNombreTermino("NombreTermino");
		try {
			convert.convertEntityToDto(termino);
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza exception de tipo NullPointerException cuando un
	 * parametro es nulo.
	 */
	@Test
	void convertEntityToDtoTestParamEjemploNull() {
		Termino termino = new Termino();
		termino.setCategoria("Categoria");
		termino.setDescripcion("Descripcion");
		termino.setId(2);
		termino.setNombreTermino("NombreTermino");
		try {
			convert.convertEntityToDto(termino);
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Comprueba que lanza exception de tipo NullPointerException cuando un
	 * parametro es nulo.
	 */
	@Test
	void convertEntityToDtoTestParamCategorioNull() {
		Termino termino = new Termino();
		termino.setId(2);
		termino.setDescripcion("Descripcion");
		termino.setEjemplo("Ejemplo");
		termino.setNombreTermino("NombreTermino");
		try {
			convert.convertEntityToDto(termino);
		} catch (NullPointerException e) {
			assertEquals("Some Termino attribute have null value. La base de datos regreso un atributo con valor null",
					e.getMessage());
		}
	}

	/**
	 * Metodo que retorna un objeto de tipo Termino.
	 */
	private Termino getTermino() {
		return new Termino(1, "Example", "Example", "Example", "Example");
	}

	/**
	 * Metodo que retorna un objeto de tipo TerminoDTO.
	 * 
	 * @return
	 */
	private TerminoDTO getTerminoDTO() {
		return new TerminoDTO("Example", "Example", "Example", "Example");
	}

}
