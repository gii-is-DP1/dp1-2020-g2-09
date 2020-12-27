package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Otro;
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
	public List<Otro> findOtros() throws DataAccessException {
		return OtrosRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public List<Integer> findIdOtroById(int cartaId) throws DataAccessException {
		return OtrosRepository.findIdOtroById(cartaId);
	}
	
	@Transactional(readOnly = true)
	public List<Integer> findOtrosPedidoById(int pedidoId) throws DataAccessException {
		return OtrosRepository.findOtrosPedidoById(pedidoId);
	}
	
	@Transactional(readOnly = true)
	public Otro findOtrosById(int OtrosId) throws DataAccessException {
		return OtrosRepository.findOtrosById(OtrosId);
	}
	
	@Transactional
	public void añadirOtroACarta(int otroId, int cartaId) throws DataAccessException {
		OtrosRepository.añadirOtroACarta(otroId, cartaId);		
	}
	
//	@Transactional(readOnly = true)
//	public List<Otros> findByCarta(int carta) throws DataAccessException {
//		return OtrosRepository.findByCartaDeOtros(carta);
//	}
	
	@Transactional
	public void saveOtros(Otro Otros) throws DataAccessException {
		OtrosRepository.save(Otros);		
	}	
	
	@Transactional
	public void deleteOtros(Otro Otros) throws DataAccessException {
		OtrosRepository.deleteComposicionIngredientes(Otros.getId());
		OtrosRepository.deleteComposicion(Otros.getId());
		OtrosRepository.delete(Otros);		
	}	
	
	@Transactional
	public void deleteOtroFromComposicionCarta(Integer otroId) throws DataAccessException {
		OtrosRepository.deleteComposicion(otroId);
	}
	
	
}
