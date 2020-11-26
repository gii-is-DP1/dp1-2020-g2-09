<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="pizzas">
    <h2>Pizzas</h2>

    <table id="pizzasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Tipo Masa</th>
            <th>Ingredientes</th>
            <th>Alérgenos</th>
            

        </tr>
        <a href="/pizzas/new" class="btn btn-default">Añadir pizzas</a>
        </thead>
        <tbody>
        <c:forEach items="${pizzas.pizzasList}" var="bebida">
            <tr>
                <td>
                    <c:out value="${pizza.nombre}"/>
                </td>
                <td>
                	<c:out value="${pizza.tamaño}"/>
             	</td>
             	<td>
             		<c:out value="${pizza.coste}"/>
             	</td>
             	<td>
             		<c:out value="${pizza.tipoMasa}"/>
             	</td>

             	<td>
             		<spring:url value="/pizzas/{pizzaId}/edit" var="pizzaUrl">
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pizzaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/pizzas/{pizzaId}/delete" var="pizzaUrl2">
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(pizzaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>