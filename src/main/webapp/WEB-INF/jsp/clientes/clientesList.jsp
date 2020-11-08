<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha Nacimiento</th>
            <th>Nombre de usuario</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cuentas.cuentasList}" var="cuenta">
            <tr>
                <td>
                    <c:out value="${cuenta.nombre}"/>
                </td>
                <td>
                	<c:out value="${cuenta.apellidos}"/>
             	</td>
             	<td>
             		<c:out value="${cuenta.fechaNacimiento}"/>
             	</td>
             	<td>
             		<c:out value="${cuenta.nombreUsuario}"/>
             	</td>
             	<td>
             		<c:out value="${cuenta.email}"/>
             	</td>
             	<td>
             		<c:out value=""></c:out>
             	</td>
             	<td>
             		<spring:url value="/cuentas/{cuentaId}/edit" var="cuentaUrl">
	                        <spring:param name="cuentaId" value="${cuenta.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(cuentaUrl)}" class="btn btn-default">Editar</a>
             	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>