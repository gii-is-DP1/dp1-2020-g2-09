package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Integer>{

	List<Administrador> findAll() throws DataAccessException;
	
	Administrador findAdministradorById(int administradorId) throws DataAccessException;
	
}
