package fr.eni.encheres.dal;

import java.util.List;


public interface DAO<T> {
	public List<T> getAll() throws DALException;
	public void add(T categorie) throws DALException;
	public void update(T utilisateur) throws DALException;
	public void remove(T utilisateur) throws DALException;

}
