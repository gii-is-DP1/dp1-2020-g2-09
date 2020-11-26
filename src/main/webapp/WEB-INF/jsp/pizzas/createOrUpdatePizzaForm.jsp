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
                        
             <select label="tipoMasa" name="tipoMasa">
			  <option value="AMERICANA">Americana</option> 
			  <option value="FINA" selected>Fina</option>
			  <option value="BORDESDEQUESO">Bordes de queso</option>
			</select>
			
			 <select label="tamaño" name="tamaño">
			  <option value="NORMAL">Normal</option> 
			  <option value="GRANDE" selected>Grande</option>
			</select>            
			
            <petclinic:inputField label="Precio" name="coste"/>
            
             <!--<petclinic:inputField label="contador" name="contador"/> -->
            <!--<petclinic:inputField label="Id" name="id"/> -->
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