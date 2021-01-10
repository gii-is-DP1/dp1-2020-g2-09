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
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = OfertaController.class,
includeFilters = @ComponentScan.Filter(value = OfertaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class OfertaControllerTests {

	private static final int TEST_OFERTA_ID = 1;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_OTRO_ID = 1;
	//private static final int TEST_PET_ID = 1;

	@Autowired
	private OfertaController ofertaController;


	@MockBean
	private OfertaService ofertaService;
	@MockBean
	private  BebidaService bebidaService;
	@MockBean
	private PizzaService pizzaService;
	@MockBean
	private OtrosService otrosService;
	

        
// @MockBean
//	private OwnerService ownerService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.of(2021, 11, 10));
		o.setFechaFinal(LocalDate.of(2021, 11, 22));
		o.setEstadoOferta(true);
		o.setId(1);
		
		NivelSocio ns = new NivelSocio();
		ns.setId(2);
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setId(2);
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		given(this.ofertaService.findOfertas()).willReturn(Lists.newArrayList(o));
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(new Pizza());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());

	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/ofertas/new", TEST_OFERTA_ID)).andExpect(status().isOk())
				.andExpect(view().name("ofertas/createOrUpdateOfertaForm")).andExpect(model().attributeExists("oferta"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/ofertas/new")
							.with(csrf())
							.param("coste", "20.0")
							.param("name", "oferta2")
							.param("fechaInicial", "2021/11/01")
							.param("fechaFinal", "2021/12/02")
							.param("nivelSocio.name", "ORO") 
							.param("tamanoOferta.name", "GRANDE")
							.param("estadoOferta.name", "true"))
		
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ofertas/"+null+"/anadirProductos"));
		//le pasamos un null porque en el controller se le pasa el id de la oferta que estamos haciendo 
		//y como con el .param no podemos pasarle el id, se pasa un null

	}

	
	@WithMockUser(value = "spring")
    @Test 
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/edit", TEST_OFERTA_ID)
							.with(csrf())
							.param("coste", "20.0")
							.param("name", "")
							.param("fechaInicial", "x")
							.param("fechaFinal", "z")
							.param("nivelSocio.name", "ORO") 
							.param("tamanoOferta.name", "GRANDE")
							.param("estadoOferta.name", "true"))
				.andExpect(model().attributeHasErrors("oferta"))
				.andExpect(view().name("ofertas/createOrUpdateOfertaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/ofertas/{ofertaId}/edit", TEST_OFERTA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("oferta"))
				.andExpect(view().name("ofertas/createOrUpdateOfertaForm"));
	}
    
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdatePedidoFormSuccess() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/edit", TEST_OFERTA_ID)
						.with(csrf())
						.param("coste", "20.0")
						.param("name", "oferta1")
						.param("fechaInicial", "2021/11/01")
						.param("fechaFinal", "2021/12/02")
						.param("nivelSocio.name", "ORO") 
						.param("tamanoOferta.name", "GRANDE")
						.param("estadoOferta.name", "true"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allOfertas"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/edit", TEST_OFERTA_ID)
							.with(csrf())
							.param("coste", "abcd")
							.param("fechaInicial", "b")
							.param("fechaFinal", "a")
							.param("nivelSocio.name", "ORO")
							.param("tamanoOferta.name", "GRANDE")
							.param("estadoOferta.name", "true"))
				.andExpect(model().attributeHasErrors("oferta"))
				.andExpect(model().attributeHasFieldErrors
						("oferta", "coste", "fechaInicial", "fechaFinal"))
				.andExpect(status().isOk())
				.andExpect(view().name("ofertas/createOrUpdateOfertaForm"));
	}
    
    @WithMockUser(value = "spring")
    @Test
    void testInitDeleteOferta() throws Exception {
    	
    	//Creo que el MockMvcRequestBuilders es para json y eso pero ni idea 
    	//Con este test me da 403 forbidden
//    	mockMvc.perform(MockMvcRequestBuilders.delete("/ofertas/{ofertasId}/delete", TEST_OFERTA_ID))
//    	.andExpect(status().is3xxRedirection())
//    	.andExpect(view().name("redirect:/allOfertas"))
//    	.andExpect(model().attributeDoesNotExist("oferta"));
    	
    	mockMvc.perform(get("/ofertas/{ofertasId}/delete", TEST_OFERTA_ID))
    			.andExpect(status().is3xxRedirection()).andExpect(view()
    					.name("redirect:/allOfertas"))
    			.andExpect(model().attributeDoesNotExist("oferta"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testShowOfertaList() throws Exception {
    	mockMvc.perform(get("/allOfertas")).andExpect(status().isOk())
		.andExpect(view().name("ofertas/ofertasList"))
		.andExpect(model().attributeExists("ofertas"));
    }
    
    //No sé muy bien cómo se prueba esto
    @WithMockUser(value = "spring")
    @Test
    void testChangeOfertaStateTrue() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/changeState", TEST_OFERTA_ID)
				.with(csrf())
				.param("estadoOferta.name", "true"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allOfertas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testChangeOfertaStateFalse() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/changeState", TEST_OFERTA_ID)
				.with(csrf())
				.param("estadoOferta.name", "false"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allOfertas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testAñadirPizza() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/anadirPizza/{pizzaId}", TEST_OFERTA_ID, TEST_PIZZA_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ofertas/{ofertaId}/anadirProductos"));

    } 
    
    @WithMockUser(value = "spring")
    @Test
    void testAñadirBebida() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/anadirBebida/{bebidaId}", TEST_OFERTA_ID, TEST_BEBIDA_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ofertas/{ofertaId}/anadirProductos"));

    } 
    @WithMockUser(value = "spring")
    @Test
    void testAñadirOtro() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/anadirOtro/{otroId}", TEST_OFERTA_ID, TEST_OTRO_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ofertas/{ofertaId}/anadirProductos"));

    } 
    @WithMockUser(value = "spring")
    @Test
    void testAñadirProducto() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/anadirProductos", TEST_OFERTA_ID))
    	.andExpect(view().name("ofertas/anadirProductos"))
		.andExpect(model().attributeExists("oferta"))
		.andExpect(model().attributeExists("otros"))
		.andExpect(model().attributeExists("bebidas"))
		.andExpect(model().attributeExists("pizzas"));
    } 
	
    @WithMockUser(value = "spring")
    @Test
    void testAñadirProductoPost() throws Exception {
    	mockMvc.perform(post("/ofertas/{ofertaId}/anadirProductos", TEST_OFERTA_ID)
    			.with(csrf())
				.param("coste", "abcd")
				.param("fechaInicial", "2021/10/02")
				.param("fechaFinal", "2021/12/02")
				.param("nivelSocio.name", "ORO")
				.param("tamanoOferta.name", "GRANDE")
				.param("estadoOferta.name", "true"))
    	
		.andExpect(status().is3xxRedirection())
    	.andExpect(view().name("redirect:/allOfertas"));
    } 
}
