package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.repository.OfertaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OfertaService {
	
	private OfertaRepository  ofertaRepository;


	@Autowired
	public  OfertaService(OfertaRepository ofertaRepository) {
		this.ofertaRepository = ofertaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Oferta> findOfertas() throws DataAccessException {
		return ofertaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Oferta findOfertaById(int ofertaId) throws DataAccessException {
		return ofertaRepository.findOfertaById(ofertaId);
	}
	
	@Transactional
	public void saveOferta(Oferta oferta) throws DataAccessException {
		ofertaRepository.save(oferta);		
	}	
	
	@Transactional
	public void deleteOferta(Oferta oferta) throws DataAccessException {
		ofertaRepository.delete(oferta);		
	}

}