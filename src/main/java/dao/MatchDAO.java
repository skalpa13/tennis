package dao;

import java.util.Collection;

import entites.Match;
import exception.DAOException;


public interface MatchDAO {
	
	//la signature int ajouter match permet de recuperer le status dans le controleur lors redirection vers la page succes.jsp
	public int ajouterMatch(Match unMatch)throws Exception;
	public void supprimerMatch(int idMatch)throws Exception;
	public Collection<Match> findAll() throws Exception;	
	public Match findByRef (int id)throws  Exception;
	//public Match ajouterScoreMatch(int idMatch, int nbsetjoueur1, int nbsetjoueur2); on fournit l'objet match voire juste en dessous
	public int ajouterScoreMatch(Match match);

}
