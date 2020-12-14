package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends CrudRepository<Bebida, Integer> {

	
	List<Bebida> findAll() throws DataAccessException;
	
	Bebida findBebidaById(int bebidaId) throws DataAccessException;
	
	//List<Bebida> findByCartaDeBebidas(Integer cartaId) throws DataAccessException;
	
	//las columnas están al revés
	@Query(value = "SELECT BEBIDAS_EN_CARTA_ID FROM COMPOSICION_CARTA_BEBIDA WHERE CARTA_ID = ?1",
			nativeQuery = true)
	List<Integer> findIdBebidaByCartaId(int cartaId) throws DataAccessException;
}

