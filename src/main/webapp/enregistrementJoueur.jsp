<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
  	<%@ include file="/head.jsp" %>
    <meta charset="utf-8">
   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title><fmt:message key="enrNouveauJoueur" /></title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/offcanvas.css" rel="stylesheet">

  
  </head>

  <body>
 	<%@ include file="/entete.jsp" %>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">
      
      <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1><fmt:message key="enrNouveauJoueur" /></h1>
            <p><fmt:message key="noticeEnrNouveauJoueur" /></p>
          </div>

	    <form name="frmJoueur" id="frmJoueur" action="Controleur?page=enrJoueur" method="post">
			<p>
			  <label><fmt:message key="nom" /></label>
			 <input type='text' name='txNomJoueur' id='txNomJoueur' required>
			</p>
			<p>
			  <label><fmt:message key="prenom" /></label>
			 <input type='text' name='txPrenomJoueur' id='txPrenomJoueur' required></p>
			<p>
			 <label><fmt:message key="sexe" /> </label>
			   <input type="radio" name="gender" value="F" checked> Feminin 
			   <input type="radio" name="gender" value="M" > Masculin
		     </p>
			
			
			<p><label><fmt:message key="nation" /></label>
			<select name='cbNationJoueur' >
 				<%@ include file="/nations.html" %>			
 			</select>
			</p>
			<p>
			<button type='submit' id="btnAjoutJoueur" class="btn btn-lg btn-primary" >
			<fmt:message key="enregistrerJoueur" /></button></p>
		 <p class="erreur">${requestScope.messageInsert}</p>
		 <p><span class="erreurfrontend">erreur insertion only letters! </span>  </p>
	
	
		</form>
		
		</div>

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="#" class="list-group-item active"><fmt:message key="listeJoueurs" /></a>
            <c:forEach var="joueur" items="${joueurDAO.listeJoueurs }">
            	<a href="#" class="list-group-item">${joueur.prenom } ${joueur.nom }</a>
            </c:forEach>
          </div>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

      <hr>
 	<%@ include file="/piedDePage.jsp" %>	
    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/offcanvas.js"></script>
   <script  src="js/joueur.js"></script>
  
  </body>
</html>
