<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<petclinic:layout pageName="home">   
    <div class="row">
    	<h2>Bienvenido a Roto's Pizza ${title}</h2>
        
     	<sec:authorize access="anonymous">
	<div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pizza.jpg" htmlEscape="true" var="pizzaImagen"/> 
            <img class="img-responsive" src="${pizzaImagen}"/>
        </div>
    </div>
    <h3>Este proyecto ha sido realizado por:  ${group}</h3>
    	<ul>
    	<c:forEach items="${persons}" var="person">
    		<h4>${person.firstName}${person.lastName}</h4>
    	</c:forEach>
<<<<<<< HEAD
    	</ul>
    </div>
	</sec:authorize>
        <sec:authorize access="hasAnyAuthority('administrador')"  >

 <!--   <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como administrador(admin1 -> admin1)).</b></p> </div> -->
 
 
     <table id="administradorTable" class="table table-striped" aria-describedby="gestionGeneral">
            <thead>
            <tr>
                <th scope="col">GESTION DEL NEGOCIO</th>
            </tr>
            </thead>
            <tbody> 
            <c:forEach items="{1}" var="nums">  
                <tr>
                     <td>
     					<div><a href="/allOfertas">Ofertas</a></div>
                    </td>
                </tr>
                <tr>
                    <td>
    					<div><a href="/allReclamaciones">Reclamaciones</a></div>
                    </td>
                </tr>
                <tr>
                     <td>
     					<div><a href="/allCartas">Carta</a></div>
                    </td>
                </tr>
                <tr>
                    <td>
                         <div> <a href="/allMesas">Mesas</a></div>
                    </td>
                </tr>
              <!--  <tr>
                    <td>
    				 <div><a >H-020: Iniciar sesión(HECHO)</a></div> 
                    </td>
                </tr>-->
            </c:forEach>
            </tbody>
        </table>
        
        
    <table id="administradorTable" class="table table-striped" aria-describedby="gestionPersonal">
            <thead>
            <tr>
                <th scope="col">GESTION DE PERSONAL</th>
            </tr>
            </thead>
            <tbody> 
            <c:forEach items="{1}" var="nums">  
           		 <tr>
                 	<td>
                         <div> <a href="/allCuentas">Gestion de cuentas</a></div>
                    </td>
           		</tr>
          		<tr>         
                    <td>
                         <div><a href="/allCocineros">Gestión de cocineros.</a></div>
                    </td>
         		</tr>
         		<tr>           
                    <td>
    					 <div><a href="/allRepartidores">Gestión de repartidores</a></div>
                    </td>
          		</tr>          
            </c:forEach>
            </tbody>
        </table>
        
        
    <table id="administradorTable" class="table table-striped" aria-describedby="Informes">
            <thead>
            <tr>
                <th scope="col">INFORMES</th>
            </tr>
            </thead>
            <tbody> 
            <c:forEach items="{1}" var="nums">  
                <tr>
                     <td>
    					 <div><a href="/informe/IngredientesMasUsados">Informe de ingredientes</a></div>
                    </td>
                </tr>
                <tr>
                    <td>
     					<div><a href="/informe/CaducidadIngredientes">Informe de caducidad</a></div>
                    </td>
                </tr>
                <tr>
                    <td>
     					<div><a href="/informe/MesasMasUsadas">Informe de mesas más reservadas</a></div>
                    </td>
                </tr>
                <tr>
                    <td>
     					<div><a href="/informe/PizzasMasPedidos">Informe pizzas más vendidas</a></div>
     				</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
         			
     </sec:authorize>
     
        <sec:authorize access="hasAnyAuthority('cliente')"  >

    <!-- <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como cliente(margarcac1 -> margarcac1)).</b></p> </div> -->
         <table id="clienteTable" class="table table-striped" aria-describedby="Cliente">
            <thead>
           	 	<th scope="col">Acceder a</th>
            </thead>
            <tbody>
            <tr>
                <th scope="col"><a href="/clientes/DetallesPerfil">Datos personales</a></th>
            </tr>            
             <tr>
                <th scope="col"><a href="/pedidos/user">Mis pedidos</a></th>
            </tr>
             <tr>
                <th scope="col"><a href="/pedidos/new">Nuevo pedido</a></th>
            </tr>
             <tr>
                <th scope="col"><a href="/reservas/user">Mis reservas</a></th>
            </tr>
             <tr>
                <th scope="col"><a href="/cartas/1/VerCarta">Carta y Ofertas</a></th>
            </tr>
            <tr>
                <th scope="col"><a href="/reclamaciones/user">Mis reclamaciones</a></th>
            </tr>

            </tbody>
            
        </table>
    		
    	</sec:authorize>
     	<sec:authorize access="hasAnyAuthority('cocinero')"  >

    <!-- <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como cocinero(cocinero1 -> cocinero1)).</b></p> </div> -->
     
     	<table id="cocineroTable" class="table table-striped" aria-describedby="Cocinero">
            <thead>     
           	 	<th scope="col"><a href="/pedidos/cocinero">PEDIDOS</a></th>
	
            </thead>            
        </table> 
      		
      	</sec:authorize>
      	<sec:authorize access="hasAnyAuthority('repartidor')"  >

      
     <!-- <div><p><b>HISTORIAS DE USUARIO IMPLEMENTADAS (Iniciar sesión como repartidor(repartidor1 -> repartidor1)).</b></p> </div> -->
      <div>
      <table id="repartidorTable" class="table table-striped" aria-describedby="Repartidor">
            <thead>     
           	 	<th scope="col"><a href="/pedidos/repartidor">PEDIDOS</a></th>
            </thead>            
        </table>
      		
      	</sec:authorize>
     </div>
 

    <!--  <div>
     <a href="/allReservas">Prueba 3: muestreo, post y edit de reservas</a>
	</div>
    <div>
     <a href="/allPedidos">Prueba 4: muestreo de pedidos</a>
    </div>
    <div>
     <a href="/allAdministradores">Prueba 6: muestreo, post, edit y eliminación de administradores</a>
    </div>
    <div>
     <a href="/allReclamaciones">Prueba 8: muestreo, post y edit de reclamaciones uwu</a>
    </div>
       <div>
     <a href="/allPizzas">Prueba 9: muestreo, post y edit de pizzas </a>
    </div>
       <div>
     <a href="/allBebidas">Prueba 10: muestreo, post y edit de bebidas</a>
    </div>
	<div>
     <a href="/allCartas">Prueba 11: muestreo, post y edit de cartas</a>
    </div> -->
    
    
</petclinic:layout>
