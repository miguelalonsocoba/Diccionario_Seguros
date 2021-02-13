package com.example.dicionario.readfile.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dicionario.readfile.service.IReadExcelService;
import com.example.dicionario.readfile.util.HandlingFiles;

import lombok.extern.slf4j.Slf4j;

/**
 * Class ReadExcelServiceImpl.
 * 
 * @author Miguel
 *
 */
@Slf4j
@Service
public class ReadExcelServiceImpl implements IReadExcelService {

	/** The handlingFiles. */
	@Autowired
	private HandlingFiles handlingFiles;

	/**
	 * Lee un archivo excel.
	 * @throws Exception 
	 */
	@Override
	public Map<Integer, List<String>> readExcel(final String fileLocation) throws Exception {
		log.info("ReadExcelServiceImpl >>>>> readExcel() - Parameter: {}", fileLocation);
		Optional<String> fileLocationO = Optional.ofNullable(fileLocation);
		fileLocationO.orElseThrow(() -> new NullPointerException(String.format("Param %s es null", "fileLocation")));
		Workbook openFile = handlingFiles.openFile(fileLocation);
		Map<Integer, List<String>> response =  handlingFiles.retrieveFirstSheet(openFile);
		log.info("ReadExcelServiceImpl <<<<< readExcek() - Return: {}", "Succesful");
		return response;
	}

}
