package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateursDAO {

	public Utilisateur getUtilisateurById(int id) throws DALException;
	public List<Utilisateur> getAllUtilisateurs() throws DALException;
	public void addUtilisateur(Utilisateur utilisateur) throws DALException;
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException;
	public void removeUtilisateur(Utilisateur utilisateur) throws DALException;
	
	public void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException;
}
