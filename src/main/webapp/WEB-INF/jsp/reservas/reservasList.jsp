<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="reservas">
    <h2>Reservas</h2>

    <table id="reservasTable" class="table table-striped">
        <thead>
        <tr>
        	<th>ID de la reserva </th>
            <th>Número de Personas</th>
            <th>Fecha de la reserva</th>
            <th>Tipo de la reserva</th>
            <th>Hora de la reserva</th>
            <th> Detalles de la reserva</th>
            <th>Editar</th>
            <th>Eliminar</th>
            
        </tr>
           <a href="/reservas/new" class="btn btn-default">Añadir reserva</a>
        </thead>
        <tbody>
        <c:forEach items="${reservas}" var="reserva">
            <tr>
            <td>
                    <c:out value="${reserva.id}"/>
                </td>
                <td>
                    <c:out value="${reserva.numeroPersonas}"/>
                </td>
                <td>
                    <c:out value="${reserva.fechaReserva}"/>
                </td>
                <td>
                    <c:out value="${reserva.tipoReserva}"/>
                </td>
                <td>
                    <c:out value="${reserva.hora}"/>
                </td>
                 <td>
             		<spring:url value="/reservas/{reservaId}/verDetalles" var="reservaUrl">
	                        <spring:param name="reservaId" value="${reserva.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(reservaUrl)}" class="btn btn-default">Ver detalles</a>
             	</td>
                <td>
             		<spring:url value="/reservas/{reservaId}/edit" var="reservaUrl">
	                        <spring:param name="reservaId" value="${reserva.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(reservaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/reservas/{reservaId}/delete" var="reservaUrl2">
	                        <spring:param name="reservaId" value="${reserva.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(reservaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <%--  <td>					
             		<spring:url value="/reservas/{reservaId}/allMesasDisponibles" var="reservamesaUrl">
	                        <spring:param name="reservaId" value="${reserva.id}"/> 
	                </spring:url>
   					<a href="${fn:escapeXml(reservamesaUrl)}" class="btn btn-default">Seleccionar mesa</a>
             	</td> --%>

    <table class="table-buttons">
    </table>
</petclinic:layout>