<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="pedidos">
	
    <h2>
        <c:if test="${pedido['new']}">Nuevo </c:if> Pedido
    </h2>
    <form:form modelAttribute="pedido" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Direccion" name="direccion"/>
			<div class="control-group">
			<petclinic:selectField name="tipoPago" label="Tipo Pago" names="${tipoPago}" size="2"/>
			<petclinic:selectField name="tipoEnvio" label="Tipo Envio" names="${tipoEnvio}" size="2"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pedido['new']}">
                        <button class="btn btn-default" type="submit">Crear pedido</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar pedido</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>