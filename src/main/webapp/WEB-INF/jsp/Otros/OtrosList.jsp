<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="otros">
<body>

<h3>Otros</h3>

	<spring:url value="/cartas/{cartaId}/otro/new" var="crearOtros">
	    <spring:param name="cartaId" value="${cartaId}"/> 
	</spring:url>
	<a href="${fn:escapeXml(crearOtros)}" class="btn btn-default">Crear otro</a>
	
	<spring:url value="/cartas/{cartaId}/VerCarta" var="volverACarta">
	     <spring:param name="cartaId" value="${cartaId}"/> 
	</spring:url>
	<a href="${fn:escapeXml(volverACarta)}" class="btn btn-default">Volver a la carta</a>
    
    <table id="otrosTableCarta" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Coste</th>
            <th>Ingredientes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${otros.otrosLista}" var="otro">
            <tr>
                <td>
                    <c:out value="${otro.nombre}"/>
                </td>
                <td>
                	<c:out value="${otro.coste}"></c:out>
             	<td>
             		<ul>
             			<c:forEach items="${otro.ingredientes}" var="ingrediente">
             				<li>
             					<c:out value="${ingrediente.nombre}"/>
             				</li>
             			</c:forEach>
             		</ul>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/otro/{OtrosId}/edit" var="otroUrl">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="OtrosId" value="${otro.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(otroUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/cartas/{cartaId}/anadirOtroACarta/{otroId}" var="otroUrl3">
	                        <spring:param name="otroId" value="${otro.id}"/>
	                        <spring:param name="cartaId" value="${cartaId}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(otroUrl3)}" class="btn btn-default">Añadir a carta</a>
             	</td>
             	  	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>

</body>
</petclinic:layout>