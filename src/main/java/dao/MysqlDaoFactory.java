package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exception.DAOConfigurationException;
import exception.DAOException;
import persistance.*;

public class MysqlDaoFactory extends DaoFactory{

 


    MysqlDaoFactory( DataSource ds ) {
    	this.ds = ds;
    }

   
	/*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     * pour chaque entié
     */
    public static MysqlDaoFactory getInstance() {
      
		Context ctx = null;
		DataSource ds = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/acosparla_tennis");
		} catch (NamingException e) {
			e.printStackTrace();
		}

    	MysqlDaoFactory instance = new MysqlDaoFactory(ds);
        return instance;
    }

 


    /*
     * Méthodes de récupération de l'implémentation des différents DAO
     */
	  public JoueurDAO getJoueurDAO() {
	        return new JoueurDAOImpl(this);
	    }
	
	
	
	public ArbitreDAO getArbitreDAO() {
        return new ArbitreDAOImpl(this);
    }

    public CourtDAO getCourtDAO() {
        return new CourtDAOImpl(this);
    }

  

    public MatchDAO getMatchDAO() {
        return new MatchDAOImpl(this);
    }

    public OrganisateurDAO getOrganisateurDAO() {
        return new OrganisateurDAOImpl(this);
    }
    
    public TypeTableauDAO getTypeTableauDAO() {
        return new TypeTableauDAOImpl(this);
    }

}
