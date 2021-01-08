<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<petclinic:layout pageName="reservas">
	<jsp:attribute name="customScript">
  		  <script>
            $(function() {
                $("#fechaReserva").datepicker({dateFormat: 'yy/mm/dd'});
            });
  		</script>
  	</jsp:attribute>

    <jsp:body>
    <h2>
        <c:if test="${reserva['new']}">Nueva </c:if> Reserva
    </h2>
    <form:form modelAttribute="reserva" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Numero de Personas" name="numeroPersonas"/>
            <petclinic:inputField label="Fecha de la reserva" name="fechaReserva"/>
            
          <!--   <select label="tipoReserva" name="tipoReserva">
			  <option value="ALMUERZO">Almuerzo</option> 
			  <option value="CENA" selected>Cena</option>
			</select> -->
            <div class="control-group">
            <petclinic:selectField name="tipoReserva" label="Tipo" names="${tipoReserva}" size="2"/>
            </div>
           <!--  <label>Hora de la reserva</label>
            <input type="time" name="hora">-->
            <petclinic:time label="Hora de la reserva" names="${hora}" name="hora" size="3"/> 
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Buscar mesa</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
   
</petclinic:layout>