package controle;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArbitreDAO;
import entites.Arbitre;
import exception.DAOException;

/**
 * Servlet implementation class CtlArbitre
 */
public class CtlArbitre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtlArbitre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		faire(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		faire(request,response);
	}
	
	private void faire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Arbitre unArbitre = new Arbitre();
		unArbitre.setNom(request.getParameter("txNomArbitre"));
		unArbitre.setPrenom(request.getParameter("txPrenomArbitre"));
		unArbitre.setNation(request.getParameter("cbNationArbitre"));
		unArbitre.setNiveau(Integer.parseInt(request.getParameter("cbNiveauArbitre")));
	
		try {
		
			ArbitreDAO arbitreDAO = (ArbitreDAO) context.getAttribute("arbitreDAO");
			arbitreDAO.ajouterArbitre(unArbitre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
