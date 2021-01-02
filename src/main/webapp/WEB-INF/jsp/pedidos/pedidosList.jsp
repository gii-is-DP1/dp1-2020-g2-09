<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="pedidos">
    <h2>Pedidos</h2>
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
<%--             <sec:authorize access="hasAnyAuthority('cliente')"  > --%>
            	<th>Cliente</th>
<%--             </sec:authorize> --%>
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
<%--              		<sec:authorize access="hasAnyAuthority('cliente')"  > --%>
                    <c:out value="${pedido.cliente.nombre}"/>
                    <c:out value=" ${pedido.cliente.apellidos}"></c:out>
<%--                     </sec:authorize> --%>
                </td>
                
             	<td>
             	<sec:authorize access="hasAnyAuthority('administrador')"  >
             		<spring:url value="/pedidos/{pedidoId}/edit" var="pedidoUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl)}" class="btn btn-default">Editar</a>
   				</sec:authorize>
             	</td>
             	
             	<td>
             	<sec:authorize access="hasAnyAuthority('cocinero')"  >
					<spring:url value="/cocinero/{pedidoId}/estadoPedido" var="pedidoUrl3">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl3)}" class="btn btn-default">Actualizar estado pedido</a>
				</sec:authorize>
				</td>
             	
             	<td>
             	<sec:authorize access="hasAnyAuthority('repartidor')"  >
					<spring:url value="/repartidor/{pedidoId}/estadoPedido" var="pedidoUrl4">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pedidoUrl4)}" class="btn btn-default">Actualizar estado pedido</a>
				</sec:authorize>
				</td>
				
             	<td>
             		<sec:authorize access="hasAnyAuthority('cliente')"  >
             		<spring:url value="/pedidos/{pedidoId}/anadirReclamacion/new" var="pedidoreclamacionUrl">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoreclamacionUrl)}" class="btn btn-default">Nueva reclamacion</a>
   					</sec:authorize>
             	</td>
             	
             	<td>
             		<sec:authorize access="hasAnyAuthority('administrador')"  >
             		<spring:url value="/pedidos/{pedidoId}/delete" var="pedidoUrl2">
	                        <spring:param name="pedidoId" value="${pedido.id}"/>
	                </spring:url>
             		<a href="${fn:escapeXml(pedidoUrl2)}" class="btn btn-default">Eliminar</a>
             		</sec:authorize>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>