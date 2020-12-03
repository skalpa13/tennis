package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import entites.Match;


public class CtlResultat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MatchDAO matchDAO ;
	@Override
	public void init() throws ServletException {
		System.out.println("methode init classe Controleur resultat");  		
	}	
	
	public CtlResultat() {
        super();
        System.out.println("constructeur classe Controleur resultat");  
        matchDAO = null ;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int  idMatch = Integer.parseInt(request.getParameter("id"));
	    System.out.println("do get Controleur resultat idmatch: "+idMatch);
	    ServletContext context = getServletContext();
        Match unMatch  = null;
		MatchDAO matchDAO = (MatchDAO) context.getAttribute("matchDAO");
		try {
			 unMatch = matchDAO.findByRef(idMatch);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//recuperation dans la page jsp enregistrementResultatByMatch ${unMatchDAO.**** }
		context.setAttribute("unMatchDAO", unMatch);
		
		RequestDispatcher dispatch = null;
		dispatch = request.getRequestDispatcher("/enregistrementResultatByMatch.jsp");
		dispatch.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retour de la jsp saisie des resultats de match
		System.out.println("do post ctlr resultat");
		System.out.println(request.getParameter("idmatch"));
	
		System.out.println(request.getParameter("nbsetjoueur1"));
		System.out.println(request.getParameter("nbsetjoueur2"));
	
		int  idMatch      = Integer.parseInt(request.getParameter("idmatch"));
		int nbsetjoueur1  = Integer.parseInt(request.getParameter("nbsetjoueur1"));
		int nbsetjoueur2  = Integer.parseInt(request.getParameter("nbsetjoueur2"));
		
		//crer un objet match
		
		
		
		ServletContext context = getServletContext();
		MatchDAO matchDAO = (MatchDAO) context.getAttribute("matchDAO");
		
		
		
		RequestDispatcher rd  = null;
		
		int success = 0;
		Match match = null;
		try {
			match = matchDAO.findByRef(idMatch);
			match.setNbsetsjoueur1(nbsetjoueur1);
			match.setNbsetsjoueur2(nbsetjoueur2);
		    success	= matchDAO.ajouterScoreMatch(match);
		} catch (Exception e) {
			
			System.out.println("addMatch ctl match: "+ e.getMessage());
			//pour duplicate entry on recup le num error ici
		}
		
		if (success == 1 ) {
			
			context.setAttribute("unMatchDAO", match);
			request.setAttribute("messageInsert", "Insert succes");
			rd = request.getRequestDispatcher("/success.jsp");
			rd.forward(request, response);
			
		}
	}

}
