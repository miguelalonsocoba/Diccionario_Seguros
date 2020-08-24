package com.example.dicionario.service;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.TerminoEntity;

/**
 * Interface ITerminoService.
 */
public interface ITerminoService {

	/**
	 * Method to retrieve a term.
	 * 
	 * @param id the Integer
	 * @return Entity TerminoEntity
	 */
	TerminoDTO findById(Integer id);

}
