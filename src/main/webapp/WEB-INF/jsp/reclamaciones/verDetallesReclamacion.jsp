<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <petclinic:layout pageName="reclamaciones">
    <h2>Datos de la reclamación</h2>
   <sec:authorize access="hasAnyAuthority('administrador')"  > 
   <p> El cliente <strong><c:out value="${cliente.nombre} "> </c:out> <c:out value="${cliente.apellidos}"> </c:out></strong>
     con nombre de usuario <strong><c:out value="${usuario.username}"> </c:out></strong> ha escrito la siguiente reclamación:</p>
    <br>
    </sec:authorize>
    <table id="datosReclamacion" class ="table table-striped">
     <thead>
        <tr>
        	<th>ID de la reclamación</th>
           <th>Descripción</th>
             <th>Respuesta</th>
        </tr>
        </thead>
        <tbody>
       <tr> 
        <td> <c:out value="${reclamacion.id}"></c:out> </td>
        <td> <c:out value="${reclamacion.observacion}"></c:out> </td>
       <td> <c:if test="${reclamacion.respuesta != 'Lo sentimos mucho, ...'}">
             		<c:out value="${reclamacion.respuesta}"></c:out> </c:if>
             	</td>
        </tbody>
    </table>
    
    <h2>Datos del pedido</h2>
    <table id="informacionPedido" class="table table-striped">
        <thead>
        <tr>
        	<th> ID de pedido </th>
        	<th>Precio</th>
            <th>Gastos de Envio</th>
            <th>Direccion</th>
            <th>Fecha de pedido</th>
            <th>Estado pedido</th>
            <th>Tipo Envio</th>
            <th>Tipo Pago</th>

      </tr>
        </thead>
        <tbody>
            <tr>
            <td>
            <c:out value="${pedido.id}"></c:out></td>
               <td>
             		<c:out value="${pedido.precio}"></c:out>
             	</td>
             	<td>
             		<c:out value="${pedido.gastosEnvio}"></c:out>
             	</td>
             	<td>
             		<c:out value="${pedido.direccion}"/>
             	</td>
             	<td>
             		<c:out value="${pedido.fechaPedido}"/>
             	</td>
             	<td>
             		<c:out value="${pedido.estadoPedido}"></c:out>
             	</td> 
             	<td>
             		<c:out value="${pedido.tipoEnvio}"></c:out>
             	</td>
             	<td>
             		<c:out value="${pedido.tipoPago}"></c:out>
             	</td>
             
</tr>
</tbody>
</table>
				<sec:authorize access="hasAnyAuthority('cliente')"  > 
					<spring:url value="/reclamaciones/user" var="reclamacionUrl">
	                </spring:url>
   					<a href="${fn:escapeXml(reclamacionUrl)}" class="btn btn-default">Volver</a>
				</sec:authorize>
				
				<sec:authorize access="hasAnyAuthority('administrador')"  > 
					<spring:url value="/allReclamaciones" var="reclamacionUrl2">
	                </spring:url>
   					<a href="${fn:escapeXml(reclamacionUrl2)}" class="btn btn-default">Volver</a>
				</sec:authorize>
</petclinic:layout>