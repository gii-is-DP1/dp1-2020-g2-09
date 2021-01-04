<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <petclinic:layout pageName="clientes">
    <h2>Mesas disponibles</h2>
    <p>Seleccione una mesa de la siguiente lista. Es probable que no haya mesas disponibles. En ese caso, 
    seleccione otra hora para realizar la reserva.</p>
    
    <table id="mesasDisponibles" class="table table-striped">
        <thead>
        <tr>
        	<th>Nº Mesa</th>
        	<th>Capacidad</th>
        	<th> Reservar mesa </th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mesasPorCapacidad}" var="mesa">
            <tr>
               <td>
             		<c:out value="${mesa.id}"></c:out>
             	</td>
             	<td>
             		<c:out value="${mesa.capacidad}"></c:out>
             	</td>
             	
             	<td>					
             	
             		<spring:url value="/reservas/{reservaId}/allMesasDisponibles/{mesaId}" var="reservamesaUrl3">
	                        <spring:param name="reservaId" value="${reservaId}"/> 
	                        <spring:param name="mesaId" value="${mesa.id}"/> 
	                </spring:url>
   					<a href="${fn:escapeXml(reservamesaUrl3)}" class="btn btn-default">Reservar mesa</a>
             	</td>
             
</tr>
</c:forEach>
</tbody>
</table>

</petclinic:layout>