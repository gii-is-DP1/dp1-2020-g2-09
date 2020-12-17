package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.stereotype.Repository;


@Repository
public interface MesaRepository  extends CrudRepository<Mesa, Integer>{

	List<Mesa> findAll() throws DataAccessException;
	
	Mesa findById(int mesaId) throws DataAccessException;
	
	@Query(value="SELECT * from MESAS WHERE ID IN (SELECT MESAS_RESERVADAS FROM RESERVA_ASOCIADA WHERE RESERVA_ID = ?1)", nativeQuery= true)
	List<Mesa> findByReserva(int reservas_id) throws DataAccessException;

	@Query(value = "SELECT COUNT(*) FROM RESERVA_MESA WHERE MESAS_EN_RESERVA_ID= ?1",
			nativeQuery = true)
	List<Integer> CountMesa(Integer id);
	
	 
}
