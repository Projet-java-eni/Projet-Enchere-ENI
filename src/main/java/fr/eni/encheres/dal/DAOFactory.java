//MAJ Lucie (généricité)
package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.jdbc.ArticlesVendusImpl;
import fr.eni.encheres.dal.jdbc.CategoriesImpl;
import fr.eni.encheres.dal.jdbc.EncheresImpl;
import fr.eni.encheres.dal.jdbc.RetraitsImpl;
import fr.eni.encheres.dal.jdbc.UtilisateursImpl;

public class DAOFactory {
	private static DAO<ArticleVendu> articlesVendusDAO = null;
	private static DAO<Categorie> categoriesDAO = null;
	private static DAO<Enchere> encheresDAO = null;
	private static DAO<Retrait> retraitsDAO = null;
	private static DAO<Utilisateur> utilisateursDAO = null;
    private DAOFactory() {
    }

    public static DAO<ArticleVendu> getArticlesVendusDAO() throws DALException {
    	if(articlesVendusDAO == null) {
    		articlesVendusDAO = new ArticlesVendusImpl();
    	}
        return articlesVendusDAO;
    }
    
    public static DAO<Categorie> getCategoriesDAO() throws DALException {
    	if(categoriesDAO == null) {
    		categoriesDAO = new CategoriesImpl();
    	}
        return categoriesDAO;
    }
    
    public static DAO<Enchere> getEncheresDAO() throws DALException {
    	if(encheresDAO == null) {
    		encheresDAO = new EncheresImpl();
    	}
        return encheresDAO;
    }
    
    public static DAO<Retrait> getRetraitsDAO() throws DALException {
    	if(retraitsDAO == null) {
    		retraitsDAO = new RetraitsImpl();
    	}
        return retraitsDAO;
    }
    
    public static DAO<Utilisateur> getUtilisateursDAO() throws DALException {
    	if(utilisateursDAO == null) {
    		utilisateursDAO = new UtilisateursImpl();
    	}
        return utilisateursDAO;
    }
}
