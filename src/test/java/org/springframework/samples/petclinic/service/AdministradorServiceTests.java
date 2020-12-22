package org.springframework.samples.petclinic.service;


import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.transaction.annotation.Transactional;



@ExtendWith(MockitoExtension.class)
public class AdministradorServiceTests {

	@Mock
	AdministradorRepository administradorRepository;
	
	AdministradorService administradorService;
	
	@BeforeEach
	void setUp() {
		administradorService = new AdministradorService(administradorRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindAdministradorById() {

		Administrador admin = new Administrador();
		admin.setNombre("Paco");
		admin.setApellidos("Florentino");
		admin.setTelefono(683020234);
		admin.setEmail("paquito@gmail.com");
		admin.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		admin.setUser(usuario);
		
		when(administradorRepository.findAdministradorById(anyInt())).thenReturn(admin);
		administradorService.findAdministradorById(99);
		verify(administradorRepository).findAdministradorById(99);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindAdministradorById() {

		Administrador admin = new Administrador();
		admin.setNombre("Paco");
		admin.setApellidos("Florentino");
		admin.setTelefono(683020234);
		admin.setEmail("paquito@gmail.com");
		admin.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		admin.setUser(usuario);
		
		when(administradorRepository.findAdministradorById(anyInt())).thenReturn(admin);
		administradorService.findAdministradorById(99);
		verify(administradorRepository, never()).findAdministradorById(12);
		
	}
	
	@Test
	@Transactional
	public void shouldFindAllAdministradores() {
		
		when(administradorRepository.findAll()).thenReturn(new ArrayList<>());
		administradorService.findAdministradores();
		verify(administradorRepository).findAll();
		
	}
	
}
