package com.example.dicionario.readfile.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.dicionario.dto.TerminoDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * Class Converter.
 * 
 * @author Miguel
 *
 */
@Slf4j
@Component
public class Converter {

	/**
	 * Convierte una List<String> a un objeto de tipo List<TerminoDTO>.
	 * 
	 * @param data the data
	 * @return list of TerminoDTO
	 */
	public List<TerminoDTO> converterListStringToTerminoDTO(List<List<String>> data) {
		Optional<List<List<String>>> optional = Optional.ofNullable(data);
		if (!optional.isPresent()) {
			throw new NullPointerException("Atributo con valor Null.");
		}
		log.info("Converter() >>>>> converterListStringToTerminoDTO() - Params: {}", data.toString());
		List<TerminoDTO> response = new ArrayList<>();
		data.stream().forEach(e -> {
			log.info("Convirtiendo la lista de tipo String a un objeto TerminoDTO. Lista: {}", e.toString());
			response.add(new TerminoDTO(e.get(0), e.get(1), e.get(2), e.get(3)));
		});
		log.info("Converter() <<<<< converterListStringToTerminoDTO() - Response: {}", response.toString());
		return response;
	}

}
