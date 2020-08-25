package com.example.dicionario.service.impl;

import org.springframework.stereotype.Service;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.service.IDiccionarioService;

import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioServiceImpl.
 */
@Slf4j
@Service(value = "diccionarioService")
public class DiccionarioServiceImpl implements IDiccionarioService {

	/**
	 * Get Termino.
	 */
	@Override
	public TerminoDTO getTerminoId(Integer idTermino) {
		log.info(String.format("getTerminoId()>>>>>> termino: %s", idTermino));
		log.info(String.format("getTerminoId()<<<<<< message: %s", "correcto"));
		return new TerminoDTO("GNP", "Empresa de aseguradora", "", "Empresa");
	}

	/**
	 * Agrega un termino.
	 */
	@Override
	public TerminoDTO addTermino(TerminoDTO termino) {
		log.info(String.format("addTermino() >>>>> termino: %s", termino.toString()));
		log.info(String.format("addTermino() <<<<< response: %s", termino.toString()));
		return termino;
	}

}
