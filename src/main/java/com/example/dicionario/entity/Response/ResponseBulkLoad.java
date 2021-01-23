package com.example.dicionario.entity.Response;

import java.util.List;

import com.example.dicionario.dto.TerminoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class ResponseBulkLoad.
 * @author Miguel
 *
 */
@Getter
@Setter
@ToString
public class ResponseBulkLoad {
	
	/** Dataos cargados. */
	List<TerminoDTO> loadedData;
	
	/** Datos no cargados. */
	List<TerminoDTO> dataNoLoaded;

}
