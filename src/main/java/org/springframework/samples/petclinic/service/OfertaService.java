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
	public List<Oferta> findOfertasByEstadoOferta(boolean estadoOferta) throws DataAccessException {
		return ofertaRepository.findOfertasByEstadoOferta(estadoOferta);
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
	
	//Encontrar ofertas de un pedido
	@Transactional
	public List<Integer> findOfertasEnPedidoById(int pedidoId) throws DataAccessException {
		return ofertaRepository.findOfertasEnPedidoById(pedidoId);
	}
		
	
	//Para borrar las pizzas
	@Transactional
	public List<Integer> numeroPizzasEnOferta(Integer pizzaId) throws DataAccessException {
		return ofertaRepository.numeroPizzasEnOferta(pizzaId);//HACER PRUEBAS
	}
	//Para borrar las bebidas
	@Transactional
	public List<Integer> numeroBebidasEnOferta(Integer bebidaId) throws DataAccessException {
		return ofertaRepository.numeroBebidasEnOferta(bebidaId);//HACER PRUEBAS
	}
	
	//Para borrar los otros
	@Transactional
	public List<Integer> numeroOtrosEnOferta(Integer otrosId) throws DataAccessException {
		return ofertaRepository.numeroOtrosEnOferta(otrosId);//HACER PRUEBAS
	}
	
	//para borrar
	@Transactional
	public void ponerEstadoOfertaAFalse(Integer ofertaId) throws DataAccessException {
		ofertaRepository.ponerEstadoOfertaAFalse(ofertaId);
	}
	
	@Transactional
	public void deleteOferta(Oferta oferta) throws DataAccessException {
		ofertaRepository.delete(oferta);		
	}
	
	
	@Transactional
	public void asociarOfertaAPizza(int ofertaId,int pizzaId) throws DataAccessException {
		ofertaRepository.asociarOfertaAPizza(ofertaId, pizzaId);
	}
		@Transactional
	public void asociarOfertaABebida(int ofertaId,int bebidaId) throws DataAccessException {
		ofertaRepository.asociarOfertaABebida(ofertaId, bebidaId);
	}
	@Transactional
	public void asociarOfertaAOtro(int ofertaId,int otroId) throws DataAccessException {
		ofertaRepository.asociarOfertaAOtro(ofertaId, otroId);
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
	
	@Transactional(readOnly = true)
	public List<Oferta> ofertasNivelSocio(Integer nivelSocio) throws DataAccessException {
		return ofertaRepository.ofertasNivelSocio(nivelSocio);	
	}
	
}
