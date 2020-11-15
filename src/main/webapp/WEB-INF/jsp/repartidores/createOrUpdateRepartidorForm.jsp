<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="clientes">
    <h2>
        <c:if test="${repartidor['new']}">Nuevo </c:if> Repartidor
    </h2>
    <form:form modelAttribute="repartidores" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Fecha Nacimiento" name="fechaNacimiento"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Nombre de usuario" name="usuario"/>
            <petclinic:inputField label="Contraseña" name="contraseña"/>
            <petclinic:inputField label="Email" name="email" />
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${repartidor['new']}">
                        <button class="btn btn-default" type="submit">Añadir repartidor</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar repartidor</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>