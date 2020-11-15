package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.stereotype.Repository;


@Repository
public interface MesaRepository  extends CrudRepository<Mesa, Integer>{

	List<Mesa> findAll() throws DataAccessException;
	
	Mesa findMesaById(int mesaId) throws DataAccessException;
}
