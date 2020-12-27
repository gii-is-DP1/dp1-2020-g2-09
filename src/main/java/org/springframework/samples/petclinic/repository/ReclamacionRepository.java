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
	void añadirReclamacionAPedido(int reclamacionId, int pedidoId);
	
	@Query(value = "SELECT RECLAMACIONES.ID, OBSERVACION, RESPUESTA FROM PEDIDO NATURAL JOIN RECLAMACIONES", 
			nativeQuery = true)
	List<Reclamacion> findPedidosConReclamaciones();
	
	@Query(value = "SELECT RECLAMACIONES.ID, OBSERVACION, RESPUESTA FROM PEDIDO NATURAL JOIN RECLAMACIONES NATURAL JOIN CLIENTES WHERE CLIENTES.ID = ?1", nativeQuery = true)
	List<Reclamacion> findReclamacionesByCliente(int userId);

}
