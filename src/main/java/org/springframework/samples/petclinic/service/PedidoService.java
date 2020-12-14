package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;


	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Pedido> findPedidos() throws DataAccessException {
		return pedidoRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Pedido findPedidoById(int pedidoId) throws DataAccessException {
		return pedidoRepository.findPedidoById(pedidoId);
	}
	
	@Transactional
	public void savePedido(Pedido pedido) throws DataAccessException {
		pedidoRepository.save(pedido);		
	}	
	
	@Transactional
	public void deletePedido(Pedido pedido) throws DataAccessException {
		pedidoRepository.delete(pedido);		
	}	
	
	@Transactional(readOnly = true)
	public Collection<EstadoPedido> findEstadoPedido() throws DataAccessException {
		return pedidoRepository.findEstadoPedido();
	}
	
	@Transactional(readOnly = true)
	public Collection<TipoPago> findTipoPago() throws DataAccessException {
		return pedidoRepository.findTipoPago();
	}
	
	@Transactional(readOnly = true)
	public Collection<TipoEnvio> findTipoEnvio() throws DataAccessException {
		return pedidoRepository.findTipoEnvio();
	}

}
