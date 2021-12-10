package fr.eni.encheres.dal;

//@author Frederic

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategoriesDAO extends DAO<Categorie>{
	
	public Categorie getCategorieById(int id) throws DALException;
	public List<Categorie> getAllCategories() throws DALException;
	public void addCategorie(Categorie categorie) throws DALException;
	public void updateCategorie(Categorie categorie) throws DALException;
	public void removeCategorie(Categorie categorie) throws DALException;
	
}
