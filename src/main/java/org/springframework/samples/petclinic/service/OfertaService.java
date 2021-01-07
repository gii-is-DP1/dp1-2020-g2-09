package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
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
	
	@Transactional(readOnly = true)
	public Collection<TamanoOferta> findTamanoOferta() throws DataAccessException {
		return ofertaRepository.findTamanoOferta();
	}
	
	@Transactional(readOnly = true)
	public Collection<NivelSocio> findNivelSocio() throws DataAccessException{
		return ofertaRepository.findNivelSocio();
	}
	
	@Transactional
	public void saveOferta(Oferta oferta) throws DataAccessException {
		ofertaRepository.save(oferta);		
	}
	@Transactional
	public void deleteOferta(Oferta oferta) throws DataAccessException {
		ofertaRepository.delete(oferta);		
	}
	
//	@Transactional
//	public void asociarOfertaAProductos(int ofertaId,List<Producto> productosEnOfertaId) throws DataAccessException {
//		for(int i =0;i<=productosEnOfertaId.size();i++) {		
//		ofertaRepository.asociarOfertaAProducto(ofertaId, productosEnOfertaId.get(i).getId());
//		}
//	}
	
	@Transactional
	public void asociarOfertaAPizzas(int ofertaId,List<Pizza> pizzasEnOferta) throws DataAccessException {
		for(int i =0;i<=pizzasEnOferta.size();i++) {		
		ofertaRepository.asociarOfertaAPizza(ofertaId, pizzasEnOferta.get(i).getId());
		}
	}
	@Transactional
	public void asociarOfertaAOtros(int ofertaId,List<Otro> otrosEnOferta) throws DataAccessException {
		for(int i =0;i<=otrosEnOferta.size();i++) {		
		ofertaRepository.asociarOfertaAOtro(ofertaId, otrosEnOferta.get(i).getId());
		}
	}
	@Transactional
	public void asociarOfertaABebidas(int ofertaId,List<Bebida> bebidasEnOferta) throws DataAccessException {
		for(int i =0;i<=bebidasEnOferta.size();i++) {		
		ofertaRepository.asociarOfertaABebida(ofertaId, bebidasEnOferta.get(i).getId());
		}
	}
	
	
	@Transactional(readOnly = true)
	public List<Pizza> findPizzasEnOferta() throws DataAccessException {
		return ofertaRepository.findPizzasEnOferta();
	}
	
	@Transactional(readOnly = true)
	public List<Bebida> findBebidasEnOferta() throws DataAccessException {
		return ofertaRepository.findBebidasEnOferta();
	}
	@Transactional(readOnly = true)
	public List<Otro> findOtrosEnOferta() throws DataAccessException {
		return ofertaRepository.findOtrosEnOferta();
	}
	
	
	@Transactional(readOnly = true)
	public List<Pizza> findPizzasEnOfertaByOfertaId(int oferta_id) throws DataAccessException {
		return ofertaRepository.findPizzasEnOfertaByOfertaId(oferta_id);
	}
	@Transactional(readOnly = true)
	public List<Bebida> findBebidasEnOfertaByOfertaId(int oferta_id) throws DataAccessException {
		return ofertaRepository.findBebidasEnOfertaByOfertaId(oferta_id);
	}
	@Transactional(readOnly = true)
	public List<Otro> findOtrosEnOfertaByOfertaId(int oferta_id) throws DataAccessException {
		return ofertaRepository.findOtrosEnOfertaByOfertaId(oferta_id);
	}
	
}
