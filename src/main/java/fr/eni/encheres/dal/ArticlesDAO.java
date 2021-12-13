package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;

//@Frederic

public interface ArticlesDAO extends DAO<Article> {

	public Article getById(int id) throws DALException;

	Article getByCategorie(Categorie categorie) throws DALException;

}