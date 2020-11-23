package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.stereotype.Component;


@Component
public class TamanoProductoFormatter implements Formatter<TamanoProducto> {

	private final OfertaService ofertaService;

	@Autowired
	public TamanoProductoFormatter(OfertaService ofertaService) {
		this.ofertaService = ofertaService;
	}

	@Override
	public String print(TamanoProducto tamanoProducto, Locale locale) {
		return tamanoProducto.getName();
	}

	@Override
	public TamanoProducto parse(String text, Locale locale) throws ParseException {
		Collection<TamanoProducto> findTamanoProducto = this.ofertaService.findTamanoProducto();
		for (TamanoProducto tamanoProducto : findTamanoProducto) {
			if (tamanoProducto.getName().equals(text)) {
				return tamanoProducto;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}

