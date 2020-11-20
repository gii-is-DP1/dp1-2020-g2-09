package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReclamacionService {
	
	private ReclamacionRepository reclamacionRepository;

	@Autowired
	public ReclamacionService(ReclamacionRepository reclamacionRepository) {
		this.reclamacionRepository = reclamacionRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Reclamacion> findReclamaciones() throws DataAccessException {
		return reclamacionRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Reclamacion findReclamacionById(int reclamacionId) throws DataAccessException {
		return reclamacionRepository.findReclamacionById(reclamacionId);
	}
	
	@Transactional
	public void saveReclamacion(Reclamacion reclamacion) throws DataAccessException {
		reclamacionRepository.save(reclamacion);		
	}	
	
	@Transactional
	public void deleteReclamacion(Reclamacion reclamacion) throws DataAccessException {
		reclamacionRepository.delete(reclamacion);		
	}

}