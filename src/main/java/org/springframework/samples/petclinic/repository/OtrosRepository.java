package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.stereotype.Repository;

@Repository
public interface OtrosRepository extends CrudRepository<Otros, Integer>{

	
	List<Otros> findAll() throws DataAccessException;
	
	Otros findOtrosById(int OtrosId) throws DataAccessException;
	
	//List<Otros> findByCartaDeOtros(int cartaId) throws DataAccessException;
	
}
