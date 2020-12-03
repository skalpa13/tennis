package dao;



import java.sql.Connection;
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

/* M�thode charg�e de fournir une connexion � la base de donn�es */
public Connection getConnection()  {
	Connection connexion = null;
	try {
       	connexion = ds.getConnection();
        connexion.setAutoCommit(false);
	} catch (Exception e) {
		//throw new DAOException("Connexion impossible.", e);
	    System.out.println("Connexion impossible: "+ e.getMessage());
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