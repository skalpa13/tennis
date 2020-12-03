package dao;

import java.util.Collection;

import entites.TypeTableau;
import exception.DAOException;


public interface TypeTableauDAO {

	public TypeTableau findByRef(int id) throws DAOException ;
	public Collection<TypeTableau> findAll() throws DAOException;
}
