<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <petclinic:layout pageName="reclamaciones">
    <h2>Mis Reclamaciones</h2>
    <table id="reclamacionesTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID de pedido</th>
            <th>Observación</th>
            <th>Respuesta</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reclamaciones.reclamacionesList}" var="reclamacion">
            <tr>
                <td>
                    <c:out value="${reclamacion.id}"/>
                </td>
                <td>
              	<c:out value="${reclamacion.observacion}"></c:out>
           		</td>
             	<td>
             	<c:if test="${reclamacion.respuesta != 'Lo sentimos mucho, ...'}">
             		<c:out value="${reclamacion.respuesta}"></c:out> </c:if>
             	</td>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>