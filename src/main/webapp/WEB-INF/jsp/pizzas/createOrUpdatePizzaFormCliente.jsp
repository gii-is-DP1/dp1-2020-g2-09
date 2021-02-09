<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="pizzas">
    <h2>
        <c:if test="${pizza['new']}">Nueva </c:if> Pizza
    </h2>
    <form:form modelAttribute="pizza" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre de la pizza" name="nombre"/>
                 
             <div class="control-group">
           
            <petclinic:selectField name="tipoMasa" label="Tipo de Masa" names="${tipoMasa}" size="3"/>
                 
			
			<petclinic:selectField name="tamano" label="Tamaño" names="${tamanyo}" size="3"/>
                     
			<input id="coste" name="coste" type="hidden" value="6">
			
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
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pizza['new']}">
                        <button class="btn btn-default" type="submit">Crear Pizza</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar pizza</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>