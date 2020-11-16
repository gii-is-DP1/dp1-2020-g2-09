package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacionRepository extends CrudRepository<Reclamacion, Integer>{
	
List<Reclamacion> findAll() throws DataAccessException;
	
	Reclamacion findReclamacionById(int reclamacionId) throws DataAccessException;

}
