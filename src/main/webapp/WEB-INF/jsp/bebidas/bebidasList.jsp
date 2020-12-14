<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="bebidas">
    <h2>Bebidas</h2>

    <table id="bebidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Carbonatada</th>
            <th>Precio</th>
        </tr>
        <a href="/bebidas/new" class="btn btn-default">Añadir bebida</a>
        </thead>
        <tbody>
        <c:forEach items="${bebidas.bebidasList}" var="bebida">
            <tr>
                <td>
                    <c:out value="${bebida.nombre}"/>
                </td>
                <td>
                	<c:out value="${bebida.esCarbonatada}"/>
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td>
             	<!-- <td>
             		<c:out value="${bebida.id}"></c:out>
             	</td> -->
             	<td>
             		<spring:url value="/bebidas/{bebidaId}/edit" var="bebidaUrl">
	                        <spring:param name="bebidaId" value="${bebida.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(bebidaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/bebidas/{bebidaId}/delete" var="bebidaUrl2">
	                        <spring:param name="bebidaId" value="${bebida.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(bebidaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}" var="bebidaUrl3">
	                        <spring:param name="bebidaId" value="${bebida.id}"/>
	                        <spring:param name="cartaId" value="${cartaId}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(bebidaUrl3)}" class="btn btn-default">Añadir a carta</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>