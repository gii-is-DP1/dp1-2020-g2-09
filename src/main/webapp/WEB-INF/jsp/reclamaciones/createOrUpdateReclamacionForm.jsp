<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<meta charset="UTF-8"/>
<petclinic:layout pageName="reclamaciones">
<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaReclamacion").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    
    <jsp:body>
    <h2>
       <c:choose>
        <c:when test="${reclamacion['new']}">
         Nueva Reclamación
         </c:when>
         <c:otherwise>
         Responder reclamación
         </c:otherwise>
         </c:choose>
    </h2>
    
    <form:form modelAttribute="reclamacion" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        <c:choose>
    	<c:when test="${reclamacion['new']}">
            <%-- <petclinic:inputField label="fecha de incidencia" name="fechaReclamacion"/> --%>
            <petclinic:inputField label="observación" name="observacion"/>
             <form:input type="hidden" path="respuesta" label="respuesta" name="respuesta"/>
             </c:when>
              <c:otherwise>
              <!-- Revisar -->
            <%-- <form:input type="hidden" path="fechaReclamacion" name="fechaReclamacion"/> --%>
            <form:input type="hidden" path="observacion" label="observacion" name="observacion"/>
              <petclinic:inputField label="respuesta" name="respuesta"/>
               </c:otherwise>
             </c:choose>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                 <c:choose>
                    <c:when test="${reclamacion['new']}">
                        <button class="btn btn-default" type="submit">Añadir reclamación</button>
                     </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Responder reclamación</button>
                    </c:otherwise>
                </c:choose> 
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>