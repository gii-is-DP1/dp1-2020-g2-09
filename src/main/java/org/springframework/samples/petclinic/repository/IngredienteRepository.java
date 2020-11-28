package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer>{

	
	//filtrar cliente 
//	@Query("SELECT DISTINCT cliente FROM Cliente cliente WHERE cliente.usuario LIKE :usuario%")
//	public Collection<Cliente> findByNombreUsuario(@Param("usuario") String usuario);
	
	List<Ingrediente> findAll() throws DataAccessException;
	
	Ingrediente findIngredienteById(int ingredienteId) throws DataAccessException;
	
	@Query("SELECT Al FROM Alergenos Al")
    List<Alergenos> findAlergenos() throws DataAccessException;
	
}
