package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

	
	//filtrar cliente 
//	@Query("SELECT DISTINCT cliente FROM Cliente cliente WHERE cliente.usuario LIKE :usuario%")
//	public Collection<Cliente> findByNombreUsuario(@Param("usuario") String usuario);
	
	List<Cliente> findAll() throws DataAccessException;
	
	Cliente findById(int clienteId) throws DataAccessException;
	
	
	
}
