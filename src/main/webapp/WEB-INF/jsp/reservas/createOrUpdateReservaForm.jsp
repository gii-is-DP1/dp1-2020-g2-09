<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="reservas">
    <h2>
        <c:if test="${reserva['new']}">Nueva </c:if> Reserva
    </h2>
    <form:form modelAttribute="reserva" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Numero de Personas" name="numeroPersonas"/>
            <petclinic:inputField label="Fecha de la reserva" name="fechaReserva"/>
            
             <select label="tipoReserva" name="tipoReserva">
			  <option value="ALMUERZO">Almuerzo</option> 
			  <option value="CENA" selected>Cena</option>
			  <option value="MERIENDA">Merienda</option>
			</select>
            
            <petclinic:inputField label="Hora de la reserva" name="hora"/>
            <!--<petclinic:inputField label="Id" name="id" />-->
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${reserva['new']}">
                        <button class="btn btn-default" type="submit">Reservar</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar reserva</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>