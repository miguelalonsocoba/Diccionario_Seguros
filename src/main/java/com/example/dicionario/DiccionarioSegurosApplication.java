package com.example.dicionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DiccionarioSegurosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiccionarioSegurosApplication.class, args);
		log.info("Inicializando application...");
	}

}
