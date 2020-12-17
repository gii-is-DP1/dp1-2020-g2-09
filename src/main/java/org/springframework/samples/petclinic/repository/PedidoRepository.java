package org.springframework.samples.petclinic.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
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
	
	

	
}
