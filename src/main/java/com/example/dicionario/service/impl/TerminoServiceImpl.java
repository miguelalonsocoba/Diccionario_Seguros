package com.example.dicionario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.TerminoEntity;
import com.example.dicionario.repository.ITerminoRepository;
import com.example.dicionario.service.ITerminoService;
import com.example.dicionario.util.convert.ConvertTermino;

/**
 * Class TerminoServiceImpl.
 */
@Service(value = "TerminoService")
public class TerminoServiceImpl implements ITerminoService {

	@Autowired
	@Qualifier("TerminoRepository")
	private ITerminoRepository terminoRepository;

	private ConvertTermino convert;

	/**
	 * Obtener Termino con el id.
	 */
	@Override
	public TerminoDTO findById(Integer id) {
		TerminoEntity terminoEnity;
		terminoEnity = terminoRepository.finById(id);
		return convert.convertEntityToDto(terminoEnity);
	}

}
