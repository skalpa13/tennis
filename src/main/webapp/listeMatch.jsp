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
  
    <link href="css/style.css" rel="stylesheet">
  
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
	<h1>Liste des matchs</h1>

  		<table>
 		<tr>
   
   			<th>Id</th>
   			<th>Joueur 1</th>
   			<th>Joueur 2</th>
   			<th>Arbitre </th>
   			<th>Nom du court </th>
   			<th>Date du match </th>
   			<th>Insert</th>
   		</tr>
   		<c:forEach items="${matchDAO.listeMatchs}" var="match">
			<tr>
	         	<td>${match.identifiant}</td>
	 			<td>${match.joueur1.nom } ${match.joueur1.prenom }</td>
	 			<!--  <td><input type="number" min="0" max="5" name="nbsetjoueur1" value="${match.nbsetsjoueur1}" ></td> -->
	 			
	 			<td>${match.joueur2.nom } ${match.joueur2.prenom }</td>
	 			
	 		  	<td>${match.arbitre.prenom } ${match.arbitre.nom }</td>
	 		 	<td> ${match.court.nom }</td>
				<td> ${match.dateMatch }</td>
				
	         	<td><a href="CtlResultat?id=${match.identifiant}">insert resultat match</a></td>

			</tr>
 		 </c:forEach>
 	 </table>
      <footer>
        <p>&copy; 2016 Company, Inc.</p>
      </footer>
    </div> <!-- /container -->


  </body>
</html>
