package controle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MysqlDaoFactory;
import dao.OrganisateurDAO;
import exception.DAOConfigurationException;
import exception.DAOException;


public class CtlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CtlLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
    //form login.jsp submit type post  le login existe deja dans la table select 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			
			DaoFactory maFactory =  DaoFactory.getDaoFactory(Integer.parseInt(getInitParameter("bddtype")));//1 -> mysql
			/*test connection 
			   Class.forName("com.mysql.cj.jdbc.Driver");
			    String url = "jdbc:mysql://mysql-acosparla.alwaysdata.net:3306/tennis?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
			
			    Connection  connectJavaSql = DriverManager.getConnection(url,"acosparla","marseille");

			  fin test connection */ 
			
		//	DaoFactory maFactory =  DaoFactory.getDaoFactory(1);
			//	Connection connexion = maFactory.getConnection();
	//		if(connexion != null)
	//			System.out.println("connection reussie");
				
			String nomUser = request.getParameter("txNomUser");
			String mdpUser = request.getParameter("txMdpUser");
			OrganisateurDAO organisateurDAO = maFactory.getOrganisateurDAO();
			String password = organisateurDAO.findByLogin(nomUser);
			if( password != null && mdpUser.equals(password)){
				request.setAttribute("acces", true);
			}
			else
			{
				request.setAttribute("acces", false);
				request.setAttribute("messageErreurLogin", "Vous n'êtes pas autorisé à accéder à l'application.");
			}
		} 
		catch (Exception e) {
			e.getMessage();
		}
	
	
	}
	


}
