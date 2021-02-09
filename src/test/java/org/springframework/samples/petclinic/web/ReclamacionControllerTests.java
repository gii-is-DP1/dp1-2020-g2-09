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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reclamaciones;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ReclamacionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class ReclamacionControllerTests {

	private static final int TEST_RECLAMACION_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	private static final String TEST_user= "spring";
	private static final Integer TEST_RECLAMACION_ID2 = 2;
	private static final Integer TEST_CLIENTE_ID2 = 2;
	private static final Integer TEST_PEDIDO_ID2 = 2;
	private static final String TEST_user2 = null;
	
	@Autowired
	private ReclamacionController reclamacionController;
	
	
	@MockBean
	private ReclamacionService reclamacionService;
	
	 @MockBean
	    private UserService userService;
	 
	 @MockBean
	    private ClienteService clienteService;
        
    @MockBean
	private PedidoService pedidoService;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		
		User u1 = new User();
		Optional<User> op= Optional.of(u1);
		given(this.userService.findUser(TEST_user)).willReturn(op);
		
		User u2 = new User();
		Optional<User> op2= Optional.of(u2);
		given(this.userService.findUser(TEST_user2)).willReturn(op2);
		
		Reclamacion r = new Reclamacion();
		Pedido p = new Pedido();
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		cliente.setUser(u1);
		
		p.setId(TEST_PEDIDO_ID);
		p.setCliente(cliente);
		p.setDireccion("Bda San Diego");
		EstadoPedido ep = new EstadoPedido();
		ep.setName("EN COCINA");
		p.setEstadoPedido(ep);
		p.setFechaPedido(LocalDate.of(2021, 12, 1));
		//p.setId(1);
		p.setPrecio(40.5);
	
		TipoEnvio te = new TipoEnvio();
		te.setName("A DOMICILIO");
		p.setTipoEnvio(te);
		
		TipoPago tp = new TipoPago();
		tp.setName("TARJETA");
		p.setTipoPago(tp);
		p.setGastosEnvio(3.90);
		
		r.setId(TEST_RECLAMACION_ID);
		r.setObservacion("aaaaaaaaaaaaaaa"); 
		r.setRespuesta("aaaaaaaaaaaaaaaaaaaa");
		
		Collection<Reclamacion> r1 =new ArrayList<>();
		r1.add(r);
		p.setReclamacion(r1);
		
		Reclamacion r2 = new Reclamacion();
		Pedido p2 = new Pedido();
		Cliente cliente2 = new Cliente();
		cliente2.setApellidos("doldán Cadena");
		cliente2.setEmail("jrc@gmaix.com");
		cliente2.setFechaNacimiento(LocalDate.of(2001, 6,7));
		cliente2.setId(TEST_CLIENTE_ID2);
		cliente2.setNombre("Jesulín");
		cliente2.setTelefono(123456789);
		cliente2.setUser(u2);
		
		p2.setId(TEST_PEDIDO_ID2);
		p2.setCliente(cliente);
		p2.setDireccion("Bda San Diego");
		EstadoPedido ep2 = new EstadoPedido();
		ep2.setName("EN COCINA");
		p2.setEstadoPedido(ep);
		p2.setFechaPedido(LocalDate.of(2021, 12, 1));
		//p.setId(1);
		p2.setPrecio(40.5);
	
		TipoEnvio te2 = new TipoEnvio();
		te2.setName("A DOMICILIO");
		p2.setTipoEnvio(te2);
		
		TipoPago tp2 = new TipoPago();
		tp2.setName("TARJETA");
		p2.setTipoPago(tp2);
		p2.setGastosEnvio(3.90);
		
		r2.setId(TEST_RECLAMACION_ID2);
		r2.setObservacion("aaaaaaaaasdasaaaaa"); 
		r2.setRespuesta("aaaaaaaaaaaa asdasd aaaaaaaa");
		
		Collection<Reclamacion> rl =new ArrayList<>();
		rl.add(r2);
		p2.setReclamacion(rl);
		
		this.reclamacionService.anadirReclamacionAPedido(TEST_RECLAMACION_ID, TEST_PEDIDO_ID);
		Cliente cliente3= p.getCliente();
		User usuario = cliente.getUser();
		Reclamaciones reclamaciones=new Reclamaciones();
		reclamaciones.getReclamacionesList().add(reclamacionService.findReclamacionById(TEST_RECLAMACION_ID));
		List<Integer> l= new ArrayList<Integer>();
		l.add(TEST_CLIENTE_ID2);
		l.add(TEST_CLIENTE_ID);
		given(this.reclamacionService.findPedidosConReclamaciones()).willReturn(l);
		given(this.clienteService.findCuentaByUser(u1)).willReturn(cliente);
		given(this.reclamacionService.findReclamaciones()).willReturn(Lists.newArrayList(r));
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(p);
		given(this.reclamacionService.findReclamacionById(TEST_RECLAMACION_ID)).willReturn(r);
		given(this.reclamacionService.findReclamacionById(TEST_RECLAMACION_ID2)).willReturn(r2);
		given(this.pedidoService.findIdPedidoByReclamacionId(TEST_RECLAMACION_ID)).willReturn(TEST_PEDIDO_ID);
		List<Integer> l2= new ArrayList<Integer>();
		l2.add(TEST_PEDIDO_ID);
		List<Integer> l3= new ArrayList<Integer>();
		l3.add(TEST_RECLAMACION_ID);
		given(this.reclamacionService.findPedidosConReclamacionDeCliente(TEST_CLIENTE_ID)).willReturn(l2);
		given(this.reclamacionService.findReclamacionesDePedidosDeCliente(TEST_PEDIDO_ID)).willReturn(l3);
	}

	
	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/pedidos/{pedidoId}/anadirReclamacion/new",TEST_PEDIDO_ID))
		.andExpect(status().isOk()).andExpect(model().attributeExists("reclamacion"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}

	
	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/anadirReclamacion/new", TEST_PEDIDO_ID)
				.with(csrf())
				.param("observacion", "No se que ocurre")
				.param("respuesta", "Lo sentimos mucho, ..."))
				.andExpect(view().name("reclamaciones/confirmarReclamacion"));
} 

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/anadirReclamacion/new", TEST_PEDIDO_ID)
							.with(csrf())
							.param("observacion", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
							.param("respuesta", ""))	
				.andExpect(model().attributeHasErrors("reclamacion"))
				.andExpect(model().attributeHasFieldErrors("reclamacion", "respuesta"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/reclamaciones/{reclamacionId}/edit", TEST_RECLAMACION_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("reclamacion"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/reclamaciones/{reclamacionId}/edit", TEST_RECLAMACION_ID)
							.with(csrf())
							.param("observacion", "pizza muy cara")
							.param("respuesta", "Sentimos las molestias"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allReclamaciones"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/reclamaciones/{reclamacionId}/edit", TEST_RECLAMACION_ID)
							.with(csrf())
							.param("observacion", "otra reclamacion")
							.param("respuesta", ""))
				.andExpect(model().attributeHasErrors("reclamacion"))
				.andExpect(model().attributeHasFieldErrors("reclamacion", "respuesta"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}
    
    @WithMockUser(value = "spring")
    @Test
    void testShowReclamacionList() throws Exception {
    	mockMvc.perform(get("/allReclamaciones")).andExpect(status().isOk())
		.andExpect(view().name("reclamaciones/reclamacionesList"))
		.andExpect(model().attributeExists("reclamaciones"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testInitDeleteReclamacion() throws Exception {

    	
    	mockMvc.perform(get("/reclamaciones/{reclamacionId}/delete", TEST_RECLAMACION_ID))
    			.andExpect(status().is3xxRedirection()).andExpect(view()
    					.name("redirect:/reclamaciones/user"))
    			.andExpect(model().attributeDoesNotExist("reclamacion"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testAnadirReclamacionAPedido() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/reclamaciones/{reclamacionId}/confirmarReclamacion", TEST_PEDIDO_ID, TEST_RECLAMACION_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/reclamaciones/user"));
    }
    
  
    @WithMockUser(value = "spring")
    @Test
    void testShowDetallesReclamacion() throws Exception {
    	mockMvc.perform(get("/reclamaciones/{reclamacionId}/verDetalles", TEST_RECLAMACION_ID))
    	.andExpect(status().isOk())
    	.andExpect(view().name("reclamaciones/verDetallesReclamacion"));

    }
    
    //Da fallo
    @WithMockUser(value = "spring")
    @Test
    void testShowMisReclamaciones() throws Exception {
    	mockMvc.perform(get("/reclamaciones/user", TEST_RECLAMACION_ID))
    	.andExpect(status().isOk()).andExpect(status().is2xxSuccessful())
    	.andExpect(view().name("reclamaciones/reclamacionUser"));
    }
    
	 

}
