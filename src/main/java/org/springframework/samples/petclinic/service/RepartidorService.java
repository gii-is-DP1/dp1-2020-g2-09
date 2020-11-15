package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.samples.petclinic.repository.RepartidorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepartidorService {

	private RepartidorRepository repartidorRepository;


	@Autowired
	public RepartidorService(RepartidorRepository repartidorRepository) {
		this.repartidorRepository = repartidorRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Repartidor> findRepartidores() throws DataAccessException {
		return repartidorRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Repartidor findRepartidorById(int repartidorId) throws DataAccessException {
		return repartidorRepository.findRepartidorById(repartidorId);
	}
	
	@Transactional
	public void saveRepartidor(Repartidor repartidor) throws DataAccessException {
		repartidorRepository.save(repartidor);		
	}	
	
	@Transactional
	public void deleteRepartidor(Repartidor repartidor) throws DataAccessException {
		repartidorRepository.delete(repartidor);		
	}	
	
	
	
}
