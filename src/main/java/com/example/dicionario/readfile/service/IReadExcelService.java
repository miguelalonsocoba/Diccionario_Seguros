package com.example.dicionario.readfile.service;

import java.util.List;
import java.util.Map;

/**
 * Interface IReadExcelService.
 * 
 * @author Miguel
 *
 */
public interface IReadExcelService {

	/**
	 * Lee el archivo Excel.
	 * 
	 * @param fileLocation the fileLocation
	 * @return object Map
	 */
	Map<Integer, List<String>> readExcel(String fileLocation);

}
