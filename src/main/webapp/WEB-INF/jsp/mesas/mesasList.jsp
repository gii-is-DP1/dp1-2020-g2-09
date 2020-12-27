<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="clientes">
    <h2>Mesas</h2>

    <table id="cocinerosTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Capacidad</th>
        	<th>Nº Mesa</th>

        </tr>
        <a href="/mesas/new" class="btn btn-default">Añadir mesa</a>
        </thead>
        <tbody>
        <c:forEach items="${mesas.mesasList}" var="mesa">
            <tr>
               <td>
             		<c:out value="${mesa.capacidad}"></c:out>
             	</td>
             	<td>
             		<c:out value="${mesa.id}"></c:out>
             	</td>
             	<td>
             		<spring:url value="/mesas/{mesaId}/edit" var="mesaUrl">
	                        <spring:param name="mesaId" value="${mesa.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(mesaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/mesas/{mesaId}/delete" var="mesaUrl2">
	                        <spring:param name="mesaId" value="${mesa.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(mesaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>