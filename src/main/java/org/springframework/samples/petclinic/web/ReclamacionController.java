package org.springframework.samples.petclinic.web;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reclamaciones;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReclamacionController {

	private ReclamacionService reclamacionService;
	private UserService userService;
	private ClienteService clienteService;
	private PedidoService pedidoService;

	
	@Autowired
	public ReclamacionController(ReclamacionService reclamacionService, 
			UserService userService, ClienteService clienteService, PedidoService pedidoService) {
		this.reclamacionService = reclamacionService;
			this.userService = userService;
			this.clienteService = clienteService;
			this.pedidoService = pedidoService;
	}
	
	@InitBinder("reclamacion")
	public void initReclamacionBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReclamacionValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
//	@GetMapping(value = { "/allReclamaciones" })
//	
//	public String showReclamacionList(Map<String, Object> model) {
//		Reclamaciones reclamaciones = new Reclamaciones();
//		reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findPedidosConReclamaciones());
//		//reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
//		//List<Integer> pedidosConReclamaciones = this.reclamacionService.findPedidosConReclamaciones();
//		//model.put("pedidosReclamaciones", pedidosConReclamaciones);
//		model.put("reclamaciones", reclamaciones);
//		return "reclamaciones/reclamacionesList";
//	} 
	
	@GetMapping(value = { "/allReclamaciones" })
	public String showReclamacionList(Map<String, Object> model) {
		Reclamaciones reclamaciones = new Reclamaciones();
		List<Integer> l =this.reclamacionService.findPedidosConReclamaciones();
		for(int i=0;i<l.size();i++) {
			reclamaciones.getReclamacionesList().add(reclamacionService.findReclamacionById(l.get(i)));
		}
		//reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
		//List<Integer> pedidosConReclamaciones = this.reclamacionService.findPedidosConReclamaciones();
		//model.put("pedidosReclamaciones", pedidosConReclamaciones);
		model.put("reclamaciones", reclamaciones);
		log.info("Mostrando listado de reclamaciones.");
		return "reclamaciones/reclamacionesList";
	}
//	
	//para ver las reclamaciones del cliente que ha iniciado sesión
	//CORREGIR, MUESTRA MAL EL ID DE PEDIDO (MUESTRA EL ID DE LA RECLAMACIÓN).
		@GetMapping("/reclamaciones/user")
		public String showMisReclamaciones(Map<String, Object> model) {
			Reclamaciones reclamaciones = new Reclamaciones();
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
			  userDetails = (UserDetails) principal;
			}
			String userName = userDetails.getUsername();
		    User usuario = this.userService.findUser(userName).get();
		    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
		    
		    List<Integer> l =this.reclamacionService.
		    		findPedidosConReclamacionesDeUnCliente(cliente.getId());
		    for(int i=0;i<l.size();i++) {
		    	reclamaciones.getReclamacionesList().add(reclamacionService.
		    			findReclamacionById(l.get(i)));
		    }
		    //reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findPedidosConReclamacionesDeUnCliente(cliente.getId()));
			model.put("reclamaciones", reclamaciones);
			log.info("Mostrando reclamaciones de un usuario que ha iniciado sesión.");
			return "reclamaciones/reclamacionUser";
		} 
		
	//Método de Parri -> No sé para qué sirve, preguntarle.//Esto coge el id del cliente y filtra las reclamaciones para un cliente 
		
//	@GetMapping(value = { "/reclamaciones/{clienteId}" })
//	public String showReclamacionListDeUnCliente(Map<String, Object> model, @PathVariable("clienteId") int clienteId) {
//		Reclamaciones reclamaciones = new Reclamaciones();
//		List<Integer> l =this.reclamacionService.findPedidosConReclamacionesDeUnCliente(clienteId);
//		for(int i=0;i<l.size();i++) {
//			reclamaciones.getReclamacionesList().add(reclamacionService.findReclamacionById(l.get(i)));
//		}
//		//reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
//		//List<Integer> pedidosConReclamaciones = this.reclamacionService.findPedidosConReclamaciones();
//		//model.put("pedidosReclamaciones", pedidosConReclamaciones);
//		model.put("reclamaciones", reclamaciones);
//		return "reclamaciones/reclamacionesUser";
//	}
	
		//Aqui tenemos que añadir la reclamación sobre un pedido seleccionado
		@GetMapping("/pedidos/{pedidoId}/anadirReclamacion/new")
		public String initCreationForm(Map<String, Object> model, @PathVariable("pedidoId") int pedidoId) {
			Reclamacion reclamacion = new Reclamacion();
			reclamacion.setRespuesta("Lo sentimos mucho, ...");
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar nueva reclamacion
		@PostMapping(value = "/pedidos/{pedidoId}/anadirReclamacion/new")
		public String processCreationForm(@Valid Reclamacion reclamacion, BindingResult result, @PathVariable("pedidoId") int pedidoId, ModelMap model) {
			if (result.hasErrors()) {
				model.put("reclamacion", reclamacion);
				log.warn("Error a la hora de crear una reclamación.");
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
				model.put("pedidoId", pedidoId);
				this.reclamacionService.saveReclamacion(reclamacion);
				log.info("Añadiendo reclamación a pedido.");
				return "reclamaciones/confirmarReclamacion";
			} 
		}
		
		@GetMapping(value = "/pedidos/{pedidoId}/reclamaciones/{reclamacionId}/confirmarReclamacion")
		public String anadirReclamacionAPedido(@PathVariable("pedidoId") int pedidoId, @PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			this.reclamacionService.anadirReclamacionAPedido(pedidoId, reclamacionId);
			return "redirect:/reclamaciones/user";
		}
		
		
		@GetMapping(value ="/reclamaciones/{reclamacionId}/verDetalles")
		public String showDetallesReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			model.put("reclamacion", reclamacion);
			
			//Tomo el pedido
			Integer pedidoId = this.pedidoService.findIdPedidoByReclamacionId(reclamacionId);
			Pedido pedido = this.pedidoService.findPedidoById(pedidoId);
			model.put("pedido", pedido);
			Cliente cliente = pedido.getCliente();
			User usuario = cliente.getUser();
			model.put("cliente", cliente);
			model.put("usuario", usuario);
			log.info("Mostrando detalles de la reclamación.");
			return "reclamaciones/verDetallesReclamacion";
		}
		
		//iniciar actualizacion -> Administrador responde reclamación
		@GetMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String initUpdateForm(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			reclamacion.setRespuesta("");
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar actualizacion
		@PostMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String processUpdateReclamacionForm(@Valid Reclamacion reclamacion, BindingResult result,
				@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			if (result.hasErrors()) {
				model.put("reclamacion", reclamacion);
				log.warn("Error a la hora de actualizar una reclamación.");
				return "reclamaciones/createOrUpdateReclamacionForm";
				
			}
			else {
				reclamacion.setId(reclamacionId);
				this.reclamacionService.saveReclamacion(reclamacion);
				log.info("Reclamación actualizada correctamente.");
				return "redirect:/allReclamaciones";
			}
		}
		
		//borrar reclamacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/delete")
		public String initDeleteReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			this.reclamacionService.deleteReclamacion(reclamacion);
			log.info("Reclamación eliminada correctamente.");
			return "redirect:/reclamaciones/user";
		} 
		
}