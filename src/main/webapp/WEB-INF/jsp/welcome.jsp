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
    <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como administrador(admin1 -> admin1)).</b></p> </div>
    <sec:authorize access="hasAnyAuthority('administrador')"  >
    <div> <a href="/allCuentas">Cuentas</a></div>
    <div><a href="/allCocineros">H-001: Gestión de plantilla (cocineros).</a></div>
    <div><a href="/allRepartidores">H-001: Gestión de plantilla (repartidores).</a></div>
     <div> <a href="/allMesas">H-002: Número de mesas.</a></div>
     <div><a href="/informe/IngredientesMasUsados">H-003: Informe de ingredientes.</a></div>
     <div><a href="/allOfertas">H-004: Ofertas disponibles, H-023: Modificar Ofertas, H-024: Eliminar Ofertas</a></div>
     <div><a href="/informe/CaducidadIngredientes">H-006: Informe de caducidad.</a></div>
     <div><a href="/allReclamaciones">H-007: Visualizar reclamaciones, H-027: Responder reclamación</a></div>
     <div><a href="/allCartas">H-008: Actualizaciones en la carta, H-030: Contenido de carta</a></div>
     <div><a >H-019: Pizzas más vendidas (NO ESTÁ HECHO)</a></div>
     <div><a >H-020: Iniciar sesión(HECHO)</a></div>
     <div><a href="/informe/MesasMasUsadas">H-021: Informe de mesas más reservadas.</a></div>
     </sec:authorize>
     
     <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como cliente(margarcac1 -> margarcac1)).</b></p> </div>
   <sec:authorize access="hasAnyAuthority('cliente')"  >
    		<div><a href="/clientes/DetallesPerfil">H-005: Datos del cliente.</a></div>
    		<div><a href="/pedidos/user">H-009:Pedidos a domicilio, H-011:Personalizar pizzas, H-012:Tamaño del producto, H-014:Pago del pedido, H-015:Recogida del pedido, H-016:Estado del pedido, H-017:Cancelar pedido</a></div>
    		<div><a href="/reservas/user">H-010: Reservar una mesa</a></div>
    		<div><a href="/allCartas">H-013: Acceder a la carta, H-028: Pizzas en oferta, H-029: Ingredientes de Pizzas</a></div>
    		<div><a href="/reclamaciones/user">H-022: Realizar reclamaciones</a></div>
    		
    	</sec:authorize>

     <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como cocinero(cocinero1 -> cocinero1)).</b></p> </div>
      <div>
     	<sec:authorize access="hasAnyAuthority('cocinero')"  >
      		<a href="/pedidos/cocinero">H-018: Control de elaboración del pedido, H-025: Control de pedido(cocinero), H-031: Elaboración de pizzas</a>
      	</sec:authorize>
     </div>
      
      
      <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como repartidor(repartidor1 -> repartidor1)).</b></p> </div>
      <div>
     	<sec:authorize access="hasAnyAuthority('repartidor')"  >
      		<a href="/pedidos/repartidor">H-018: Control de elaboración del pedido, H-025: Control de pedido(repartidor)</a>
      	</sec:authorize>
     </div>
 
    
    
      
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
