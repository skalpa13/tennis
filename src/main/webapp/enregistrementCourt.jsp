<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
  	<%@ include file="/head.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="enrCourt" /></title>

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
            <h1><fmt:message key="enrCourt" /></h1>
            <p><fmt:message key="noticeEnrNouveauCourt" /></p>
          </div>

	    <form name="frmCourt" id="frmCourt" action="Controleur?page=enrAutreCourt" method="post">
			<p><label><fmt:message key="nom" /></label>
			<input type='text' name='txNomCourt' id='txNomCourt' required ></p>
			<p><button type='submit' class="btn btn-lg btn-primary" ><fmt:message key="enregistrerCourt" /></button></p>
		</form>
		 	<span class="erreurfadeout">${requestScope.msgInsert}</span>
		</div>

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="#" class="list-group-item active"><fmt:message key="listeCourts" /></a>
            <c:forEach var="court" items="${courtDAO.listeCourts }">
            	<a href="#" class="list-group-item">${court}</a>
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
    <script  src="js/fadeOut.js"></script>
   
    
    
    
  </body>
</html>