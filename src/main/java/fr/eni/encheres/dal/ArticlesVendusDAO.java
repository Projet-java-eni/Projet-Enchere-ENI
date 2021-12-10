package fr.eni.encheres.dal;

//@Frederic

public interface ArticlesVendusDAO {

	public ArticlesVendus getArticlesVendusById(int id) throws DALException;
	public List<ArticlesVendus> getAllArticlesVendus() throws DALException;
	public void addArticlesVendus(ArticlesVendus articlesvendus) throws DALException;
	public void updateArticlesVendus(ArticlesVendus articlesvendus) throws DALException;
	public void removeArticlesVendus(ArticlesVendus articlesvendus) throws DALException;
	
	
}