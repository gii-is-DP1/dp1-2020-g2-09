<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="bebidas">
    <h2>
        <c:if test="${bebida['new']}">Nueva </c:if> Bebida
    </h2>
    <form:form modelAttribute="bebida" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre de la bebida" name="nombre"/>
            <label for="esCarbonatada">Es o no carbonatada:</label>
             <select label="esCarbonatada" name="esCarbonatada">
			  <option value="true">Carbonatada</option> 
			  <option value="false" selected>No carbonatada</option>
			</select>
			</br>
			<label for="tamano">Tamaño:</label>
			 <select label="tamaño" name="tamano">
			  <option value="NORMAL">Normal</option> 
			  <option value="GRANDE" selected>Grande</option>
			</select>
			<petclinic:input name="coste" label="Precio"/>
			<input type="hidden" name="version" value="${bebida.version}"/>
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