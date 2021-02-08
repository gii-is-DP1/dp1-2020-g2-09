package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

	
	List<Cliente> findAll() throws DataAccessException;
	
	Cliente findById(int clienteId) throws DataAccessException;
	
	Cliente findByUser(User usuario) throws DataAccessException;
	
	
}
