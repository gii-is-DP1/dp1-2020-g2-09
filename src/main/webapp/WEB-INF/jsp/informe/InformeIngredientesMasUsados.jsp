<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<petclinic:layout pageName="InformeClientesMasUsados">
    <h2>Informe Ingredientes Mas Usados</h2>

    <table id="IngredienteTable" class="table table-striped">
        <thead>
        <tr>
            <th>Ingrediente</th>
            <th>Cantidad</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mapa}" var="item">
            <tr>
                <td>
                    <c:out value="${item.key}"/>
                </td>
                <td>
                	<c:out value="${item.value}"/>
             	</td>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>