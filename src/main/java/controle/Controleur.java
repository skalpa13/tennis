/* servlet front controller unique servlet d'entree dans le site web*/



package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Controleur() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher dispatch = null;
		String page = request.getParameter("page");
		
		if(page==null){//on demarre sur le controleur 
			// On passe par le controleur d'initialisation, puis on arrive par d�faut sur la page de login
			dispatch = context.getRequestDispatcher("/CtlInitialisation");
			dispatch.include(request,response);
			dispatch = context.getRequestDispatcher("/login.jsp");
			dispatch.forward(request,response);
		}else
		{
			if ("login".equals(page))	{
					dispatch = context.getRequestDispatcher("/CtlLogin");
					dispatch.include(request,response);
					if ((Boolean)request.getAttribute("acces")){
						// Si l'utilisateur est un administrateur il acc�de au menu principal
						dispatch = context.getRequestDispatcher("/menu.jsp");
						dispatch.forward(request,response);
					} else {
						// Sinon l'utilisateur est renvoy� vers la page de login
						dispatch = context.getRequestDispatcher("/login.jsp");
						dispatch.forward(request,response);
					}
			}
			else if  ("deconnexion".equals(page) ) {
					// Lorsque l'utilisateur veut se d�connecter
					dispatch = context.getRequestDispatcher("/CtlDeconnexion");
					dispatch.include(request,response);
					dispatch = context.getRequestDispatcher("/deconnexion.jsp");
					dispatch.forward(request,response);
			}
			else if  ("enrJoueur".equals(page) ) {
					// Apr�s avoir enregistr� un joueur, on revient � la page d'enregistrement de joueur
				
				   dispatch = context.getRequestDispatcher("/CtlJoueur");
					dispatch.include(request,response);
				
			}
			else if  ("enrArbitre".equals(page) ) {
					// Lorsque l'utilisateur clique sur enregistrer un arbitre dans le menu
					dispatch = context.getRequestDispatcher("/enregistrementArbitre.jsp");
					dispatch.forward(request,response);
			}
			else if  ("enrAutreArbitre".equals(page) ) {
					// Apr�s avoir enregistr� un arbitre, on revient � la page d'enregistrement d'arbitre
					dispatch = context.getRequestDispatcher("/CtlArbitre");
					dispatch.include(request,response);
					dispatch = context.getRequestDispatcher("/enregistrementArbitre.jsp");
					dispatch.forward(request,response);
			}
			else if  ("enrCourt".equals(page) ) {
					// Lorsque l'utilisateur clique sur enregistrer un court dans le menu
					dispatch = context.getRequestDispatcher("/enregistrementCourt.jsp");
					dispatch.forward(request,response);
			}
			else if  ("enrAutreCourt".equals(page) ) {
					// Apr�s avoir enregistr� un court, on revient � la page d'enregistrement de court
					dispatch = context.getRequestDispatcher("/CtlCourt");
					dispatch.include(request,response);
					dispatch = context.getRequestDispatcher("/enregistrementCourt.jsp");
					dispatch.forward(request,response);
			}
			else if  ("enrMatch".equals(page) ) {
					// Lorsque l'utilisateur clique sur enregistrer un match dans le menu
					dispatch = context.getRequestDispatcher("/CtlMatch");
					dispatch.forward(request,response);
			}
			else if ("enrResultat".equals(page) ) {
				// L'utilisateur a demand� � regarder les r�sultats depuis le menu
				//dispatch = context.getRequestDispatcher("/CtlResultat");
				dispatch = context.getRequestDispatcher("/listeMatch.jsp");
					
				dispatch.forward(request,response);
		}
			else if  ("revenirAuMenu".equals(page) ) {
					dispatch = context.getRequestDispatcher("/menu.jsp");
					dispatch.forward(request,response);
			}
			
		}
		
	}

}
