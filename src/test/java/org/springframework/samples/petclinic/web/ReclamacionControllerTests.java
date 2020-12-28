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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ReclamacionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class ReclamacionControllerTests {

	private static final int TEST_RECLAMACION_ID = 1;

	private static final int TEST_PEDIDO_ID = 1;
	
	@Autowired
	private ReclamacionController reclamacionController;
	
	//Muy importante añadir los MockBean necesarios para el ApplicationContext

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
		Reclamacion r = new Reclamacion();
		Pedido p = new Pedido();
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(1);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		
		p.setCliente(cliente);
		p.setDireccion("Bda San Diego");
		
		//p.setEstadoPedido(estadoPedido);
		p.setFechaPedido(LocalDate.of(2020, 12, 1));
		p.setId(1);
		//p.setOfertasEnPedido(ofertasEnPedido);
		p.setPrecio(40.5);
		//p.setTipoEnvio(tipoEnvio);
		//p.setTipoPago(tipoPago);
		
		
		r.setId(1); 
		//r.setFechaReclamacion(LocalDate.of(2020, 11, 24));
		r.setObservacion("aaaaaaaaaaaaaaa"); 
		r.setRespuesta("aaaaaaaaaaaaaaaaaaaa");
		
		given(this.reclamacionService.findReclamaciones()).willReturn(Lists.newArrayList(r));
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.reclamacionService.findReclamacionById(TEST_RECLAMACION_ID)).willReturn(new Reclamacion());
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
				.with(csrf()))
				//.param("fechaReclamacion", "2020/11/27")
				//.param("observacion", "No se que ocurre"))
				//.param("respuesta", "Lo sentimos mucho"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allReclamaciones"));
		//.andExpect(view().name("reclamaciones/reclamacionesList"));
	//.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
} 

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/anadirReclamacion/new", TEST_PEDIDO_ID)
							.with(csrf())
							//.param("fechaReclamacion", "2020/12/25")
							.param("observacion", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
				.andExpect(model().attributeHasErrors("reclamacion"))
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
							//.param("fechaReclamacion", "2020/11/24")
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
							//.param("fechaReclamacion", "2020/12/25")

							.param("observacion", "otra reclamacion")
							.param("respuesta", ""))
				//.andExpect(model().attributeHasErrors("reclamacion"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}
	

}
