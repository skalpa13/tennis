package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


import dao.MysqlDaoFactory;
import dao.DaoFactory;
import dao.JoueurDAO;
import entites.Joueur;
import exception.DAOException;


public class JoueurDAOImpl implements JoueurDAO{
	
	private ArrayList<Joueur> listeJoueurs;
	private final String ORDRE_INSERT = "insert into joueur(Nom,Prenom,Sexe,Nation) values ";
	private final String VALUES_INSERT = "(?,?,?,?)";
	private final String ORDRE_DELETE = "delete from joueur where identifiant = ";
	private final String ORDRE_FINDALL = "select identifiant,nom,prenom,sexe,nation from joueur";
	private final String ORDRE_FINDBYREF = "select identifiant,nom,prenom,sexe,nation"
			+ " from joueur where identifiant = ?";

	private final String ORDRE_FINDBYGENRE = "select identifiant,nom,prenom,sexe,nation from joueur where sexe = ?";
	
    private DaoFactory        daoFactory;

  
    
    
	public JoueurDAOImpl(DaoFactory daoFactory) {
		listeJoueurs = new ArrayList<Joueur>();
		this.daoFactory = daoFactory;
	}
	
	public int ajouterJoueur(Joueur unJoueur)throws Exception {
		ResultSet rs = null;
		Connection connexion = null;
	
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unJoueur.getNom());
			pst.setString(2, unJoueur.getPrenom());
			pst.setString(3, unJoueur.getSexe());
			pst.setString(4, unJoueur.getNation());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
		
			listeJoueurs.add(unJoueur);//affiche la liste des joueurs sur la page JSP
			System.out.println("insert player sucess");
				
			
			if (rs.next()){
				unJoueur.setId(rs.getInt(1));
			}
			connexion.commit();//export bdd alawaysdata SET AUTOCOMMIT = 0;
			daoFactory.closeConnexion(connexion);
		} 
		catch ( SQLException e) {
			 System.out.println("code error sql: "+e.getErrorCode());
			 System.out.println("msg error sql: "+e.getMessage());
				
			 return(e.getErrorCode());
			
		} 
		return 1;//1 code success
	}
	public void supprimerJoueur(int idJoueur){
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			if (requete.executeUpdate(ORDRE_DELETE + "'"+idJoueur+"'")== 1){
				connexion.commit();
			}
			else {
				System.out.println("inexistant");
			}
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Joueur findByRef (int id)throws DAOException{
		Joueur unJoueur = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unJoueur = new Joueur( rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getString("nation"));	
				unJoueur.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un joueur. " );
			}
			daoFactory.closeConnexion(connexion);
		} catch ( Exception e) {
			throw new DAOException(e);
		}
		return unJoueur;
	}
	public Collection<Joueur> findByGenre (String  Genre){
		
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYGENRE);
			pst.setString(1, Genre);
			ResultSet rs = pst.executeQuery();
			listeJoueurs.removeAll(listeJoueurs);
			resultSetToArrayList(rs);
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			//throw new DAOException(e);
		}
		return listeJoueurs;
	}
	public  Collection<Joueur> findAll() throws DAOException{	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeJoueurs.removeAll(listeJoueurs);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return listeJoueurs;
	}

	public Collection<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}


	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Joueur j = new Joueur();
			j.setId(resultSet.getInt("Identifiant"));
			j.setNom(resultSet.getString("Nom"));
			j.setPrenom(resultSet.getString("Prenom"));
			j.setSexe(resultSet.getString("Sexe"));
			j.setNation(resultSet.getString("Nation"));
			getListeJoueurs().add(j);
		}
	}
	

}
