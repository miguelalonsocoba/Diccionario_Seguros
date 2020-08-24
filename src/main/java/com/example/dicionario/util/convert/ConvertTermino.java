package com.example.dicionario.util.convert;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.TerminoEntity;

/**
 * Class ConvertTermino.
 */
public class ConvertTermino {

	/**
	 * Convierte objeto DTO a Entity.
	 * 
	 * @param terminoDto de TerminoDTO
	 * @return TerminoEntity
	 */
	public TerminoEntity convertDtoToEntity(TerminoDTO terminoDto) {
		TerminoEntity terminoEntity = new TerminoEntity();
		terminoEntity.setNombreTermino(terminoDto.getNombreTermino());
		terminoEntity.setDescripcion(terminoDto.getDescripcion());
		terminoEntity.setCategoria(terminoDto.getCategoria());
		terminoEntity.setEjemplo(terminoDto.getEjemplo());
		return terminoEntity;
	}

	/**
	 * Convierte objeto DTO a Entity.
	 * 
	 * @param terminoEntity de TerminoEntity
	 * @return TerminoDTO
	 */
	public TerminoDTO convertEntityToDto(TerminoEntity terminoEntity) {
		TerminoDTO terminoDTO = new TerminoDTO();
		terminoDTO.setNombreTermino(terminoEntity.getNombreTermino());
		terminoDTO.setDescripcion(terminoEntity.getDescripcion());
		terminoDTO.setCategoria(terminoEntity.getCategoria());
		terminoDTO.setEjemplo(terminoEntity.getEjemplo());
		return terminoDTO;
	}

}
