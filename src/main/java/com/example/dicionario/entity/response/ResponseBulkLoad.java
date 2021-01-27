package com.example.dicionario.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.example.dicionario.dto.TerminoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class ResponseBulkLoad.
 * 
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

	/** Almacena el numero total de datos eliminados por el rollBack. */
	Long totalesRollBack;

	public List<TerminoDTO> getLoadedData() {
		List<TerminoDTO> castedList = new ArrayList<>();
		if (this.loadedData == null) {
			return castedList;
		}
		for (TerminoDTO terminoDTO : loadedData) {
			castedList.add(terminoDTO);
		}
		return castedList;
	}

	public List<TerminoDTO> getDataNoLoaded() {
		List<TerminoDTO> castedList = new ArrayList<>();
		if (this.dataNoLoaded == null) {
			return castedList;
		}
		for (TerminoDTO terminoDTO : dataNoLoaded) {
			castedList.add(terminoDTO);
		}
		return castedList;
	}

}
