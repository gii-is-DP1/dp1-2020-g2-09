package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = MesaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class MesaControllerTests {
	
	private static final int TEST_MESA_ID = 1;
	private static final int TEST_MESA_ID2 = 2;
	private static final int TEST_MESA_ID3 = 3;

	@MockBean
	private MesaService mesaService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Mesa m=new Mesa();
		m.setCapacidad(3);
		m.setId(TEST_MESA_ID);
		m.setVersion(1);
		Mesa m2=new Mesa();
		m2.setCapacidad(3);
		m2.setId(TEST_MESA_ID2);
		m2.setVersion(null);
		
		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(m);
		given(this.mesaService.findById(TEST_MESA_ID2)).willReturn(m2);
		given(this.mesaService.findById(TEST_MESA_ID3)).willReturn(new Mesa());

	}

	@WithMockUser(value = "spring")
    @Test
    void testShowMesaList() throws Exception {
	mockMvc.perform(get("/allMesas"))
			.andExpect(status().isOk())
			.andExpect(view().name("mesas/mesasList"))
			.andExpect(model().attributeExists("mesas"));
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
	void testprocessUpdateMesaFormSuccessNoVersion() throws Exception {
		mockMvc.perform(post("/mesas/{mesaId}/edit", TEST_MESA_ID)
				.with(csrf())
				.param("capacidad", "5"));

	}
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateMesaFormSuccess() throws Exception {
		mockMvc.perform(post("/mesas/{mesaId}/edit", TEST_MESA_ID2)
				.with(csrf())
				.param("capacidad", "3"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allMesas"));

	}
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateMesaFormHasErrors() throws Exception {
		mockMvc.perform(post("/mesas/{mesaId}/edit", TEST_MESA_ID2)
				.with(csrf())
				.param("capacidad", "kbiygu"))
		.andExpect(model().attributeHasErrors("mesa"))
		.andExpect(model().attributeHasFieldErrors("mesa", "capacidad"))
		.andExpect(status().isOk())
		.andExpect(view().name("mesas/createOrUpdateMesaForm"));
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testinitDeleteMesa() throws Exception {
   		mockMvc.perform(get("/mesas/{mesaId}/delete", TEST_MESA_ID))
   				.andExpect(status().is3xxRedirection())
   				.andExpect(view().name("redirect:/allMesas"));
   	}
}
