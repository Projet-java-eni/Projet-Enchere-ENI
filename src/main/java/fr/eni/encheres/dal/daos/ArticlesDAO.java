package fr.eni.encheres.dal.daos;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DALException;

//@Frederic

public interface ArticlesDAO extends DAO<Article> {

	public Article getById(int id) throws DALException;
	public void getByIdAvecInstance(Article article, int id) throws DALException;

	Article getByCategorie(Categorie categorie) throws DALException;

}