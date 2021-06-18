package controle;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
	List<Court> listeCourts;
    public CtlCourt() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//never pass by this method do get 
	   /* System.out.println("doGet");
		RequestDispatcher dispatch = request.getRequestDispatcher("/enregistrementCourt.jsp");
		dispatch.forward(request,response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost");
		String nomcourt = request.getParameter("txNomCourt");
        RequestDispatcher rd = null;
		
		if(addCourt(nomcourt) == 1062) {
			request.setAttribute("msgInsert", "nom de court :  deja saisie");
		}
		else {
			request.setAttribute("msgInsert", "insert with success");
		}
		
		
		//la liste m.a.J  avec new  insert  le param list contentant cette nouvelle liste est transmis à la page JSP enregistrementCourt.jsp
		//modif juin 2021   <form  action="Controleur?page=enrAutreCourt" mthod  getRequestDispatcher est utilisé dans le controleur 
		//commentez les lignes ci dessous pour eviter exception 
		//	rd = request.getRequestDispatcher("/enregistrementCourt.jsp"); 
        //   rd.forward(request, response);	
		
		
		
		
		
	}
	
	private int addCourt(String nomCourt)
	{
		ServletContext context = getServletContext();
		CourtDAO courtDAO = (CourtDAO) context.getAttribute("courtDAO");
		//courtDAO.findAll();
		//RequestDispatcher rd = null;
		
		Court unCourt = new Court (nomCourt);
	   return courtDAO.ajouterCourt( unCourt);
	
	}

}
