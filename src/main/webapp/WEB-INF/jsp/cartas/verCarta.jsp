<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<petclinic:layout pageName="carta">
    <h2>Carta</h2>
    
    <h3>Pizzas</h3>
    <!--<c:out value="${cartaId}"></c:out>-->

		<spring:url value="/cartas/{cartaId}/pizzas" var="listaPizzas">
	         <spring:param name="cartaId" value="${cartaId}"/> 
	    </spring:url>
	    <a href="${fn:escapeXml(listaPizzas)}" class="btn btn-default">Añadir pizza a la carta</a>  
	
    <table id="pizzasTableCarta" class="table table-striped">
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <h3>Bebidas</h3>
    
    <spring:url value="/cartas/{cartaId}/bebidas" var="listaBebidas">
	         <spring:param name="cartaId" value="${cartaId}"/> 
	    </spring:url>
	    <a href="${fn:escapeXml(listaBebidas)}" class="btn btn-default">Añadir bebida a la carta</a>

    <table id="bebidasTableCarta" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Carbonatada</th>
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
                	<!-- <c:out value="${bebida.esCarbonatada}"/> -->
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td>
             	<!-- <td>
             		<c:out value="${bebida.id}"></c:out>
             	</td> -->  	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    <h3>Otros</h3>
    
    <spring:url value="/cartas/{cartaId}/otros" var="listaOtros">
	         <spring:param name="cartaId" value="${cartaId}"/> 
	    </spring:url>
	    <a href="${fn:escapeXml(listaOtros)}" class="btn btn-default">Añadir bebida a la carta</a>

    <table id="otrosTableCarta" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Carbonatada</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${Otros.otrosList}" var="otro">
            <tr>
                <td>
                    <c:out value="${otro.nombre}"/>
                </td>
                <td>
                	<c:if test="${bebida.esCarbonatada}">
                		<c:out value="Sí"></c:out>
                	</c:if>
                	<c:if test="${!bebida.esCarbonatada}">
                		<c:out value="No"></c:out>
                	</c:if>
                	<!-- <c:out value="${bebida.esCarbonatada}"/> -->
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td>
             	<!-- <td>
             		<c:out value="${bebida.id}"></c:out>
             	</td> -->  	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    
</petclinic:layout>