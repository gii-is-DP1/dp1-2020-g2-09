<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="cliente">

    <h2>Tu información</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cliente.nombre}"/></b></td>
        </tr>
        <tr>
            <th>Apellidos</th>
            <td><c:out value="${cliente.apellidos}"/></td>
        </tr>
        <tr>
            <th>Fecha Nacimiento</th>
            <td><c:out value="${cliente.fechaNacimiento}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${cliente.telefono}"/></td>
        </tr>
        <tr>
            <th>Nombre de usuario</th>
            <td><c:out value="${cliente.user.username}"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${cliente.email}"/></td>
        </tr>
        <tr>
            <th>Nivel de Socio</th>
            <td><c:out value="${cliente.nivelSocio}"/></td>
        </tr>
    </table>

    <spring:url value="/clientes/{cuentaId}/edit" var="cuentaUrl">
        <spring:param name="cuentaId" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(cuentaUrl)}" class="btn btn-default">Editar perfil</a>

    


</petclinic:layout>