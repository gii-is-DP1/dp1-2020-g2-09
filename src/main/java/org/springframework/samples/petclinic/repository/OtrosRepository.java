package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.stereotype.Repository;

@Repository
public interface OtrosRepository extends CrudRepository<Otro, Integer>{

	
	List<Otro> findAll() throws DataAccessException;
	
	Otro findOtrosById(int OtrosId) throws DataAccessException;
	
	//List<Otros> findByCartaDeOtros(int cartaId) throws DataAccessException;
	
	@Query(value = "SELECT OTROS_EN_CARTA_ID FROM COMPOSICION_CARTA_OTROS  WHERE CARTA_ID = ?1",
			nativeQuery = true)
	List<Integer> findIdOtroById(int cartaId) throws DataAccessException;
	
	@Query(value = "SELECT OTROS_EN_PEDIDO_ID FROM PRODUCTO_OTROS_PEDIDO  WHERE PEDIDO_ID = ?1",
			nativeQuery = true)
	List<Integer> findOtrosPedidoById(int pedidoId) throws DataAccessException;
	
	@Modifying
	@Query(value = "INSERT INTO COMPOSICION_CARTA_OTROS(OTROS_EN_CARTA_ID, CARTA_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirOtroACarta(int otroId, int cartaId);
	
	@Modifying
	@Query(value = "DELETE FROM OTROS_INGREDIENTES WHERE OTROS_ID = ?1",
			nativeQuery = true)
	void deleteComposicionIngredientes(Integer id);

	@Modifying
	@Query(value = "DELETE FROM COMPOSICION_CARTA_OTROS WHERE OTROS_EN_CARTA_ID = ?1",
			nativeQuery = true)
	void deleteComposicion(Integer id);
	
}
