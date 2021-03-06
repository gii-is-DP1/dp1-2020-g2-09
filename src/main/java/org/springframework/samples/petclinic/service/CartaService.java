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

	private CartaRepository cartaRepository;

	@Autowired
	public CartaService(CartaRepository CartaRepository) {
		this.cartaRepository = CartaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Carta> findCartas() throws DataAccessException {
		return cartaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Carta findCartaById(int cartaId) throws DataAccessException {
		return cartaRepository.findById(cartaId);
	}
	
	@Transactional(readOnly = true)
	public Carta findCartaByFechaCreacionYFechaFinal(LocalDate hoy) {
		return cartaRepository.findCartaByFechaCreacionYFechaFinal(hoy);
	}
	
	@Transactional
	public void saveCarta(Carta carta) throws DataAccessException {
		cartaRepository.save(carta);		
	}	
	
	@Transactional
	public void deleteCarta(Carta carta) throws DataAccessException {
		cartaRepository.delete(carta);		
	}

}
