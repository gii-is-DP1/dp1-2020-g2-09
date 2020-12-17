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

import static org.hamcrest.xml.HasXPath.hasXPath;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for the {@link BebidaController}
 *
 * @author Colin But
 */
@WebMvcTest(value = BebidaController.class,
		includeFilters = @ComponentScan.Filter(value = BebidaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class BebidaControllerTests {
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_PEDIDO_ID=1;
	private static final int TEST_OFERTA_ID=1;

//carta oferta pedido bebida
	@Autowired
	private BebidaController bebidaController;

	@MockBean
	private BebidaService bebidaService;

	@MockBean
	private CartaService cartaService;
	
	@MockBean
	private PedidoService pedidoService;

	@MockBean
	private OfertaService ofertaService;
        
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		TamanoProducto t=new TamanoProducto();
		t.setId(5);
		t.setName("ENORME");
		
		Bebida b = new Bebida();
		b.setId(3);
		b.setCoste(10);
		b.setContador(1);
		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(t);
		given(this.bebidaService.findBebidas()).willReturn(Lists.newArrayList(b));
		given(this.bebidaService.findById(TEST_BEBIDA_ID)).willReturn(new Bebida());
		given(this.bebidaService.findIdBebidaByCartaId(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
		
		given(this.cartaService.findCartaById(TEST_CARTA_ID)).willReturn(new Carta());
		
//		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
	}
	
	@WithMockUser(value = "spring")
    	@Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/bebidas/new", TEST_CARTA_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"))
			.andExpect(model().attributeExists("bebida"));
		
//		mockMvc.perform(get("/pedidos/{pedidoId}/bebidas/new", TEST_PEDIDO_ID)).andExpect(status().isOk())
//		.andExpect(view().name("pedidos/createOrUpdatePedidoForm")).andExpect(model().attributeExists("pedido"));
//		
//		mockMvc.perform(get("/ofertas/{ofertaId}/bebidas/new", TEST_PEDIDO_ID)).andExpect(status().isOk())
//		.andExpect(view().name("ofertas/createOrUpdateOfertaForm")).andExpect(model().attributeExists("oferta"));
	}
	
	@WithMockUser(value = "spring")
		@Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/bebidas/new", TEST_CARTA_ID)
							.with(csrf())
							.param("contador","1")
							.param("nombre","Hidromiel")
							.param("coste","10")
							.param("tamano", "ENORME")
							.param("esCarbonatada","true"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/cartas/{cartaId}"));
			
//		mockMvc.perform(post("/pedidos/{pedidoId}/bebidas/new", TEST_PEDIDO_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
//		
//		mockMvc.perform(post("/ofertas/{ofertaId}/bebidas/new", TEST_OFERTA_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/ofertas/{ofertaId}"));
	}
	
	@WithMockUser(value = "spring")
		@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/bebidas/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID)
							.with(csrf())
							.param("contador","ttt")
							.param("nombre","Hidromiel")
							.param("coste","10")
							.param("tamano", "ENORME")
							.param("esCarbonatada","true"))
				//.andExpect(model().attributeHasNoErrors("carta"))
				//.andExpect(model().attributeHasErrors("bebida"))
				.andExpect(status().isOk())
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		
//		mockMvc.perform(post("/pedidos/{pedidoId}/bebidas/{bebidaId}/edit", TEST_PEDIDO_ID, TEST_BEBIDA_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(model().attributeHasNoErrors("pedido"))
//				.andExpect(model().attributeHasErrors("bebida"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
//		
//		mockMvc.perform(post("/ofertas/{ofertaId}/bebidas/{bebidaId}/edit", TEST_OFERTA_ID, TEST_BEBIDA_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(model().attributeHasNoErrors("oferta"))
//				.andExpect(model().attributeHasErrors("bebida"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
	}
	
	@WithMockUser(value = "spring")
		@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/bebidas/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("bebida"))
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		
//		mockMvc.perform(get("/pedidos/{pedidoId}/bebidas/{bebidaId}/edit", TEST_PEDIDO_ID, TEST_BEBIDA_ID))
//		.andExpect(status().isOk()).andExpect(model().attributeExists("bebida"))
//		.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
//		
//		mockMvc.perform(get("/ofertas/{ofertaId}/bebidas/{bebidaId}/edit", TEST_OFERTA_ID, TEST_BEBIDA_ID))
//		.andExpect(status().isOk()).andExpect(model().attributeExists("bebida"))
//		.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
	}
	
	@WithMockUser(value = "spring")
		@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/bebidas/{bebidaId}/edit", TEST_BEBIDA_ID)
				.with(csrf())
				.param("contador","1")
				.param("nombre","Hidromiel")
				.param("coste","10")
				.param("tamano", "ENORME")
				.param("esCarbonatada","true"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/bebidas/{bebidaId}"));
		
//		mockMvc.perform(post("/pedidos/{pedidoId}/bebidas/{bebidaId}/edit", TEST_PEDIDO_ID, TEST_BEBIDA_ID)
//				.with(csrf())
//				.param("contador","1")
//				.param("nombre","Hidromiel")
//				.param("coste","10")
//				.param("tamano", "ENORME")
//				.param("esCarbonatada","true"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
//		
//		mockMvc.perform(post("/ofertas/{ofertaId}/bebidas/{bebidaId}/edit", TEST_OFERTA_ID, TEST_BEBIDA_ID)
//				.with(csrf())
//				.param("contador","1")
//				.param("nombre","Hidromiel")
//				.param("coste","10")
//				.param("tamano", "ENORME")
//				.param("esCarbonatada","true"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/ofertas/{ofertaId}"));
	}
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/bebidas/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID)
							.with(csrf())
							.param("contador","ttt")
							.param("nombre","Hidromiel")
							.param("coste","10")
							.param("tamano", "ENORME")
							.param("esCarbonatada","true"))
				//.andExpect(model().attributeHasNoErrors("carta"))
				//.andExpect(model().attributeHasErrors("bebida"))
				.andExpect(status().isOk())
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		
//		mockMvc.perform(post("/pedidos/{pedidoId}/bebidas/{bebidaId}/edit", TEST_PEDIDO_ID, TEST_BEBIDA_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(model().attributeHasNoErrors("pedido"))
//				.andExpect(model().attributeHasErrors("bebida")).andExpect(status().isOk())
//				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
//		
//		mockMvc.perform(post("/ofertas/{ofertaId}/bebidas/{bebidaId}/edit", TEST_OFERTA_ID, TEST_BEBIDA_ID)
//							.with(csrf())
//							.param("contador","1")
//							.param("nombre","Hidromiel")
//							.param("coste","10")
//							.param("tamano", "ENORME")
//							.param("esCarbonatada","true"))
//				.andExpect(model().attributeHasNoErrors("oferta"))
//				.andExpect(model().attributeHasErrors("bebida")).andExpect(status().isOk())
//				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
	}
	
//	@WithMockUser(value = "spring")
//    	@Test
//		void testShowBebidasListXml() throws Exception {
//			mockMvc.perform(get("/bebidas.xml").accept(MediaType.APPLICATION_XML))
//					.andExpect(status().isOk())
//					.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
//					.andExpect(content().node(hasXPath("/bebidas/bebidaList[id=3]/id")));
//	}
}
