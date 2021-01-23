package com.example.dicionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dicionario.entity.Termino;

/**
 * Interface TerminoRepository.
 */
@Repository(value = "terminoRepository")
public interface TerminoRepository extends JpaRepository<Termino, Integer> {

	/**
	 * Busca por nombre un Termino.
	 * 
	 * @param nombreTermino the nombreTermino
	 * @return list Termino
	 */
	List<Termino> findByNombreTermino(String nombreTermino);

	/**
	 * Elimina un Termino a partir de un nombre.
	 * 
	 * @param nombreTermino the nombreTermino
	 * @return object long
	 */
	@Transactional
	Long deleteByNombreTermino(@Param("nombreTermino") String nombreTermino);

}
