package org.springframework.samples.petclinic.repository;

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
	
	//la siguiente creo que está mal
	List<Pedido> findByOfertasEnPedido(int pedidoId) throws DataAccessException;
	
	@Query("SELECT estPed FROM EstadoPedido estPed")
	List<EstadoPedido> findEstadoPedido() throws DataAccessException;

	@Query("SELECT tipPag FROM TipoPago tipPag")
	List<TipoPago> findTipoPago() throws DataAccessException;

	@Query("SELECT tipEnvio FROM TipoEnvio tipEnvio")
	List<TipoEnvio> findTipoEnvio() throws DataAccessException;
	
	//FILTRAR PEDIDOS SEGUN SU ESTADO
	@Query(value ="SELECT * FROM Pedido WHERE Estado_Pedido='1' or Estado_Pedido='2'", nativeQuery = true)
	List<Pedido> findPedidoForCocinero() throws DataAccessException;
	
	@Query(value ="SELECT * FROM Pedido WHERE Tipo_Envio='1' and  Estado_Pedido='2' or Estado_Pedido='3' or Estado_Pedido='4'", nativeQuery = true)
	List<Pedido> findPedidoForRepartidor() throws DataAccessException;
	
	//ACTUALIZAR ESTADO DE UN PEDIDO
	@Modifying //al finalizar pedido
	@Query(value ="UPDATE Pedido SET Estado_Pedido='1' WHERE id=?1", nativeQuery = true)
	void putEnCocina(int pedidoId) throws DataAccessException;
	
	@Modifying //cocinero
	@Query(value ="UPDATE Pedido SET Estado_Pedido='2' WHERE id=?1", nativeQuery = true)
	void putPreparado(int pedidoId) throws DataAccessException;
	
	@Modifying //repartidor
	@Query(value ="UPDATE Pedido SET Estado_Pedido='3' WHERE id=?1", nativeQuery = true)
	void putEnReparto(int pedidoId) throws DataAccessException;
	
	@Modifying
	@Query(value ="UPDATE Pedido SET Estado_Pedido='4' WHERE id=?1", nativeQuery = true)
	void putEntregado(int pedidoId) throws DataAccessException;
	
	//INSERTAR PRODUCTOS EN PEDIDO
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
	
	/*//ELIMINAR PRODUCTOS DE UN PEDIDO
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_PIZZA_PEDIDO WHERE PEDIDO_ID='?1'",
			nativeQuery = true)
	void eliminarPizzaPedido(int pedidoId);
	
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_BEBIDA_PEDIDO WHERE PEDIDO_ID='?1'",
			nativeQuery = true)
	void eliminarBebidaPedido(int pedidoId);
	
	@Modifying
    @Query(value = "DELETE FROM PRODUCTO_OTROS_PEDIDO WHERE PEDIDO_ID='?1'",
			nativeQuery = true)
	void eliminarOtrosPedido(int pedidoId);*/

	//COGER PRECIOS DE PRODUCTOS
    @Query(value = "SELECT COSTE FROM PIZZAS WHERE ID = ?1",
			nativeQuery = true)
    Double cogerPrecioPizza(int pizzaId);

    @Query(value = "SELECT COSTE FROM BEBIDAS WHERE ID = ?1",
			nativeQuery = true)
    Double cogerPrecioBebida(int bebidaId);

    @Query(value = "SELECT COSTE FROM OTROS WHERE ID = ?1",
			nativeQuery = true)
	Double cogerPrecioOtros(int otrosId);
}
