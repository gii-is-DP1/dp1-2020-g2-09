package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaRepository  extends CrudRepository<Carta, Integer>{

	List<Carta> findAll() throws DataAccessException;
	
	Carta findById(int cartaId) throws DataAccessException;
	
	@Query(value = "SELECT * FROM CARTAS WHERE ?1 BETWEEN FECHA_CREACION AND FECHA_FINAL" ,nativeQuery = true)
	Carta findCartaByFechaCreacionYFechaFinal(LocalDate hoy) throws DataAccessException;
	//LocalDate hoy = LocalDate.now();
	
	@Modifying
    @Query(value = "INSERT INTO  PRODUCTO_PIZZA_PEDIDO (PEDIDO_ID, PIZZAS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirPizzaAPedido(int pizzaId, int pedidoId);
	
	@Modifying
    @Query(value = "INSERT INTO  PRODUCTO_BEBIDA_PEDIDO (PEDIDO_ID, BEBIDAS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirBebidaAPedido(int bebidaId, int pedidoId);
	
	@Modifying
    @Query(value = "INSERT INTO  PRODUCTO_OTROS_PEDIDO (PEDIDO_ID, OTROS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirOtrosAPedido(int otrosId, int pedidoId);
}