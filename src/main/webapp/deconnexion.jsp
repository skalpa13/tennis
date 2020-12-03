<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <head>
  	<%@ include file="/head.jsp" %>	
    <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title><fmt:message key="login" /></title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/signin.css" rel="stylesheet">
    
  </head>

  <body>
  	<%@ include file="/entete.jsp" %>	
  	<div class="container">
  	
  	<h1>	<fmt:message key="messageDeconnexion" />  	</h1>
	<a href="Controleur">Se connecter</a>
  	<%@ include file="/piedDePage.jsp" %>	
</div>
</body>
</html>