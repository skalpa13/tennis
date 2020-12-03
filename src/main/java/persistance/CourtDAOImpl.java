package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import dao.CourtDAO;
import dao.MysqlDaoFactory;
import entites.Court;
import exception.DAOException;


public class CourtDAOImpl implements CourtDAO {
	
	private ArrayList<Court> listeCourts;
	private final String ORDRE_INSERT = "insert into court(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from court where numero = ";
	private final String ORDRE_FINDALL = "select numero,nom from court";
	private final String ORDRE_FINDBYREF = "select numero,nom"
			+ " from court where numero = ?";

    private MysqlDaoFactory daoFactory;

	public CourtDAOImpl(MysqlDaoFactory daoFactory) {
		listeCourts = new ArrayList<Court>();
		this.daoFactory = daoFactory;
	}
	
	public void ajouterCourt(Court unCourt) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeCourts().add(unCourt);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			System.out.println("pst: "+ pst);
			pst.setString(1, unCourt.getNom());
			pst.executeUpdate();
			
			
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unCourt.setNumero(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un court. " );
			}
			connexion.commit();
		//	daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if(e.getErrorCode()==1062)
                  throw new DAOException("Erreur création d'un court: doublon " );
			
		}
	}
	
	public void supprimerCourt(int idCourt) throws DAOException{
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idCourt+"'");
			connexion.commit();
			connexion.commit();
		//	daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	public Court findByRef (int id) throws DAOException {
		Court unCourt = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unCourt = new Court( rs.getString("nom"));	
				unCourt.setNumero(id);
			} else {
				throw new DAOException("Erreur recherche d'un court. " );
			}
			connexion.commit();
		//	daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unCourt;
	}
	public Collection<Court> findAll()  throws DAOException{	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeCourts.removeAll(listeCourts);
			resultSetToArrayList(resultSet);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
		//	daoFactory.closeConnexion(connexion);
		}
		return listeCourts;
	}
	

	public Collection<Court> getListeCourts() {
		return listeCourts;
	}

	public void setListeCourts(ArrayList<Court> listeCourts) {
		this.listeCourts = listeCourts;
	}
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Court c = new Court();
			c.setNumero(resultSet.getInt("Numero"));
			c.setNom(resultSet.getString("Nom"));
			getListeCourts().add(c);
		}
	}
	
}
