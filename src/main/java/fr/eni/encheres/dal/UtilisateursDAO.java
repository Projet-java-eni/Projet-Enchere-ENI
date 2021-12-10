package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateursDAO  extends DAO<Utilisateur> {
	
	public void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException;
}
