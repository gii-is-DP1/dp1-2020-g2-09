<%-- <!--<%@ page session="false" trimDirectiveWhitespaces="true" %> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%-- <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %> --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>

<%-- <petclinic:layout pageName="pedidos">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaPedido").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
   <jsp:body>
    <h2>
        <c:if test="${pedido['new']}">Nuevo </c:if> Pedido
    </h2>
    precio, gastode envio, direccion, fecha edido, estado pedido, tipo pago, tipo envio
    <form:form modelAttribute="oferta" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Precio" name="precio"/>
            <petclinic:inputField label="Fecha Pedido" name="fechaPedido"/>
            <petclinic:inputField label="Fecha Pedido" name="fechaPedido"/>
			<div class="control-group">
			<petclinic:selectField name="tamanoProducto" label="Tamaño producto" names="${tamanoProducto}" size="2"/>
			<petclinic:selectField name="nivelSocio" label="Nivel Socio" names="${nivelSocio}" size="3"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pedido['new']}">
                        <button class="btn btn-default" type="submit">Añadir pedido</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar pedido</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
     </jsp:body>
</petclinic:layout>--> --%>