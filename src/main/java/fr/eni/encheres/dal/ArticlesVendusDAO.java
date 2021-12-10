package fr.eni.encheres.dal;


//@Frederic

public interface ArticleVenduDAO extends DAO <ArticleVendu>  {

	public ArticleVendu getArticleVenduById(int id) throws DALException;
	public List<ArticleVendu> getAllArticleVendu() throws DALException;
	public void addArticleVendu(ArticleVendu articlevendu) throws DALException;
	public void updateArticleVendu(ArticleVendu articlevendu) throws DALException;
	
    //inutile car légalement on veut garder en mémoire toutes les transactions
	
	public void removeArticleVendu(ArticleVendu articlevendu) throws DALException;

	
}