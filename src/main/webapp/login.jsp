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
    
    <link href="css/style.css" rel="stylesheet">
  </head>

  <body>

  	<!-- admin admin -->
    <div class="container">

      <form name="frmLogin" id="frmLogin" class="form-signin" action="Controleur?page=login" method="post">
        <h2 class="form-signin-heading"><fmt:message key="enregistrement" /></h2>
        
          <input type="text" name="txNomUser" id="txNomUser" class="form-control" placeholder="<fmt:message key="votreNom" />" required autofocus>
        
          <input type="password" name="txMdpUser" id="txMdpUser" class="form-control" placeholder="<fmt:message key="Mdp" />" required>
        
          <input type="checkbox" value="remember-me" name="ckSouvenir" id="ckSouvenir">&nbsp;<fmt:message key="rememberMe" />
         
         <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login" /></button>
         <p class="erreur">${requestScope.messageErreurLogin}</p>
       </form>
 
 
    </div> <!-- /container -->

  </body>
</html>
