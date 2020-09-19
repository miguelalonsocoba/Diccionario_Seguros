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
	TerminoDTO addTermino(TerminoDTO termino);

	/**
	 * Elimina un termino por ID.
	 * 
	 * @param id of Integer
	 */
	void deleteById(Integer id);

	/**
	 * Enlista todos los terminos.
	 * 
	 * @return List of Termino
	 */
	List<TerminoDTO> listAll();

	/**
	 * Obtener un termino en base a su nombre.
	 * 
	 * @param termino de String
	 * @return list TerminoDTO
	 */
	List<TerminoDTO> getTerminoByNombre(String termino);

	/**
	 * Actualizar información del Termino.
	 * 
	 * @param termino of Termino
	 * @return object TErminoDTO
	 * @throws Exception
	 */
	TerminoDTO updateTermino(TerminoDTO termino) throws Exception;

}
