package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class InformeController {

	
	private final IngredienteService IngredienteService;
	private final PizzaService PizzaService;
	private final MesaService mesaService;
	private final PedidoService pedidoService;

	@Autowired
	public InformeController(IngredienteService IngredienteService, PizzaService PizzaService,MesaService mesaService, PedidoService pedidoService) {
		this.mesaService = mesaService;
		this.IngredienteService = IngredienteService;
		this.PizzaService = PizzaService;
		this.pedidoService = pedidoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/informe")
	public String initList(Map<String, Object> model) {
		log.info("Mostrando lista de informes");
		return "informe/InformeList";
	}
	
	@GetMapping(value = "/informe/IngredientesMasUsados")
	public String informeIngredientesMasUsados(Map<String, Object> model) {
		List<Ingrediente> l = this.IngredienteService.findIngredientes();
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		int minValue = 0;
		String keyValue = "";
		for(Ingrediente i:l) {
			Integer aux = this.IngredienteService.CountIngrediente(i.getId());
			if(aux != 0) {
				int tamMap = mapa.size(); 
				if(tamMap != 10) {
					mapa.put(i.getNombre(), aux);
					if(aux>minValue) {
						keyValue = i.getNombre();
						minValue = aux;
					}
				}else {
					if(aux>minValue) {
						mapa.remove(keyValue);
						mapa.put(i.getNombre(), aux);
						keyValue = i.getNombre();
						minValue = aux;
					}
				}
				
			}
		}
		Map<String, Integer> mapaOrdered =mapa.entrySet().stream()
		.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		model.put("mapa", mapaOrdered);
		log.info("Mostrando informe ingredientes mas usados");
		return "informe/InformeIngredientesMasUsados";
	}
	@GetMapping(value = "/informe/MesasMasUsadas")
	public String informeMesasMasUsadas(Map<String, Object> model) {
		List<Mesa> l = this.mesaService.findMesas();
		Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		for(Mesa i:l) {
			Integer aux = this.mesaService.CountMesa(i.getId());
			mapa.put(i.getId(), aux);
		}
		model.put("mapa", mapa);
		log.info("Mostrando informe mesas mas usadas");
		return "informe/InformeMesasMasUsadas";
	}
	@GetMapping(value = "/informe/CaducidadIngredientes")
	public String informeCaducidadIngredientes(Map<String, Object> model) {
		List<Ingrediente> l = this.IngredienteService.findIngredientes();
		Map<Ingrediente, LocalDate> mapa = new HashMap<Ingrediente, LocalDate>();
		for(Ingrediente i:l) {
			LocalDate aux = i.getFechaCaducidad();
			mapa.put(i, aux);
		}
		Map<Ingrediente,LocalDate> mapaOrdenado=mapa.entrySet().stream()
				.sorted((Map.Entry.<Ingrediente, LocalDate>comparingByValue()))
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		model.put("mapa", mapaOrdenado);
		log.info("Mostrando informe caducidad ingredientes");

		return "informe/CaducidadIngredientes";
	}
	
	@GetMapping(value = "/informe/PizzasMasPedidos")
	public String informePizzasMasPedidos(Map<String, Object> model) {
		List<Pedido> l = this.pedidoService.findPedidos();
		Map<Pizza, Integer> pizzaMap = new HashMap<Pizza, Integer>();
		for (Pedido pedido: l) {
			Collection<Pizza> pizzasEnPedido = pedido.getPizzasEnPedido();
			for (Pizza p:pizzasEnPedido) {
				if(pizzaMap.containsKey(p)) {
					Integer contador = pizzaMap.get(p) +1;
					pizzaMap.put(p, contador);
					
				}else {
					pizzaMap.put(p, 1);
				}
			}
			Collection<Oferta> ofertasEnPedido = pedido.getOfertasEnPedido();
			for(Oferta o: ofertasEnPedido) {
				List<Pizza> lPizza = o.getPizzasEnOferta();
				for (Pizza p:lPizza) {
					if(pizzaMap.containsKey(p)) {
						Integer contador = pizzaMap.get(p) +1;
						pizzaMap.put(p, contador);
						
					}else {
						pizzaMap.put(p, 1);
					}
				}
			}
			
		}
	
		Map<Pizza, Integer> mapaOrdenado=pizzaMap.entrySet().stream()
				.sorted((Map.Entry.<Pizza, Integer>comparingByValue().reversed()))
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		model.put("mapa", mapaOrdenado);
		
		log.info("Monstrando informe de las pizzas mas pedidas");

		return "informe/InformePizzasMasPedidas";
	}
}