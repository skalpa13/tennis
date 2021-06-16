package controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourtDAO;
import entites.Court;
import exception.DAOException;


public class CtlCourt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CtlCourt() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCourt(request,response);
	
		RequestDispatcher dispatch = request.getRequestDispatcher("/enregistrementCourt.jsp");
		dispatch.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCourt(request,response);
	}
	
	private void addCourt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		CourtDAO courtDAO = (CourtDAO) context.getAttribute("courtDAO");
		courtDAO.findAll();
		RequestDispatcher rd =null;
		String nomcourt = request.getParameter("txNomCourt");
		Court unCourt = new Court (nomcourt);
			
			 //modif le juin 2021 permet de mettre ajour la liste des cours sur la page jsp
			if( courtDAO.ajouterCourt( unCourt)== 1062) {
				request.setAttribute("messageInsert", "nom de court :  deja saisie");
				 rd = request.getRequestDispatcher("/erreur.jsp"); 
                rd.forward(request, response);
			}	
	}

}
