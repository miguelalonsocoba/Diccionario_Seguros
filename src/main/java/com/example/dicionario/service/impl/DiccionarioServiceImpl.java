package com.example.dicionario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.Termino;
import com.example.dicionario.repository.TerminoRepository;
import com.example.dicionario.service.IDiccionarioService;
import com.example.dicionario.util.convert.ConvertTermino;

import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioServiceImpl.
 */
@Slf4j
@Service(value = "diccionarioService")
public class DiccionarioServiceImpl implements IDiccionarioService {

	/** The Repository. */
	@Autowired
	@Qualifier(value = "terminoRepository")
	private TerminoRepository repository;

	/** The Converter. */
	@Autowired
	@Qualifier(value = "convert")
	private ConvertTermino converter;

	/**
	 * Get Termino.
	 */
	@Override
	public TerminoDTO getTerminoId(Integer idTermino) {
		log.info(String.format("getTerminoId()>>>>>> termino: %s", idTermino));
		try {
			Optional<Termino> terminoEntityResponse = repository.findById(idTermino);
			TerminoDTO responesDto = converter.convertEntityToDto(terminoEntityResponse.get());
			log.info(String.format("getTerminoId()<<<<<< message: %s", responesDto));
			return responesDto;
		} catch (NoSuchElementException e) {
			log.error(String.format("IllegalArgumentException: %s", e.getMessage()));
			throw new IllegalArgumentException(
					"No se encontro el termino con el id " + idTermino + " " + e.getMessage());
		}
	}

	/**
	 * Agrega un termino.
	 */
	@Override
	public TerminoDTO addTermino(TerminoDTO terminoDto) {
		log.info(String.format("addTermino() >>>>> termino: %s", terminoDto.toString()));
		Termino terminoEntityRequest = converter.convertDtoToEntity(terminoDto);
		Termino terminoEntityResponse = repository.save(terminoEntityRequest);
		TerminoDTO terminoDtoResponse = converter.convertEntityToDto(terminoEntityResponse);
		log.info(String.format("addTermino() <<<<< response: %s", terminoDtoResponse.toString()));
		return terminoDtoResponse;
	}

	@Override
	public void deleteById(Integer id) {
		log.info(String.format("deleteById() >>>>> id: %s", id));
		try {
			repository.deleteById(id);
			log.info(String.format("deleteById() <<<<< response: %s", "Correct"));
		} catch (IllegalArgumentException e) {
			log.error(String.format("IllegalArgumentException: %s", e.getMessage()));
			throw new IllegalArgumentException("El id no existe. " + e.getMessage());
		}
	}

	/**
	 * Enlista todos los terminos.
	 */
	@Override
	public List<TerminoDTO> listAll() {
		log.info("listAll() >>>>> ");
		List<Termino> listTerminosResponse = repository.findAll();
		List<TerminoDTO> responseTerminos = new ArrayList<>();
		for (Termino termino : listTerminosResponse) {
			TerminoDTO responseDto = new TerminoDTO();
			responseDto = converter.convertEntityToDto(termino);
			responseTerminos.add(responseDto);
		}
		log.info(String.format("listAll() <<<<< response: %s", "response"));
		return responseTerminos;
	}

}