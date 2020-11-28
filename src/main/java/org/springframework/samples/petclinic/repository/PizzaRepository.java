package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {
	
	List<Pizza> findAll() throws DataAccessException;
	
	Pizza findPizzaById(int pizzaId) throws DataAccessException;

	List<Pizza> findByCartaDePizzas(Carta carta) throws DataAccessException;
	
	@Query("SELECT TipoMasa FROM tipoMasa TipoMasa")
    List<tipoMasa> findTipoMasa() throws DataAccessException;
    
    @Query("SELECT TamanoProducto FROM TamanoProducto TamanoProducto")
    List<TamanoProducto> findTamaño() throws DataAccessException;
}
