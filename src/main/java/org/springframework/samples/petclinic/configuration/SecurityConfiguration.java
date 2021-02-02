package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups","/welcome").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/users/**").permitAll()
				.antMatchers("/allAdministradores/**").hasAnyAuthority("administrador")
				.antMatchers("/administradores/**").hasAnyAuthority("administrador")
				.antMatchers("/allBebidas/**").hasAnyAuthority("administrador")
				.antMatchers("/bebidas/**").hasAnyAuthority("administrador")
				.antMatchers("/allCartas/**").hasAnyAuthority("administrador")
				.antMatchers("/cartas/cartaActiva").hasAnyAuthority("cliente")
				.antMatchers("/cartas/**/VerCarta").hasAnyAuthority("administrador", "cliente")
				.antMatchers("/cartas/**").hasAnyAuthority("administrador")
				.antMatchers("/allCuentas/**").hasAnyAuthority("administrador")
				.antMatchers("/clientes/new").permitAll()
				.antMatchers("/clientes/**").hasAnyAuthority("cliente")
				.antMatchers("/allCocineros/**").hasAnyAuthority("administrador")
				.antMatchers("/cocineros/**").hasAnyAuthority("administrador")
				.antMatchers("/NoEsPosibleDarDeBaja").hasAnyAuthority("administrador")
				.antMatchers("/PizzaDuplicadaEnCarta").hasAnyAuthority("administrador")
				.antMatchers("/BebidaDuplicadaEnCarta").hasAnyAuthority("administrador")
				.antMatchers("/OtroDuplicadaEnCarta").hasAnyAuthority("administrador")
				.antMatchers("/NombreDePizzaPersonalizadaDuplicado").hasAnyAuthority("cliente")
				.antMatchers("/informe/**").hasAnyAuthority("administrador")
				.antMatchers("/allIngredientes/**").hasAnyAuthority("administrador")
				.antMatchers("/Ingredientes/**").hasAnyAuthority("administrador")
				.antMatchers("/allMesas/**").hasAnyAuthority("administrador")
				.antMatchers("/mesas/**").hasAnyAuthority("administrador")
				.antMatchers("/allOfertas/**").hasAnyAuthority("administrador")
				.antMatchers("/ofertas/**").hasAnyAuthority("administrador")
				.antMatchers("/allOtros/**").hasAnyAuthority("administrador")
				.antMatchers("/Otros/**").hasAnyAuthority("administrador")
				.antMatchers("/allPedidos/**").hasAnyAuthority("administrador")
				.antMatchers("/pedidos/**").hasAnyAuthority("cliente")
				.antMatchers("/pedidos/cocinero").hasAuthority("cocinero")
				.antMatchers("/pedidos/repartidor").hasAuthority("repartidor")
				.antMatchers("/cocinero/**").hasAnyAuthority("cocinero")
				.antMatchers("/repartidor/**").hasAnyAuthority("repartidor")
				.antMatchers("/allPizzas/**").hasAnyAuthority("administrador")
				.antMatchers("/pizzas/cliente/**").hasAnyAuthority("cliente")
				.antMatchers("/pizzas/admin/**").hasAnyAuthority("administrador")
				.antMatchers("/allReclamaciones/**").hasAnyAuthority("administrador")
				.antMatchers("/reclamaciones/**").hasAnyAuthority("cliente")
				.antMatchers("/allRepartidores/**").hasAnyAuthority("administrador")
				.antMatchers("/repartidores/**").hasAnyAuthority("administrador")
				.antMatchers("/allReservas/**").hasAnyAuthority("administrador")
				.antMatchers("/reservas/**").hasAnyAuthority("cliente")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


