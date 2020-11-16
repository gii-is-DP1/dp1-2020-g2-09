<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Reservas">
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
        <c:forEach items="${Reservas.reservasList}" var="reserva">
            <tr>
                <td>
                    <c:out value="${Reservas.numeroPersonas}"/>
                </td>
                <td>
                    <c:out value="${Reservas.fecha_reserva}"/>
                </td>
                <td>
                    <c:out value="${Reservas.tipo_reserva_id}"/>
                </td>
                <td>
                    <c:out value="${Reservas.hora_reserva}"/>
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