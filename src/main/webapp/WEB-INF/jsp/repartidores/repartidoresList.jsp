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

    <table id="repartidorTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha Nacimiento</th>
            <th>Nombre de usuario</th>
            <th>Email</th>
        </tr>
        <a href="/repartidores/new" class="btn btn-default">Añadir repartidor</a>
        </thead>
        <tbody>
        <c:forEach items="${repartidores.repartidoresList}" var="repartidor">
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
             		<c:out value="${repartidor.usuario}"/>
             	</td>
             	<td>
             		<c:out value="${repartidor.email}"/>
             	</td>
             	<!-- <td>
             		<c:out value="${cuenta.id}"></c:out>
             	</td> -->
             	<td>
             		<spring:url value="/repartidores/{repartidorId}/edit" var="repartidorUrl">
	                        <spring:param name="repartidorId" value="${repartidor.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(repartidorUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/repartidores/{repartidorId}/delete" var="repartidorUrl2">
	                        <spring:param name="repartidorId" value="${repartidor.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(repartidorUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>