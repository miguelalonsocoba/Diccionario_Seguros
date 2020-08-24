package com.example.dicionario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dicionario.entity.TerminoEntity;

/**
 * Interface ITerminoRepository.
 */
@Repository(value = "TerminoRepository")
public interface ITerminoRepository extends CrudRepository<TerminoEntity, Integer> {

	/**
	 * Method to retrieve a term.
	 * 
	 * @param id the Integer
	 * @return Entity TerminoEntity
	 */
	TerminoEntity finById(Integer id);

}
