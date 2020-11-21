package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PizzaService {

	private PizzaRepository PizzaRepository;


	@Autowired
	public PizzaService(PizzaRepository PizzaRepository) {
		this.PizzaRepository = PizzaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Pizza> findPizzas() throws DataAccessException {
		return PizzaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Pizza findPizzaById(int pizzaId) throws DataAccessException {
		return PizzaRepository.findPizzaById(pizzaId);
	}
	
	@Transactional
	public void savePizza(Pizza Pizza) throws DataAccessException {
		PizzaRepository.save(Pizza);		
	}	
	
	@Transactional
	public void deletePizza(Pizza Pizza) throws DataAccessException {
		PizzaRepository.delete(Pizza);		
	}
}
