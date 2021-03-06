package org.springframework.samples.petclinic.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer>{

	List<Ingrediente> findAll() throws DataAccessException;
	
	Ingrediente findIngredienteById(int ingredienteId) throws DataAccessException;
	
	@Query("SELECT Al FROM Alergenos Al")
    List<Alergenos> findAlergenos() throws DataAccessException;
	
	@Query(value = "SELECT INGREDIENTES_ID  FROM PIZZAS_INGREDIENTES WHERE PIZZA_ID = ?1",
			nativeQuery = true)
	List<Integer> findIngredienteIdByPizzaId(int pizzaId) throws DataAccessException;
	
	@Query(value = "SELECT INGREDIENTES_ID  FROM OTROS_INGREDIENTES WHERE OTROS_ID = ?1",
			nativeQuery = true)
	List<Integer> findIngredienteIdByOtrosId(int otroId) throws DataAccessException;
	
	@Query(value = "SELECT COUNT(*) FROM otros_INGREDIENTES WHERE INGREDIENTES_ID= ?1 union  SELECT COUNT(*) FROM PIZZAS_INGREDIENTES WHERE INGREDIENTES_ID= ?1",
			nativeQuery = true)
	List<Integer> CountIngrediente(int ingredienteId) throws DataAccessException;

	@Query(value = "SELECT FECHA_CADUCIDAD FROM INGREDIENTE WHERE ID=?1",
			nativeQuery = true)
	List<Date> CaducidadIngrediente(int ingredienteId) throws DataAccessException;
	
}
