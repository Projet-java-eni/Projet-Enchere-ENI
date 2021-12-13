ackage fr.eni.encheres.dal;

import fr.eni.encheres.bo.Article;

//@Frederic

public interface ArticlesDAO extends DAO<Article> {

	public Article getById(int id) throws DALException;

}