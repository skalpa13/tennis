/* en lien avec vue organisationMatch.jsp*/ 
package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dao.MysqlDaoFactory;

import dao.TypeTableauDAO;

import entites.TypeTableau;
import exception.DAOException;


public class TypeTableauDAOImpl implements TypeTableauDAO {
	private ArrayList<TypeTableau> listeTypeTableau;
	
	final String ORDRE_FINDALL = "select identifiant, nomtableau, nbsets from typetableau";
	final String ORDRE_FIND_BY_REF = "select identifiant, nomtableau, nbsets from typetableau where identifiant = ?";

	private MysqlDaoFactory daoFactory;

	public TypeTableauDAOImpl(MysqlDaoFactory daoFactory) {
		listeTypeTableau = new ArrayList<TypeTableau>();
		this.daoFactory = daoFactory;
	}
	

	public TypeTableau findByRef (int id) throws DAOException {	
		TypeTableau unTypeTableau = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FIND_BY_REF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				unTypeTableau = new TypeTableau(
						rs.getInt("identifiant"), 
						rs.getString("nomtableau"), 								
						rs.getInt("nbsets"));	
			} else {
				throw new DAOException("Erreur recherche d'un Type de Tableau " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return unTypeTableau;
	}

	public Collection<TypeTableau> findAll() throws DAOException{	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				TypeTableau unTypeTableau = new TypeTableau();
				unTypeTableau.setId(resultSet.getInt("identifiant"));
				unTypeTableau.setNbSets(resultSet.getInt("nbSets"));
				unTypeTableau.setNomTableau(resultSet.getString("nomtableau"));
				listeTypeTableau.add(unTypeTableau);
		}
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return listeTypeTableau;
	}
	
    /* methode indispensable pour <c:forEach var="joueur" items="${joueurDAO.listeJoueurs }">*/
	public ArrayList<TypeTableau> getListeTypeTableau() {
		return listeTypeTableau;
	}
/*
	public void setListeTypeTableau(ArrayList<TypeTableau> listeTypeTableau) {
		this.listeTypeTableau = listeTypeTableau;
	}
*/
}
