package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourtDAO;
import entites.Court;
import exception.DAOException;

/**
 * Servlet implementation class CtlCourt
 */
public class CtlCourt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtlCourt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCourt(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCourt(request,response);
	}
	
	private void addCourt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		try {
			
			String nomcourt = request.getParameter("txNomCourt");
			Court unCourt = new Court (nomcourt);
			
			CourtDAO courtDAO = (CourtDAO) context.getAttribute("courtDAO");
			courtDAO.ajouterCourt( unCourt);
		} 
		catch (DAOException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/erreur.jsp"); 
            rd.forward(request, response);
		}		
	}

}
