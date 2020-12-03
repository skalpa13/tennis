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
        <h1><fmt:message key="planifMatch" /></h1>
        <p><fmt:message  key="textePlanifMatch" /></p>
    </div>

<!--  how to populate a dropdown box using another drop down box dynamically by fetching in database through JSP -->
        <form name="frmMatch" id="frmMatch" action="Controleur?page=enrMatch" method="post">
			<!-- type de match simple messieur /simple dame  
			restriction where sur la liste des joueurs where sexe = "M" 
			-->
			<p>
				<label>Type tableau</label>
				<select name="cbNomTableau"  id="cbNomTableau" size="1" >
					<!-- <option><fmt:message key="selectionListe" /> -->
					<c:forEach var="typetab" items="${tabDAO.listeTypeTableau }"> 
					<!--context.setAttribute("tabDAO", tabDAO) dans CltInitialisation.java  ;;; 
					listeTypeTableau getListeTypeTableau() -> TypeTableauDAOImpl
					-->
						<option value="${typetab.nomTableau}"  >${typetab.nomTableau }
					   <!--   <c:set var="myVar" value="${typetab.nomTableau}" /> -->
					     <c:set var="myVar" value="${typetab.nomTableau}" />
					    
					    </option>
					</c:forEach>
				
				</select>
			${myVar}
		
			
			
			<p>
				<label><fmt:message key="joueur1" /></label>
				<select name="cbJoueur1"  id="cbJoueur1" size="1" >
					<option><fmt:message key="selectionListe" />
					<c:forEach var="joueur" items="${joueurDAO.listeJoueurs }">
						<option value="${joueur.id}">${joueur.nom } ${joueur.prenom }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label><fmt:message key="joueur2" /></label>
				<select name="cbJoueur2"  id="cbJoueur2"  size="1">
					<option><fmt:message key="selectionListe" />
					<c:forEach var="joueur" items="${joueurDAO.listeJoueurs}">
						<option value="${joueur.id}">${joueur.nom } ${joueur.prenom }</option>
				
				
					</c:forEach>
				</select>
			</p>
			<p><label><fmt:message key="arbitre" /></label>
			<select name="cbArbitre"  id="cbArbitre"  size="1">
				<option><fmt:message key="selectionListe" />
				<c:forEach var="arbitre" items="${arbitreDAO.listeArbitres }">
					<option value="${arbitre.id}">${arbitre.nom } ${arbitre.prenom }</option>
				</c:forEach>
			</select></p>
			<p><label><fmt:message key="court" /></label>
			<select name="cbCourt"  id="cbCourt"  size="1">
					<option><fmt:message key="selectionListe" />
				<c:forEach var="court" items="${courtDAO.listeCourts }">
					<option value="${court.numero}">${court.nom }</option>
				</c:forEach>
			</select></p>
			<p>
				<label><fmt:message key="dateMatch" /></label>
				<input type="text" name="txDateMatch" id="txDateMatch" ></p>
			<p>
			    <button type='submit' class="btn btn-lg btn-primary" ><fmt:message key="enregistrerMatch" /></button></p>
		</form>
      
      </div>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="#" class="list-group-item active"><fmt:message key="listeMatchs" /></a>

            <c:forEach var="match" items="${matchDAO.listeMatchs }">
            	<a href="#" class="list-group-item">
            	    ${match.joueur1.prenom } ${match.joueur1.nom }
            	    ${match.joueur2.prenom } ${match.joueur2.nom }
            	    ${match.arbitre.prenom } ${match.arbitre.nom }
            	    ${match.court.nom }
            	    ${match.dateMatch}
            	    <!--  SELECT date_format(dateMatch, '%d-%m-%Y')-->
              	</a>
            </c:forEach>
          </div>
        </div><!--/.sidebar-offcanvas--> 
      </div><!--/row-->

      <hr>

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
