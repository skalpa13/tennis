<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <head>
  	<%@ include file="/head.jsp" %>	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title><fmt:message key="menu" /></title>

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
            <h1><fmt:message key="menuGeneral" /></h1>
            <p><fmt:message key="petitTexte" /></p>
          </div>
          <div class="row">
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="enrJoueur" /></h2>
              <a href="Controleur?page=enrJoueur">
              	<img class="lien" src="img/joueur.jpg" alt="joueur" title="joueur" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="enrArbitre" /></h2>
              <a href="Controleur?page=enrArbitre">
              	<img class="lien" src="img/arbitre.jpg" alt="arbitre" title="arbitre" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="enrCourt" /></h2>
              <a href="Controleur?page=enrCourt">
              	<img class="lien" src="img/court.jpg" alt="court" title="court" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="planifMatch" /></h2>
              <a href="Controleur?page=enrMatch">
              	<img class="lien" src="img/match.jpg" alt="match" title="match" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="enrResultat" /></h2>
              <a href="Controleur?page=enrResultat">
              	<img class="lien" src="img/resultats.jpg" alt="resultats" title="resultats" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
            <div class="col-xs-6 col-lg-4">
              <h2><fmt:message key="deco" /></h2>
              <a href="Controleur?page=deconnexion">
              	<img class="lien" src="img/logout.png" alt="logout" title="logout" />
              </a>
            </div><!--/.col-xs-6.col-lg-4-->
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->
       
     
        <!--/.sidebar-offcanvas-->
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
    <script src="js/offcanvas.js"></script>
  </body>
</html>
