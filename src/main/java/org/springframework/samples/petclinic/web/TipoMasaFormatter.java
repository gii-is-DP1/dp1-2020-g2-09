package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Component;


@Component
public class TipoMasaFormatter implements Formatter<tipoMasa> {

    private final PizzaService pizzaService;

    @Autowired
    public TipoMasaFormatter(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Override
    public String print(tipoMasa tipoMasa, Locale locale) {
        return tipoMasa.getName();
    }

    @Override
    public tipoMasa parse(String text, Locale locale) throws ParseException {
        Collection<tipoMasa> findTipoMasa = this.pizzaService.findTipoMasa();
        for (tipoMasa tipoMasa : findTipoMasa) {
            if (tipoMasa.getName().equals(text)) {
                return tipoMasa;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}