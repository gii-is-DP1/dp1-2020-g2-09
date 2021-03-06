<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="clientes">
    <h2>Repartidores</h2>

	<a href="/repartidores/new" class="btn btn-default">Añadir repartidor</a>
    <table id="repartidorTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha Nacimiento</th>
            <th>Nombre de usuario</th>
            <th>Email</th>
            <th>Fecha de Alta</th>
            <th>Fecha de Baja</th>
        </tr>
        
        </thead>
        <tbody>
        <c:forEach items="${listarepartidores.repartidoresList}" var="repartidor">
            <tr>
                <td>
                    <c:out value="${repartidor.nombre}"/>
                </td>
                <td>
                	<c:out value="${repartidor.apellidos}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.fechaNacimiento}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.user.username}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.email}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.fechaInicioContrato}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.fechaFinContrato}"/>
             	</td>
             	
             	<td>
             		<spring:url value="/repartidores/{repartidorId}/edit" var="repartidorUrl">
	                        <spring:param name="repartidorId" value="${repartidor.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(repartidorUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/repartidores/{repartidorId}/altaobaja" var="repartidorUrl3">
	                        <spring:param name="repartidorId" value="${repartidor.id}"/>
	                </spring:url>
	                <c:choose>
    					<c:when test="${empty repartidor.fechaFinContrato}">
       						<a href="${fn:escapeXml(repartidorUrl3)}" class="btn btn-default">Dar de Baja</a>
    					</c:when>
    					<c:otherwise>
        					<a href="${fn:escapeXml(repartidorUrl3)}" class="btn btn-default">Dar de Alta</a>
    					</c:otherwise>
					</c:choose>
             		
             	</td>
             	
            </tr>
        </c:forEach>

        </tbody>
    </table>
   
</petclinic:layout>