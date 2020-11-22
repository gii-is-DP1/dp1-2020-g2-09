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
import java.time.LocalTime;

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
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for the {@link PetController}
 *
 * @author Colin But
 */
@WebMvcTest(value = ReservaController.class,
		includeFilters = @ComponentScan.Filter(value = ReservaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class ReservaControllerTests {

	private static final int TEST_MESA_ID = 9;

	private static final int TEST_RESERVA_ID = 99;

	@Autowired
	private ReservaController reservaController;


	@MockBean
	private ReservaService reservaService;
        
    @MockBean
	private MesaService mesaService;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Reserva r = new Reserva();
		tipoReserva tr = new tipoReserva();
		tr.setName("MERIENDA");
		tr.setId(3);
		r.setId(3);
		r.setFechaReserva(LocalDate.of(2020, 11, 24));
		r.setHora(LocalTime.of(20, 34));
		r.setNumeroPersonas(6);
		r.setTipoReserva(tr);
		given(this.reservaService.findReservas()).willReturn(Lists.newArrayList(r));
		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(new Mesa());
		given(this.reservaService.findById(TEST_RESERVA_ID)).willReturn(new Reserva());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/mesas/new")).andExpect(status().isOk()).andExpect(model().attributeExists("mesa"))
				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/mesas/new", TEST_RESERVA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipo_reserva", "CENA")
							.param("fecha_reserva", "2015/02/12"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/reservas/{reservaId}"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipo_reserva", "CENA")
							.param("fecha_reserva", "2015/02/12"))
				.andExpect(model().attributeHasNoErrors("reserva"))
				.andExpect(model().attributeHasErrors("mesa"))
				.andExpect(status().isOk())
				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("mesa"))
				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipo_reserva", "CENA")
							.param("fecha_reserva", "2015/02/12"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/reservas/{reservaId}"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipo_reserva", "CENA")
							.param("fecha_reserva", "2015/02/12"))
				.andExpect(model().attributeHasNoErrors("reserva"))
				.andExpect(model().attributeHasErrors("mesa")).andExpect(status().isOk())
				.andExpect(view().name("mesas/createOrUpdatePetForm"));
	}

}