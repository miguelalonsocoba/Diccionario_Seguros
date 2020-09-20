package com.example.dicionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DiccionarioSegurosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DiccionarioSegurosApplication.class, args);
		log.info("Inicializando application...");
	}

	/**
	 * Metodo que inicializa un servlet.
	 */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(DiccionarioSegurosApplication.class);
	}

}
