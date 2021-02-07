package com.example.dicionario.readfile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Class HandlingFiles.
 * 
 * @author Miguel
 *
 */
@Slf4j
@Component
public class HandlingFiles {

	/**
	 * Abre un archivo excel.
	 * 
	 * @param fileLocation
	 * @return
	 */
	public Workbook openFile(String fileLocation) {
		log.info("HandlingFiles >>>>> openFile() - Parameter: {}", fileLocation);
		Workbook workbook; // Libro de Trabjo.
		try (FileInputStream file = new FileInputStream(new File(fileLocation))) {
			workbook = new XSSFWorkbook(file);
			log.info("HandlingFiles <<<<< openFile() - Return; {}", workbook.getActiveSheetIndex());
			return workbook;
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: {}", e.getMessage());
		} catch (IOException e) {
			log.error("IOException: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * Se recupera la primera hoja del archivo y se itera por cada fila.
	 * 
	 * @param workbook the workbook
	 */
	public Map<Integer, List<String>> retrieveFirstSheet(final Workbook workbook) {
		log.info("HandlingFiles >>>>> retrieveFirstSheet() - Param: {}", workbook.toString());

		Sheet sheet = workbook.getSheetAt(0);// Se obtiene la primera hoja del archivo Excel.

		Map<Integer, List<String>> data = new HashedMap<>();
		int i = 0;
		for (Row row : sheet) { // Recorre las filas de la Hoja
			List<String> valoresFila = new ArrayList<>();
			for (Cell cell : row) {// Recorre las celdas.
				switch (cell.getCellType()) {
				case STRING:
					log.info("Valor String: {}", cell.getStringCellValue());
					log.info("Value: {}", cell.getStringCellValue().isEmpty());
					if (i >= 4 && !cell.getStringCellValue().isEmpty()) {
						valoresFila.add(cell.getStringCellValue());
					}
					break;
				default:
					// data.get(new Integer(i)).add(" ");
				}
			}
			data.put(i - 3, valoresFila);
			log.info("Valor autoincremental: {}", i);
			i++;
		}

		Map<Integer, List<String>> dataClean = cleanMap(data);
		Map<Integer, List<String>> dataSorted = dataSorted(dataClean);
		log.info("HandlingFiles <<<<< retrieveFirstSheet() - Param: {}", data.toString());
		return dataSorted;
	}

	/**
	 * Elimina valores nulos o vacios del Map.
	 * 
	 * @param values the values
	 * @return object Map
	 */
	private Map<Integer, List<String>> cleanMap(Map<Integer, List<String>> data) {
		log.info("HandlingFiles >>>>> cleanMap() - Parameters: {}", data.toString());
		Map<Integer, List<String>> dataClean = new HashedMap<>();

		data.forEach((k, v) -> {
			log.info("Data: {}", v.toString());
			if (!v.isEmpty()) {
				dataClean.put(k, v);
			}
		});
		log.info("HandlingFiles <<<<< cleanMap() - Return: {}", dataClean.toString());
		return dataClean;
	}

	/**
	 * Oredena la información con respecto a su Key.
	 * 
	 * @param data the data
	 * @return object Map
	 */
	private Map<Integer, List<String>> dataSorted(Map<Integer, List<String>> data) {
		log.info("HandlingFiles >>>>> dataSorted() - Parameters: {}", data.toString());
		return data.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,
				Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

}
