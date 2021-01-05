<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="cartas">
    <h2>Cartas</h2>
    	<sec:authorize access="hasAnyAuthority('administrador')"  >
			<a href="/cartas/new" class="btn btn-default">Añadir carta</a>
		</sec:authorize>
    <table id="cartasTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Nombre</th>
            <th>Fecha de Creacion</th>
            <th>Fecha de Caducidad</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartas}" var="cartas">
            <tr>
            	<td>
            		<c:out value="${cartas.nombre}"/>
            	</td>
                <td>
                    <c:out value="${cartas.fechaCreacion}"/>
                </td>
                <td>
                    <c:out value="${cartas.fechaFinal}"/>
                </td>
                <td>
                	<spring:url value="/cartas/{cartaId}/VerCarta" var="cartaUrl3">
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(cartaUrl3)}" class="btn btn-default">Acceder a la carta</a>
                </td>
                <td>
                <sec:authorize access="hasAnyAuthority('administrador')"  >
             		<spring:url value="/cartas/{cartaId}/edit" var="cartaUrl">
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(cartaUrl)}" class="btn btn-default">Editar</a>
   					</sec:authorize>
             	</td>
             	<td>
             	<sec:authorize access="hasAnyAuthority('administrador')"  >
             		<spring:url value="/cartas/{cartaId}/delete" var="cartaUrl2">
	                        <spring:param name="cartaId" value="${cartas.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(cartaUrl2)}" class="btn btn-default">Eliminar</a>
             		</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>    
        </tr>
    </table>
</petclinic:layout>