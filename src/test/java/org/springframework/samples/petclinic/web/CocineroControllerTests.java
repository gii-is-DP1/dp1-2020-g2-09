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
	private static final int TEST_COCINA_ID2 = 2;
	private static final int TEST_COCINA_ID3 = 3;

	@MockBean
	private CocineroService cocineroService;
    
	@Autowired
	private MockMvc mockMvc;

	
	@BeforeEach
	void setup() {
		Cocina cocinero = new Cocina();
		cocinero.setNombre("Paco");
		cocinero.setApellidos("Florentino");
		cocinero.setTelefono(683020234);
		cocinero.setEmail("paquito@gmail.com");
		cocinero.setFechaInicioContrato(LocalDate.of(2010, 10, 10));
		cocinero.setFechaFinContrato(null);
		cocinero.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		cocinero.setUser(usuario); 
		Cocina cocinero2 = new Cocina();
		cocinero2.setNombre("Pacoe");
		cocinero2.setApellidos("Floreentino");
		cocinero2.setTelefono(683070234);
		cocinero2.setEmail("paquito2@gmail.com");
		cocinero2.setFechaInicioContrato(LocalDate.of(2000, 10, 10));
		cocinero2.setFechaFinContrato(LocalDate.of(2019,12,12));
		cocinero2.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario2 = new User();
		usuario2.setUsername("PAO");
		usuario2.setPassword("Tompas");
		usuario2.setEnabled(true);
		cocinero2.setUser(usuario); 
		Cocina cocinero3 = new Cocina();
		cocinero3.setNombre("Paddco");
		cocinero3.setApellidos("Florentddino");
		cocinero3.setTelefono(683030234);
		cocinero3.setEmail("paquitdddo@gmail.com");
		cocinero3.setFechaInicioContrato( LocalDate.now().plusDays(2222222));
		cocinero3.setFechaFinContrato(null);
		cocinero3.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario3 = new User();
		usuario3.setUsername("PAqggguitoO");
		usuario3.setPassword("Tomatggge y papas");
		usuario3.setEnabled(true);
		cocinero3.setUser(usuario3); 
		given(this.cocineroService.findCocineros()).willReturn(Lists.newArrayList(cocinero));
		given(this.cocineroService.findCocineroById(TEST_COCINA_ID)).willReturn(cocinero);
		given(this.cocineroService.findCocineroById(TEST_COCINA_ID2)).willReturn(cocinero2);
		given(this.cocineroService.findCocineroById(TEST_COCINA_ID3)).willReturn(cocinero3);

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
							.param("user.username", "escoba2000")
							.param("user.password", "escoba2000")
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
							.param("user.username", "escoba2000")
							.param("user.password", "escoba2000"))
				.andExpect(model().attributeHasErrors("cocina"))
				.andExpect(status().isOk())
				.andExpect(view().name("cocineros/createOrUpdateCocinaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/cocineros/{cocineroId}/edit", TEST_COCINA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("cocineros/createOrUpdateCocinaForm"))
				.andExpect(model().attributeExists("cocina"));
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
				.param("email", "5hcwu@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allCocineros"));

	}
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateCocineroFormSuccess2() throws Exception {
		mockMvc.perform(post("/cocineros/{cocineroId}/edit", TEST_COCINA_ID2)
				.with(csrf())
				.param("nombre", "Mario")
				.param("apellidos", "Antom")
				.param("fechaNacimiento", "2012/05/05")
				.param("telefono", "123698745")
				.param("email", "5hcwu@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
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
				.param("email", "5hcwu@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("cocina"))
		.andExpect(status().isOk())
		.andExpect(view().name("cocineros/createOrUpdateCocinaForm"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void initDeleteCuenta() throws Exception {
    	mockMvc.perform(get("/cocineros/{cocineroId}/delete", TEST_COCINA_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCocineros"));
    }
   
    @WithMockUser(value = "spring")
   	@Test
   	void testsshowRepartidoresList() throws Exception {
    	mockMvc.perform(get("/allCocineros"))
    	.andExpect(status().isOk())
		.andExpect(view().name("cocineros/cocinerosList"))
		.andExpect(model().attributeExists("cocinas"));
    }
    
	@WithMockUser(value = "spring")
    @Test
    void testDarAltayBajaIf() throws Exception {
		mockMvc.perform(get("/cocineros/{cocineroId}/altaobaja", TEST_COCINA_ID2)
				.with(csrf())
				.param("fechaFinContrato", "2020/11/12"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCocineros"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testDarAltayBajaElseIf() throws Exception {
		mockMvc.perform(get("/cocineros/{cocineroId}/altaobaja", TEST_COCINA_ID)
				.with(csrf()) 
				.param("fechaInicioContrato", String.valueOf(LocalDate.now().plusDays(30))))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCocineros"));

	}
	
	@WithMockUser(value = "spring")
    @Test
    void testDarAltayBajaElseElse() throws Exception {
		mockMvc.perform(get("/cocineros/{cocineroId}/altaobaja", TEST_COCINA_ID3))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/NoEsPosibleDarDeBaja"));

	}
	
}
