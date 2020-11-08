package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

	
	//coger todos los clientes
	public Collection<Cliente> findAll() throws DataAccessException;
	
	//filtrar cliente 
	@Query("SELECT DISTINCT cliente FROM Cliente cliente WHERE cliente.nombreUsuario LIKE :nombreUsuario%")
	public Collection<Cliente> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	
}
