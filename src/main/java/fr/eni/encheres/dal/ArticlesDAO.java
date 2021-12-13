package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.DAO;

//@Frederic

public interface ArticlesDAO extends DAO<Article> {

	public Article getById(int id) throws DALException;

}