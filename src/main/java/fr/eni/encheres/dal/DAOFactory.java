package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticlesVendusImpl;
import fr.eni.encheres.dal.jdbc.CategoriesImpl;
import fr.eni.encheres.dal.jdbc.EncheresImpl;
import fr.eni.encheres.dal.jdbc.RetraitsImpl;
import fr.eni.encheres.dal.jdbc.UtilisateursImpl;

public class DAOFactory {
    private DAOFactory() {
    }

    public static ArticlesVendusDAO getArticlesVendusDAO() throws DALException {
        return new ArticlesVendusImpl();
    }
    
    public static CategoriesDAO getCategoriesDAO() throws DALException {
    	return new CategoriesImpl();
    }
    
    public static EncheresDAO getEncheresDAO() throws DALException {
    	return new EncheresImpl();
    }
    
    public static RetraitsDAO getRetraitsDAO() throws DALException {
    	return new RetraitsImpl();
    }
    
    public static UtilisateursDAO getUtilisateursDAO() throws DALException {
    	return new UtilisateursImpl();
    }
}
