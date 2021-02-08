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
	
	<spring:url value="/cartas/{cartaId}/bebida/new" var="crearBebidas">
		<spring:param name="cartaId" value="${cartaId}"/> 
	</spring:url>
	<a href="${fn:escapeXml(crearBebidas)}" class="btn btn-default">Crear bebida</a>
	    
	<spring:url value="/cartas/{cartaId}/VerCarta" var="volverACarta">
	     <spring:param name="cartaId" value="${cartaId}"/> 
	</spring:url>
	<a href="${fn:escapeXml(volverACarta)}" class="btn btn-default">Volver a la carta</a>


    <table id="bebidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Carbonatada</th>
            <th>Precio</th>
        </tr>
	    
        </thead>
        <tbody>
        <c:forEach items="${bebidas.bebidasList}" var="bebida">
            <tr>
                <td>
                    <c:out value="${bebida.nombre}"/>
                </td>
               <td>
                	<c:if test="${bebida.esCarbonatada}">
                		<c:out value="Sí"></c:out>
                	</c:if>
                	<c:if test="${!bebida.esCarbonatada}">
                		<c:out value="No"></c:out>
                	</c:if>
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/bebida/{bebidaId}/edit" var="bebidaUrl">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="bebidaId" value="${bebida.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(bebidaUrl)}" class="btn btn-default">Editar</a>
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