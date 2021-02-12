package com.example.dicionario.readfile.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.readfile.request.ReadFileExcelRequest;
import com.example.dicionario.readfile.service.IReadExcelService;

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

	@Autowired
	private IReadExcelService service;

	@PostMapping(value = "/excel", consumes = "application/json", produces = "application/json")
	public Map<Integer, List<String>> readExcel(@Validated @RequestBody(required = true) final ReadFileExcelRequest fileL) throws Exception {
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
		log.info("ReadExcelController <<<<< readExcel() - Response: {}", response);
		return response;
	}

}
