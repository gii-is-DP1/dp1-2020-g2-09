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
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = RepartidorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class RepartidorControllerTests {
	
	private static final int TEST_REPARTIDOR_ID = 1;

	@MockBean
	private RepartidorService repartidorService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Repartidor repartidor = new Repartidor();
		repartidor.setNombre("Paco");
		repartidor.setApellidos("Florentino");
		repartidor.setTelefono(683020234);
		repartidor.setEmail("paquito@gmail.com");
		repartidor.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		repartidor.setUser(usuario); 
		
		given(this.repartidorService.findRepartidores()).willReturn(Lists.newArrayList(repartidor));
		given(this.repartidorService.findRepartidorById(TEST_REPARTIDOR_ID)).willReturn(repartidor);
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/repartidores/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("repartidores/createOrUpdateRepartidorForm"))
				.andExpect(model().attributeExists("repartidor"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/repartidores/new")
							.with(csrf())
							.param("nombre", "Antonio")
							.param("apellidos", "Antom")
							.param("fechaNacimiento", "2000/05/05")
							.param("telefono", "123698745")
							.param("user.username", "jaja")
							.param("user.password", "jaja")
							.param("email", "antonioJajas@gmail.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allRepartidores"));

	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/repartidores/new")
							.with(csrf())
							.param("nombre", "frdede")
							.param("apellidos", "jbnhbjhb")
							.param("fechaNacimiento", "bb")
							.param("telefono", "123")
							.param("user.username", "jaja")
							.param("user.password", "jaja")
							.param("email", "5hcwu@gmail.com"))
				.andExpect(model().attributeHasErrors("repartidor"))
				.andExpect(status().isOk())
				.andExpect(view().name("repartidores/createOrUpdateRepartidorForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/repartidores/{repartidorId}/edit", TEST_REPARTIDOR_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("repartidor"))
				.andExpect(view().name("repartidores/createOrUpdateRepartidorForm"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateRepartidorFormSuccess() throws Exception {
		mockMvc.perform(post("/repartidores/{repartidorId}/edit", TEST_REPARTIDOR_ID)
				.with(csrf())
				.param("nombre", "Mario")
				.param("apellidos", "Antom")
				.param("fechaNacimiento", "2012/05/05")
				.param("telefono", "123698745")
				.param("user.username", "jaja")
				.param("user.password", "jaja")
				.param("email", "5hcwu@gmail.com"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allRepartidores"));

	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateRepartidorFormHasErrors() throws Exception {
		mockMvc.perform(post("/repartidores/{repartidorId}/edit", TEST_REPARTIDOR_ID)
				.with(csrf())
				.param("nombre", "77")
				.param("apellidos", "Antom")
				.param("fechaNacimiento", "5161")
				.param("telefono", "123698745")
				.param("user.username", "jaja")
				.param("user.password", "jaja")
				.param("email", "5hcwu@gmail.com"))
		.andExpect(model().attributeHasErrors("repartidor"))
		.andExpect(status().isOk())
		.andExpect(view().name("repartidores/createOrUpdateRepartidorForm"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void initDeleteCuenta() throws Exception {
    	mockMvc.perform(get("/repartidores/{repartidorId}/delete", TEST_REPARTIDOR_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allRepartidores"));
    }

}
