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
    	<p><h2>Este proyecto ha sido realizado por:  ${group}</h2></p>	
    	<p><ul>
    	<c:forEach items="${persons}" var="person">
    		<li>${person.firstName}${person.lastName}</li>
    	</c:forEach>
    	</ul></p>	
    </div>    
    <div>
    	<sec:authorize access="isAuthenticated()" >
    		<a href="/cuentas/DetallesPerfil">Aquí encontrará sus datos si ha iniciado sesión</a>
    	</sec:authorize>
    </div>
    <div>
     <a href="/allCuentas">Prueba 1: muestreo, post, edit y eliminación de clientes</a>
    </div>
    <div>
     <a href="/allCocineros">Prueba 2: muestreo, post y edit de cocineros</a>
    </div>
     <div>
     <a href="/allReservas">Prueba 3: muestreo, post y edit de reservas</a>
	</div>
    <div>
     <a href="/allPedidos">Prueba 4: muestreo de pedidos</a>
    </div>
    <div>
     <a href="allRepartidores">Prueba 5: muestreo, post, edit y eliminación de repartidores</a>
    </div>
    <div>
     <a href="allAdministradores">Prueba 6: muestreo, post, edit y eliminación de administradores</a>
    </div>
    <div>
     <a href="/allMesas">Prueba 7: muestreo, post y edit de mesas :)</a>
    </div>
    <div>
     <a href="/allReclamaciones">Prueba 8: muestreo, post y edit de reclamaciones uwu</a>
    </div>

    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pizza.jpg" htmlEscape="true" var="pizzaImagen"/>
            <img class="img-responsive" src="${pizzaImagen}"/>
        </div>
    </div>
</petclinic:layout>
