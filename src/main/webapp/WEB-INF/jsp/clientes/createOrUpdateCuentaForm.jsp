<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="clientes">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaNacimiento").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${cliente['new']}">Nueva </c:if> Cuenta
    </h2>
    <form:form modelAttribute="cliente" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        	<input type="hidden" name="id" value="${cliente.id}"/>
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Fecha Nacimiento" name="fechaNacimiento"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Email" name="email" />
            <form:hidden path="user.username"/>
            <form:hidden path="user.password" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">Registrarse</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar cliente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <c:out value="${mensaje}"></c:out>
    </form:form>
    </jsp:body>
</petclinic:layout>