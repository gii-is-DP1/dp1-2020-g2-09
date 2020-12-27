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
	public List<Pedido> findPedidoForCocinero() throws DataAccessException {
		return pedidoRepository.findPedidoForCocinero();
	}
	
	@Transactional(readOnly = true)	
	public List<Pedido> findPedidoForRepartidor() throws DataAccessException {
		return pedidoRepository.findPedidoForRepartidor();
	}
	
	@Transactional(readOnly = true)	
	public List<Pedido> findPedidosByCliente(int userId) throws DataAccessException {
		return pedidoRepository.findPedidosByCliente(userId);
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
	
	@Transactional
	public void añadirPizzaAPedido(int pedidoId, int pizzaId) throws DataAccessException {
		pedidoRepository.añadirPizzaAPedido(pedidoId, pizzaId);
	}
	
	@Transactional
	public void añadirBebidaAPedido(int pedidoId, int bebidaId) throws DataAccessException {
		pedidoRepository.añadirBebidaAPedido(pedidoId, bebidaId);
	}
	
	@Transactional
	public void añadirOtrosAPedido(int pedidoId, int otrosId) throws DataAccessException {
		pedidoRepository.añadirOtrosAPedido(pedidoId, otrosId);
	}

	@Transactional
	public Double cogerPrecioPizza(int pizzaId) {
		return pedidoRepository.cogerPrecioPizza(pizzaId);
	}
	
	@Transactional
	public Double cogerPrecioBebida(int bebidaId) {
		return pedidoRepository.cogerPrecioBebida(bebidaId);
	}

	@Transactional
	public Double cogerPrecioOtros(int otrosId) {
		return pedidoRepository.cogerPrecioOtros(otrosId);
	}
}
