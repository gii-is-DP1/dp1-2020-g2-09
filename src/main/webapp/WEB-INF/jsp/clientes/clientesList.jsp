<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <c:forEach items="${clientes.clientesList}" var="cliente">
            <tr>
                <td>
                    <c:out value="${cliente.nombre}"/>
                </td>
                <td>
                	<c:out value="${cliente.apellidos}"/>
             	</td>
             	<td>
             		<c:out value="${cliente.fechaNacimiento}"/>
             	</td>
             	<td>
             		<c:out value="${cliente.user.username}"/>
             	</td>
             	<td>
             		<c:out value="${cliente.email}"/>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>