package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;

public interface CuentaRepository extends Repository<Cuenta, Integer>{

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
	List<Cuenta> findAll() throws DataAccessException;
	
	Cuenta findCuentaById(int cuentaId) throws DataAccessException;
	
	void save(Cuenta cuenta) throws DataAccessException;
	
	
}
