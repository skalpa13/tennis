package dao;

import java.util.Collection;

import entites.Court;
import exception.DAOException;

public interface CourtDAO {
	public int ajouterCourt(Court unCourt) ;
	public void supprimerCourt(int idCourt) throws DAOException;
	public Court findByRef (int id) throws DAOException;
	public Collection<Court> findAll()  ;	
}
