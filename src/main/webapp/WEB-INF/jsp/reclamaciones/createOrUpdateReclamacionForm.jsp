<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="reclamaciones">
    <h2>
        <c:if test="${reclamacion['new']}">Nueva </c:if> Reclamaci�n
    </h2>
    <form:form modelAttribute="reclamacion" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        
        	<!-- La fecha de reclamaci�n no la deber�a poner el cliente, 
        	deber�a rellenarse autom�ticamente. -->
            <petclinic:inputField label="fechaReclamacion" name="fechaReclamacion"/>
            <petclinic:inputField label="observacion" name="observacion"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${reclamacion['new']}">
                        <button class="btn btn-default" type="submit">A�adir reclamaci�n</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar reclamaci�n</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>