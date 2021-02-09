package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository  extends CrudRepository<Oferta, Integer> {
	
	List<Oferta> findAll() throws DataAccessException;
	
	Oferta findOfertaById(int ofertaId) throws DataAccessException;
	
	List<Oferta> findOfertasByEstadoOferta(boolean estadoOferta) throws DataAccessException;
	
	@Query("SELECT tamofert FROM TamanoOferta tamofert")
	List<TamanoOferta> findTamanoOferta() throws DataAccessException;

	@Query("SELECT nsocio FROM NivelSocio nsocio")
	List<NivelSocio> findNivelSocio() throws DataAccessException;
	
	@Query(value ="SELECT * FROM OFERTAS WHERE ESTADO_OFERTA=TRUE AND (CAST(FECHA_FINAL AS date) >= CAST(?1 AS date)) ",	nativeQuery = true)
	List<Oferta> findOfertasTrueEnTiempo(LocalDate hoy) throws DataAccessException;
	
	@Query(value ="SELECT OFERTAS_EN_PEDIDO_ID FROM OFERTA_PEDIDO  WHERE PEDIDO_ID = ?1",	nativeQuery = true)
	List<Integer> findOfertasEnPedidoById(int pedidoId) throws DataAccessException;

	@Query(value ="SELECT * FROM PIZZAS WHERE ID LIKE( SELECT PIZZAS_EN_OFERTA_ID FROM OFERTA_PIZZA ) ",	nativeQuery = true)
	List<Pizza> findPizzasEnOferta() throws DataAccessException;
	
	@Query(value ="SELECT * FROM BEBIDAS WHERE ID LIKE( SELECT BEBIDAS_EN_OFERTA_ID FROM OFERTA_BEBIDA )",	nativeQuery = true)
	List<Bebida> findBebidasEnOferta() throws DataAccessException;
	
	@Query(value ="SELECT * FROM OTROS WHERE ID LIKE( SELECT OTROS_EN_OFERTA_ID FROM OFERTA_OTRO )",	nativeQuery = true)
	List<Otro> findOtrosEnOferta() throws DataAccessException;
	
	@Modifying
	  @Query(value = "INSERT INTO OFERTA_PIZZA(OFERTA_ID, PIZZAS_EN_OFERTA_ID) VALUES (?1, ?2)",	nativeQuery = true)
		void asociarOfertaAPizza(int ofertaId, int pizzaEnOfertaId);
	
	@Modifying
	  @Query(value = "INSERT INTO OFERTA_BEBIDA(OFERTA_ID, BEBIDAS_EN_OFERTA_ID) VALUES (?1, ?2)",	nativeQuery = true)
		void asociarOfertaABebida(int ofertaId, int bebidaEnOfertaId);
	
	@Modifying
	  @Query(value = "INSERT INTO OFERTA_OTRO(OFERTA_ID, OTROS_EN_OFERTA_ID) VALUES (?1, ?2)",	nativeQuery = true)
		void asociarOfertaAOtro(int ofertaId, int otroEnOfertaId);
	
	@Query(value ="SELECT * FROM PIZZAS WHERE PIZZAS.ID IN( SELECT PIZZAS_EN_OFERTA_ID FROM OFERTA_PIZZA WHERE OFERTA_ID = ?1) ",	nativeQuery = true)
	List<Pizza> findPizzasEnOfertaByOfertaId(int ofertaId) throws DataAccessException;
	
	@Query(value ="SELECT * FROM BEBIDAS WHERE BEBIDAS.ID IN( SELECT BEBIDAS_EN_OFERTA_ID FROM OFERTA_BEBIDA WHERE OFERTA_ID = ?1) ",	nativeQuery = true)
	List<Bebida> findBebidasEnOfertaByOfertaId(int ofertaId) throws DataAccessException;
	
	@Query(value ="SELECT * FROM OTROS WHERE OTROS.ID IN( SELECT OTROS_EN_OFERTA_ID FROM OFERTA_OTRO WHERE OFERTA_ID = ?1) ",	nativeQuery = true)
	List<Otro> findOtrosEnOfertaByOfertaId(int ofertaId) throws DataAccessException;
	
	@Query(value = "SELECT OFERTA_ID FROM OFERTA_PIZZA WHERE PIZZAS_EN_OFERTA_ID = ?1", nativeQuery = true)
	List<Integer> numeroPizzasEnOferta(Integer pizzaId);
	
	
	@Query(value = "SELECT OFERTA_ID FROM OFERTA_BEBIDA WHERE BEBIDAS_EN_OFERTA_ID = ?1", nativeQuery = true)
	List<Integer> numeroBebidasEnOferta(Integer bebidaId);
	
	@Query(value = "SELECT OFERTA_ID FROM OFERTA_OTRO WHERE OTROS_EN_OFERTA_ID = ?1", nativeQuery = true)
	List<Integer> numeroOtrosEnOferta(Integer otrosId);
	
	@Modifying
	@Query(value = "UPDATE OFERTAS SET ESTADO_OFERTA = FALSE WHERE OFERTAS.ID = ?1", nativeQuery = true)
	void ponerEstadoOfertaAFalse(Integer ofertaId);
	
	@Query(value= "SELECT * FROM OFERTAS WHERE NIVEL_SOCIO=?1 AND ESTADO_OFERTA=TRUE", nativeQuery = true)
	List<Oferta> ofertasNivelSocio(Integer nivelSocio);

	
}
