<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="clientes">
    <h2>Administradores</h2>

    <table id="administradoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha Nacimiento</th>
            <th>Nombre de usuario</th>
            <th>Email</th>
        </tr>
        <a href="/administradores/new" class="btn btn-default">Añadir administrador</a>
        </thead>
        <tbody>
        <c:forEach items="${administradores.administradoresList}" var="administrador">
            <tr>
                <td>
                    <c:out value="${administrador.nombre}"/>
                </td>
                <td>
                	<c:out value="${administrador.apellidos}"/>
             	</td>
             	<td>
             		<c:out value="${administrador.fechaNacimiento}"/>
             	</td>
             	<td>
             		<c:out value="${administrador.user.username}"/>
             	</td>
             	<td>
             		<c:out value="${administrador.email}"/>
             	</td>

             	<td>
             		<spring:url value="/administradores/{administradorId}/edit" var="administradorUrl">
	                        <spring:param name="administradorId" value="${administrador.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(administradorUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/administradores/{administradorId}/delete" var="administradorUrl2">
	                        <spring:param name="administradorId" value="${administrador.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(administradorUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>