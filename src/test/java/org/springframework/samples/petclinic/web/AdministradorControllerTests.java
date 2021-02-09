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
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
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
	void showAdministradoresList() throws Exception {
		mockMvc.perform(get("/allAdministradores"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/administradoresList"))
		.andExpect(model().attributeExists("administradores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/administradores/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/createOrUpdateAdministradorForm"))
		.andExpect(model().attributeExists("administrador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/administradores/new")
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allAdministradores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/administradores/new")
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "falloJeje")
				.param("telefono", "no soy un numero")
				.param("email", "mal formato salu2")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("administrador"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/createOrUpdateAdministradorForm"));

	}
	
	@WithMockUser(value = "spring")
    @Test
	void initUpdateForm() throws Exception {
		mockMvc.perform(get("/administradores/{administradorId}/edit", TEST_ADMIN_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/createOrUpdateAdministradorForm"))
		.andExpect(model().attributeExists("administrador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormSuccess() throws Exception {
		mockMvc.perform(post("/administradores/{administradorId}/edit", TEST_ADMIN_ID)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allAdministradores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormHasErrors() throws Exception {
		mockMvc.perform(post("/administradores/{administradorId}/edit", TEST_ADMIN_ID)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "no es fecha")
				.param("telefono", "5439 muy corto")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("administrador"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/createOrUpdateAdministradorForm"));
	}
	
	
}
