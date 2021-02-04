<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/pizza.jpg" var="pizzaImagen"/>
    <img src="${pizzaImagen}"/>

    <h2>Algo paso...</h2>

	<p>Problema del propio servidor, lo sentimos.</p>
	
    <p>${mensaje}</p>

</petclinic:layout>
