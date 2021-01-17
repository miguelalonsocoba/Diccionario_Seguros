package com.example.dicionario.service;

import java.util.List;

import com.example.dicionario.dto.TerminoDTO;

/**
 * Inteface IDiccionarioService.
 */
public interface IDiccionarioService {

	/**
	 * Obtener un termino a partir de su ID.
	 * 
	 * @param idTermino de Integer
	 * @return Object TerminoDTO
	 */
	TerminoDTO getTerminoId(Integer idTermino);

	/**
	 * Agrega un termino.
	 * 
	 * @param termino de TerminoDTO
	 * @return objeto TerminoDTO
	 */
	TerminoDTO addTermino(TerminoDTO termino) throws Exception;

	/**
	 * Elimina un termino por ID.
	 * 
	 * @param id of Integer
	 * @return object Stirng
	 */
	String deleteById(Integer id);

	/**
	 * Enlista todos los terminos.
	 * 
	 * @return List of Termino
	 * @throws Exception the exception
	 */
	List<TerminoDTO> listAll() throws Exception;

	/**
	 * Obtener un termino en base a su nombre.
	 * 
	 * @param termino de String
	 * @return list TerminoDTO
	 */
	List<TerminoDTO> getTerminoByNombre(String termino);

	/**
	 * Actualizar informacion del Termino.
	 * 
	 * @param termino of Termino
	 * @return object TerminoDTO
	 * @throws Exception
	 */
	TerminoDTO updateTermino(TerminoDTO termino) throws Exception;

	/**
	 * Realiza una carga masiva de terminos.
	 * 
	 * @param terminos of Termino
	 * @return list TerminoDTO
	 * @throws Exception
	 */
	List<TerminoDTO> bulkLoad(List<TerminoDTO> terminos) throws Exception;

}
