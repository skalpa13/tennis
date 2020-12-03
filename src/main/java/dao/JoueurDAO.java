package dao;

import java.util.Collection;

import entites.Joueur;

public interface JoueurDAO {
	
	public int ajouterJoueur(Joueur unJoueur) throws Exception;
	public void supprimerJoueur(int idJoueur)throws Exception;
	public Joueur findByRef (int id)throws Exception;
	public Collection<Joueur> findAll() throws Exception;
	public Collection<Joueur> findByGenre(String string) throws Exception;	

}
