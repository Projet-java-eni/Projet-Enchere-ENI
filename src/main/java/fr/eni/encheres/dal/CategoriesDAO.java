package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategoriesDAO {
	public Categorie getUtilisateurById(int id) throws DALException;
	public List<Categorie> getAllUtilisateurs() throws DALException;
	public void addUtilisateur(Categorie categorie) throws DALException;
	public void updateUtilisateur(Categorie utilisateur) throws DALException;
	public void removeUtilisateur(Categorie utilisateur) throws DALException;

}
