<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="ofertas">
    <h2>Ofertas</h2>
        <a href="/ofertas/new" class="btn btn-default"> Añadir ofertas</a>
    <table id="ofertasTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Título</th>
            <th>Tamaño producto</th>
            <th>Precio</th>
            <th>Fecha inicial</th>
            <th>Fecha final</th>
            <th>Nivel Socio</th>
            <th>Estado Oferta</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ofertas.ofertasList}" var="oferta">
        
            <tr>
           		<td>
                    <c:out value="${oferta.name}"/>
                </td>
                <td>
                    <c:out value="${oferta.tamanoOferta}"/>
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
             	<c:choose>				
					<c:when test="${!(oferta.fechaFinal ge hoy && oferta.fechaInicial le hoy)}">
      						<!--<c:out value="CADUCADA"></c:out>-->
      						<img src="/resources/images/expired.png" width="39px" height="27px">
      				</c:when>					 
					<c:otherwise>
						<c:if test="${oferta.estadoOferta == 'true'}">
      						<img src="/resources/images/check-verde.png" width="30px" height="30px">
      						<!--<c:out value="ACTIVA"></c:out>-->
   						</c:if>       					
    					<c:if test="${oferta.estadoOferta == 'false'}">
      						<img src="/resources/images/off.png" width="35px" height="35px">
      						<!--<c:out value="INACTIVA"></c:out>-->
   						</c:if>
    				</c:otherwise>
				</c:choose>             		
                </td>
             	<td>
             		<spring:url value="/ofertas/{ofertaId}/edit" var="ofertaUrl">
	                        <spring:param name="ofertaId" value="${oferta.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(ofertaUrl)}" class="btn btn-default">Editar</a>
   					
             	</td>
             	<td>
             		<spring:url value="/ofertas/{ofertaId}/changeState" var="ofertaUrl">
	                        <spring:param name="ofertaId" value="${oferta.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(ofertaUrl)}" class="btn btn-default">Activar/Desactivar</a>
   					
             	</td>
             <!--	<td>
             		<spring:url value="/ofertas/{ofertaId}/delete" var="ofertaUrl2">
	                        <spring:param name="ofertaId" value="${oferta.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(ofertaUrl2)}" class="btn btn-default">Eliminar</a>
             	</td> -->
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>