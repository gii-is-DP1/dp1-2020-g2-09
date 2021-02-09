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
<a href="/pedidos/new" class="btn btn-default">Nuevo Pedido</a>
<c:if test="${pedidoActual.id != null}"> 
	<h2>Pedido Actual</h2>
   <table class="table table-striped">
        <tr>
         	<th>Precio</th>
            <td><b><c:out value="${pedidoActual.precio}"/></b></td>
            <td></td>
        </tr>
        <tr>
            <th>Gastos de Envio</th>
            <td>
                <c:if test="${pedidoActual.tipoEnvio == 'DOMICILIO'}">
            		<c:out value="3.5"/>
            	</c:if>
           		<c:if test="${pedidoActual.tipoEnvio == 'RECOGER EN TIENDA'}">
            		<c:out value="0.0"/>
           		</c:if>
           	</td> 
           	<td></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${pedidoActual.direccion}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Fecha pedido</th>
            <td><c:out value="${pedidoActual.fechaPedido}"/></td>
            <td></td>
        </tr>
        
        <tr>
            <th>Tipo Envio</th>
            <td><c:out value="${pedidoActual.tipoEnvio}"/></td>
            <td></td>
        </tr>
        <tr>
           <th>Tipo Pago</th>
            <td><c:out value="${pedidoActual.tipoPago}"/></td>
            <td></td>
        </tr>
    </table>
    <c:choose>
    <c:when test="${pedidoActual.estadoPedido == null}">
        <td>					
             		<spring:url value="/pedidos/{pedidoId}/allCartas" var="pedidoproductoUrl">
	                        <spring:param name="pedidoId" value="${pedidoActual.id}"/> 
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoproductoUrl)}" class="btn btn-default">Añadir Productos</a>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/edit" var="pedidoUrl">
	                        <spring:param name="pedidoId" value="${pedidoActual.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Editar</a>
             	</td> 
    	</c:when>    
   		<c:otherwise>
        	<td>
          		<spring:url value="/pedidos/{pedidoId}/estadoPedido" var="pizzaTracker">
            		<spring:param name="pedidoId" value="${pedidoActual.id}"/>
	        	</spring:url>
        	    <a href="${fn:escapeXml(pizzaTracker)}" class="btn btn-default">Ver estado pedido</a>
     	   </td>
  	  </c:otherwise>
	</c:choose>
 	
    </c:if>          	
 
    <h2>Mis Pedidos</h2>
    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Precio</th>
            <th>Gastos de Envio</th>
            <th>Direccion</th>
            <th>Fecha de pedido</th>
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
             		<c:out value="${pedido.tipoEnvio}"></c:out>
             	</td>
             	<td>
             		<c:out value="${pedido.tipoPago}"></c:out>
             	</td>
             	<td>
             		<spring:url value="/pedidos/{pedidoId}/anadirReclamacion/new" var="pedidoreclamacionUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoreclamacionUrl)}" class="btn btn-default">Nueva reclamacion</a>
             	</td>
             	
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>