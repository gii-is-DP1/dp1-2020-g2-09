package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = AdministradorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AdministradorControllerTests {

	private static final int TEST_ADMIN_ID = 1;

    @MockBean
    private AdministradorService administradorService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
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
        
        given(this.administradorService.findAdministradores()).willReturn(Lists.newArrayList(admin));
        given(this.administradorService.findAdministradorById(TEST_ADMIN_ID)).willReturn(admin);
        
	}
	
	@WithMockUser(value = "spring")
    @Test
	void showAdministradoresList() throws Exception{
		mockMvc.perform(get("/allAdministradores"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/administradoresList"))
		.andExpect(model().attributeExists("administradores"));
	}
	
	
}
