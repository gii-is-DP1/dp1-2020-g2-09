<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <petclinic:layout pageName="mesas">
    <h2>Reservar mesa</h2>
    <p>A continuación se muestran los datos de su reserva y las mesas disponibles que puede reservar.</p>
    
    <table id="datosReserva" class ="table table-striped">
     <thead>
        <tr>
        	<th>ID de la reserva</th>
        	<th>Fecha de la reserva</th>
           <th>Tipo de la reserva</th>
             <th>Hora de la reserva</th>
            <th>Número de personas</th>

        </tr>
        </thead>
        <tbody>
       <tr> 
        <td> <c:out value="${miReserva.id}"></c:out> </td>
        <td> <c:out value="${miReserva.fechaReserva}"></c:out> </td>
        <td> <c:out value="${miReserva.tipoReserva}"></c:out> </td>
        <td> <c:out value="${miReserva.hora}"></c:out> </td>
        <td> <c:out value="${miReserva.numeroPersonas}"></c:out> </td>
        </tr>
        </tbody>
    </table>
    

    <p> Estas son las mesas disponibles. Si no hay mesas disponibles, pulse el siguiente botón, 
     modifique los datos de su reserva y vuelva a intentarlo.</p>
     
     <spring:url value="/reservas/{reservaId}/edit" var="editmesaUrl">
	                        <spring:param name="reservaId" value="${reservaId}"/> 
	                </spring:url>
	                <a href="${fn:escapeXml(editmesaUrl)}" class="btn btn-default">Modificar reserva</a>
	                
	  <h2>Mesas disponibles</h2>
    <table id="mesasDisponibles" class="table table-striped">
        <thead>
        <tr>
        	<th>Nº Mesa</th>
        	<th>Capacidad</th>
        	<th> Reservar mesa </th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mesasDisponiblesSolucion}" var="mesa">
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