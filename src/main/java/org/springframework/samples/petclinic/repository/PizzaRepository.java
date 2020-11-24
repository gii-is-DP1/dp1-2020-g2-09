package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

	//Crear un findbynombre??	
	
	List<Pizza> findAll() throws DataAccessException;
	
	Pizza findPizzaById(int pizzaId) throws DataAccessException;
	
}
