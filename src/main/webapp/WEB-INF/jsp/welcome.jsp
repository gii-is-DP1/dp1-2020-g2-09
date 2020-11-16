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
    <h2><fmt:message key="welcome"/></h2>
    <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="logoImage"/>
            <img class="img-responsive" src="${logoImage}"/>
    <div class="row">
    	<h2>Project ${title}</h2>
    	<p><h2>Group ${group}</h2></p>	
    	<p><ul>
    	<c:forEach items="${persons}" var="person">
    		<li>${person.firstName}${person.lastName}</li>
    	</c:forEach>
    	</ul></p>	
    </div>    
    
    <div>
     <a href="/allCuentas">Prueba 1: muestreo, post, edit y eliminación de clientes</a>
    </div>
    <div>
     <a href="/allCocineros">Prueba 2: muestreo, post, edit y eliminación de cocineros</a>
    </div>
    <div>
<<<<<<< HEAD
     <a href="/allPedidos">Prueba 3: muestreo de pedidos</a>
    </div>
    
    
=======
     <a href="allRepartidores">Prueba 3: muestreo, post, edit y eliminación de repartidores</a>
    </div>
    <div>
     <a href="allAdministradores">Prueba 4: muestreo, post, edit y eliminación de administradores</a>
    </div>
    <div>
     <a href="/allMesas">Prueba 3: muestreo, post y edit de mesas :)</a>
    </div>
    <div>
     <a href="/allReclamaciones">Prueba 5: muestreo, post y edit de reclamaciones uwu</a>
    </div>
>>>>>>> refs/remotes/origin/master
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
