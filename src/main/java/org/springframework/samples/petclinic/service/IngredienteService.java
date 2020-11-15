package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredienteService {

	private IngredienteRepository IngredienteRepository;


	@Autowired
	public IngredienteService(IngredienteRepository IngredienteRepository) {
		this.IngredienteRepository = IngredienteRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Ingrediente> findIngredientes() throws DataAccessException {
		return IngredienteRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Ingrediente findIngredienteById(int IngredienteId) throws DataAccessException {
		return IngredienteRepository.findIngredienteById(IngredienteId);
	}
	
	@Transactional
	public void saveIngrediente(Ingrediente Ingrediente) throws DataAccessException {
		IngredienteRepository.save(Ingrediente);		
	}	
	
	@Transactional
	public void deleteIngrediente(Ingrediente Ingrediente) throws DataAccessException {
		IngredienteRepository.delete(Ingrediente);		
	}	
	
}
