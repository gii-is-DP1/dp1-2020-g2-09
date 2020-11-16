<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="clientes">
    <h2>Reclamaciones de clientes</h2>

    <table id="reclamacionTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Fecha de la reclamación</th>
            <th>Descripción</th>
        </tr>
        
        <a href="/reclamaciones/new" class="btn btn-default">Añadir reclamación</a>
        </thead>
        <tbody>
        <c:forEach items="${reclamaciones.reclamacionesList}" var="reclamacion">
            <tr>
               <td>
             		<c:out value="${reclamacion.fechaReclamacion}"></c:out>
             		</td>
             		<td>
             		<c:out value="${reclamacion.observacion}"></c:out>
             		</td>
             	<td>
             		<spring:url value="/reclamaciones/{reclamacionId}/edit" var="reclamacionUrl">
	                        <spring:param name="reclamacionId" value="${reclamacion.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(reclamacionUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/reclamaciones/{reclamacionId}/delete" var="reclamacionUrl2">
	                        <spring:param name="reclamacionId" value="${reclamacion.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(reclamacionUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>