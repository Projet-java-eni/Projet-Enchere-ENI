package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;

//@Frederic

public interface ArticlesVendusDAO extends DAO <Article>  {

	public Article getById(int noArticle) throws DALException;
	
}