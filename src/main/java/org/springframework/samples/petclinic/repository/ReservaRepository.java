package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer>{


//    //filtrar Reserva 
//    @Query("SELECT DISTINCT nombreCliente FROM Reserva JOIN cliente WHERE cliente.reserva = :reserva_id%")
//    public Collection<Reserva> findByNombreCliente(@Param("nombreCliente") String nombre_cliente);

    List<Reserva> findAll() throws DataAccessException;

    Reserva findById(int reserva_id) throws DataAccessException;



}