package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = MesaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class MesaControllerTests {
	
	private static final int TEST_MESA_ID = 1;

	@MockBean
	private MesaService mesaService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Mesa mesa1 = new Mesa();
		mesa1.setId(3);
		mesa1.setCapacidad(5);
		mesa1.setVersion(1);
		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(new Mesa());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/mesas/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("mesas/createOrUpdateMesaForm"))
				.andExpect(model().attributeExists("mesa"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/mesas/new")
							.with(csrf())
							.param("capacidad", "5"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allMesas"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/mesas/new")
							.with(csrf())
							.param("capacidad", "kbiygu"))
		
				.andExpect(model().attributeHasErrors("mesa"))
				.andExpect(status().isOk())
				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/mesas/{mesaId}/edit", TEST_MESA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateMesaFormSuccess() throws Exception {
		mockMvc.perform(post("/mesas/{mesaId}/edit", TEST_MESA_ID)
				.with(csrf())
				.param("capacidad", "5"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allMesas"));

	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateMesaFormHasErrors() throws Exception {
		mockMvc.perform(post("/mesas/{mesaId}/edit", TEST_MESA_ID)
				.with(csrf())
				.param("capacidad", "kbiygu"))
		.andExpect(model().attributeHasErrors("mesa"))
		.andExpect(model().attributeHasFieldErrors("mesa", "capacidad"))
		.andExpect(status().isOk())
		.andExpect(view().name("mesas/createOrUpdateMesaForm"));
    }

}
