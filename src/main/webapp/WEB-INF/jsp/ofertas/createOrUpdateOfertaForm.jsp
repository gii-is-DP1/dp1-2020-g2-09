<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="ofertas">
    <h2>
        <c:if test="${oferta['new']}">Nueva </c:if> Oferta
    </h2>
    <form:form modelAttribute="oferta" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
<%--             <petclinic:inputField label="Tamaño producto" name="tamanoProducto"/> --%>
            <petclinic:inputField label="Coste" name="coste"/>
            <petclinic:inputField label="Fecha Inicial" name="fechaInicial"/>
            <petclinic:inputField label="Fecha Final" name="fechaFinal"/>
            <%-- <petclinic:inputField label="Nivel Socio" name="nivelSocio"/> --%>
            <div class="control-group">
              <petclinic:selectField name="tamanoProducto" label="Tamaño producto" names="${tamano_producto}" size="2"/>
              <petclinic:selectField name="nivelSocio" label="Nivel Socio" names="${nivel_socio}" size="3"/>
        	</div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${oferta['new']}">
                        <button class="btn btn-default" type="submit">Añadir oferta</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar oferta</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>