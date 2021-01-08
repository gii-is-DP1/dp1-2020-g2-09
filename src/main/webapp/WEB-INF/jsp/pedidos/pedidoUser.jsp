<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="pedidos">
    <h2>Mis Pedidos</h2>
    <%-- ESTA ES LA LISTA DE TODOS MIS PEDIDOS DE UN CLIENTE--%>
 	<a href="/pedidos/new" class="btn btn-default">Nuevo Pedido</a>
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
                <c:if test="${pedido.tipoEnvio == 'DOMICILIO'}">
            		<c:out value="3.5"/>
            	</c:if>
           		<c:if test="${pedido.tipoEnvio == 'RECOGER EN TIENDA'}">
            		<c:out value="0.0"/>
           		</c:if>
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
             		<spring:url value="/pedidos/{pedidoId}/allCartas" var="pedidoproductoUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/> 
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoproductoUrl)}" class="btn btn-default">Añadir Productos</a>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/edit" var="pedidoUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/anadirReclamacion/new" var="pedidoreclamacionUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoreclamacionUrl)}" class="btn btn-default">Nueva reclamacion</a>
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