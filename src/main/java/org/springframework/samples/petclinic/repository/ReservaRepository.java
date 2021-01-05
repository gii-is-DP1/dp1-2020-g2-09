package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer>{


//    //filtrar Reserva 
//    @Query("SELECT DISTINCT nombreCliente FROM Reserva JOIN cliente WHERE cliente.reserva = :reserva_id%")
//    public Collection<Reserva> findByNombreCliente(@Param("nombreCliente") String nombre_cliente);

    List<Reserva> findAll() throws DataAccessException;

    Reserva findById(int reserva_id) throws DataAccessException;

	@Query("SELECT tr FROM tipoReserva tr")
    List<tipoReserva> findTipoReserva() throws DataAccessException;

    @Modifying
    @Query(value = "INSERT INTO RESERVA_MESA(RESERVA_ID, MESAS_EN_RESERVA_ID) VALUES (?1, ?2)",
			nativeQuery = true)
	void anadirMesaAReserva(int reservaId, int mesaId);
    
    @Query(value ="SELECT * FROM RESERVAS WHERE RESERVACLIENTE=?1", nativeQuery = true)
	List<Reserva> findReservasByCliente(int userId) throws DataAccessException;
   
    
    

}