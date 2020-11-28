<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Ingredientes">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaCaducidad").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${Ingrediente['new']}">Nuevo </c:if> Ingrediente
    </h2>
    
    <form:form modelAttribute="Ingrediente" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Tipo" name="tipo"/>
            <petclinic:inputField label="Fecha de Caducidad" name="fechaCaducidad"/>
      		
            <petclinic:selectField name="alergenos" label="Alergenos" names="${AlergenosList}" size="14"/>
            
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${Ingrediente['new']}">
                        <button class="btn btn-default" type="submit">Añadir ingrediente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar ingrediente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>