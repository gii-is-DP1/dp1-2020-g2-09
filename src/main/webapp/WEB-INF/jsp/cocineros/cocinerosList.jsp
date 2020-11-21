<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="clientes">
    <h2>Cocineros</h2>

    <table id="cocinerosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha Nacimiento</th>
            <th>Nombre de usuario</th>
            <th>Email</th>
            <th>Fecha Alta</th>
            <th>Fecha Baja</th>
            
        </tr>
        <a href="/cocineros/new" class="btn btn-default">Añadir cocinero</a>
        </thead>
        <tbody>
        <c:forEach items="${cocineros.cocinerosList}" var="cocinero">
            <tr>
                <td>
                    <c:out value="${cocinero.nombre}"/>
                </td>
                <td>
                	<c:out value="${cocinero.apellidos}"/>
             	</td>
             	<td>
             		<c:out value="${cocinero.fechaNacimiento}"/>
             	</td>
             	<td>
             		<c:out value="${cocinero.usuario}"/>
             	</td>
             	<td>
             		<c:out value="${cocinero.email}"/>
             	</td>
             	<td>
             		<c:out value="${cocinero.fechaInicioContrato}"/>
             	</td>
             		<c:choose>
    					<c:when test="${empty cocinero.fechaFinContrato}">
       						<td>
             					<c:out value=" "/>
             				</td>
    					</c:when>
    					<c:otherwise>
        					<td>
             					<c:out value="${cocinero.fechaInicioContrato}"/>
             				</td>
    					</c:otherwise>
					</c:choose>
             	<!-- <td>
             		<c:out value="${cuenta.id}"></c:out>
             	</td> -->
             	<td>
             		<spring:url value="/cocineros/{cocineroId}/edit" var="cocineroUrl">
	                        <spring:param name="cocineroId" value="${cocinero.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(cocineroUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/cocineros/{cocineroId}/delete" var="cocineroUrl2">
	                        <spring:param name="cocineroId" value="${cocinero.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(cocineroUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	<td>
             		<spring:url value="/cocineros/{cocineroId}/altaobaja" var="cocineroUrl3">
	                        <spring:param name="cocineroId" value="${cocinero.id}"/>
	                </spring:url>
	                <c:choose>
    					<c:when test="${empty cocinero.fechaFinContrato}">
       						<a href="${fn:escapeXml(cocineroUrl3)}" class="btn btn-default">Dar de Baja</a>
    					</c:when>
    					<c:otherwise>
        					<a href="${fn:escapeXml(cocineroUrl3)}" class="btn btn-default">Dar de Alta</a>
    					</c:otherwise>
					</c:choose>
             		
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>