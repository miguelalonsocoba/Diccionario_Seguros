package com.example.dicionario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioController.
 */
@Slf4j
@RestController
@RequestMapping(path = "/diccionario")
public class DiccionarioController {

	/** Metodo de ejemplo. */
	@GetMapping(path = "/message")
	public String getMessage(@RequestParam(name = "message") final String message) {
		log.info(String.format("getMEssage() >>>>>> message: %s", message));
		return message;
	}

}
