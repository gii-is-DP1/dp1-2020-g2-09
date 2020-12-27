/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.stereotype.Component;


@Component
public class BebidaFormatter implements Formatter<Bebida> {

	private final BebidaService bebidaService;

	@Autowired
	public BebidaFormatter(BebidaService bebidaService) {
		this.bebidaService = bebidaService;
	}

	@Override
	public String print(Bebida b, Locale locale) {
		return b.getNombre();
	}

	@Override
	public Bebida parse(String text, Locale locale) throws ParseException {
		Collection<Bebida> findBebidas = this.bebidaService.findBebidas();
		for (Bebida b : findBebidas) {
			if (b.getNombre().equals(text)) {
				return b;
			}
		}
		throw new ParseException("bebida not found: " + text, 0);
	}

}