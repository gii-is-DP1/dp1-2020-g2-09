<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="cartas">
    <h2>
        <c:if test="${carta['new']}">Nueva </c:if> Carta
    </h2>
    <form:form modelAttribute="carta" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
         	<petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Fecha de Creacion" name="fechaCreacion"/>
            <petclinic:inputField label="Fecha Final" name="fechaFinal"/>
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${carta['new']}">
                        <button class="btn btn-default" type="submit">Añadir carta</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar carta</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>