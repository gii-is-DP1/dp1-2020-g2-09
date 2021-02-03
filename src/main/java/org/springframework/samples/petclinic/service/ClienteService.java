package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;


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
		return clienteRepository.findById(cuentaId);
	}
	
	@Transactional(readOnly = true)
	public Cliente findCuentaByUser(User usuario) {
		return clienteRepository.findByUser(usuario);
	}
	
	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		clienteRepository.save(cliente);				
		
		//creating user
		userService.saveUser(cliente.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");
	}	
	
	@Transactional
	public void deleteCliente(Cliente cliente) throws DataAccessException {
		clienteRepository.delete(cliente);	
	}	
	
	//Para RN-1
	@Transactional
	public Integer findNumeroDePedidosRealizados(int clienteId) throws DataAccessException {
		return clienteRepository.findNumeroDePedidosRealizados(clienteId);
	}
	
}
