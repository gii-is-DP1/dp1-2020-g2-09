package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.stereotype.Component;

@Component
public class TipoEnvioFormatter implements Formatter<TipoEnvio>{
	private final PedidoService pedidoService;

	@Autowired
	public TipoEnvioFormatter(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public String print(TipoEnvio tipoEnvio, Locale locale) {
		return tipoEnvio.getName();
	}

	@Override
	public TipoEnvio parse(String text, Locale locale) throws ParseException {
		Collection<TipoEnvio> findTipoEnvio = this.pedidoService.findTipoEnvio();
		for (TipoEnvio tipoEnvio : findTipoEnvio) {
			if (tipoEnvio.getName().equals(text)) {
				return tipoEnvio;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}


}