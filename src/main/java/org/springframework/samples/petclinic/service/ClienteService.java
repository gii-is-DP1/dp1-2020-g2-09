package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
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
	public List<Cliente> findCuentas() throws DataAccessException {
		return clienteRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Cliente findCuentaById(int cuentaId) throws DataAccessException {
		return clienteRepository.findCuentaById(cuentaId);
	}
	
	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		clienteRepository.save(cliente);		
	}	
	
}
