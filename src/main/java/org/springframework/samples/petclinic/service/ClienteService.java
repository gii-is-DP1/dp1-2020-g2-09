package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Clientes;
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
	public Clientes findClienteByNombreUsuario(String nombreUsuario) throws DataAccessException {
		return (Clientes) clienteRepository.findByNombreUsuario(nombreUsuario);
	}
	
}
