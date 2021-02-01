<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="error">

	<h2>Algo paso...</h2>
	<p>Lo sentimos, ha ocurrido un error debido a que intentó entrar sin autorización o algún otro tipo de fallo.</p>
	<a href="/">Pulse aquí para ir a la página principal</a>
	
	<br></br>
    <spring:url value="/resources/images/pizza.jpg" var="pizzaImagen"/>
    <img src="${pizzaImagen}"/>

</petclinic:layout>
