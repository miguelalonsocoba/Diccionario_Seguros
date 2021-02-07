package com.example.dicionario.readfile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(value = "/excel", produces = "application/json")
	public Map<Integer, List<String>> readExcel(@RequestBody(required = true) final ReadFileExcelRequest fileLocation) {
		log.info("ReadExcelController >>>>> readExcel() - Parameter: {}", fileLocation.getFileLocation());
		Map<Integer, List<String>> response = service.readExcel(fileLocation.getFileLocation());
		log.info("ReadExcelController <<<<< readExcel() - Response: {}", response);
		return response;
	}

}
