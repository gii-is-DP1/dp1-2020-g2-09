package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.repository.BebidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BebidaService {

	private BebidaRepository bebidaRepository;


	@Autowired
	public BebidaService(BebidaRepository bebidaRep) {
		this.bebidaRepository = bebidaRep;
	}		

	@Transactional(readOnly = true)	
	public List<Bebida> findBebidas() throws DataAccessException {
		return bebidaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Bebida findById(int bebidaId) throws DataAccessException {
		return bebidaRepository.findBebidaById(bebidaId);
	}
	
	@Transactional(readOnly = true)
	public List<Integer> findIdBebidaByCartaId(int cartaId) throws DataAccessException {
		return bebidaRepository.findIdBebidaByCartaId(cartaId);
	}
	
	@Transactional(readOnly = true)
	public List<Integer> findBebidaPedidoById(int pedidoId) throws DataAccessException {
		return bebidaRepository.findBebidaPedidoById(pedidoId);
	}
	
	@Transactional
	public void saveBebida(Bebida bebida) throws DataAccessException {
		bebidaRepository.save(bebida);		
	}	
	
	@Transactional
	public void deleteBebidaFromComposicionCarta(Integer bebidaId) throws DataAccessException {
		bebidaRepository.deleteComposicion(bebidaId);
	}
	
	@Transactional
	public void añadirBebidaACarta(int bebidaId, int cartaId) throws DataAccessException {
		bebidaRepository.añadirBebidaACarta(bebidaId, cartaId);		
	}	
}

