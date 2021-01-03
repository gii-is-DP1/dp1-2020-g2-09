<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    
<petclinic:layout pageName="resumenPedido">
    <h2>Resumen Pedido</h2>
    
    <sec:authorize access="hasAnyAuthority('cliente')"  >
	   			<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta" var="verCarta">
		        	<spring:param name="pedidoId" value="${pedido.id}"/>
		        	<spring:param name="cartaId" value="${cartaId}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(verCarta)}" class="btn btn-default">Volver a la carta</a>
    	</sec:authorize>

    <table class="table table-striped">
    
    	<sec:authorize access="hasAnyAuthority('cocinero')"  >
     	<tr>
           <th>Numero pedido</th>
            <td><c:out value="${pedido.id}"/></td>
        </tr>
        
        <tr>
           <th>Cliente</th>
            <td> <c:out value="${pedido.cliente.nombre}"/>
              <c:out value=" ${pedido.cliente.apellidos}"></c:out>
            </td>
        </tr>
        </sec:authorize>
        <tr>
         	<th>Precio</th>
            <td><b><c:out value="${pedido.precio}"/></b></td>
        </tr>
        <tr>
            <th>Gastos de Envio</th>
            <td>
                <c:if test="${pedido.tipoEnvio == 'DOMICILIO'}">
            		<c:out value="3.5"/>
            	</c:if>
           		<c:if test="${pedido.tipoEnvio == 'RECOGER EN TIENDA'}">
            		<c:out value="0.0"/>
           		</c:if>
           	</td> 
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${pedido.direccion}"/></td>
        </tr>
        <tr>
            <th>Fecha pedido</th>
            <td><c:out value="${pedido.fechaPedido}"/></td>
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
    
    <h3>Pizzas</h3>
    <table id="pizzasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Tipo Masa</th>
            <th>Ingredientes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pizzas.pizzasList}" var="pizza">
            <tr>
               <td>
                    <c:out value="${pizza.nombre}"/>
               </td>
               <td>
                	<c:out value="${pizza.tamano}"/>
             	</td>
             	<td>
             		<c:out value="${pizza.coste}"/>
             	</td>
             	 <td>
             		<c:out value="${pizza.tipoMasa}"/>
             	</td>
             	 <td>
             		<ul>
             			<c:forEach items="${pizza.ingredientes}" var="ingrediente">
             				<li>
             					<c:out value="${ingrediente.nombre}"/>
             				</li>
             			</c:forEach>
             		</ul>
             	</td>
             	<td>
             	<sec:authorize access="hasAnyAuthority('cliente')"  >
             		<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit" var="editarPizza">
             			<spring:param name="cartaId" value="${cartaId}"/>
             			<spring:param name="pedidoId" value="${pedido.id}"/>
						<spring:param name="pizzaId" value="${pizza.id}"/>
				 	</spring:url>
					<a href="${fn:escapeXml(editarPizza)}" class="btn btn-default">Editar</a>
				</sec:authorize>
             	</td>
             	
             	<td>
             	<sec:authorize access="hasAnyAuthority('cliente')"  >
             		<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/borrarP" var="borrarPizza">
             			<spring:param name="cartaId" value="${cartaId}"/>
             			<spring:param name="pedidoId" value="${pedido.id}"/>
						<spring:param name="pizzaId" value="${pizza.id}"/>
				 	</spring:url>
					<a href="${fn:escapeXml(borrarPizza)}" class="btn btn-default">Eliminar</a>
				</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <h3>Bebidas</h3>
    <table id="bebidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Carbonatada</th>
            <th>Tamaño</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bebidas.bebidasList}" var="bebida">
            <tr>
                <td>
                    <c:out value="${bebida.nombre}"/>
                </td>
                <td>
                	<c:if test="${bebida.esCarbonatada}">
                		<c:out value="Sí"></c:out>
                	</c:if>
                	<c:if test="${!bebida.esCarbonatada}">
                		<c:out value="No"></c:out>
                	</c:if>
             	</td>
             	<td>
             		<c:out value="${bebida.tamano}"></c:out>
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td> 	
             	
             	<td>
             	<sec:authorize access="hasAnyAuthority('cliente')"  >
             		<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/bebidas/{bebidaId}/borrarB" var="borrarB">
             			<spring:param name="cartaId" value="${cartaId}"/>
             			<spring:param name="pedidoId" value="${pedido.id}"/>
						<spring:param name="bebidaId" value="${bebida.id}"/>
				 	</spring:url>
					<a href="${fn:escapeXml(borrarB)}" class="btn btn-default">Eliminar</a>
				</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    <h3>Otros</h3>
    <table id="otrosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Coste</th>
            <th>Ingredientes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${otros.otrosLista}" var="otro">
            <tr>
                <td>
                    <c:out value="${otro.nombre}"/>
                </td>
                <td>
                	<c:out value="${otro.coste}"></c:out>

             	<td>
             		<ul>
             			<c:forEach items="${otro.ingredientes}" var="ingrediente">
             				<li>
             					<c:out value="${ingrediente.nombre}"/>
             				</li>
             			</c:forEach>
             		</ul>
             	</td>
             	<td>
             	<sec:authorize access="hasAnyAuthority('cliente')"  >
             		<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/otros/{otrosId}/borrarO" var="borrarO">
             			<spring:param name="cartaId" value="${cartaId}"/>
             			<spring:param name="pedidoId" value="${pedido.id}"/>
						<spring:param name="otrosId" value="${otro.id}"/>
				 	</spring:url>
					<a href="${fn:escapeXml(borrarO)}" class="btn btn-default">Eliminar</a>
				</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        </tbody>
        
    </table>
    
    <sec:authorize access="hasAnyAuthority('cliente')"  >
     	<spring:url value="/pedidos/{pedidoId}/delete" var="borrarPedido">
			<spring:param name="pedidoId" value="${pedido.id}"/>			 	
		</spring:url>
	<a href="${fn:escapeXml(borrarPedido)}" class="btn btn-default">Cancelar pedido</a>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('cliente')"  >
		<spring:url value="/pedidos/{pedidoId}/finalizarPedido" var="finalizarPedido">
        	<spring:param name="pedidoId" value="${pedido.id}"/>			 	
		</spring:url>
	<a href="${fn:escapeXml(finalizarPedido)}" class="btn btn-default">Finalizar pedido</a>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('cocinero')"  >
		<spring:url value="/pedidos/cocinero" var="cocineroPedido">
        	<spring:param name="pedidoId" value="${pedido.id}"/>			 	
		</spring:url>
	<a href="${fn:escapeXml(cocineroPedido)}" class="btn btn-default">Volver a todos los pedidos</a>
	</sec:authorize>
	
    
</petclinic:layout>