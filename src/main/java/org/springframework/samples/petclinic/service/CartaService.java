package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.repository.CartaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaService {

	private CartaRepository CartaRepository;

	@Autowired
	public CartaService(CartaRepository CartaRepository) {
		this.CartaRepository = CartaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Carta> findCartas() throws DataAccessException {
		return CartaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Carta findCartaById(int CartaId) throws DataAccessException {
		return CartaRepository.findCartaById(CartaId);
	}
	
	@Transactional(readOnly = true)
	public Carta findCartaByFecga(LocalDate CartaFecha) throws DataAccessException {
		return CartaRepository.findCartaByFecha(CartaFecha);
	}
	@Transactional
	public void saveCarta(Carta Carta) throws DataAccessException {
		CartaRepository.save(Carta);		
	}	
	
	@Transactional
	public void deleteCarta(Carta Carta) throws DataAccessException {
		CartaRepository.delete(Carta);		
	}	
}
