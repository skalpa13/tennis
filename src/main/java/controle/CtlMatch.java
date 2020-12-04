package controle;

import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import entites.Arbitre;
import entites.Court;
import entites.Joueur;
import entites.Match;
import exception.DAOConfigurationException;
import exception.DAOException;
import persistance.JoueurDAOImpl;

public class CtlMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Override
	public void init() throws ServletException {
		System.out.println("methode init classe Controleur match");  		
	}	
	
	public CtlMatch() {
        super();
        System.out.println("constructeur classe Controleur match");  
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		System.out.println("do get  Controleur match");  
		
		ServletContext context = getServletContext();
		DaoFactory maFactory = null;
		
		try {
			
			  // choix du ttype de la bdd (mysql) se parametre dans le fichier web.xml
			//	 maFactory =  DaoFactory.getDaoFactory(Integer.parseInt(getInitParameter("bddtype")));// numberformatexception  
				 maFactory =  DaoFactory.getDaoFactory(1);//1 -> mysql	
				//m.a.j page jsp de la liste des matchs 
				 MatchDAO matchDAO = maFactory.getMatchDAO();
				 matchDAO.findAll();
				 context.setAttribute("matchDAO", matchDAO);
				 //liste des tableaux 
				TypeTableauDAO tabDAO = maFactory.getTypeTableauDAO();
				tabDAO.findAll();//remplit la liste 
				context.setAttribute("tabDAO", tabDAO);	
				//la liste de joueuses 
				JoueurDAO joueurDAO = maFactory.getJoueurDAO();
				joueurDAO.findByGenre("M");
				context.setAttribute("joueurDAO", joueurDAO);
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		
		RequestDispatcher dispatch = null;
		dispatch = request.getRequestDispatcher("/organisationMatch.jsp");
		dispatch.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addMatch(request,response);
	}
	
	private void addMatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher rd  = null;
		int  idJoueur1 = Integer.parseInt(request.getParameter("cbJoueur1"));
		JoueurDAO joueurDAO = (JoueurDAO) context.getAttribute("joueurDAO");
		Joueur joueur1 = null;
		try {
			joueur1 = joueurDAO.findByRef(idJoueur1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		int  idJoueur2 = Integer.parseInt(request.getParameter("cbJoueur2"));
		Joueur joueur2 = null;
		try {
			joueur2 = joueurDAO.findByRef(idJoueur2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
		int  idArbitre = Integer.parseInt(request.getParameter("cbArbitre"));
		ArbitreDAO ArbitreDAO = (ArbitreDAO) context.getAttribute("arbitreDAO");
		Arbitre arbitre = ArbitreDAO.findByRef(idArbitre);
	
		int  idCourt = Integer.parseInt(request.getParameter("cbCourt"));
		CourtDAO courtDAO = (CourtDAO) context.getAttribute("courtDAO");
		Court court = null;
		try {
			court = courtDAO.findByRef(idCourt);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		String strDateMatch = request.getParameter("txDateMatch");
		//Convert String strDateMatch dd/mm/yyyy  into java.sql.date yyyy-mm-dd
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
			
		String sqlDateMatch = null;
		try {
			sqlDateMatch = sdf2.format(sdf1.parse(strDateMatch));
		}
		catch (ParseException e) {
				System.out.println(e.getMessage());
		}
			
		Date dateMatch  = Date.valueOf(sqlDateMatch);
			
		Match unMatch = new Match();
		unMatch.setJoueur1(joueur1);
		unMatch.setJoueur2(joueur2);
		unMatch.setArbitre(arbitre);
		unMatch.setCourt(court);
		unMatch.setDateMatch(dateMatch);
			
		MatchDAO matchDAO = (MatchDAO) context.getAttribute("matchDAO");
		int i = 0;
		try {
			i = matchDAO.ajouterMatch(unMatch);
		} catch (Exception e) {
			
			System.out.println("addMatch ctl match: "+ e.getMessage());
			//pour duplicate entry on recup le num error ici
		}
	
		if (i == 1 ) {
			request.setAttribute("messageInsert", "Insert succes");
			rd = request.getRequestDispatcher("/success.jsp");
			rd.forward(request, response);
			
		}
		
		//contrainte 2 joueurs se rencontrent dans un seul match au cours du tournoi 
		//ALTER TABLE match ADD UNIQUE( refjoueur1, refjoueur2) 
		if(i==1062) //ErrorCode()== 1062 MySQLIntegrityConstraintViolationException duplicate entry
    	{
    		request.setAttribute("messageInsert", "match: "+ joueur1.getNom() +" "+joueur1.getPrenom() 
    	    +joueur2.getNom()+ " "+joueur2.getPrenom()  +" deja saisie");
    	    rd = request.getRequestDispatcher("/erreur.jsp"); 
            rd.forward(request, response);
    	}
	}

}
