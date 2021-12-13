package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateursDAO  extends DAO<Utilisateur> {
	
	Utilisateur getById(int idUtilisateur) throws DALException;
	Utilisateur getByPseudo(String pseudo) throws DALException;
	void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException;
	void changeMDP(Utilisateur utilisateur, String motDePasse) throws DALException;

	Utilisateur getByPseudoEtMotDePasse(String utilisateurPseudo, String motDePasse) throws DALException;
}
