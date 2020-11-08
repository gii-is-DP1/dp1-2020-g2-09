package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.stereotype.Repository;


public interface ClienteRepository extends CrudRepository<Clientes, Integer>{

	
	//filtrar cliente 
	@Query("SELECT DISTINCT cliente FROM Clientes cliente WHERE cliente.nombreUsuario LIKE :nombreUsuario%")
	public Collection<Clientes> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
}
