<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="cartas">
    <h2>Cartas</h2>
<a href="/cartas/new" class="btn btn-default">Añadir carta</a>
    <table id="cartasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Pizzas</th>
            <th>Bebidas</th>
            <th>Otros</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartas}" var="cartas">
            <tr>
                <td>
                    <c:out value="${cartas.fecha}"/>
                </td>
                <td></td>
                <td>
                    <c:out value="${reserva.cartaDePizzas}"/> <!-- hay que cambiarlo -->
                </td>
                <td>
                    <c:out value="${reserva.cartaDeBebidas}"/><!-- hay que cambiarlo -->
                </td>
					<c:out value="${reserva.cartaDeOtros}"/><!-- hay que cambiarlo -->
                <td>
             		<spring:url value="/cartas/{cartaId}/edit" var="cartaUrl">
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(cartaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/delete" var="cartaUrl2">
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(cartaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>    
        </tr>
    </table>
</petclinic:layout>