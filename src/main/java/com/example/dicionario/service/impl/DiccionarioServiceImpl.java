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
import com.example.dicionario.util.validator.Validate;

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

	/** The validator. */
	private Validate validator = new Validate();

	/** The list terminos entity. */
	List<Termino> terminosEntity = new ArrayList<>();

	/** The list terminos dto. */
	List<TerminoDTO> terminosDTO = new ArrayList<>();

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
	 * Add Termino.
	 * @throws Exception 
	 */
	@Override
	public TerminoDTO addTermino(TerminoDTO terminoDto) throws Exception {
		log.info(String.format("addTermino() >>>>> termino: %s", terminoDto.toString()));
		try {
			log.info("Validando parametros del objeto.");
			validator.validateTerminoNull(terminoDto);
		} catch (IllegalArgumentException e) {
			log.info("Exception IllegalArgumentException - " + e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		Termino terminoEntityRequest = converter.convertDtoToEntity(terminoDto);
		List<Termino> terminos = repository.findByNombreTermino(terminoEntityRequest.getNombreTermino());
		if (!terminos.isEmpty() ) {
			log.error("Ya existe el Termino: " + terminoEntityRequest.getNombreTermino());
			throw new Exception(String.format("El termino %s ya existe...", terminoEntityRequest.getNombreTermino()));
		}
		Termino terminoEntityResponse = repository.save(terminoEntityRequest);
		TerminoDTO terminoDtoResponse = converter.convertEntityToDto(terminoEntityResponse);
		log.info(String.format("addTermino() <<<<< response: %s", terminoDtoResponse.toString()));
		return terminoDtoResponse;
	}

	/**
	 * Elimina un termino a partir de un ID.
	 */
	@Override
	public String deleteById(Integer id) {
		log.info(String.format("deleteById() >>>>> id: %s", id));
		try {
			repository.deleteById(id);
			log.info(String.format("deleteById() <<<<< response: %s", "Correct"));
			return "Success ok";
		} catch (IllegalArgumentException e) {
			log.error(String.format("IllegalArgumentException: %s", e.getMessage()));
			throw new IllegalArgumentException("El id no existe. " + e.getMessage());
		}
	}

	/**
	 * Enlista todos los terminos.
	 */
	@Override
	public List<TerminoDTO> listAll() throws Exception {
		log.info("listAll() >>>>> ");
		List<Termino> listTerminosResponse = repository.findAll();
		List<TerminoDTO> responseTerminos = new ArrayList<>();
		TerminoDTO responseDto;
		for (Termino termino : listTerminosResponse) {
			responseDto = converter.convertEntityToDto(termino);
			responseTerminos.add(responseDto);
		}
		log.info(String.format("listAll() <<<<< response: %s", "response"));
		return responseTerminos;
	}

	/**
	 * Obtiene terminos que coincidan con el nombre indicado.
	 */
	@Override
	public List<TerminoDTO> getTerminoByNombre(String termino) {
		log.info("getTerminoByNombre() >>>>");
		List<Termino> responseEntity = repository.findByNombreTermino(termino);
		if (responseEntity.isEmpty()) {
			throw new NullPointerException(String.format("El termino %s no existe en la base de datos.", termino));
		}
		List<TerminoDTO> responsDtos = new ArrayList<>();
		for (Termino terminoEntity : responseEntity) {
			TerminoDTO responseDto = new TerminoDTO();
			responseDto = converter.convertEntityToDto(terminoEntity);
			responsDtos.add(responseDto);
		}
		log.debug(responseEntity.toString());
		return responsDtos;
	}

	/**
	 * Actualizar informacion de un Termino.
	 * 
	 * @throws Exception
	 */
	@Override
	public TerminoDTO updateTermino(TerminoDTO termino) throws Exception {
		log.info("updateTermino >>>> request: %s" + termino.toString());
		try {
			log.info("Validando parametros del objeto.");
			validator.validateTerminoNull(termino);
		} catch (IllegalArgumentException e) {
			log.info("Exception IllegalArgumentException - " + e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		List<Termino> existeTermino = repository.findByNombreTermino(termino.getNombreTermino());

		if (existeTermino.isEmpty()) {
			log.error("Exception: %s", "El termino no existe");
			throw new Exception(String.format("El termino %s no existe.", termino.getNombreTermino()));
		}

		Termino response = repository.save(converter.convertDtoToEntity(termino));
		log.info("updateTermino() <<<<< response: %s", response.toString());
		return converter.convertEntityToDto(response);
	}

	/**
	 * Realiza la carga masiva de datos.
	 */
	@Override
	public List<TerminoDTO> bulkLoad(List<TerminoDTO> terminos) throws Exception {
		log.info("bulkLoad() >>>>> Param: " + terminos.toString());
		terminos.stream().forEach((e) -> terminosEntity.add(converter.convertDtoToEntity(e)));
		terminosEntity.stream().forEach(e -> {
			log.info("Value Entity: " + e.toString());
			terminosDTO.add(converter.convertEntityToDto(repository.save(e)));
		});
		return terminosDTO;
	}

}
