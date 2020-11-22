package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaRepository  extends CrudRepository<Carta, Integer>{

	List<Carta> findAll() throws DataAccessException;
	
	Carta findCartaById(int cartaId) throws DataAccessException;

	Carta findCartaByFecha(LocalDate cartaFecha)throws DataAccessException;
}