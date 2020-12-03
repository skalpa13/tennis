<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <head>
  	<%@ include file="/head.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="planifMatch" /></title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/jumbotron.css" rel="stylesheet">
	<link href="jQueryUI/css/jquery-ui.min.css" rel="stylesheet">
	<script src="jQueryUI/js/jquery.js"></script>
	<script src="jQueryUI/js/jquery-ui.min.js"></script>
	<script src="jQueryUI/js/datepicker-fr.js"></script>
  </head>

  <body>

	<%@ include file="/entete.jsp" %>
    <div class="container">
      <!-- Example row of columns -->
       <div class="row row-offcanvas row-offcanvas-right">
       <div class="col-xs-12 col-sm-9">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1><fmt:message key="enrResultat" /></h1>
        <p><fmt:message  key="texteEnrResultat" /></p>
    </div>

    <h1>Insertion des sets pour le match</h1>
    <form name="frmJoueur" id="frmJoueur" action="CtlResultat" method="post">
		
			 <input  type=hidden  name=idmatch  value="${unMatchDAO.identifiant }" >
		
			
			<p>
			  <label><fmt:message key="nom" /></label>
			 <input type=text name=txNomJoueur1   value="${unMatchDAO.joueur1.nom } ${unMatchDAO.joueur1.prenom }"   disabled>
			</p>
			<p>
				<label>nbre de set joueur 1 </label>
				 <input type=number  min=0 max=3  name=nbsetjoueur1 value="${unMatchDAO.nbsetsjoueur1}"/>
			</p>
			
			<p>
			  <label><fmt:message key="nom" /></label>
			 <input type=text name=txNomJoueur2  value="${unMatchDAO.joueur2.nom } ${unMatchDAO.joueur2.prenom }" disabled>
			 </p>
			<p>
				<label>nbre de set joueur 2 </label>
			    <input type=number min=0 max=3  name=nbsetjoueur2 value="${unMatchDAO.nbsetsjoueur2}"/>
			</p>
			<p>
				<button type='submit' id="btnAjoutJoueur" class="btn btn-lg btn-primary" >Enregistrer resultat match</button>
			</p>
		</form>
       <footer>
        <p>&copy; 2016 Company, Inc.</p>
      </footer>
    </div> <!-- /container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>
    	$.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
		$( "#txDateMatch" ).datepicker({
			inline: true
		});
		</script>
  </body>
</html>
