package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
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

	@Query(value = "SELECT PIZZAS_EN_CARTA_ID FROM COMPOSICION_CARTA_PIZZA WHERE CARTA_ID = ?1",
			nativeQuery = true)
	List<Integer> findIdPizzaById(int cartaId) throws DataAccessException;
	
	@Query("SELECT TipoMasa FROM tipoMasa TipoMasa")
    List<tipoMasa> findTipoMasa() throws DataAccessException;
    
    @Query("SELECT TamanoProducto FROM TamanoProducto TamanoProducto")
    List<TamanoProducto> findTamaño() throws DataAccessException;
    
    @Modifying
    @Query(value = "INSERT INTO COMPOSICION_CARTA_PIZZA(PIZZAS_EN_CARTA_ID, CARTA_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirPizzaACarta(int pizzaId, int cartaId);

    @Modifying
	@Query(value = "DELETE FROM PIZZAS_INGREDIENTES WHERE PIZZA_ID = ?1",
			nativeQuery = true)
	void deleteComposicionIngredientes(Integer id);

	@Modifying
	@Query(value = "DELETE FROM COMPOSICION_CARTA_PIZZA WHERE PIZZAS_EN_CARTA_ID = ?1",
			nativeQuery = true)
	void deleteComposicion(Integer pizzaId);
}
