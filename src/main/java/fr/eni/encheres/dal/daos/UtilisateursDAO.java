package fr.eni.encheres.dal.daos;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.daos.DAO;

public interface UtilisateursDAO  extends DAO<Utilisateur> {
	
	Utilisateur getById(int idUtilisateur) throws DALException;
	Utilisateur getByPseudo(String pseudo) throws DALException;
	void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException;
	void changeMDP(Utilisateur utilisateur, String motDePasse) throws DALException;

	Utilisateur getByPseudoEtMotDePasse(String utilisateurPseudo, String motDePasse) throws DALException;
}
