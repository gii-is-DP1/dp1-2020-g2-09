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
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = CocineroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class CocineroControllerTests {
	
	private static final int TEST_COCINA_ID = 1;

	@Autowired
	private CocineroController cocineroController;

	@MockBean
	private CocineroService cocineroService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Cocina coci = new Cocina();
		coci.setId(3);
		coci.setApellidos("Gonz");
		coci.setFechaFinContrato(LocalDate.of(2022, 05, 05));
		coci.setFechaNacimiento(LocalDate.of(2010, 06, 06));
		coci.setEmail("gonzalito@gmail.com");
		coci.setNombre("Gonzalo");
		coci.setTelefono(321145698);
		
		User usuario = new User();
		usuario.setUsername("gonz");
		usuario.setPassword("gonz");
		coci.setUser(usuario);
		given(this.cocineroService.findCocineros()).willReturn(Lists.newArrayList(coci));
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/cocineros/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("cocineros/createOrUpdateCocinaForm"))
				.andExpect(model().attributeExists("cocina"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/cocineros/new")
							.with(csrf())
							.param("nombre", "Antonio")
							.param("apellidos", "Antom")
							.param("fechaNacimiento", "2012/05/05")
							.param("telefono", "123698745")
							.param("email", "5hcwu@gmail.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allCocineros"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/cocineros/new")
							.with(csrf())
							.param("nombre", "2525252")
							.param("apellidos", "85885")
							.param("fechaNacimiento", "bb")
							.param("telefono", "123698745")
							.param("email", "5hcwu@gmail.com")
							.param("user", "anolo")
							.param("password", "jeje"))
				.andExpect(status().isOk())
				.andExpect(view().name("cocineros/createOrUpdateCocinaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/cocineros/{cocineroId}/edit", TEST_COCINA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("cocineros/createOrUpdateCocinaForm"));
				//.andExpect(model().attributeExists("cocina"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateCocineroFormSuccess() throws Exception {
		mockMvc.perform(post("/cocineros/{cocineroId}/edit", TEST_COCINA_ID)
				.with(csrf())
				.param("nombre", "Mario")
				.param("apellidos", "Antom")
				.param("fechaNacimiento", "2012/05/05")
				.param("telefono", "123698745")
				.param("email", "5hcwu@gmail.com"))
		
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allCocineros"));

	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateCocineroFormHasErrors() throws Exception {
		mockMvc.perform(post("/cocineros/{cocineroId}/edit", TEST_COCINA_ID)
				.with(csrf())
				.param("nombre", "2525252")
				.param("apellidos", "Antom")
				.param("fechaNacimiento", "5161")
				.param("telefono", "123698745")
				.param("email", "5hcwu@gmail.com"))

		.andExpect(status().isOk())
		.andExpect(view().name("cocineros/createOrUpdateCocinaForm"));
    }

}
