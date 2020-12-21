<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    
<petclinic:layout pageName="carta">
    <h2>Resumen Pedido</h2>
    
    <sec:authorize access="hasAnyAuthority('cliente')"  >
	   			<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta" var="verCarta">
		        	<spring:param name="pedidoId" value="${pedido.id}"/>
		        	<spring:param name="cartaId" value="${cartaId}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(verCarta)}" class="btn btn-default">Volver a la carta</a>
    	</sec:authorize>

    <table class="table table-striped">
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
        <c:forEach items="${otros.otrosList}" var="otro">
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
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    
</petclinic:layout>