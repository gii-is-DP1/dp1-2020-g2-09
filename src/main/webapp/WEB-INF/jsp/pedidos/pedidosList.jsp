<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="pedidos">
    <h2>Pedidos</h2>

    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Precio</th>
            <th>Gastos de Envio</th>
            <th>Direccion</th>
            <th>Fecha de pedido</th>
            <th>Estado pedido</th>
            <th>Tipo Envio</th>
            <th>Tipo Pago</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedidos.pedidosList}" var="pedido">
            <tr>
                <td>
                    <c:out value="${pedido.precio}"/>
                </td>
                <td>
                	<c:out value="${pedido.gastosEnvio}"/>
             	</td>
             	<td>
             		<c:out value="${pedido.direccion}"/>
             	</td>
             	<td>
             		<c:out value="${pedido.fechaPedido}"/>
             	</td>
             	<td>
             		<c:out value="${pedido.estadoPedido}"></c:out>
             	</td> 
             	<td>
             		<c:out value="${pedido.tipoEnvio}"></c:out>
             	</td>
             	<td>
             		<c:out value="${pedido.tipoPago}"></c:out>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/edit" var="pedidoUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/delete" var="pedidoUrl2">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(pedidoUrl2)}" class="btn btn-default">Eliminar</a>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>