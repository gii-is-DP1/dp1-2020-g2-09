package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends CrudRepository<Pedido, Integer> {
	
	List<Pedido> findAll() throws DataAccessException;
	
	Pedido findPedidoById(int pedidoId) throws DataAccessException;
	
	List<Pedido> findByPedidosConOferta(int pedidoId) throws DataAccessException;

}
