package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorService {

	private AdministradorRepository administradorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public AdministradorService(AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Administrador> findAdministradores() throws DataAccessException {
		return administradorRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Administrador findAdministradorById(int administradorId) throws DataAccessException {
		return administradorRepository.findAdministradorById(administradorId);
	}
	
	@Transactional
	public void saveAdministrador(Administrador administrador) throws DataAccessException {
		this.administradorRepository.save(administrador);	
		//creating user
		userService.saveUser(administrador.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(administrador.getUser().getUsername(), "administrador");
	}	
	
	
}
