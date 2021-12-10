package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

//@Frederic

public interface ArticlesVendusDAO extends DAO <Article>  {

	public Article getArticleVenduById(int id) throws DALException;
	public List<Article> getAllArticleVendu() throws DALException;
	public void addArticleVendu(Article articlevendu) throws DALException;
	public void updateArticleVendu(Article articlevendu) throws DALException;
	
    //inutile car légalement on veut garder en mémoire toutes les transactions
	
	public void removeArticleVendu(Article articlevendu) throws DALException;

	
}