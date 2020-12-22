package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.stereotype.Repository;

@Repository
public interface CocineroRepository  extends CrudRepository<Cocina, Integer>{

	List<Cocina> findAll() throws DataAccessException;
	
	Cocina findCocineroById(int cocineroId) throws DataAccessException;
	
}
