package com.example.dicionario.readfile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.detDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.controller.DiccionarioController;
import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.readfile.request.ReadFileExcelRequest;
import com.example.dicionario.readfile.service.IReadExcelService;
import com.fasterxml.jackson.core.io.DataOutputAsStream;

import lombok.extern.slf4j.Slf4j;

/**
 * Class ReadExcelController.
 * 
 * @author Miguel
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/readFile")
public class ReadExcelController {

	/** The servie. */
	@Autowired
	private IReadExcelService service;

	/** The diccionarioController. */
	@Autowired
	private DiccionarioController diccionarioController;
	
	/** The terminos. */
	private List<List<String>> terminos = new ArrayList<>();

	@PostMapping(value = "/excel", consumes = "application/json", produces = "application/json")
	public Map<Integer, List<String>> readExcel(
			@Validated @RequestBody(required = true) final ReadFileExcelRequest fileL) throws Exception {
		Optional<ReadFileExcelRequest> optionalRequest = Optional.ofNullable(fileL);
		optionalRequest.orElseThrow(NullPointerException::new);
		Optional<String> parameterRequest = Optional.ofNullable(optionalRequest.get().getFileLocation());
		parameterRequest.orElseThrow(NullPointerException::new);
		log.info("ReadExcelController >>>>> readExcel() - Parameter: {}", fileL.toString());
		Map<Integer, List<String>> response = null;
		try {
			response = service.readExcel(optionalRequest.get().getFileLocation());
		} catch (Exception e) {
			log.error("Exception: {}", e.getMessage());
			throw new Exception(e.getMessage());
		}
		log.info("Obteniendo terminos para realizar la carga masiva.");
		response.entrySet().forEach( e -> terminos.add(e.getValue()));
		log.info("Terminos Obtenidos: {}", terminos);
		List<TerminoDTO> terminoDTOList = converterListStringToTerminoDTO(terminos);
		diccionarioController.bulkLoad(terminoDTOList, "false");
		log.info("ReadExcelController <<<<< readExcel() - Response: {}", response);
		return response;
	}
	
	private List<TerminoDTO> converterListStringToTerminoDTO(List<List<String>> data) {
		log.info("ReadExcelController >>>>> converterListStringToTerminoDTO() - Parameter: {}", data.toString());
		List<TerminoDTO> response = new ArrayList<>();
		data.stream().forEach( e -> {
			log.info("Convirtiendo la lista de tipo String a un objeto TerminoDTO. Lista: {}", e.toString());
			response.add(new TerminoDTO(e.get(0), e.get(1), e.get(2), e.get(3)));
		});
		log.info("ReadExcelController <<<<< convertListStringToTerminoDTO() - Response: {}", response.toString());
		return response;
	}

}
