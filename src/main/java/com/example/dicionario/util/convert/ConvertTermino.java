package com.example.dicionario.util.convert;

import org.springframework.stereotype.Component;

import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.Termino;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ConverterTermino.
 */
@Slf4j
@Component(value = "convert")
public class ConvertTermino {

	/**
	 * Convierte objeto DTO a Entity.
	 * 
	 * @param terminoDto de TerminoDTO
	 * @return TerminoEntity
	 */
	public Termino convertDtoToEntity(TerminoDTO terminoDto) {
		log.info(String.format("convertDtoToEntity() >>>>> terminoDto: %s", terminoDto.toString()));
		Termino terminoEntity = new Termino();
		terminoEntity.setNombreTermino(terminoDto.getNombreTermino());
		terminoEntity.setDescripcion(terminoDto.getDescripcion());
		terminoEntity.setCategoria(terminoDto.getCategoria());
		terminoEntity.setEjemplo(terminoDto.getEjemplo());
		log.info(String.format("convertDtoToEntity <<<<< terminoEntity: %s", terminoEntity));
		return terminoEntity;
	}

	/**
	 * Convierte objeto DTO a Entity.
	 * 
	 * @param terminoEntity de TerminoEntity
	 * @return TerminoDTO
	 */
	public TerminoDTO convertEntityToDto(Termino terminoEntity) {
		log.info(String.format("convertEntityToDto() >>>>> terminoEntity: %s", terminoEntity.toString()));
		TerminoDTO terminoDTO = new TerminoDTO();
		terminoDTO.setNombreTermino(terminoEntity.getNombreTermino());
		terminoDTO.setDescripcion(terminoEntity.getDescripcion());
		terminoDTO.setCategoria(terminoEntity.getCategoria());
		terminoDTO.setEjemplo(terminoEntity.getEjemplo());
		log.info(String.format("convertEntityToDto() <<<<< terminoDto: %s", terminoDTO));
		return terminoDTO;
	}

}
