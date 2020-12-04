package controle;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import dao.ArbitreDAO;
import dao.CourtDAO;
import dao.DaoFactory;
import dao.MysqlDaoFactory;
import dao.TypeTableauDAO;
import dao.JoueurDAO;
import dao.MatchDAO;


/**
 * Servlet implementation class CtlInitialisation
 */
public class CtlInitialisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtlInitialisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		DaoFactory maFactory = null;
		System.out.println("methode init classe Controleur initialisation");  		
		try {
			
		  // choix du ttype de la bdd (mysql) se parametre dans le fichier web.xml
			 maFactory =  DaoFactory.getDaoFactory(Integer.parseInt(getInitParameter("bddtype")));//1 -> mysql
			
			 JoueurDAO joueurDAO = maFactory.getJoueurDAO();
			/*permet d'afficher une liste d'objet sur la page page jsp correspoondant au ttype d'objet enrgistreer des joueurs -> liste des joueurs  */
			joueurDAO.findAll();
			context.setAttribute("joueurDAO", joueurDAO);
			
			ArbitreDAO arbitreDAO = maFactory.getArbitreDAO();
			arbitreDAO.findAll();
			context.setAttribute("arbitreDAO", arbitreDAO);
			
			 
			CourtDAO courtDAO = maFactory.getCourtDAO();
			courtDAO.findAll();
			context.setAttribute("courtDAO", courtDAO);
			
			MatchDAO matchDAO = maFactory.getMatchDAO();
			matchDAO.findAll();
			context.setAttribute("matchDAO", matchDAO);	
			
			TypeTableauDAO tabDAO = maFactory.getTypeTableauDAO();
			tabDAO.findAll();
			context.setAttribute("tabDAO", tabDAO);	
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	@Override
	public void destroy() {
	    String prefix = getClass().getSimpleName() +" destroy() ";
	    ServletContext ctx = getServletContext();
	    try {
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
	        while(drivers.hasMoreElements()) {
	            DriverManager.deregisterDriver(drivers.nextElement());
	        }
	    } catch(Exception e) {
	        ctx.log(prefix + "Exception caught while deregistering JDBC drivers", e);
	    }
	    ctx.log(prefix + "complete");
	}


}
