package dao;

import exception.DAOException;


public interface OrganisateurDAO {

	public String findByLogin(String login)throws DAOException ;

}
