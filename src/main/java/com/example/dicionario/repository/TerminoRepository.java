package com.example.dicionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dicionario.entity.Termino;

/**
 * Interface TerminoRepository.
 */
@Repository(value = "terminoRepository")
public interface TerminoRepository extends JpaRepository<Termino, Integer> {
	
	List<Termino> findByNombreTermino(String nombreTermino);
	

}
