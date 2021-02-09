<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<petclinic:layout pageName="clientes">
    <h2>Tus Reclamaciones</h2>

    <table id="reclamacionTable" class="table table-striped">
        <thead>
        <tr>
            <th>Descripción</th>
            <th>Respuesta<th>
            <th> ID de pedido </th>
        </tr>
        
       </thead>
        <tbody>
        <c:forEach items="${reclamaciones.reclamacionesList}" var="reclamacion">
            <tr>
               
             		<td>
             		<c:out value="${reclamacion.observacion}"></c:out>
             		</td>
             		<td>
             		<c:if test="${reclamacion.respuesta != 'Lo sentimos mucho, ...'}">
             		<c:out value="${reclamacion.respuesta}"></c:out></c:if>
             		</td>
             		
             		<td>
             		
             		
             		<c:out value="${reclamacion.id}"></c:out>
             		</td>
             		
             	<c:if test="${empty reclamacion.respuesta || reclamacion.respuesta == 'Lo sentimos mucho, ...'}">
             	<td>
             		<spring:url value="/reclamaciones/{reclamacionId}/edit" var="reclamacionUrl">
	                        <spring:param name="reclamacionId" value="${reclamacion.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(reclamacionUrl)}" class="btn btn-default">Responder</a>
             	</td>
             	</c:if>
             	
             	
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>