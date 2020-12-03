/*servlet sur les joueurs permet ajout */
package controle;

import java.io.IOException;
import java.sql.SQLException;
import exception.DAOConfigurationException;
import exception.DAOException;
import java.lang.Exception;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.DaoFactory;
import dao.JoueurDAO;
import dao.TypeTableauDAO;
import entites.Joueur;

import persistance.JoueurDAOImpl;

public class CtlJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("methode init classe Controleur joueur");  		
		
		ServletContext context = getServletContext();
		
	}	
	public CtlJoueur() {
    	
    	super();
    	System.out.println("constructeur classe Controleur joueur");  	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		DaoFactory maFactory = null;
		System.out.println("do get Controleur joueur");  		
		try {
			// choix du ttype de la bdd (mysql) se parametre dans le fichier web.xml
			 maFactory =  DaoFactory.getDaoFactory(1);//1 -> mysql
			JoueurDAO joueurDAO = maFactory.getJoueurDAO();
			/*permet d'afficher une liste d'objet sur la page page jsp correspoondant au ttype d'objet enrgistreer des joueurs -> liste des joueurs  */
			joueurDAO.findByGenre("M");
			context.setAttribute("joueurDAO", joueurDAO);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		RequestDispatcher dispatch = null;
		dispatch = request.getRequestDispatcher("/enregistrementJoueur.jsp");
		dispatch.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			addPlayer(request,response);
			
	}	
	
	private void addPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		ServletContext context = getServletContext();
		
		RequestDispatcher rd  = null;
		String nomJoueur = null, prenomJoueur = null;
		
	
		nomJoueur = request.getParameter("txNomJoueur");
		prenomJoueur = request.getParameter("txPrenomJoueur");
		String sexeJoueur = request.getParameter("gender");
		String nomNationJoueur = request.getParameter("cbNationJoueur");
			//new player dao 
		JoueurDAOImpl joueurDAO = (JoueurDAOImpl) context.getAttribute("joueurDAO");
			
		int i = 0;
		try {
			i = joueurDAO.ajouterJoueur(new Joueur (nomJoueur, prenomJoueur, sexeJoueur, nomNationJoueur));
		} 
		catch (Exception e) {
			//le 11/05/2020 ne passe par le catch si MySQLIntegrityConstraintViolationException duplicate entry
			System.out.println("addPlayer ctl joueur: "+ e.getMessage());
		}
    	if (i == 1 ) {
    		request.setAttribute("messageInsert", "Insert succes");
    		rd = request.getRequestDispatcher("/success.jsp");
    		rd.forward(request, response);
    	}
    	
    	if( i == 1205 ) //1205 Lock wait timeout exceeded; try restarting transaction
        {
           request.setAttribute("messageInsert", "Lock wait timeout exceeded; try restarting transaction");
           rd = request.getRequestDispatcher("/erreur.jsp"); 
           rd.forward(request, response);
        }
        if( i == 1062) //ErrorCode()== 1062 MySQLIntegrityConstraintViolationException duplicate entry
    	{
    	   request.setAttribute("messageInsert", "joueur: "+ prenomJoueur +" "+ nomJoueur+  " deja saisie");
    	   rd = request.getRequestDispatcher("/erreur.jsp"); 
           rd.forward(request, response);
       }
    
    		/*if (i == 0 ) {
    	   System.out.println("succes failed");
		        request.setAttribute("messageInsert", "Insert failed");
			   rd = request.getRequestDispatcher("/erreur.jsp"); //no workin work in progress
           rd.forward(request, response);
		}*/	
	
	}
}
