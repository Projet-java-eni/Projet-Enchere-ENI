package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;

//@Frederic

public interface ArticlesVendusDAO extends DAO <ArticleVendu>  {

	public ArticleVendu getById(int noArticle) throws DALException;
	
}