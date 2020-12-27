<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="ofertas">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaInicial").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
        <script>
            $(function () {
                $("#fechaFinal").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
   <jsp:body>
    <h2>
        <c:if test="${oferta['new']}">Nueva </c:if> Oferta
    </h2>
    <form:form modelAttribute="oferta" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Coste" name="coste"/>
            <petclinic:inputField label="Fecha Inicial" name="fechaInicial"/>
            <petclinic:inputField label="Fecha Final" name="fechaFinal"/>
			<div class="control-group">
			<petclinic:selectField name="tamanoOferta" label="Tamaño producto" names="${tamanoOferta}" size="2"/>
			<petclinic:selectField name="nivelSocio" label="Nivel Socio" names="${nivelSocio}" size="3"/>
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
     </jsp:body>
</petclinic:layout>