//MAJ Lucie (généricité)
package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticlesVendusImpl;
import fr.eni.encheres.dal.jdbc.CategoriesImpl;
import fr.eni.encheres.dal.jdbc.EncheresImpl;
import fr.eni.encheres.dal.jdbc.RetraitsImpl;
import fr.eni.encheres.dal.jdbc.UtilisateursImpl;

public class DAOFactory {
	private static ArticlesVendusDAO articlesVendusDAO = null;
	private static CategoriesDAO categoriesDAO = null;
	private static EncheresDAO encheresDAO = null;
	private static RetraitsDAO retraitsDAO = null;
	private static UtilisateursDAO utilisateursDAO = null;
    private DAOFactory() {
    }

    public static ArticlesVendusDAO getArticlesVendusDAO() throws DALException {
    	if(articlesVendusDAO == null) {
    		articlesVendusDAO = new ArticlesVendusImpl();
    	}
        return articlesVendusDAO;
    }
    
    public static CategoriesDAO getCategoriesDAO() throws DALException {
    	if(categoriesDAO == null) {
    		categoriesDAO = new CategoriesImpl();
    	}
        return categoriesDAO;
    }
    
    public static EncheresDAO getEncheresDAO() throws DALException {
    	if(encheresDAO == null) {
    		encheresDAO = new EncheresImpl();
    	}
        return encheresDAO;
    }
    
    public static RetraitsDAO getRetraitsDAO() throws DALException {
    	if(retraitsDAO == null) {
    		retraitsDAO = new RetraitsImpl();
    	}
        return retraitsDAO;
    }
    
    public static UtilisateursDAO getUtilisateursDAO() throws DALException {
    	if(utilisateursDAO == null) {
    		utilisateursDAO = new UtilisateursImpl();
    	}
        return utilisateursDAO;
    }
}
