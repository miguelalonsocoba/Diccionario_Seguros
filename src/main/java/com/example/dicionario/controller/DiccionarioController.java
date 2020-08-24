package com.example.dicionario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.service.ITerminoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioController.
 */
@Slf4j
@RestController
@RequestMapping(path = "/diccionario")
public class DiccionarioController {

	@Autowired
	@Qualifier(value = "TerminoService")
	private ITerminoService service;

	@GetMapping(path = "/getTermino")
	public TerminoDTO getTermino(@RequestParam(name = "id") final Integer id) {
		return service.findById(id);
	}

	/** Metodo de ejemplo. */
	@GetMapping(path = "/message")
	public String getMessage(@RequestParam(name = "message") final String message) {
		log.info(String.format("getMEssage() >>>>>> message: %s", message));
		return message;
	}

}
