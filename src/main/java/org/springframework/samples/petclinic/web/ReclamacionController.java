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


@Controller
public class ReclamacionController {

	private ReclamacionService reclamacionService;
	private UserService userService;
	private ClienteService clienteService;
	private PedidoService pedidoService;
	
	//PROBLEMA: EL CONSTRUCTOR HACE QUE ME FALLEN TODAS LAS PRUEBAS DEL RECLAMACIONCONTROLLERTESTS
	//Me dice Failed to load application context
	//Si quito el userService y el clienteService como parámetros del constructor 
	//y quito todo lo de dentro del método menos lo de reclamacionService
	//vuelve a funcionar, pero claro si lo quito después me mete 
	//un NullPointer a la hora de crear la sesión del usuario  y mostrar sus reclamaciones.
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
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
//				ReclamacionValidator ofValidator = new ReclamacionValidator();
//				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				model.put("reclamacion", reclamacion);
				model.put("pedidoId", pedidoId);
				this.reclamacionService.saveReclamacion(reclamacion);
				
				//ESTAS DOS LÍNEAS SON LAS QUE HACEN QUE EL testProcessCreationFormSuccess de fallo
				//=====================
				//FIJARME EN CARTA E INTENTAR HACERLO DE FORMA SIMILAR
				//COMO SI ESTUVIERA AÑADIENDO UNA PIZZA A LA CARTA, 
				//PERO EN VEZ DE ESO ESTOY AÑADIENDO UNA RECLAMACION AL PEDIDO
//				Integer reclamacionId=reclamacion.getId();
				
				//======================
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
				//reclamacion.setId(reclamacionId);
				model.put("reclamacion", reclamacion);
				return "reclamaciones/createOrUpdateReclamacionForm";
				
			}
			else {
//				ReclamacionValidator ofValidator = new ReclamacionValidator();
//				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				reclamacion.setId(reclamacionId);
				this.reclamacionService.saveReclamacion(reclamacion);
				return "redirect:/allReclamaciones";
			}
		}
		
		//borrar reclamacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/delete")
		public String initDeleteReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			this.reclamacionService.deleteReclamacion(reclamacion);
			return "redirect:/reclamaciones/user";
		} 
		
//		@ModelAttribute("reclamacion")
//		public Reclamacion findReclamacion(@PathVariable("reclamacionId") int reclamacionId) {
//			return this.findReclamacion(reclamacionId);
//		}
}