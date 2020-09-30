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
		if (terminoDto.getCategoria() == null || terminoDto.getDescripcion() == null || terminoDto.getEjemplo() == null
				|| terminoDto.getNombreTermino() == null) {
			log.error("Some TerminoDto attribute have null value: ");
			throw new NullPointerException("Some attribute have null value.");
		}
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
		if (terminoEntity.getCategoria() == null || terminoEntity.getDescripcion() == null
				|| terminoEntity.getEjemplo() == null || terminoEntity.getId() == null
				|| terminoEntity.getNombreTermino() == null) {
			log.info("Some Termino attribute have null value");
			throw new NullPointerException("Some Termino attribute have null value");
		}
		TerminoDTO terminoDTO = new TerminoDTO();
		terminoDTO.setNombreTermino(terminoEntity.getNombreTermino());
		terminoDTO.setDescripcion(terminoEntity.getDescripcion());
		terminoDTO.setCategoria(terminoEntity.getCategoria());
		terminoDTO.setEjemplo(terminoEntity.getEjemplo());
		log.info(String.format("convertEntityToDto() <<<<< terminoDto: %s", terminoDTO));
		return terminoDTO;
	}

}
