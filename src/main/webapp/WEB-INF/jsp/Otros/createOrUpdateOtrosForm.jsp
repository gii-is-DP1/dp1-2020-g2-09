<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="Otros">
    <h2>
        <c:if test="${otro['new']}">Otro </c:if> nuevo
    </h2>
    <form:form modelAttribute="otros" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre del plato" name="nombre"/>
			<petclinic:input name="coste" label="Precio"/>
            <petclinic:inputField label="contador" name="contador"/>
            <petclinic:selectField name="ingredientes" label="ingredientes" names="${ingredientes}" size="3"/>
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