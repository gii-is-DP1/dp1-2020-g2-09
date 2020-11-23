<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="reservas">
    <h2>Reservas</h2>

    <table id="reservasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Numero de Personas</th>
            <th>Fecha de la reserva</th>
            <th>Hora de la reserva</th>
            <th>Hora de la reserva</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservas}" var="reserva">
            <tr>
                <td>
                    <c:out value="${reserva.numeroPersonas}"/>
                </td>
                <td>
                    <c:out value="${reserva.fechaReserva}"/>
                </td>
                <td>
                    <c:out value="${reserva.tipoReserva}"/>
                </td>
                <td>
                    <c:out value="${reserva.hora}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/reservas.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout>