package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reclamaciones;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReclamacionController {

	private final ReclamacionService reclamacionService;
	private final PedidoService pedidoService;

	
	@Autowired
	public ReclamacionController(ReclamacionService reclamacionService,PedidoService pedidoService) {
		this.reclamacionService = reclamacionService;
		this.pedidoService = pedidoService;

	}
	
	@InitBinder("reclamacion")
	public void initReclamacionBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReclamacionValidator());
	}
	
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
	
	@GetMapping(value = { "/reclamaciones/{clienteId}" })
		public String showReclamacionListDeUnCliente(Map<String, Object> model, @PathVariable("clienteId") int clienteId) {
			Reclamaciones reclamaciones = new Reclamaciones();
			List<Integer> l =this.reclamacionService.findPedidosConReclamacionesDeUnCliente(clienteId);
			for(int i=0;i<l.size();i++) {
				reclamaciones.getReclamacionesList().add(reclamacionService.findReclamacionById(l.get(i)));
			}
			//reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
			//List<Integer> pedidosConReclamaciones = this.reclamacionService.findPedidosConReclamaciones();
			//model.put("pedidosReclamaciones", pedidosConReclamaciones);
			model.put("reclamaciones", reclamaciones);
			return "reclamaciones/reclamacionesUser";
		} 
	
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
		public String processCreationForm(@PathVariable("pedidoId") int pedidoId, @Valid Reclamacion reclamacion, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				model.put("reclamacion", reclamacion);
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
				ReclamacionValidator ofValidator = new ReclamacionValidator();
				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				this.reclamacionService.saveReclamacion(reclamacion);
				Cliente c=pedidoService.findPedidoById(pedidoId).getCliente();
				Integer reclamacionId=reclamacion.getId();
				this.reclamacionService.añadirReclamacionAPedido(pedidoId, reclamacionId);
				return "redirect:/reclamaciones/"+c.getId().toString();//SI QUIERES VER TODAS LAS RECLAMACIONES "redirect:/allReclamaciones"
			} //SI QUIERES VER LAS RECLAMACIONES DE UN CLIENTE "redirect:/reclamaciones/"+c.getId().toString();
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
				reclamacion.setId(reclamacionId);
				model.put("reclamacion", reclamacion);
				return "reclamaciones/createOrUpdateReclamacionForm";
				
			}
			else {
				ReclamacionValidator ofValidator = new ReclamacionValidator();
				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				reclamacion.setId(reclamacionId);
				this.reclamacionService.saveReclamacion(reclamacion);
				return "redirect:/allReclamaciones";
			}
		}
		
		/* //borrar reclamacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/delete")
		public String initDeleteReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			this.reclamacionService.deleteReclamacion(reclamacion);
			return "redirect:/allReclamaciones";
		} */
		
//		@ModelAttribute("reclamacion")
//		public Reclamacion findReclamacion(@PathVariable("reclamacionId") int reclamacionId) {
//			return this.findReclamacion(reclamacionId);
//		}
}