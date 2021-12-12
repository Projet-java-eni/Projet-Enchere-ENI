package fr.eni.encheres.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateursDAO;

public class UtilisateursManager {
	
	private final UtilisateursDAO utilisateursDAO;
	
	private static UtilisateursManager instance = null;
	
	private Map<Integer, Utilisateur> utilisateursMap = null;

	private UtilisateursManager() throws BLLException {

		try {
		
			this.utilisateursDAO = (UtilisateursDAO)DAOFactory.getUtilisateursDAO();
		} catch (DALException e) {
			
			throw new BLLException(e.getLocalizedMessage(), e);
		}
		
		this.utilisateursMap = new HashMap<>();
	}

	public static UtilisateursManager GetInstance() throws BLLException {

		if (instance == null) {
			instance = new UtilisateursManager();
		}
		
		return instance;
	}

	public Utilisateur createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur, Erreurs erreurs) {
		
		Utilisateur utilisateur = new Utilisateur(
				pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur
		);

		validerMotDePasse(motDePasse, erreurs);

		validerUtilisateurUnique(utilisateur, erreurs);

		validerUtilisateur(utilisateur, erreurs);

		// On retourne un Utilisateur meme si on l'ajout pas à la DAO histoire
		// de pouvoir préremplir le formulaire à l'essai suivant

		if(erreurs.hasErrors()) {
			return utilisateur;
		}

		try {
			
			utilisateursDAO.addUtilisateurSecurise(utilisateur, motDePasse);
		} catch (DALException e) {
			
			erreurs.addErreur(e.getLocalizedMessage());
			utilisateur = null;
		}
	
		return utilisateur;
	}

	// Verifie que le nom d'utilisateur n'est pas déjà pris
	private void validerUtilisateurUnique(Utilisateur utilisateur, Erreurs erreurs) {
		try {
			Utilisateur existant = utilisateursDAO.getByPseudo(utilisateur.getPseudo());
			if(existant != null) {
				erreurs.addErreur("Cet utilisateur existe deja");
			}
		} catch (DALException e) {
			// normalement si la requete SQL lève une exception, c'est qu'aucun utilisateur
			// avec ce pseudo n'a été trouvé donc c'est ok, et on fait rien.
		}
	}

	public Utilisateur createUtilisateurDepuisLeWeb(String pseudo, String nom, String prenom, 
			String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String credit, String administrateur, Erreurs erreurs) {
		
		if(pseudo == null) erreurs.addErreur("Le pseudo doit être renseigné");
		if(nom == null) erreurs.addErreur("Le nom doit être renseigné");
		if(prenom == null) erreurs.addErreur("Le prenom doit être renseigné");
		if(email == null) erreurs.addErreur("L'email doit être renseigné");
		if(telephone == null) erreurs.addErreur("Le telephone doit être renseigné");
		if(rue == null) erreurs.addErreur("La rue doit être renseignée");
		if(codePostal == null) erreurs.addErreur("Le code postal doit être renseigné");
		if(ville == null) erreurs.addErreur("La ville doit être renseignée");
		if(motDePasse == null) erreurs.addErreur("Le mot de passe doit être renseigné");
		if(credit == null) erreurs.addErreur("Le credit doit être renseigné");
		
		boolean is_admin = false;
		
		if(administrateur == null) {
			is_admin = false;
		} else {
			if(administrateur.equalsIgnoreCase("on")) {
				is_admin = true;
			} else if (administrateur.equalsIgnoreCase("off")) {
				is_admin = false;
			} else {
				erreurs.addErreur("Statut administrateur indefini");
			}
		}
		
		Integer credit_int = 0;
		
		try{
			credit_int = Integer.parseInt(credit);
		} catch(NumberFormatException e) {
			erreurs.addErreur("Impossible de convertir le crédit en nombre entier");
		}
		
		if (erreurs.hasErrors()) return null;
		
		return createUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit_int, is_admin, erreurs);
	}

	public void modifUtilisateurDepuisLeWeb(Utilisateur utilisateur, String pseudo, String nom, String prenom, 
			String email, String telephone, String rue,
			String codePostal, String ville, String credit, String administrateur, Erreurs erreurs) {
		
		if(pseudo == null) erreurs.addErreur("Le pseudo doit être renseigné");
		if(nom == null) erreurs.addErreur("Le nom doit être renseigné");
		if(prenom == null) erreurs.addErreur("Le prenom doit être renseigné");
		if(email == null) erreurs.addErreur("L'email doit être renseigné");
		if(telephone == null) erreurs.addErreur("Le telephone doit être renseigné");
		if(rue == null) erreurs.addErreur("La rue doit être renseignée");
		if(codePostal == null) erreurs.addErreur("Le code postal doit être renseigné");
		if(ville == null) erreurs.addErreur("La ville doit être renseignée");
		if(credit == null) erreurs.addErreur("Le credit doit être renseigné");
		
		boolean is_admin = false;
		if(administrateur == null) {
			is_admin = false;
		} else {
			if(administrateur.equalsIgnoreCase("on")) {
				is_admin = true;
			} else if (administrateur.equalsIgnoreCase("off")) {
				is_admin = false;
			} else {
				erreurs.addErreur("Statut administrateur indefini");
			}
		}
		
		Integer credit_int = 0;
		try{
			credit_int = Integer.parseInt(credit);
		} catch(NumberFormatException e) {
			erreurs.addErreur("Impossible de convertir le crédit en nombre entier");
		}
		
		if (erreurs.hasErrors()) return;
		
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setCredit(credit_int);
		utilisateur.setAdministrateur(is_admin);
		
		try {
			sauvegarderUtilisateur(utilisateur, erreurs);
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}
	}

	
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BLLException {
		
		try {
			utilisateursDAO.remove(utilisateur);
			utilisateursMap.remove(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	public Utilisateur getUtilisateurById(int utilisateurId) throws BLLException {
		
		Utilisateur utilisateur = null;
		
		if (utilisateursMap.containsKey(utilisateurId)) {
			utilisateur = utilisateursMap.get(utilisateurId);
		} else {
			
			try {
				utilisateur = utilisateursDAO.getById(utilisateurId);
				utilisateursMap.put(utilisateurId, utilisateur);
			} catch (DALException e) {
				throw new BLLException(e.getLocalizedMessage(), e);
			}
		}

		return utilisateur;
	}

	public List<Utilisateur> getAllUtilisateur() throws BLLException {
		
		try {
			return utilisateursDAO.getAll();
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	public void sauvegarderUtilisateur(Utilisateur utilisateur, Erreurs erreurs) throws BLLException {
		
		validerUtilisateur(utilisateur, erreurs);
		
		try {
			this.utilisateursDAO.update(utilisateur);
			this.utilisateursMap.put(utilisateur.getNoUtilisateur(), utilisateur);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	private void validerUtilisateur(Utilisateur utilisateur, Erreurs erreurs) {
		
		if(utilisateur.getPseudo() == null) erreurs.addErreur("Le pseudo doit être renseigné");
		
		if(utilisateur.getNom() == null) erreurs.addErreur("Le nom doit être renseigné");
		
		if(utilisateur.getPrenom() == null) erreurs.addErreur("Le prenom doit être renseigné");
		
		if(utilisateur.getEmail() == null) erreurs.addErreur("L'email doit être renseigné");
		
		if(utilisateur.getTelephone() == null) erreurs.addErreur("Le telephone doit être renseigné");
		
		if(utilisateur.getRue() == null) erreurs.addErreur("La rue doit être renseignée");
		
		if(utilisateur.getCodePostal() == null) erreurs.addErreur("Le code postal doit être renseigné");
		
		if(utilisateur.getVille() == null) erreurs.addErreur("La ville doit être renseignée");
		
		if(erreurs.hasErrors()) {
			return;
		}

		if (utilisateur.getPseudo().length() < 1 || utilisateur.getPseudo().length() > 30) {
			erreurs.addErreur("Le pseudo a pas la bonne longueur.");
		}
		
		if (utilisateur.getNom().length() < 1 || utilisateur.getNom().length() > 50) {
			erreurs.addErreur("Le nom a pas la bonne longueur.");
		}
		
		if (utilisateur.getPrenom().length() < 1 || utilisateur.getPrenom().length() > 50) {
			erreurs.addErreur("Le prénom a pas la bonne longueur.");
		}
		
		if (utilisateur.getEmail().length() < 1 || utilisateur.getEmail().length() > 50) {
			erreurs.addErreur("L'email a pas la bonne longueur.");
		}
		
		if (utilisateur.getTelephone().length() < 1 || utilisateur.getTelephone().length() > 15) {
			erreurs.addErreur("Le téléphone a pas la bonne longueur.");
		}
		
		if (utilisateur.getRue().length() < 1 || utilisateur.getRue().length() > 250) {
			erreurs.addErreur("La rue a pas la bonne longueur.");
		}
		
		if (utilisateur.getCodePostal().length() != 5) {
			erreurs.addErreur("Le code postal a pas la bonne longueur.");
		}
		
		if (utilisateur.getVille().length() < 1 || utilisateur.getVille().length() > 50) {
			erreurs.addErreur("La ville a pas la bonne longueur.");
		}
	}
	
	private void validerMotDePasse(String motDePasse, Erreurs erreurs) {
		
		if(motDePasse == null) erreurs.addErreur("Le mot de passe doit être renseigné");
		
		if(erreurs.hasErrors()) {
			return;
		}

		if (motDePasse.length() < 6) {
			erreurs.addErreur("Le mot de passe doit faire 6 caractères au moins !");
		}
	}


	private void validerMotDePasseRepete(String motDePasse, String motDePasseRepete, Erreurs erreurs) {
		
		if(motDePasse == null) erreurs.addErreur("Le mot de passe doit être renseigné");
		
		if(motDePasseRepete == null) erreurs.addErreur("Le mot de passe répété doit être renseigné");

		if(erreurs.hasErrors()) {
			return;
		}
		
		if (motDePasse.length() < 6) {
			erreurs.addErreur("Le mot de passe doit faire 6 caractères au moins !");
		}
		
		if(!motDePasse.equals(motDePasseRepete)) {
			erreurs.addErreur("Le mot de passe répété doit être identique !");
		}
	}
	
	public Utilisateur traiteRequeteInscription(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, String motDePasseRepete,
			Erreurs errors) {
		
		validerMotDePasseRepete(motDePasse, motDePasseRepete, errors);

		return createUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false, errors);

	}
}
