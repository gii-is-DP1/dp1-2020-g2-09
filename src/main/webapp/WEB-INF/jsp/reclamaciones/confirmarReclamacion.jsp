<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <petclinic:layout pageName="reclamaciones">
    <h2>Detalles de la reclamación</h2>

    <table id="reclamacionTable" class="table table-striped">
        <thead>
        <tr>
            <th>Descripción</th>
            <th> ID de pedido </th>
        </tr>
        
      </thead>
        <tbody>

            <tr>
             		<td>
             		<c:out value="${reclamacion.observacion}"></c:out>          		
					</td>
					
					<td>
             		<c:out value="${pedidoId}"></c:out>          		
					</td>



 </tbody>
    </table>
    
     <spring:url value="/pedidos/{pedidoId}/reclamaciones/{reclamacionId}/confirmarReclamacion" var="mandarreclamacionUrl">
	                        <spring:param name="pedidoId" value="${pedidoId}"/>
	                        <spring:param name="reclamacionId" value="${reclamacion.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(mandarreclamacionUrl)}" class="btn btn-default">Confirmar reclamación</a>
    
   					
   	<spring:url value="/reclamaciones/{reclamacionId}/delete" var="deletereclamacionUrl">
	                        <spring:param name="reclamacionId" value="${reclamacion.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(deletereclamacionUrl)}" class="btn btn-default">Cancelar reclamación</a>
    
    <p> Pulse el botón confirmar reclamación para enviar la reclamación sobre su pedido. En otro caso, pulse el botón cancelar reclamación.
    Recibirás una respuesta en un plazo de 48-72h. Si no es así, por favor contacta con nosotros en rotospizzeria@pizzas.com</p>
    

</petclinic:layout>