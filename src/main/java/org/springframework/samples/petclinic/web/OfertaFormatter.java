package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.stereotype.Component;

@Component
public class OfertaFormatter implements Formatter<Oferta>{
private final OfertaService rs;
	
	@Autowired
	public OfertaFormatter(OfertaService ofertaService) {
		this.rs = ofertaService;
	}
	@Override
	public String print(Oferta object, Locale locale) {
		return String.valueOf(object.getId());
	}

	@Override
	public Oferta parse(String text, Locale locale) throws ParseException {
		Collection<Oferta> findOferta = this.rs.findOfertas();
		for(Oferta o:findOferta) {
			if(String.valueOf(o.getId()).equals(text)) {
				return o;
			}
		}
		throw new ParseException("Oferta not found: " + text, 0);
	}
	
	public Oferta parse2(Integer i, Locale locale) throws ParseException {
		Collection<Oferta> findOferta = this.rs.findOfertas();
		for(Oferta o:findOferta) {
			if(o.getId()==i) {
				return o;
			}
		}
		throw new ParseException("Oferta not found: " + i, 0);
	}
}