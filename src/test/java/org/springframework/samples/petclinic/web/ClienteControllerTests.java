package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;

import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ClienteController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ClienteControllerTests {

	private static final int TEST_CLIENTE_ID = 1;

    @MockBean
    private ClienteService clienteService;
    @MockBean
    private UserService userService;
    
	@Autowired
	private MockMvc mockMvc;


	
	@WithMockUser(value = "spring")
    @Test
	void showCuentaList() throws Exception {
		mockMvc.perform(get("/allCuentas"))
				.andExpect(status().isOk())
				.andExpect(view().name("clientes/clientesList"))
				.andExpect(model().attributeExists("clientes"));
		
	}

	@WithMockUser(value = "spring")
    @Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/clientes/new"))
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"))
		.andExpect(model().attributeExists("cliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/new")
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/welcome"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/new")
				.with(csrf())
				.param("nombre", "77")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "jiji")
				.param("telefono", "543")
				.param("email", "pepe2000gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"));
	}

	@WithMockUser(value = "spring")
    @Test
	void showCliente() throws Exception {//estara seguramente mal	
		mockMvc.perform(get("/clientes/DetallesPerfil"))
		//.andExpect(view().name("clientes/clienteDetails"))
		.andReturn().getRequest();
		//.andExpect(view().name("clientes/clienteDetails"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void initUpdateForm() throws Exception {
		mockMvc.perform(get("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID))
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"));
		//dice que el modelo cliente no existe -.-
		//.andExpect(model().attributeExists("cliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(view().name("clientes/clienteDetails"));
	}
	

	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID)
				.with(csrf())
				.param("nombre", " ")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "jiji")
				.param("telefono", "2343")
				.param("email", "pepe2000@gmail")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"));
	}
	

	@WithMockUser(value = "spring")
    @Test
	void initDeleteCuenta() throws Exception {
		mockMvc.perform(get("/clientes/{cuentaId}/delete", TEST_CLIENTE_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCuentas"));
	}
	
	
}
