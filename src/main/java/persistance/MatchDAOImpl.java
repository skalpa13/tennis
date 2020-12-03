package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import dao.ArbitreDAO;
import dao.CourtDAO;
import dao.MysqlDaoFactory;
import dao.JoueurDAO;
import dao.MatchDAO;
import dao.TypeTableauDAO;
import entites.Match;
import exception.DAOException;


public class MatchDAOImpl implements MatchDAO {
	
	private ArrayList<Match> listeMatchs;
	private final String ORDRE_INSERT =	"insert into acosparla_tennis.match(refjoueur1,refjoueur2,refarbitre,nocourt,datematch,nbsetsjoueur1,nbsetsjoueur2 ) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from match where identifiant = ";
	private final String ORDRE_FINDALL = "select identifiant,refjoueur1,refjoueur2,refarbitre,nocourt,datematch,nbsetsjoueur1,nbsetsjoueur2  from acosparla_tennis.match";
	private final String ORDRE_FINDBYREF = ORDRE_FINDALL + " where identifiant = ?";

    private MysqlDaoFactory          daoFactory;

	public MatchDAOImpl(MysqlDaoFactory daoFactory) {
		listeMatchs = new ArrayList<Match>();
		this.daoFactory = daoFactory;
	}

	public int ajouterMatch(Match unMatch) {
		ResultSet rs = null;
		Connection connexion = null;
		System.out.println("unMatch: "+ unMatch.getJoueur1().getNom());
		
		
		try {
			
			connexion = daoFactory.getConnection();
			/*String sql = "insert into acosparla_tennis.match ( reftype, datetournoi, nocourt, refarbitre, refjoueur1, refjoueur2, nbsetsjoueur1, nbsetsjoueur2) "
					+ "VALUES( 1, '2016-04-10', 1, 4, 1, 2, 0, 0)";*/
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unMatch.getJoueur1().getId());
			pst.setInt(2, unMatch.getJoueur2().getId());
			pst.setInt(3, unMatch.getArbitre().getId());
			pst.setInt(4, unMatch.getCourt().getNumero());
			pst.setDate(5,  (Date) unMatch.getDateMatch());
			pst.setInt(6, unMatch.getNbsetsjoueur1());
			pst.setInt(7, unMatch.getNbsetsjoueur2());
			
			
			pst.executeUpdate();// try catch gestion error mysql
		    //ajout dans la lste des matchs pour affichage dans la page jsp 
			//si erreur d'insert pas d'ajout dans liste car on passe de pst.executeUpdate() au catch {} 
			rs = pst.getGeneratedKeys();
			listeMatchs.add(unMatch);
			if (rs.next()){
				unMatch.setIdentifiant(rs.getInt(1));
				System.out.println("create match succes!");
				
			}
			connexion.commit();//le commit est nécessaire bien que autocommit = true pour la base ???????(work in progress)
			daoFactory.closeConnexion(connexion);
		 
		}
		
		catch (  SQLException  e) {
			 System.out.println("code error sql: "+e.getErrorCode());
			 System.out.println("msg error sql: "+e.getMessage());
				
			 return(e.getErrorCode());
		}
		catch (  NullPointerException  e) {
			System.out.println("pointerexception"+e.getMessage());
		} catch (Exception e) {// pour le close connexion 
			e.printStackTrace();
		}
		return 1;//1 code succes
		
	}


	public Collection<Match> findAll() {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (Exception e) {
			
		}
		return listeMatchs;
	}

	public ArrayList<Match> getListeMatchs() {
		return listeMatchs;
	}

	public void setListeMatchs(ArrayList<Match> listeMatchs) {
		this.listeMatchs = listeMatchs;
	}
	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException, Exception {
		Match unMatch;

		while (resultSet.next()) {
			unMatch = new Match();
			JoueurDAO unJoueurDAO = daoFactory.getJoueurDAO();
			ArbitreDAO unArbitreDAO = daoFactory.getArbitreDAO();
			CourtDAO unCourtDAO = daoFactory.getCourtDAO();
		    //debut pour afficher id dans la liste des matchs enregistrement resultat
			unMatch.setIdentifiant(resultSet.getInt("Identifiant"));
			//fin pour afficher id dans la liste des matchs enregistrement resultat
			
			unMatch.setJoueur1(unJoueurDAO.findByRef(resultSet.getInt("refjoueur1")));
			unMatch.setJoueur2(unJoueurDAO.findByRef(resultSet.getInt("refjoueur2")));
			unMatch.setArbitre(unArbitreDAO.findByRef(resultSet.getInt("refarbitre")));
			unMatch.setCourt(unCourtDAO.findByRef(resultSet.getInt("nocourt")));
			//unMatch.setTypeTournoi(unTypeTournoiDAO.findByRef(resultSet.getInt("reftype")));
			unMatch.setDateMatch(resultSet.getDate("datematch"));
			unMatch.setNbsetsjoueur1(resultSet.getInt("nbsetsjoueur1"));
			unMatch.setNbsetsjoueur2(resultSet.getInt("nbsetsjoueur2"));
			
			
			getListeMatchs().add(unMatch);
				
		}
	}

public void supprimerMatch(int idMatch) throws Exception{
	Connection connexion = null;
	try {
		connexion = daoFactory.getConnection();
		Statement requete = connexion.createStatement();
		requete.executeUpdate(ORDRE_DELETE + "'"+idMatch+"'");
		connexion.commit();
		daoFactory.closeConnexion(connexion);
	} catch (SQLException e) {
		throw new DAOException(e);
	}
}

public Match findByRef (int id) throws Exception  {
	Connection connexion = null;
	Match unMatch;
	try {
		connexion = daoFactory.getConnection();
		PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			unMatch = new Match();
			JoueurDAO unJoueurDAO = daoFactory.getJoueurDAO();
			ArbitreDAO unArbitreDAO = daoFactory.getArbitreDAO();
			CourtDAO unCourtDAO = daoFactory.getCourtDAO();
		

			unMatch.setIdentifiant(rs.getInt("Identifiant"));
			unMatch.setJoueur1(unJoueurDAO.findByRef(rs.getInt("refjoueur1")));
			unMatch.setJoueur2(unJoueurDAO.findByRef(rs.getInt("refjoueur2")));
			unMatch.setArbitre(unArbitreDAO.findByRef(rs.getInt("refarbitre")));
			unMatch.setCourt(unCourtDAO.findByRef(rs.getInt("nocourt")));
			
			unMatch.setDateMatch(rs.getDate("datematch"));
			unMatch.setNbsetsjoueur1(rs.getInt("nbsetsjoueur1"));
			unMatch.setNbsetsjoueur2(rs.getInt("nbsetsjoueur2"));
			
		} else {
			throw new Exception("Erreur recherche d'un match. " );
		}
		connexion.commit();
		daoFactory.closeConnexion(connexion);

	} catch (SQLException e) {
		throw new DAOException(e);
	}
	return unMatch;
}

	public int ajouterScoreMatch(Match match) {
		
		/* first	testé directement sur  php my admin UPDATE `match` SET `nbsetsjoueur1`= 1,`nbsetsjoueur2`= 2 WHERE  `identifiant` = 16;  */	
	   final String ORDRE_UPDATE =	"update acosparla_tennis.match set nbsetsjoueur1 = ? ,nbsetsjoueur2  = ? where match.identifiant = ?  ";
		
		Connection connexion = null;
	   
		
		try {
			connexion = daoFactory.getConnection();
			
			
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1,  match.getNbsetsjoueur1());
			pst.setInt(2,  match.getNbsetsjoueur2());
			pst.setInt(3, match.getIdentifiant());
				
			pst.executeUpdate();
			connexion.commit();//le commit est nécessaire bien que autocommit = true pour la base ???????(work in progress)
			daoFactory.closeConnexion(connexion);
			
			
		}
		catch (Exception e) {
			System.out.println("exception "+ e.getMessage());
		}
	
	    return 1;//code succes
	
	}
 /*
   @Override
   public Match ajouterScoreMatch(int idMatch, int nbsetjoueur1, int nbsetjoueur2) {
   	System.out.println("ajouterscorematch");
   	
   
   	
   	
   	final String ORDRE_UPDATE =	"update acosparla_tennis.match set nbsetsjoueur1 = ? ,nbsetsjoueur2  = ? where match.identifiant = ?  ";
   	System.out.println(nbsetjoueur1);
   	Connection connexion = null;
   	
   	Match match = null;
   	try {
   		connexion = daoFactory.getConnection();
   		
   		
   		PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
   		pst.setInt(1, nbsetjoueur1);
   		pst.setInt(2, nbsetjoueur2);
   		pst.setInt(3, idMatch);
   			
   		pst.executeUpdate();
   		connexion.commit();
   		daoFactory.closeConnexion(connexion);
   		
   		match = findByRef(idMatch);
   	}
   	catch (Exception e) {
   		System.out.println("exception "+ e.getMessage());
   	}
   	return match;

   }
  */




}