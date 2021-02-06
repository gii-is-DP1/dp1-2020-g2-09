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
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ClienteController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ClienteControllerTests {

	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_CLIENTE_ID2 = 2;
	private static final int TEST_CLIENTE_ID3 = 3;
	private static final int TEST_CLIENTE_ID4 = 4;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_PEDIDO_ID2 = 2;
	private static final int TEST_PEDIDO_ID4 = 4;


    @MockBean
    private ClienteService clienteService;
    @MockBean
    private UserService userService;
    @MockBean
    private PedidoService pedidoService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Paco");
		cliente.setApellidos("Florentino");
		cliente.setTelefono(683020234);
		cliente.setEmail("paquito@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		cliente.setId(TEST_CLIENTE_ID);
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
        cliente.setUser(usuario); 
        
        Cliente cliente2 = new Cliente();
		cliente2.setNombre("Paco");
		cliente2.setApellidos("Florentino");
		cliente2.setTelefono(683020234);
		cliente2.setEmail("paquito@gmail.com");
		cliente2.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		cliente2.setId(TEST_CLIENTE_ID4);
		User usuario2 = new User();
		usuario2.setUsername("PAquitrrrrrrrrrrrroO");
		usuario2.setPassword("Tomarrrrrrrrrrrrrte y papas");
		usuario2.setEnabled(true);
        cliente.setUser(usuario2);
        
        
		EstadoPedido estd = new EstadoPedido();
		estd.setName("EN COCINA");
		estd.setId(1);
		
		EstadoPedido estd2 = new EstadoPedido();
		estd2.setName("PREPARADO");
		estd2.setId(2);
		
		TipoPago tp = new TipoPago();
		tp.setName("TARJETA");
		
		TipoEnvio te= new TipoEnvio();
		te.setId(1);		
		te.setName("RECOGER EN TIENDA");
		
		TipoEnvio te2 = new TipoEnvio();
		te2.setName("A DOMICILIO");
		te2.setId(2);
		
		Pedido pedido=new Pedido();
		pedido.setId(TEST_PEDIDO_ID);
		pedido.setEstadoPedido(estd2);
		pedido.setDireccion("C/Ferrara, 4");
		pedido.setTipoEnvio(te);
		pedido.setCliente(cliente);
		pedido.setGastosEnvio(3.50);
		pedido.setTipoPago(tp);
		pedido.setPrecio(202.12);
		Pedido pedido2=new Pedido();
		pedido2.setId(TEST_PEDIDO_ID);
		pedido2.setEstadoPedido(estd2);
		pedido2.setDireccion("C/Ferrara, 4");
		pedido2.setTipoEnvio(te);
		pedido2.setCliente(cliente);
		pedido2.setGastosEnvio(3.50);
		pedido2.setTipoPago(tp);
		pedido2.setPrecio(1.12);
        List<Pedido> pedido1111= new ArrayList<Pedido>();
        pedido1111.add(pedido);
        pedido1111.add(pedido2);
        Pedido pedido3=new Pedido();
		pedido3.setId(TEST_PEDIDO_ID2);
		pedido3.setEstadoPedido(estd2);
		pedido3.setDireccion("C/Ferrara, 4");
		pedido3.setTipoEnvio(te);
		pedido3.setCliente(cliente);
		pedido3.setGastosEnvio(3.50);
		pedido3.setTipoPago(tp);
		pedido3.setPrecio(100.12);
		pedido3.setCliente(cliente2);
		List<Pedido> aux= new ArrayList<Pedido>();
		aux.add(pedido3);
		Pedido pedido4=new Pedido();
		pedido4.setId(TEST_PEDIDO_ID4);
		pedido4.setEstadoPedido(estd2);
		pedido4.setDireccion("C/Ferrara, 4");
		pedido4.setTipoEnvio(te);
		pedido4.setCliente(cliente);
		pedido4.setGastosEnvio(3.50);
		pedido4.setTipoPago(tp);
		pedido4.setPrecio(400.12);
		pedido4.setCliente(cliente2);
		List<Pedido> aux2= new ArrayList<Pedido>();
		aux2.add(pedido4);
		

		// pedido1111.add(pedido3);
        Optional<User> op= Optional.of(usuario);
        given(this.clienteService.findCuentas()).willReturn(Lists.newArrayList(cliente));
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(cliente);
		given(this.clienteService.findCuentaByUser(usuario)).willReturn(cliente);
		given(this.userService.findUser(usuario.getUsername())).willReturn(op);
		//given(this.userService.findUser(usuario.getUsername()).get()).willReturn(usuario);
		given(this.clienteService.findCuentaByUser(usuario)).willReturn(cliente);
		given(this.pedidoService.findPedidosByCliente(TEST_CLIENTE_ID2)).willReturn(pedido1111);
		given(this.pedidoService.findPedidosByCliente(TEST_CLIENTE_ID3)).willReturn(aux2);
		given(this.pedidoService.findPedidosByCliente(TEST_CLIENTE_ID4)).willReturn(aux);

        
	}

	
	@WithMockUser(value = "spring")
    @Test
	void showCuentaList() throws Exception {
		mockMvc.perform(get("/allCuentas"))
				.andExpect(status().isOk())
				.andExpect(view().name("clientes/clientesList"))
				.andExpect(model().attributeExists("clientes"));
		
	}

	@WithMockUser(value = "spring")
    @Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/clientes/new"))
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"))
		.andExpect(model().attributeExists("cliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/new")
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/new")
				.with(csrf())
				.param("nombre", "77")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "jiji")
				.param("telefono", "543")
				.param("email", "pepe2000gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"));
	}

	@WithMockUser(value = "spring")
    @Test
	void showCliente() throws Exception {
		mockMvc.perform(get("/clientes/DetallesPerfil"))
		.andReturn().getRequest();
	}
	
	@WithMockUser(value = "spring")
    @Test
	void initUpdateForm() throws Exception {
		mockMvc.perform(get("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID))
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"))
		.andExpect(model().attributeExists("cliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/clienteDetails"));
	}
@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormSuccessAcum200() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID2)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/clienteDetails"));
	}

@WithMockUser(value = "spring")
@Test
void processUpdateCuentaFormSuccessAcumElse() throws Exception {
	mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID3)
			.with(csrf())
			.param("nombre", "Pepe")
			.param("apellidos", "escobar paez")
			.param("fechaNacimiento", "2000/07/12")
			.param("telefono", "543972343")
			.param("email", "pepe2000@gmail.com")
			.param("user.username", "escoba2000")
			.param("user.password", "escoba2000"))
	.andExpect(model().attributeExists("cliente"))
	.andExpect(view().name("clientes/clienteDetails"));
}
@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormSuccessAcum300() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID4)
				.with(csrf())
				.param("nombre", "Pepe")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "2000/07/12")
				.param("telefono", "543972343")
				.param("email", "pepe2000@gmail.com")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/clienteDetails"));
	}

	@WithMockUser(value = "spring")
    @Test
	void processUpdateCuentaFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/{cuentaId}/edit", TEST_CLIENTE_ID)
				.with(csrf())
				.param("nombre", " ")
				.param("apellidos", "escobar paez")
				.param("fechaNacimiento", "jiji")
				.param("telefono", "2343")
				.param("email", "pepe2000@gmail")
				.param("user.username", "escoba2000")
				.param("user.password", "escoba2000"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/createOrUpdateCuentaForm"));
	}
	

	@WithMockUser(value = "spring")
    @Test
	void initDeleteCuenta() throws Exception {
		mockMvc.perform(get("/clientes/{cuentaId}/delete", TEST_CLIENTE_ID))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCuentas"));
	}
	
	
}
