package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DaoFactory;
import dao.MysqlDaoFactory;
import dao.OrganisateurDAO;
import exception.DAOException;


public class OrganisateurDAOImpl implements OrganisateurDAO {
	
	final String ORDRE_FIND_BY_LOGIN = "select password from utilisateur where login=?";

	
	
	private DaoFactory daoFactory;

	public OrganisateurDAOImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public String findByLogin(String login) throws DAOException {	
		String password = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pstm = connexion.prepareStatement(ORDRE_FIND_BY_LOGIN);
			pstm.setString(1, login);
	//     tentative injection sql
	//		String sql ="select password from utilisateur where login='" + login + "'";
	//		Statement stmt =  connexion.createStatement();
	//		ResultSet resultSet = stmt.executeQuery(sql);
			ResultSet resultSet = pstm.executeQuery();
			if (resultSet.next()){
				password =  resultSet.getString(1);
			}
			connexion.close();
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return password;
	}
	
	

}
