package org.springframework.samples.petclinic.web;


import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class MesaValidator implements Validator{

	private static final String REQUIRED = "requerido";
	
	public boolean supports(Class<?> clazz) {
		return Mesa.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Mesa mesa = (Mesa) obj;
		Integer capacidad= mesa.getCapacidad();

		if (capacidad <= 0 || capacidad <6 || capacidad.equals(null)) {
			errors.rejectValue("La capacidad", REQUIRED+" debe ser mayor que 0 y menor o igual que 6", 
					REQUIRED+" debe ser mayor que 0 y menor o igual que 6");
		}
		
	}
}
