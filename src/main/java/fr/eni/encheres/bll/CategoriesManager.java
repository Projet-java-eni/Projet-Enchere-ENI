package fr.eni.encheres.bll;


import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoriesManager {
	private final DAO<Categorie> categoriesDAO;
	
	private static CategoriesManager instance = null;
	

	private CategoriesManager() throws BLLException {

		try {
		
			this.categoriesDAO = DAOFactory.getCategoriesDAO();
		} catch (DALException e) {
			
			throw new BLLException(e.getLocalizedMessage(), e);
		}
		
	}

	public static CategoriesManager GetInstance() throws BLLException {

		if (instance == null) {
			instance = new CategoriesManager();
		}
		
		return instance;
	}

	
	public Categorie createCategorie(String libelle, List<String> erreurs) {
		
		Categorie categorie = new Categorie(
				libelle
		);

		try {
			
			categoriesDAO.add(categorie);
		} catch (DALException e) {
			
			erreurs.add(e.getLocalizedMessage());
			categorie = null;
		}
	
		return categorie;
	}
	
	public void supprimerCategorie(Categorie categorie) throws BLLException {
		
		try {
			categoriesDAO.remove(categorie);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	public Categorie getCategorieById(int categorieId) throws BLLException {
		
		Categorie categorie = null;
		
		try {
			categorie = categoriesDAO.getById(categorieId);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}

		return categorie;
	}

	public List<Categorie> getAllUtilisateur() throws BLLException {
		
		try {
			return categoriesDAO.getAll();
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	public void sauvegarderUtilisateur(Categorie categorie, List<String> erreurs) throws BLLException {
		
		validerUtilisateur(categorie, erreurs);
		
		try {
			this.categoriesDAO.update(categorie);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	private void validerUtilisateur(Categorie categorie, List<String> erreurs) {
		
		if (categorie.getLibelle() == null) {
			erreurs.add("Le libellé de categorie ne peut pas être vide");
		}
	}

}
