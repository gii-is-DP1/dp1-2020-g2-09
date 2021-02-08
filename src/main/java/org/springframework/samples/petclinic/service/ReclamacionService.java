package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReclamacionService {
	
	private ReclamacionRepository reclamacionRepository;

	@Autowired
	public ReclamacionService(ReclamacionRepository reclamacionRepository) {
		this.reclamacionRepository = reclamacionRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Reclamacion> findReclamaciones() throws DataAccessException {
		return reclamacionRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Reclamacion findReclamacionById(int reclamacionId) throws DataAccessException {
		return reclamacionRepository.findReclamacionById(reclamacionId);
	}
	
	@Transactional
	public void saveReclamacion(Reclamacion reclamacion) throws DataAccessException {
		reclamacionRepository.save(reclamacion);		
	}	
	
	@Transactional
	public void deleteReclamacion(Reclamacion reclamacion) throws DataAccessException {
		reclamacionRepository.delete(reclamacion);		
	}
	
	 @Transactional
	 public void anadirReclamacionAPedido(int reclamacionId, int pedidoId) throws DataAccessException {
		 reclamacionRepository.anadirReclamacionAPedido(reclamacionId, pedidoId);		
	}		
	 
	 @Transactional 
	 public List<Integer> findPedidosConReclamaciones() throws DataAccessException {
		return reclamacionRepository.findPedidosConReclamaciones();
	 }
	 
	 public List<Integer> findPedidosConReclamacionDeCliente(int clienteId) throws DataAccessException {
		 return this.reclamacionRepository.findPedidosConReclamacionDeCliente(clienteId);
	 }
 
	 public List<Integer> findReclamacionesDePedidosDeCliente(int pedidoId) throws DataAccessException {
		 return this.reclamacionRepository.findReclamacionesDePedidosDeCliente(pedidoId);
	 }
}
