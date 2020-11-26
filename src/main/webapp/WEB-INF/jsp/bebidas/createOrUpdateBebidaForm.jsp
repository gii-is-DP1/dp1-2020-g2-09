<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="bebidas">
    <h2>
        <c:if test="${pizza['new']}">Nueva </c:if> Bebida
    </h2>
    <form:form modelAttribute="pizza" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre de la bebida" name="nombre"/>
            
             <select label="esCarbonatada" name="esCarbonatada">
			  <option value="CARBONATADA">Carbonatada</option> 
			  <option value="NOCARBONATADA" selected>No carbonatada</option>
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
                    <c:when test="${bebida['new']}">
                        <button class="btn btn-default" type="submit">Crear Bebida</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar bebida</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>