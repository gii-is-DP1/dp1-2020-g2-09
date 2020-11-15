package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorRepository extends CrudRepository<Repartidor, Integer>{

	List<Repartidor> findAll() throws DataAccessException;
	
	Repartidor findRepartidorById(int repartidorId) throws DataAccessException;
	
}
