package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import dao.ArbitreDAO;
import dao.MysqlDaoFactory;
import entites.Arbitre;
import exception.DAOException;


public class ArbitreDAOImpl implements ArbitreDAO{

	
	private ArrayList<Arbitre> listeArbitres;
	private final String ORDRE_INSERT = "insert into arbitre(Nom,Prenom,Niveau,Nation) values ";
	private final String VALUES_INSERT = "(?,?,?,?)";
	private final String ORDRE_DELETE = "delete from arbitre where identifiant = ";
	private final String ORDRE_FINDALL = "select identifiant,nom,prenom,nation,niveau from arbitre";
	private final String ORDRE_FINDBYREF = "select identifiant,nom,prenom,nation,niveau"
			+ " from arbitre where identifiant = ?";
	
    private MysqlDaoFactory          daoFactory;

	public ArbitreDAOImpl(MysqlDaoFactory daoFactory) {
		listeArbitres = new ArrayList<Arbitre>();
		this.daoFactory = daoFactory;
	}

	public void ajouterArbitre(Arbitre unArbitre){
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeArbitres().add(unArbitre);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unArbitre.getNom());
			pst.setString(2, unArbitre.getPrenom());
			pst.setInt(3, unArbitre.getNiveau());
			pst.setString(4, unArbitre.getNation());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unArbitre.setId(rs.getInt(1));
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			
		}
	}
	public void supprimerArbitre(int idArbitre){
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idArbitre+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			
		}
	}
	public Arbitre findByRef (int id) {
		Arbitre unArbitre = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unArbitre = new Arbitre( rs.getString("nom"), rs.getString("prenom"), rs.getString("nation"), rs.getInt("niveau"));	
				unArbitre.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un arbitre. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			
		}
		return unArbitre; 
	}
	public Collection<Arbitre> findAll()  {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeArbitres.removeAll(listeArbitres);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
		
		}
		return listeArbitres;
	}
	
	public void setListeArbitres(ArrayList<Arbitre> listeArbitres) {
		this.listeArbitres = listeArbitres;
	}

	public Collection<Arbitre> getListeArbitres() {
		return listeArbitres;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Arbitre a = new Arbitre();
			a.setId(resultSet.getInt("Identifiant"));
			a.setNom(resultSet.getString("Nom"));
			a.setPrenom(resultSet.getString("Prenom"));
			a.setNation(resultSet.getString("Nation"));
			a.setNiveau(resultSet.getInt("Niveau"));
			getListeArbitres().add(a);
		}
	}

}
