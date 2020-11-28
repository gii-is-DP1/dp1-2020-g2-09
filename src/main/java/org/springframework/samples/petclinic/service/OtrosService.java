package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.repository.OtrosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OtrosService {

	private OtrosRepository OtrosRepository;


	@Autowired
	public OtrosService(OtrosRepository OtrosRepository) {
		this.OtrosRepository = OtrosRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Otros> findOtros() throws DataAccessException {
		return OtrosRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Otros findOtrosById(int OtrosId) throws DataAccessException {
		return OtrosRepository.findOtrosById(OtrosId);
	}
	
	@Transactional(readOnly = true)
	public List<Otros> findByCarta(int carta) throws DataAccessException {
		return OtrosRepository.findByCartaDeOtros(carta);
	}
	
	@Transactional
	public void saveOtros(Otros Otros) throws DataAccessException {
		OtrosRepository.save(Otros);		
	}	
	
	@Transactional
	public void deleteOtros(Otros Otros) throws DataAccessException {
		OtrosRepository.delete(Otros);		
	}	
	
}
