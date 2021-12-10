package fr.eni.encheres.dal;

//@author Frederic


import fr.eni.encheres.bo.Categorie;

public interface CategoriesDAO extends DAO<Categorie>{

	Categorie getById(int categorieId) throws DALException;
	
	
}
