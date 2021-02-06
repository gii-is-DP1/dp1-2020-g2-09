package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacionRepository extends CrudRepository<Reclamacion, Integer>{
	
List<Reclamacion> findAll() throws DataAccessException;
	
	Reclamacion findReclamacionById(int reclamacionId) throws DataAccessException;
	
	@Modifying
    @Query(value = "INSERT INTO PEDIDO_RECLAMACION(PEDIDO_ID, RECLAMACION_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void anadirReclamacionAPedido(int reclamacionId, int pedidoId);
	
	@Query(value = "SELECT DISTINCT PEDIDO_RECLAMACION.RECLAMACION_ID FROM PEDIDO NATURAL JOIN PEDIDO_RECLAMACION", 
			nativeQuery = true)
	List<Integer> findPedidosConReclamaciones();
	
	@Query(value = "SELECT DISTINCT PEDIDO_RECLAMACION.RECLAMACION_ID FROM PEDIDO NATURAL JOIN PEDIDO_RECLAMACION WHERE (PEDIDO.PEDIDOCLIENTE LIKE ?1)", 
			nativeQuery = true)
	List<Integer> findPedidosConReclamacionesDeUnCliente(int clienteId);//Obsoleto
		
	@Query(value = "SELECT DISTINCT PEDIDO.ID FROM PEDIDO NATURAL JOIN PEDIDO_RECLAMACION WHERE PEDIDO_ID = PEDIDO.ID AND PEDIDOCLIENTE = ?1",
			nativeQuery=true)
	List<Integer> findPedidosConReclamacionDeCliente(int clienteId);

	@Query(value = "SELECT DISTINCT RECLAMACION_ID FROM PEDIDO_RECLAMACION  WHERE PEDIDO_ID = ?1", nativeQuery = true)
	List<Integer> findReclamacionesDePedidosDeCliente(int pedidoId);
}
