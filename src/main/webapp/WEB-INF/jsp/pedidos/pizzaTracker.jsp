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
    <h2>Pizza Tracker</h2>
    <c:choose>
    <c:when test="${pedido.tipoEnvio == 'DOMICILIO'}">
    	<c:if test="${pedido.estadoPedido == 'EN COCINA'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/1EnCocina.png" htmlEscape="true" var="pizzaImagen"/>
            <img class="img-responsive" src="${pizzaImagen}"/>
        </div>
       </c:if>
       
       <c:if test="${pedido.estadoPedido == 'PREPARADO'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/2Preparado.png" htmlEscape="true" var="pizzaP"/>
            <img class="img-responsive" src="${pizzaP}"/>
        </div>
       </c:if>
       
       <c:if test="${pedido.estadoPedido == 'EN REPARTO'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/3EnReparto.png" htmlEscape="true" var="pizzaR"/>
            <img class="img-responsive" src="${pizzaR}"/>
        </div>
       </c:if>
       
       <c:if test="${pedido.estadoPedido == 'ENTREGADO'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/4Entregado.png" htmlEscape="true" var="pizzaE"/>
            <img class="img-responsive" src="${pizzaE}"/>
        </div>
       </c:if>
     </c:when>
     
     <c:otherwise>
    	<c:if test="${pedido.estadoPedido == 'EN COCINA'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/L1EnCocina.png" htmlEscape="true" var="pizzaC"/>
            <img class="img-responsive" src="${pizzaC}"/>
        </div>
       </c:if>
       
       <c:if test="${pedido.estadoPedido == 'PREPARADO'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/L2Preparado.png" htmlEscape="true" var="pizzaRP"/>
            <img class="img-responsive" src="${pizzaRP}"/>
        </div>
       </c:if>
       
       <c:if test="${pedido.estadoPedido == 'RECOGIDO'}">
        <div class="col-md-12">
            <spring:url value="/resources/images/L3Recogido.png" htmlEscape="true" var="pizzaRR"/>
            <img class="img-responsive" src="${pizzaRR}"/>
        </div>
       </c:if>
       </c:otherwise>
     </c:choose>
           		
</petclinic:layout>