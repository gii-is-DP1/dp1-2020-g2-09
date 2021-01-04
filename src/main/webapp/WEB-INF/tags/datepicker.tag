<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
        
              
              
<spring:bind path="${name}">

<script>
 $(function () {
 	$("#"+"${name}").datepicker({dateFormat: 'yy/mm/dd'});
 });
</script>

</spring:bind>