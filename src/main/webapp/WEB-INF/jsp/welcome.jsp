<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!-- LA SIGUIENTE LINEA ES IMPRESCINDIBLE SINO NO SALDRIA EL C:FOREACH -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<petclinic:layout pageName="home">
    <!--<h2><fmt:message key="welcome"/></h2>
    <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="logoImage"/>
            <img class="img-responsive" src="${logoImage}"/>-->
    <div class="row">
    	<h2>Bienvenido a Roto's Pizza ${title}</h2>
    	<h2>Este proyecto ha sido realizado por:  ${group}</h2>
    	<ul>
    	<c:forEach items="${persons}" var="person">
    		<li>${person.firstName}${person.lastName}</li>
    	</c:forEach>
    	</ul>	
    </div>    
    <div>
    <p><b>HISTORIAS DE USUARIO IMPLEMENTADAS.</b></p>
    </div>
    <div> 
     <a href="/allCuentas"></a>
    </div>
    <div>
     <a href="/allCocineros">H-001: Gestión de plantilla (cocineros). Pruebas hechas.</a>
    </div>
    <div>
     <a href="/allRepartidores">H-001: Gestión de plantilla (repartidores). Pruebas hechas.</a>
    </div>
     <div>
     <a href="/allMesas">H-002: Número de mesas. Faltan pruebas.</a>
    </div>
     <div>
     <a href="/allOfertas">H-004: Ofertas disponibles. Pruebas hechas.</a>
    </div>
   <sec:authorize access="hasAnyAuthority('cliente')"  >
    		<a href="/clientes/DetallesPerfil">H-005: Datos del cliente. Pruebas hechas.</a>
    	</sec:authorize>
     <div>
     	<sec:authorize access="hasAnyAuthority('cliente')"  >
      		<a href="/pedidos/user">Hola quiero ver mis pedidos.</a>
      	</sec:authorize>
     </div>
     
      <div>
     	<sec:authorize access="hasAnyAuthority('cliente')"  >
      		<a href="/reclamaciones/user">Hola quiero ver mis reclamaciones.</a>
      	</sec:authorize>
     </div>
     
      <div>
     	<sec:authorize access="hasAnyAuthority('cocinero')"  >
      		<a href="/pedidos/cocinero">Hola quiero ver mis pedidos a cocinar.</a>
      	</sec:authorize>
     </div>
      <div>
     	<sec:authorize access="hasAnyAuthority('repartidor')"  >
      		<a href="/pedidos/repartidor">Hola quiero ver mis pedidos a repartir.</a>
      	</sec:authorize>
     </div>
  
     <div>
     <a href="/allReclamaciones">H-007: Visualizar reclamaciones. Pruebas hechas.</a>
    </div>
    
    <div>
     <a href="/allReservas">H-010: Reservar una mesa. Pruebas hechas.</a>
    </div>
    
       <div>
      <p>H-012: Tamaño del producto. ¿Pruebas?</p>
    </div>
    
    <div>
     <a href="/allCartas">H-013: Acceso a la carta. ¿Pruebas?</a>
    </div>
    
    <div>
     <p>H-020: Iniciar sesión. Pruebas no implementadas puesto que son del controller.</p>
    </div>
    <sec:authorize access="hasAnyAuthority('cliente')"  >
	    <div>
	     <a href="/reclamaciones/new">H-022: Escribir reclamaciones. Pruebas hechas.</a>
	    </div>
    </sec:authorize>
    <div>
     <a href="allOfertas">H-023: Añadir ofertas. Pruebas hechas.</a>
    </div>
    
    <div>
    	<a href="allOfertas">H-024: Eliminar ofertas. Pruebas hechas.</a>
    </div>
    <br>
    
    
      
    <!--  <div>
     <a href="/allReservas">Prueba 3: muestreo, post y edit de reservas</a>
	</div>
    <div>
     <a href="/allPedidos">Prueba 4: muestreo de pedidos</a>
    </div>
    <div>
     <a href="/allAdministradores">Prueba 6: muestreo, post, edit y eliminación de administradores</a>
    </div>
    <div>
     <a href="/allReclamaciones">Prueba 8: muestreo, post y edit de reclamaciones uwu</a>
    </div>
       <div>
     <a href="/allPizzas">Prueba 9: muestreo, post y edit de pizzas </a>
    </div>
       <div>
     <a href="/allBebidas">Prueba 10: muestreo, post y edit de bebidas</a>
    </div>
	<div>
     <a href="/allCartas">Prueba 11: muestreo, post y edit de cartas</a>
    </div> -->
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pizza.jpg" htmlEscape="true" var="pizzaImagen"/>
            <img class="img-responsive" src="${pizzaImagen}"/>
        </div>
    </div>
</petclinic:layout>
