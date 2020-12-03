<!DOCTYPE html>
<html lang="en">
<%@page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <head>
  	<%@ include file="/head.jsp" %>	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title><fmt:message key="login" /></title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/signin.css" rel="stylesheet">
    
  </head>

  <body>
  	 <p class="erreur">${requestScope.messageInsert}</p>
		
	
	<a href="CtlJoueur">Back to Insert Player</a>
	<a href="CtlMatch"> Back to Insert Match</a>
	
  	<%@ include file="/piedDePage.jsp" %>	

      <script  src="js/joueur.js"></script>

</body>
</html>