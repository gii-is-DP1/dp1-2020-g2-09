package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends CrudRepository<Pedido, Integer> {
	
	List<Pedido> findAll() throws DataAccessException;
	
	Pedido findPedidoById(int pedidoId) throws DataAccessException;
	
	@Query(value ="SELECT * FROM Pedido WHERE PedidoCliente=?1", nativeQuery = true)
	List<Pedido> findPedidosByCliente(int userId) throws DataAccessException;
	
	@Query(value = "SELECT * FROM PEDIDO WHERE (CAST(FECHA_PEDIDO AS date) = CAST(?1 AS date)) AND PedidoCliente=?2 ORDER BY ID DESC LIMIT 1",
			nativeQuery = true)
	Pedido findPedidoByFecha(LocalDate hoy, int userId) throws DataAccessException;
	
	@Query("SELECT estPed FROM EstadoPedido estPed")
	List<EstadoPedido> findEstadoPedido() throws DataAccessException;

	@Query("SELECT tipPag FROM TipoPago tipPag")
	List<TipoPago> findTipoPago() throws DataAccessException;

	@Query("SELECT tipEnvio FROM TipoEnvio tipEnvio")
	List<TipoEnvio> findTipoEnvio() throws DataAccessException;
	
	@Query(value ="SELECT * FROM Pedido WHERE Estado_Pedido='1' or Estado_Pedido='2' or Estado_Pedido='5' ORDER BY Fecha_Pedido DESC", nativeQuery = true)
	List<Pedido> findPedidoForCocinero() throws DataAccessException;
	
	@Query(value ="SELECT * FROM Pedido WHERE Tipo_Envio='2' and (Estado_Pedido='2' or Estado_Pedido='3' or Estado_Pedido='4') ORDER BY Fecha_Pedido DESC", nativeQuery = true)
	List<Pedido> findPedidoForRepartidor() throws DataAccessException;
	
	@Modifying 
	@Query(value ="UPDATE Pedido SET Estado_Pedido='1' WHERE id=?1", nativeQuery = true)
	void putEnCocina(int pedidoId) throws DataAccessException;
	
	@Modifying 
	@Query(value ="UPDATE Pedido SET Estado_Pedido='2' WHERE id=?1", nativeQuery = true)
	void putPreparado(int pedidoId) throws DataAccessException;
	
	@Modifying 
	@Query(value ="UPDATE Pedido SET Estado_Pedido='3' WHERE id=?1", nativeQuery = true)
	void putEnReparto(int pedidoId) throws DataAccessException;
	
	@Modifying
	@Query(value ="UPDATE Pedido SET Estado_Pedido='4' WHERE id=?1", nativeQuery = true)
	void putEntregado(int pedidoId) throws DataAccessException;
	
	@Modifying
	@Query(value ="UPDATE Pedido SET Estado_Pedido='5' WHERE id=?1", nativeQuery = true)
	void putRecogido(int pedidoId) throws DataAccessException;
	
	@Modifying
    @Query(value = "INSERT INTO PRODUCTO_PIZZA_PEDIDO(PEDIDO_ID, PIZZAS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirPizzaAPedido(int pedidoId, int pizzaId);
	
	@Modifying
    @Query(value = "INSERT INTO PRODUCTO_BEBIDA_PEDIDO(PEDIDO_ID, BEBIDAS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirBebidaAPedido(int pedidoId, int bebidaId);
	
	@Modifying
    @Query(value = "INSERT INTO PRODUCTO_OTROS_PEDIDO(PEDIDO_ID, OTROS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirOtrosAPedido(int pedidoId, int otrosId);
	
	@Modifying
    @Query(value = "INSERT INTO OFERTA_PEDIDO(PEDIDO_ID, OFERTAS_EN_PEDIDO_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirOfertaAPedido(int pedidoId, int ofertaId);
	
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_PIZZA_PEDIDO WHERE PEDIDO_ID=?1 AND PIZZAS_EN_PEDIDO_ID=?2 LIMIT 1",
			nativeQuery = true)
	void eliminarPizzaPedido(int pedidoId, int pizzaId);
	
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_BEBIDA_PEDIDO WHERE PEDIDO_ID=?1 AND BEBIDAS_EN_PEDIDO_ID=?2 LIMIT 1",
			nativeQuery = true)
	void eliminarBebidaPedido(int pedidoId, int bebidaId);
	
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_OTROS_PEDIDO WHERE PEDIDO_ID=?1 AND OTROS_EN_PEDIDO_ID=?2 LIMIT 1",
			nativeQuery = true)
	void eliminarOtrosPedido(int pedidoId, int otrosId);
	
	@Modifying
    @Query(value = "DELETE FROM OFERTA_PEDIDO WHERE PEDIDO_ID=?1 AND OFERTAS_EN_PEDIDO_ID=?2 LIMIT 1",
			nativeQuery = true)
	void eliminarOfertaPedido(int pedidoId, int otrosId);

    @Query(value = "SELECT COSTE FROM PIZZAS WHERE ID = ?1",
			nativeQuery = true)
    Double cogerPrecioPizza(int pizzaId);

    @Query(value = "SELECT COSTE FROM BEBIDAS WHERE ID = ?1",
			nativeQuery = true)
    Double cogerPrecioBebida(int bebidaId);

    @Query(value = "SELECT COSTE FROM OTROS WHERE ID = ?1",
			nativeQuery = true)
	Double cogerPrecioOtros(int otrosId);
    
    @Query(value = "SELECT COSTE FROM OFERTAS WHERE ID = ?1",
			nativeQuery = true)
	Double cogerPrecioOferta(int ofertaId);
    
    @Query(value = "SELECT PEDIDO_ID FROM PEDIDO_RECLAMACION WHERE RECLAMACION_ID = ?1", nativeQuery=true)
    Integer findIdPedidoByReclamacionId(int reclamacionId);
}
