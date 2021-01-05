package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.stereotype.Component;


@Component
public class TipoPagoFormatter implements Formatter<TipoPago>{
	private final PedidoService pedidoService;

	@Autowired
	public TipoPagoFormatter(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public String print(TipoPago tipoPago, Locale locale) {
		return tipoPago.getName();
	}

	@Override
	public TipoPago parse(String text, Locale locale) throws ParseException {
		Collection<TipoPago> findTipoPago = this.pedidoService.findTipoPago();
		for (TipoPago tipoPago : findTipoPago) {
			if (tipoPago.getName().equals(text)) {
				return tipoPago;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}


}
