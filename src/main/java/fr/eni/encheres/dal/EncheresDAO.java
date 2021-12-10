// @author Lucie
package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;


public interface EncheresDAO {
		
	public List<Enchere> selectEncheresByNoUtilisateur(int noUtilisateur) throws DALException;

	public List<Enchere> selectEncheresByNoArticle(int noArticle) throws DALException;
	
	public List<Enchere> selectEncheresByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle) throws DALException;

	public void deleteEnchereByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle) throws DALException;

}
