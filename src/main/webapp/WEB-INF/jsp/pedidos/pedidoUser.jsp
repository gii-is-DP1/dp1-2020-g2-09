<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="pedido">

    <h2>Mis pedidos</h2>


    <table class="table table-striped">
        <tr>
            <th>Precio</th>
            <td><b><c:out value="${pedido.precio}"/></b></td>
        </tr>
        <tr>
            <th>Gastos de Envio</th>
            <td><c:out value="${pedido.gastosEnvio}"/></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${pedido.direccion}"/></td>
        </tr>
        <tr>
            <th>Fecha de pedido</th>
            <td><c:out value="${pedido.fechaPedido}"/></td>
        </tr>
        <tr>
            <th>Estado pedido</th>
            <td><c:out value="${pedido.estadoPedido}"/></td>
        </tr>
        <tr>
            <th>Tipo Envio</th>
            <td><c:out value="${pedido.tipoEnvio}"/></td>
        </tr>
        <tr>
            <th>Tipo Pago</th>
            <td><c:out value="${pedido.tipoPago}"/></td>
        </tr>
    </table>

</petclinic:layout>