package dao;

import java.util.Collection;

import entites.Arbitre;
import exception.DAOException;


public interface ArbitreDAO {

	public void ajouterArbitre (Arbitre unArbitre);
	public void supprimerArbitre(int idArbitre);
	public Collection<Arbitre> findAll() ;	
	public Arbitre findByRef (int id);
}
