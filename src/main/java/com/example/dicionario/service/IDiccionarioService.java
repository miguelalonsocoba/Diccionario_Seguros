package com.example.dicionario.service;

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

}
