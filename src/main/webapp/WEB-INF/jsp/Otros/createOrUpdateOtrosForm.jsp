<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="Otros">
    <h2>
        <c:if test="${otro['new']}">Nuevo </c:if> otro
    </h2>
    <form:form modelAttribute="otro" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre del plato" name="nombre"/>
			<petclinic:input name="coste" label="Precio"/>
            <table id="ingredientesTable" class="table table-striped">
             <thead>
       		 <tr>
            	<th>Ingrediente</th>
            	<th>Agregar</th>
       	 	 </tr>
       		 </thead>
       		 <tbody>
       		 <c:forEach items="${ingredientes}" var="ing">
       		 <tr>
       		 <td>${ing.nombre}</td>
       		 <td><form:checkbox path="ingredientes" value="${ing}"/> Añadir</td>
       		 </tr>
       		 </c:forEach>

       		 
       		 </tbody>
             </table>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${otro['new']}">
                        <button class="btn btn-default" type="submit">Crear Otros</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Otros</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>