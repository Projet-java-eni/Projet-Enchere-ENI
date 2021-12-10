package fr.eni.encheres.dal;

import java.util.List;


public interface DAO<T> {
	public List<T> getAll() throws DALException;
	public void add(T generique) throws DALException;
	public void update(T generique) throws DALException;
	public void remove(T generique) throws DALException;

}
