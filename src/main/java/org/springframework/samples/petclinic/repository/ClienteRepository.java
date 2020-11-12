package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

	
	//filtrar cliente 
	@Query("SELECT DISTINCT cliente FROM Cliente cliente WHERE cliente.nombreUsuario LIKE :nombreUsuario%")
	public Collection<Cliente> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	List<Cliente> findAll() throws DataAccessException;
	
	Cliente findCuentaById(int clienteId) throws DataAccessException;
	
	
	
}
