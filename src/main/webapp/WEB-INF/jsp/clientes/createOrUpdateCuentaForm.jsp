<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <h2>
        <c:if test="${cuenta['new']}">Nueva </c:if> Cuenta
    </h2>
    <form:form modelAttribute="cuenta" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Fecha Nacimiento" name="fechaNacimiento"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="Nombre de usuario" name="nombreUsuario"/>
            <petclinic:inputField label="Contraseña" name="contraseña"/>
            <petclinic:inputField label="Email" name="email" />
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${Nueva['new']}">
                        <button class="btn btn-default" type="submit">Añadir cliente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar cliente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>