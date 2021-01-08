package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = InformeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class InformeControllerTests {
	
	private static final int TEST_INFORME_ID = 1;

	@Autowired
	private InformeController informeController;

	@MockBean
	private PizzaService cocineroService;
	@MockBean
	private BebidaService bebidaService;
	@MockBean
	private MesaService mesasService;
	@MockBean
	private IngredienteService ingredienteService;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
//		Cocina coci = new Cocina();
//		coci.setId(3);
//		coci.setApellidos("Gonz");
//		coci.setFechaFinContrato(LocalDate.of(2022, 05, 05));
//		coci.setFechaNacimiento(LocalDate.of(2010, 06, 06));
//		coci.setEmail("gonzalito@gmail.com");
//		coci.setNombre("Gonzalo");
//		coci.setTelefono(321145698);
//		
//		User usuario = new User();
//		usuario.setUsername("gonz");
//		usuario.setPassword("gonz");
//		coci.setUser(usuario);
//		given(this.cocineroService.findCocineros()).willReturn(Lists.newArrayList(coci));
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitList() throws Exception {
		mockMvc.perform(get("/informe"))
				.andExpect(status().isOk())
				.andExpect(view().name("informe/InformeList"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testInformeIngredientesMasUsados() throws Exception {
		mockMvc.perform(get("/informe/IngredientesMasUsados"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mapa"))
				.andExpect(view().name("informe/InformeIngredientesMasUsados"));
	}

	@WithMockUser(value = "spring")
    @Test
    void testInformeMesasMasUsadas() throws Exception {
		mockMvc.perform(get("/informe/MesasMasUsadas"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("mapa"))
		.andExpect(view().name("informe/InformeMesasMasUsadas"));

	}
	@WithMockUser(value = "spring")
    @Test
	void testInformeCaducidadIngredientes() throws Exception {
		mockMvc.perform(get("/informe/CaducidadIngredientes"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mapa"))
				.andExpect(view().name("informe/CaducidadIngredientes"));
	}


}