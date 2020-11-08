package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}	
	
	@Transactional(readOnly = true)	
	public Collection<Cliente> findClientes() throws DataAccessException {
		return clienteRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Cliente findClienteByNombreUsuario(String nombreUsuario) throws DataAccessException {
		return (Cliente) clienteRepository.findByNombreUsuario(nombreUsuario);
	}
	
}
