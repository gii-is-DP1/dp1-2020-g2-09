package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReservaRepository extends CrudRepository<tipoReserva, Integer>{
    List<tipoReserva> findAll() throws DataAccessException;

    tipoReserva findById(int tipo_id) throws DataAccessException;
}