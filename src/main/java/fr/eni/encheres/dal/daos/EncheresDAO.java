// @author Lucie
package fr.eni.encheres.dal.daos;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.daos.DAO;


public interface EncheresDAO extends DAO<Enchere> {
		
	public List<Enchere> selectEncheresByNoUtilisateur(int noUtilisateur) throws DALException;

	public List<Enchere> selectEncheresByNoArticle(int noArticle) throws DALException;
	
	public List<Enchere> selectEncheresByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle) throws DALException;

	public void deleteEnchereByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle) throws DALException;
	
	//@authorLucie ajout requete
	//pour récupérer le plus haut montant_enchere dans les offres faites sur un article
	public int selectMeilleureOffreByNoArticle(int noArticle) throws DALException;
}
