package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import exception.DAOConfigurationException;
import exception.DAOException;

public abstract class DaoFactory {

	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SYBASE = 3;
	protected DataSource ds;
	public static DaoFactory getDaoFactory(int whichFactory)   {
	 switch (whichFactory)
	 {
				case MYSQL: return  MysqlDaoFactory.getInstance();
			//	case ORACLE : return new OracleDAOFactory();
			//	case SYBASE : return new SybaseDAOFactory();
	 }
	 return null;
    }

/* Méthode chargée de fournir une connexion à la base de données */
public Connection getConnection()  {

	
	Connection connexion = null;
	try {
    //   	connexion = ds.getConnection();
    //    connexion.setAutoCommit(false);
	
		   Class.forName("com.mysql.cj.jdbc.Driver");
		    String url = "jdbc:mysql://mysql-acosparla.alwaysdata.net:3306/tennis?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		
		    connexion = DriverManager.getConnection(url,"acosparla","marseille");

	
	
	
	} catch (Exception e) {
		//throw new DAOException("Connexion impossible.", e);
	    System.out.println("echec Connection: "+ e.getMessage()); //herou java.lang.NullPointerException
	}
   	return connexion;
}
public void closeConnexion(Connection connexion) throws Exception {
	try {
		connexion.close();
	} catch (Exception e) {
		throw new Exception("Fermeture connexion incorrecte.", e);
	}
}

   public abstract OrganisateurDAO getOrganisateurDAO();
  
   public abstract ArbitreDAO getArbitreDAO();
	
   public abstract JoueurDAO getJoueurDAO();

    public abstract CourtDAO getCourtDAO();

    public abstract MatchDAO getMatchDAO();
    public abstract TypeTableauDAO getTypeTableauDAO();
 
}