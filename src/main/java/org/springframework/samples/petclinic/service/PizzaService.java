package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PizzaService {

	private PizzaRepository pizzaRepository;


	@Autowired
	public PizzaService(PizzaRepository PizzaRepository) {
		this.pizzaRepository = PizzaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Pizza> findPizzas() throws DataAccessException {
		return pizzaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Pizza findPizzaById(int pizzaId) throws DataAccessException {
		return pizzaRepository.findPizzaById(pizzaId);
	}
	
	@Transactional(readOnly = true)
	public List<Integer> findIdPizzaById(int cartaId) throws DataAccessException {
		return pizzaRepository.findIdPizzaById(cartaId);
	}
	
	@Transactional
	public void savePizza(Pizza pizza) throws DataAccessException {
		pizzaRepository.save(pizza);		
	}	
	
	@Transactional
	public void deletePizza(Pizza pizza) throws DataAccessException {
		pizzaRepository.delete(pizza);		
	}
}
