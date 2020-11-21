<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="ofertas">
    <h2>Ofertas</h2>

    <table id="ofertasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Fecha inicial</th>
            <th>Fecha final</th>
            <th>Nivel Socio</th>
        </tr>
        <a href="/ofertas/new" class="btn btn-default">Añadir ofertas</a>
        </thead>
        <tbody>
        <c:forEach items="${ofertas.ofertasList}" var="oferta">
            <tr>
                <td>
                    <c:out value="${oferta.tamanoProducto}"/>
                </td>
                <td>
                	<c:out value="${oferta.coste}"/>
             	</td>
             	<td>
             		<c:out value="${oferta.fechaInicial}"/>
             	</td>
             	<td>
             		<c:out value="${oferta.fechaFinal}"/>
             	</td>
             	<td>
             		<c:out value="${oferta.nivelSocio}"></c:out>
             	</td>
             	<td>
             		<spring:url value="/ofertas/{ofertaId}/edit" var="ofertaUrl">
	                        <spring:param name="ofertaId" value="${oferta.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(ofertaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/ofertas/{ofertaId}/delete" var="ofertaUrl2">
	                        <spring:param name="ofertaId" value="${oferta.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(ofertaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>