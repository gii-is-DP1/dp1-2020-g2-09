package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CocineroService {

	private CocineroRepository cocineroRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public CocineroService(CocineroRepository cocineroRepository) {
		this.cocineroRepository = cocineroRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Cocina> findCocineros() throws DataAccessException {
		return cocineroRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Cocina findCocineroById(int cocineroId) throws DataAccessException {
		return cocineroRepository.findCocineroById(cocineroId);
	}
	
	@Transactional
	public void saveCocinero(Cocina cocinero) throws DataAccessException {
		//creating user
		userService.saveUser(cocinero.getUser());
		
		cocineroRepository.save(cocinero);	
		
		//creating authorities
		authoritiesService.saveAuthorities(cocinero.getUser().getUsername(), "cocinero");
	}	
	
	@Transactional
	public void deleteCocinero(Cocina cocinero) throws DataAccessException {
		cocineroRepository.delete(cocinero);		
	}	
}
