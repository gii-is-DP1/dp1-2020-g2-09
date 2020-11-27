<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="Ingredientes">
    <h2>Ingredientes</h2>
    

    
	<script>
            $(function () {
                $("#fechaCaducidad").datepicker({dateFormat: 'yyyy/mm/dd'});
            });
        </script>
    <table id="IngredientesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Fecha de Caducidad</th>
            <th>Alergenos</th>
        </tr>
        <a href="/Ingredientes/new" class="btn btn-default">Añadir ingrediente</a>
        </thead>
        <tbody>
        <c:forEach items="${Ingredientes}" var="ingrediente">
            <tr>
                <td>
                    <c:out value="${ingrediente.nombre}"/>
                </td>
                <td>
                	<c:out value="${ingrediente.tipo}"/>
             	</td>
             	<td>
                	<c:out value="${ingrediente.fechaCaducidad}"/>
             	</td>
             	<td>
             	<ul>
             	<c:forEach items="${ingrediente.alergenos}" var="alergeno">
             		<li><c:out value="${alergeno}"/></li>
             	</c:forEach>
             	</ul>
             	</td>
             	<td>
             		<spring:url value="/Ingredientes/{IngredienteId}/edit" var="IngredienteUrl">
	                        <spring:param name="IngredienteId" value="${ingrediente.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(IngredienteUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/Ingredientes/{IngredienteId}/delete" var="IngredienteUrl2">
	                       <spring:param name="IngredienteId" value="${ingrediente.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(IngredienteUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
</petclinic:layout>