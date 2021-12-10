package fr.eni.encheres.dal;

//@author Frederic


import fr.eni.encheres.bo.Categorie;

public interface CategoriesDAO extends DAO<Categorie>{
	
	public Categorie getById(int id) throws DALException;
	
}
