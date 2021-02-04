<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <petclinic:layout pageName="reservas">
        <h2>Mis reservas</h2>

    <a href="/reservas/new" class="btn btn-default">Nueva reserva</a>
    
    <table id="reservasTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID de reserva</th>
           <th>Numero de Personas</th>
            <th>Fecha de la reserva</th>
            <th>Tipo de la reserva</th>
            <th>Hora de la reserva</th>
            <th>Detalles de la reserva</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservas.reservasList}" var="reserva">
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
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>