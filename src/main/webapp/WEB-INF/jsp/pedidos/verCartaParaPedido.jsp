<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
   <%-- ESTA ES LA VISTA EN LA QUE UN CLIENTE ELIGE LOS PRODUCTOS DE LA CARTA Y LOS AÑADE, EL ADMINISTRADOR PUEDE AÑADIR PRODUCTOS--%>
    
<petclinic:layout pageName="carta">
    <h2>Carta</h2>
    
    <h3>Pizzas Personalizadas</h3>
    <!--<c:out value="${cartaId}"></c:out>-->
	    <sec:authorize access="hasAnyAuthority('administrador')"  >
	   			<spring:url value="/cartas/{cartaId}/pizzas" var="listaPizzas">
		         <spring:param name="cartaId" value="${cartaId}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(listaPizzas)}" class="btn btn-default">Añadir pizza a la carta</a>
    	</sec:authorize>
    	<sec:authorize access="hasAnyAuthority('cliente')"  >
	   			<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new" var="crearPizzaPers">
		         <spring:param name="cartaId" value="${cartaId}"/> 
		         <spring:param name="pedidoId" value="${pedido.id}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(crearPizzaPers)}" class="btn btn-default">Crear Pizza Personalizada</a>
    	</sec:authorize>

	<table id="pizzasPersonalizadasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tamaño producto</th>
            <th>Coste</th>
            <th>Tipo Masa</th>
            <th>Ingredientes</th>
         
            <th></th>
             <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${PizzasP.pizzasList}" var="pizza">
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
             		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/edit" var="pizzaUrl">
             				<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pizzaUrl)}" class="btn btn-default">Editar</a>
             	</td>
             	<td>
             	
             		
             		<sec:authorize access="hasAnyAuthority('cliente')"  >
                    <spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirPizza/{pizzaId}" var="pedidoPizzaUrl">
                    		<spring:param name="pedidoId" value="${pedido.id}"/>
                    		<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoPizzaUrl)}" class="btn btn-default">Añadir Pizza</a>
   					</sec:authorize>
               </td> 
             	
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    <br/> 
    <h3>Ofertas</h3>
	    <table id="ofertasTableCarta" class="table table-striped">
	        <thead>
	        <tr>
	            <th>Nombre</th>
	            <th>Tamaño producto</th>
	            <th>Coste</th>
	            <th>Fin Oferta</th>
	            <th>Nivel socio</th>
	            <th>Descripción</th>
	            <th></th>
	            
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${ofertaPedido}" var="oferta">
	            <tr>
	            <!-- <input  value="${pizza.id}">  -->
	               <td>
	                    <c:out value="${oferta.name}"/>
	               </td>
	                <td>
	             		<c:out value="${oferta.tamanoOferta}"/>
	             	</td>
	               <td>
	                	<c:out value="${oferta.coste}"/>
	             	</td>
	             	<td>
	             		<c:out value="${oferta.fechaFinal}"/>
	             	</td>
	             	
	             	<td>
	             		<c:out value="${oferta.nivelSocio}"/>
	             	</td>
	             	 <td>
	             		<ul>
	             			<c:forEach items="${oferta.pizzasEnOferta}" var="pizza">
	             				<li>
	             					<c:out value="${pizza.nombre}"/>
	             				</li>
	             			</c:forEach>
	             		</ul>
	             		<ul>
	             			<c:forEach items="${oferta.bebidasEnOferta}" var="bebida">
	             				<li>
	             					<c:out value="${bebida.nombre}"/>
	             				</li>
	             			</c:forEach>
	             		</ul>
	             		<ul>
	             			<c:forEach items="${oferta.otrosEnOferta}" var="otro">
	             				<li>
	             					<c:out value="${otro.nombre}"/>
	             				</li>
	             			</c:forEach>
	             		</ul>
	             		<ul>
	             			<sec:authorize access="hasAnyAuthority('cliente')"  >
             					<spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirOferta/{ofertaId}" var="añadirOferta">
             					<spring:param name="cartaId" value="${cartaId}"/>
             					<spring:param name="pedidoId" value="${pedido.id}"/>
								<spring:param name="ofertaId" value="${oferta.id}"/>
				 			</spring:url>
							<a href="${fn:escapeXml(añadirOferta)}" class="btn btn-default">Añadir Oferta</a>
							</sec:authorize>
	             		</ul>
	             	</td>
	             	
	            </tr>
	        </c:forEach>
	        </tbody>
	    </table>
	    <br/> 
	<h3>Pizzas</h3>
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
            <!-- <input  value="${pizza.id}">  -->
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
                    <spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirPizza/{pizzaId}" var="pedidoPizzaUrl">
                    		<spring:param name="pedidoId" value="${pedido.id}"/>
                    		<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="pizzaId" value="${pizza.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoPizzaUrl)}" class="btn btn-default">Añadir Pizza</a>
   					</sec:authorize>
               </td> 
             	<td>
             		<sec:authorize access="hasAnyAuthority('administrador')"  >
			    		<spring:url value="/cartas/{cartaId}/pizza/{pizzaId}/deleteFromCarta" var="quitarPizza">
				        	<spring:param name="cartaId" value="${cartaId}"/> 
				        	<spring:param name="pizzaId" value="${pizza.id}"/> 
				    	</spring:url>
						<a href="${fn:escapeXml(quitarPizza)}" class="btn btn-default">Eliminar de la carta</a>
					</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <h3>Bebidas</h3>
    <sec:authorize access="hasAnyAuthority('administrador')"  >
	    <spring:url value="/cartas/{cartaId}/bebidas" var="listaBebidas">
		         <spring:param name="cartaId" value="${cartaId}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(listaBebidas)}" class="btn btn-default">Añadir bebida a la carta</a>
	</sec:authorize>
    <table id="bebidasTableCarta" class="table table-striped">
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
                	<!-- <c:out value="${bebida.esCarbonatada}"/> -->
             	</td>
             	<td>
             		<c:out value="${bebida.tamano}"></c:out>
             	</td>
             	<td>
             		<c:out value="${bebida.coste}"/>
             	</td>
             	<!-- <td>
             		<c:out value="${bebida.id}"></c:out>
             	</td> -->  
             	<td>
             		<sec:authorize access="hasAnyAuthority('cliente')"  >
                    <spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirBebida/{bebidaId}" var="pedidoBebidaUrl">
                    		<spring:param name="pedidoId" value="${pedido.id}"/>
                    		<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="bebidaId" value="${bebida.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoBebidaUrl)}" class="btn btn-default">Añadir Bebida</a>
   					</sec:authorize>
               </td> 	
             	<td>
             		<sec:authorize access="hasAnyAuthority('administrador')"  >
			    		<spring:url value="/cartas/{cartaId}/bebida/{bebidaId}/deleteFromCarta" var="quitarBebida">
				        	<spring:param name="cartaId" value="${cartaId}"/> 
				        	<spring:param name="bebidaId" value="${bebida.id}"/> 
				    	</spring:url>
						<a href="${fn:escapeXml(quitarBebida)}" class="btn btn-default">Eliminar de la carta</a>
					</sec:authorize>
             	</td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    <h3>Otros</h3>
    <sec:authorize access="hasAnyAuthority('administrador')"  >
	    <spring:url value="/cartas/{cartaId}/otros" var="listaOtrosVer">
		         <spring:param name="cartaId" value="${cartaId}"/> 
		    </spring:url>
		    <a href="${fn:escapeXml(listaOtrosVer)}" class="btn btn-default">Añadir otro a la carta</a>
	</sec:authorize>
    <table id="otrosTableCarta" class="table table-striped">
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
                    <spring:url value="/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirOtros/{otrosId}" var="pedidoOtrosUrl">
                    		<spring:param name="pedidoId" value="${pedido.id}"/>
                    		<spring:param name="cartaId" value="${cartaId}"/>
	                        <spring:param name="otrosId" value="${otro.id}"/>
	                </spring:url>
   					<a href=" ${fn:escapeXml(pedidoOtrosUrl)}" class="btn btn-default">Añadir</a>
   					</sec:authorize>
               </td> 
             	<td>
             		<sec:authorize access="hasAnyAuthority('administrador')"  >
			    		<spring:url value="/cartas/{cartaId}/otros/{otrosId}/deleteFromCarta" var="quitarOtro">
				        	<spring:param name="cartaId" value="${cartaId}"/> 
				        	<spring:param name="otrosId" value="${otro.id}"/> 
				    	</spring:url>
						<a href="${fn:escapeXml(quitarOtro)}" class="btn btn-default">Eliminar de la carta</a>
					</sec:authorize>
             	</td> 	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    
</petclinic:layout>