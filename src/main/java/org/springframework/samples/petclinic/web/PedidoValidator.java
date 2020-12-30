package org.springframework.samples.petclinic.web;


import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PedidoValidator implements Validator {
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Pedido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Pedido pedido = (Pedido) obj;
		String direccion = pedido.getDireccion();
		TipoEnvio tipoEnvio = pedido.getTipoEnvio();
		TipoPago tipoPago = pedido.getTipoPago();
//		pedido.setPrecio(0.0);
//		pedido.setFechaPedido(LocalDate.now());
		
//		Cliente cliente = pedido.getCliente();
//		Integer idCliente = cliente.getId();
	
		//direccion
		if(direccion==null) {
			errors.rejectValue("direccion",
					"La direccion no puede estar vacía", 
					"La direccion no puede estar vacía");
		}
		else if(direccion.length()<2 || direccion.length()>20) {
			errors.rejectValue("direccion",
					"La direccion debe tener de 2 a 20 caracteres", 
					"La direccion debe tener de 2 a 20 caracteres");
		}			
		
		//tipoEnvio
		if(tipoEnvio==null) {
			errors.rejectValue("tipoEnvio",
					"Elija tipo de envío", 
					"Elija tipo de envío");
		}
		
		
		
		//tipoPago
		if(tipoPago==null) {
			errors.rejectValue("tipoPago",
					"Elija tipo de pago", 
					"Elija tipo de pago");
		}
		
		
	}

}
