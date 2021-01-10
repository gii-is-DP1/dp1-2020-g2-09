package org.springframework.samples.petclinic.web;
/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for the {@link IngredienteController}
 *
 * @author Colin But
 */
@WebMvcTest(value = IngredienteController.class,
		includeFilters = @ComponentScan.Filter(value = IngredienteFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)

class IngredientesControllerTests {

	private static final int TEST_INGREDIENTE_ID = 20;


	@Autowired
	private IngredienteController ingredienteController;


	@MockBean
	private IngredienteService ingredienteService;
        
    
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
//		Reserva r = new Reserva();
//		tipoReserva tr = new tipoReserva();
//		tr.setName("MERIENDA");
//		tr.setId(3);
//		r.setId(3);
//		r.setFechaReserva(LocalDate.of(2020, 11, 24));
//		r.setHora(LocalTime.of(20, 34));
//		r.setNumeroPersonas(6);
//		r.setTipoReserva(tr);
//		given(this.reservaService.findReservas()).willReturn(Lists.newArrayList(r));
//		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(new Mesa());
//		given(this.reservaService.findById(TEST_RESERVA_ID)).willReturn(new Reserva());
		
		Ingrediente ing = new Ingrediente();
		ing.setId(20);
		ing.setNombre("Langosta");
		ing.setTipo("Marisco");
		ing.setFechaCaducidad(LocalDate.of(2021, 11, 24));
		Alergenos Alergeno = new Alergenos();
		Alergeno.setId(1);
		Alergeno.setName("Marisco u otros crustaceos");
		ing.setAlergenos(Alergeno);
		
		given(this.ingredienteService.findIngredientes()).willReturn(Lists.newArrayList(ing));
		given(this.ingredienteService.findIngredienteById(TEST_INGREDIENTE_ID)).willReturn(ing);
		
	}

	@WithMockUser(value = "spring")
        @Test
	void testShowIngredienteList() throws Exception {
		mockMvc.perform(get("/allIngredientes"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("Ingredientes"))
				.andExpect(view().name("Ingredientes/ingredientesList"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {
	mockMvc.perform(get("/Ingredientes/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("Ingrediente"))
			.andExpect(view().name("Ingredientes/createOrUpdateIngredienteForm"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
	mockMvc.perform(post("/Ingredientes/new")
				.with(csrf())
				.param("Nombre", "Lenteja")
				.param("Tipo", "Legumbre")
				.param("fechaCaducidad", "2021/02/12"))
	
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/allIngredientes"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormFailure() throws Exception {
	mockMvc.perform(post("/Ingredientes/new")
				.with(csrf())
				.param("Nombre", "")
				.param("Tipo", "")
				.param("fechaCaducidad", ""))
	
			.andExpect(model().attributeHasErrors("ingrediente"))
			.andExpect(status().isOk())
			.andExpect(view().name("Ingredientes/createOrUpdateIngredienteForm"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testInitEditForm() throws Exception {
	mockMvc.perform(get("/Ingredientes/{IngredienteId}/edit", TEST_INGREDIENTE_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("Ingrediente"))
			.andExpect(view().name("Ingredientes/createOrUpdateIngredienteForm"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testProcessEditFormSuccess() throws Exception {
	mockMvc.perform(post("/Ingredientes/{IngredienteId}/edit", TEST_INGREDIENTE_ID)
				.with(csrf())
				.param("Nombre", "Lenteja")
				.param("Tipo", "Legumbre")
				.param("fechaCaducidad", "2021/02/12"))
	
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/allIngredientes"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testProcessEditFormFailure() throws Exception {
	mockMvc.perform(post("/Ingredientes/{IngredienteId}/edit", TEST_INGREDIENTE_ID)
				.with(csrf())
				.param("Nombre", "")
				.param("Tipo", "")
				.param("fechaCaducidad", ""))
	
			.andExpect(model().attributeHasErrors("ingrediente"))
			.andExpect(status().isOk())
			.andExpect(view().name("Ingredientes/createOrUpdateIngredienteForm"));
	}
	@WithMockUser(value = "spring")
    @Test
    void testDelete() throws Exception {
	mockMvc.perform(get("/Ingredientes/{IngredienteId}/delete", TEST_INGREDIENTE_ID))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allIngredientes"))
	.andExpect(model().attributeDoesNotExist("Ingrediente"));
	}
	
    

}