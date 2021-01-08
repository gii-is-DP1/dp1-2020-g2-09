package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.stereotype.Component;

@Component
public class EstadoPedidoFormatter implements Formatter<EstadoPedido> {
	private final PedidoService pedidoService;

	@Autowired
	public EstadoPedidoFormatter(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public String print(EstadoPedido estadoPedido, Locale locale) {
		return estadoPedido.getName();
	}

	@Override
	public EstadoPedido parse(String text, Locale locale) throws ParseException {
		Collection<EstadoPedido> findEstadoPedido = this.pedidoService.findEstadoPedido();
		for (EstadoPedido estadoPedido : findEstadoPedido) {
			if (estadoPedido.getName().equals(text)) {
				return estadoPedido;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
