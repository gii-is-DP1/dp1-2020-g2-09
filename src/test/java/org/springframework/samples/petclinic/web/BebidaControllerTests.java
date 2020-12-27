package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

//@RunWith(SpringRunner.class)
@WebMvcTest(value = BebidaController.class,
		includeFilters = @ComponentScan.Filter(value = BebidaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class BebidaControllerTests {
	private static final int TEST_BEBIDA_ID = 1;

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
				
		
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {

		mockMvc.perform(get("/bebidas/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"))
			.andExpect(model().attributeExists("bebida"));
}
	
	@WithMockUser(value = "spring")
	@Test
	//Falla en que no redireciona y lo lleva a createOrUpdate
    void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/bebidas/new")
				.with(csrf())
				.param("contador","3")
				.param("nombre","coca-cola")
				.param("coste","10")
				.param("tamano.name", "PEQUEÑO")
				.param("esCarbonatada", "true"))
	.andExpect(view().name("redirect:/allBebidas"))
		.andExpect(status().is3xxRedirection())
		.andDo(MockMvcResultHandlers.print ());

}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/bebidas/new")
							.with(csrf())
							.param("contador","ttt")
							.param("nombre","Hidromiel")
							.param("coste","10")
							.param("tamano", "ENORME")
							.param("esCarbonatada","true"))
				.andExpect(model().attributeHasErrors("bebida"))
				.andExpect(status().isOk())
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"))
;
		}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/bebidas/{bebidaId}/edit", TEST_BEBIDA_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("bebida"))
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		
}
	
	@WithMockUser(value = "spring")
	@Test
	//Falla en que no redireciona y lo lleva a createOrUpdate
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/bebidas/{bebidaId}/edit", TEST_BEBIDA_ID)
				.with(csrf())
				//.param("id", "99")
				.param("contador","5")
				.param("nombre","Hidromiel")
				.param("coste","10")
				.param("tamano.name","GRANDE")//creo que el fallo es esto que no puede hacer un TamanoProducto de GRANDE
				.param("esCarbonatada","true"))
//		.andExpect(model().attributeExists("bebida"))
//		.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allBebidas"));
	}
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/bebidas/{bebidaId}/edit", TEST_BEBIDA_ID)
							.with(csrf())
							.param("contador","ttt")
							.param("nombre","Hidromiel")
							.param("coste","10")
							.param("tamano", "ENORME")
							.param("esCarbonatada","true"))
				.andExpect(model().attributeHasErrors("bebida"))
				.andExpect(status().isOk())
				.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
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
