package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = CrashController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class CrashControllerTests {

    
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "spring")
    @Test
	void testTriggerExceptionRN5() throws Exception {
		mockMvc.perform(get("/NoEsPosibleDarDeBaja"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testNombreDePizzaPersonalizadaDuplicado() throws Exception {
		mockMvc.perform(get("/NombreDePizzaPersonalizadaDuplicado"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testTriggerExceptionRN7Pizza() throws Exception {
		mockMvc.perform(get("/PizzaDuplicadaEnCarta"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testTriggerExceptionRN7Bebida() throws Exception {
		mockMvc.perform(get("/BebidaDuplicadaEnCarta"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testTriggerExceptionRN7Otro() throws Exception {
		mockMvc.perform(get("/OtroDuplicadaEnCarta"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
		
	}
	
}
