package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateursDAO  extends DAO<Utilisateur> {
	
	public Utilisateur getById(int idUtilisateur) throws DALException;
	public void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException;
}
