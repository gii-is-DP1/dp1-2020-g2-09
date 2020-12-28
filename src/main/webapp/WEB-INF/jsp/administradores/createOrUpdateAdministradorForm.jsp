<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="administradores">
    <h2>
        <c:if test="${administrador['new']}">Nuevo </c:if> Administrador
    </h2>
    <form:form modelAttribute="administrador" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Fecha Nacimiento" name="fechaNacimiento"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Email" name="email" />
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${administrador['new']}">
                        <button class="btn btn-default" type="submit">Añadir administrador</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar administrador</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>