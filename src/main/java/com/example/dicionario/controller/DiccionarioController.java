package com.example.dicionario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.constants.Constants;
import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.exceptions.ParameterException;
import com.example.dicionario.exceptions.ProxyException;
import com.example.dicionario.service.IDiccionarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioController.
 */
@Slf4j
@RestController
@RequestMapping(path = "/diccionario")
public class DiccionarioController {

	/** The service. */
	@Autowired
	@Qualifier(value = "diccionarioService")
	private IDiccionarioService serviceDiccionario;

	/** The oMapper. */
	@Autowired
	private ObjectMapper oMapper;

	/** Metodo de ejemplo. */
	@GetMapping(path = "/getTermino")
	public String getTerminoID(@RequestParam(name = "idTermino") final Integer idTermino) {
		log.info(String.format("getTerminoID() >>>>>> idTermino: %s", idTermino));
		TerminoDTO termino = serviceDiccionario.getTerminoId(idTermino);
		log.info(String.format("getTerminoID() <<<<<< termino: %s", termino.toString()));
		return termino.toString();
	}

	@PostMapping(path = "/addTermino", consumes = "application/json", produces = "application/json")
	public String addTermino(@RequestBody final String request) {
		log.info(String.format("addTermino() >>>>>>> request: %s", request));
		try {
			TerminoDTO objRequest = oMapper.readValue(request, TerminoDTO.class);
			if (null == objRequest) {
				log.error(String.format("ParameterException: %s", Constants.ERROR_JSON_FORMATO));
				throw new ParameterException(Constants.ERROR_JSON_FORMATO);
			}
			TerminoDTO response = serviceDiccionario.addTermino(objRequest);
			log.info(String.format("addTermino() <<<<<<<< response: %s", response.toString()));
			return  oMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error(String.format("JsonProccesingException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		}
	}

}
