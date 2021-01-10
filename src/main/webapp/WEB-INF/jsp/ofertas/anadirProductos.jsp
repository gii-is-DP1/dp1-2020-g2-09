<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <petclinic:layout pageName="ofertas">
    
<form:form modelAttribute="oferta" class="form-horizontal" id="add-oferta-form">
    			<td>Has añadido los productos ${pizzasEnOferta}</td>
			 <table id="pizzasTable" class="table table-striped">
             <thead>
       		 <tr>
            	<th>Pizza</th>
            	<th> </th>
       	 	 </tr>
       		 </thead>
       		 <tbody>
       		 <c:forEach items="${pizzas}" var="pizza">
       		 <tr>
       		 <td>${pizza.nombre}</td>
       		<%-- <td><form:checkbox path="pizzasEnOferta" value="${pizza}"/> Añadir <br>
       		   <form:input label="Cantidad" name="Cantidad" type="number" path="Cantidad"/></td>--%>
       		   <td><spring:url value="/ofertas/{ofertaId}/anadirPizza/{pizzaId}" var="ofertaPizzaUrl">
                    		<spring:param name="ofertaId" value="${oferta.id}"/>
                    		<spring:param name="pizzaId" value="${pizza.id}"/>
	              </spring:url>
	             <%-- <c:out value="${pizza.id}"/>
	              <c:out value="${oferta.id}"/>--%>
   				<a href=" ${fn:escapeXml(ofertaPizzaUrl)}" class="btn btn-default">Ofertar Pizza</a>
   			</td>
       		 </tr>
       		 </c:forEach>
			
       		 
       		 </tbody>
             </table>
             
			<table id="bebidasTable" class="table table-striped">
             <thead>
       		 <tr>
            	<th>Bebida</th>
            	<th> </th>
       	 	 </tr>
       		 </thead>
       		 <tbody>
       		 <c:forEach items="${bebidas}" var="bebida">
       		 <tr>
       		 <td>${bebida.nombre}</td>
       		<%-- <td><form:checkbox path="bebidasEnOferta" value="${bebida}"/> Añadir
       		  <form:input label="Cantidad" name="Cantidad" type="number" path="Cantidad"/></td>	--%> 
       		  <td><spring:url value="/ofertas/{ofertaId}/anadirBebida/{bebidaId}" var="ofertaBebidaUrl">
                    		<spring:param name="ofertaId" value="${oferta.id}"/>
                    		<spring:param name="bebidaId" value="${bebida.id}"/>
	             </spring:url>
   				<a href=" ${fn:escapeXml(ofertaBebidaUrl)}" class="btn btn-default">Ofertar Bebida</a>
   			</td>
       		 </tr>
       		 </c:forEach>

       		 
       		 </tbody>
             </table>
             
             <table id="otrosTable" class="table table-striped">
             <thead>
       		 <tr>
            	<th>Producto</th>
            	<th> </th>
       	 	 </tr>
       		 </thead>
       		 <tbody>
       		 <c:forEach items="${otros}" var="otro">
       		 <tr>
       		 <td>${otro.nombre}</td>
       		<%--  <td><form:checkbox path="otrosEnOferta" value="${otro}"/> Añadir
       		<form:input label="Cantidad" name="Cantidad" type="number" path="Cantidad"/></td>--%>
       		<td><spring:url value="/ofertas/{ofertaId}/anadirOtro/{otroId}" var="ofertaPizzaUrl">
            		 <spring:param name="ofertaId" value="${oferta.id}"/>
            		 <spring:param name="otroId" value="${otro.id}"/>
	       		 </spring:url>
   				<a href=" ${fn:escapeXml(ofertaPizzaUrl)}" class="btn btn-default">Ofertar Producto</a>
   			</td>
       		 </tr>
       		 </c:forEach>

       		 
       		 </tbody>
             </table>
            <%--  <c:forEach var="p" items="${pizzas}">
          	<c:out value="${p.nombre}"/><br>
         	 </c:forEach> --%>
   
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${oferta['new']}">
                        <button class="btn btn-default" type="submit">Añadir oferta</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar oferta</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
 </petclinic:layout>