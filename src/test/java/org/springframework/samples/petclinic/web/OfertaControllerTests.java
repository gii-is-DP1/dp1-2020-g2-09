package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.model.TamanoProducto;
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
	private static final int TEST_OFERTA_ID2 = 2;
	private static final int TEST_OFERTA_ID3 = 3;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_PIZZA_ID2 = 2;
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_OTRO_ID = 1;

	@MockBean
	private OfertaService ofertaService;
	@MockBean
	private  BebidaService bebidaService;
	@MockBean
	private PizzaService pizzaService;
	@MockBean
	private OtrosService otrosService;
	@Autowired
	private MockMvc mockMvc;
	

	@BeforeEach
	void setup() {
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.of(2021, 11, 10));
		o.setFechaFinal(LocalDate.of(2021, 11, 22));
		o.setEstadoOferta(true);
		o.setId(TEST_OFERTA_ID);
		
		Oferta o2 = new Oferta();
		o2.setCoste(30.0);
		o2.setFechaInicial(LocalDate.of(2021, 03, 10));
		o2.setFechaFinal(LocalDate.of(2021, 03, 22));
		o2.setEstadoOferta(false);
		o2.setId(TEST_OFERTA_ID2);
		
		Oferta o3 = new Oferta();
		o3.setCoste(20.0);
		o3.setFechaInicial(LocalDate.of(2020, 11, 10));
		o3.setFechaFinal(LocalDate.of(2020, 11, 22));
		o3.setEstadoOferta(true);
		o3.setId(TEST_OFERTA_ID3);
		
		NivelSocio ns = new NivelSocio();
		ns.setId(2);
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setId(2);
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		
		Pizza pizza1 = new Pizza();
		pizza1.setId(TEST_PIZZA_ID);
		pizza1.setCoste(12.0);
		pizza1.setNombre("Barbacoa");
		
		Pizza pizza2 = new Pizza();
		pizza2.setId(TEST_PIZZA_ID2);
		pizza2.setCoste(10.0);
		pizza2.setNombre("Hawaiana");
		
		List<Pizza> listP=new ArrayList<>();
		listP.add(pizza1); listP.add(pizza2);
		
		TamanoProducto t=new TamanoProducto();
		t.setId(5);
		t.setName("NORMAL");
		
		Bebida b = new Bebida();
		b.setId(3);
		b.setCoste(1.5);

		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(t);
		
		List<Bebida> listB=new ArrayList<>();
		listB.add(b); 
		
		Otro patatas = new Otro();
		patatas.setId(3);
		patatas.setCoste(8.5);
		patatas.setNombre("Patatas fritas");

		List<Otro> listO=new ArrayList<>();
		listO.add(patatas); 

		given(this.ofertaService.findOfertas()).willReturn(Lists.newArrayList(o));
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(o);
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID2)).willReturn(o2);
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID3)).willReturn(o3);
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(pizza1);
		given(this.pizzaService.findPizzas()).willReturn(listP);
		given(this.bebidaService.findBebidas()).willReturn(listB);
		given(this.otrosService.findOtros()).willReturn(listO);


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
		mockMvc.perform(post("/ofertas/new", TEST_OFERTA_ID)
							.with(csrf())
							.param("coste", "20.0")
							.param("fechaInicial", "x")
							.param("fechaFinal", "z")
							.param("nivelSocio.name", "ORO") 
							.param("tamanoOferta.name", "GRANDE")
							.param("estadoOferta.name", "true"))
				.andExpect(model().attributeHasErrors("oferta"))
				.andExpect(model().attributeExists("oferta"))
				.andExpect(model().attributeExists("pizzas"))
				.andExpect(model().attributeExists("bebidas"))
				.andExpect(model().attributeExists("otros"))
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
    
    @WithMockUser(value = "spring")
    @Test
    void testChangeOfertaStateTrue() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/changeState", TEST_OFERTA_ID2)
				.with(csrf())
				.param("estadoOferta.name", "true"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allOfertas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testChangeOfertaStateTrueEnTiempo() throws Exception {
    	mockMvc.perform(get("/ofertas/{ofertaId}/changeState", TEST_OFERTA_ID3)
				.with(csrf())
				.param("fechaInicial", "LocalDate.now()")
				.param("fechaFinal", "LocalDate.now().plusDays(30)"))
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
