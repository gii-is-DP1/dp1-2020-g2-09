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
    <h2>Datos de la reserva</h2>
    <p> El cliente <strong><c:out value="${cliente.nombre} "> </c:out> <c:out value="${cliente.apellidos}"> </c:out></strong>
     con nombre de usuario <strong><c:out value="${usuario.username}"> </c:out></strong> ha realizado la siguiente reserva:</p>
    <br>
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
        <td> <c:out value="${reserva.id}"></c:out> </td>
        <td> <c:out value="${reserva.fechaReserva}"></c:out> </td>
        <td> <c:out value="${reserva.tipoReserva}"></c:out> </td>
        <td> <c:out value="${reserva.hora}"></c:out> </td>
        <td> <c:out value="${reserva.numeroPersonas}"></c:out> </td>
        </tr>
        </tbody>
    </table>
    
    <h2>Mesa reservada</h2>
    <table id="mesasDisponibles" class="table table-striped">
        <thead>
        <tr>
        	<th>Nº Mesa</th>
        	<th>Capacidad</th>

      </tr>
        </thead>
        <tbody>
            <tr>
               <td>
             		<c:out value="${mesa.id}"></c:out>
             	</td>
             	<td>
             		<c:out value="${mesa.capacidad}"></c:out>
             	</td>
             
</tr>
</tbody>
</table>

</petclinic:layout>