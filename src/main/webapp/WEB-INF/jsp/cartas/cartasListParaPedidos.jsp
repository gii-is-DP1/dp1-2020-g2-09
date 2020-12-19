<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="cartas">
    <h2>Cartas</h2>
<a href="/cartas/new" class="btn btn-default">Añadir carta</a>
    <table id="cartasTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Nombre</th>
            <th>Fecha</th>
        </tr>
        </thead>
        <tbody>
            <tr>
            	<td>
            		<c:out value="${cartas.nombre}"/>
            	</td>
                <td>
                    <c:out value="${cartas.fechaCreacion}"/>
                </td>
                <td>
                	<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta" var="cartaUrl99">
                			<spring:param name="pedidoId" value="${pedido.id}"/>
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(cartaUrl99)}" class="btn btn-default">Acceder a la carta</a>
                </td>
            </tr>
        
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>    
        </tr>
    </table>
</petclinic:layout>