package org.springframework.samples.petclinic.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends CrudRepository<Bebida, Integer> {

	
	List<Bebida> findAll() throws DataAccessException;
	
	Bebida findBebidaById(int bebidaId) throws DataAccessException;
	
	//List<Bebida> findByCartaDeBebidas(Integer cartaId) throws DataAccessException;

	@Query(value = "SELECT BEBIDAS_EN_CARTA_ID FROM COMPOSICION_CARTA_BEBIDA WHERE CARTA_ID = ?1",
			nativeQuery = true)
	List<Integer> findIdBebidaByCartaId(int cartaId) throws DataAccessException;
	
	@Query(value = "SELECT BEBIDAS_EN_PEDIDO_ID FROM PRODUCTO_BEBIDA_PEDIDO  WHERE PEDIDO_ID = ?1",
			nativeQuery = true)
	List<Integer> findBebidaPedidoById(int pedidoId) throws DataAccessException;
	
	@Modifying
    @Query(value = "INSERT INTO COMPOSICION_CARTA_BEBIDA(BEBIDAS_EN_CARTA_ID, CARTA_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void añadirBebidaACarta(int bebidaId, int cartaId);

	/*Bebida save(Bebida bebida);
	
	void delete(Bebida bebida);*/
	
	@Modifying
	@Query(value = "DELETE FROM COMPOSICION_CARTA_BEBIDA WHERE BEBIDAS_EN_CARTA_ID = ?1",
			nativeQuery = true)
	void deleteComposicion(Integer id);
	
	

}

