<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="pizzas">
    <h2>Pizzas Personalizadas</h2>

	<spring:url value="/pizzas/cliente/new" var="crearPizzas">
	         <spring:param name="cartaId" value="${cartaId}"/> 
	    </spring:url>
	    <a href="${fn:escapeXml(crearPizzas)}" class="btn btn-default">Crear pizza personalizada</a>
	  
	<spring:url value="/cartas/{cartaId}/VerCarta" var="volverACarta">
	         <spring:param name="cartaId" value="${cartaId}"/> 
	    </spring:url>
	    <a href="${fn:escapeXml(volverACarta)}" class="btn btn-default">Volver a la carta</a>
	    
	    
	<table id="pizzasPersonalizadasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Tipo Masa</th>
            <th>Ingredientes</th>
            <th>Cliente</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${PizzasP.pizzasList}" var="pizza">
            <tr>
                <td>
                    <c:out value="${pizza.nombre}"/>
                </td>
               <td>
                	<c:out value="${pizza.tamano}"/>
             	</td>
             	<td>
             		<c:out value="${pizza.coste}"/>
             	</td>
             	 <td>
             		<c:out value="${pizza.tipoMasa}"/>
             	</td>

             	
             	<td>
             		<ul>
             			<c:forEach items="${pizza.ingredientes}" var="ingrediente">
             				<li>
             					<c:out value="${ingrediente.nombre}"/>
             				</li>
             			</c:forEach>
             		</ul>
             	</td>
             	
             	<td>
             		<c:out value="${pizza.cliente.nombre}"></c:out>
             	</td>


             	<td>
             		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/edit" var="pizzaUrl">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pizzaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/delete" var="pizzaUrl2">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(pizzaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    <br/>   
    <h2>Pizzas</h2> 
    <table id="pizzasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Tipo Masa</th>
            <th>Ingredientes</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${Pizzas.pizzasList}" var="pizza">
            <tr>
                <td>
                    <c:out value="${pizza.nombre}"/>
                </td>
               <td>
                	<c:out value="${pizza.tamano}"/>
             	</td>
             	<td>
             		<c:out value="${pizza.coste}"/>
             	</td>
             	 <td>
             		<c:out value="${pizza.tipoMasa}"/>
             	</td>

             	
             	<td>
             		<ul>
             			<c:forEach items="${pizza.ingredientes}" var="ingrediente">
             				<li>
             					<c:out value="${ingrediente.nombre}"/>
             				</li>
             			</c:forEach>
             		</ul>
             	</td>


             	<td>
             		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/edit" var="pizzaUrl">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pizzaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/delete" var="pizzaUrl2">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(pizzaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>