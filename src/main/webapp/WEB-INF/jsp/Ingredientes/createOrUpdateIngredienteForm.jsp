<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Ingredientes">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaCaducidad").datepicker({dateFormat: 'yyyy/mm/dd'});
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
            <form:checkbox value="Cereales que contengan gluten" path="alergenos" label=" Cereales que contengan gluten"/><br/>
            <form:checkbox value="Crustáceos y productos a base de crustáceos" path="alergenos" label=" Crustáceos y productos a base de crustáceos"/><br/>
            <form:checkbox value="Huevos y productos a base de huevo" path="alergenos" label=" Huevos y productos a base de huevo"/><br/>
            <form:checkbox value="Pescado y productos a base de pescado" path="alergenos" label=" Pescado y productos a base de pescado"/><br/>
            <form:checkbox value="Cacahuetes y productos a base de cacahuetes" path="alergenos" label=" Cacahuetes y productos a base de cacahuetes"/><br/>
            <form:checkbox value="Soja y productos a base de soja" path="alergenos" label=" Soja y productos a base de soja"/><br/>
            <form:checkbox value="Leche y sus derivados" path="alergenos" label=" Leche y sus derivados"/><br/>
            <form:checkbox value="Frutos de cáscara" path="alergenos" label=" Frutos de cáscara"/><br/>
            <form:checkbox value="Apio y productos derivados" path="alergenos" label=" Apio y productos derivados"/><br/>
            <form:checkbox value="Mostaza y productos derivados" path="alergenos" label=" Mostaza y productos derivados"/><br/>
            <form:checkbox value="Granos de sésamo y productos a base de granos de sésamo" path="alergenos" label=" Granos de sésamo y productos a base de granos de sésamo"/><br/>
            <form:checkbox value="Dióxido de azufre y sulfitos" path="alergenos" label=" Dióxido de azufre y sulfitos"/><br/>
            <form:checkbox value="Altramuces y productos a base de altramuces" path="alergenos" label=" Altramuces y productos a base de altramuces"/><br/>
            <form:checkbox value="Moluscos y productos a base de moluscos" path="alergenos" label=" Moluscos y productos a base de moluscos" /><br/>
            
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