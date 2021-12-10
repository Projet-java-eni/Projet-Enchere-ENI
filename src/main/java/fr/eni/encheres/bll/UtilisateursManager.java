package fr.eni.encheres.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur, List<String> erreurs) {
		
		Utilisateur utilisateur = new Utilisateur(
				pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur
		);

		
		validerMotDePasse(motDePasse, erreurs);
		
		// On retourne un Utilisateur meme si on l'ajout pas à la DAO histoire
		// de pouvoir préremplir le formulaire à l'essai suivant
		if(erreurs.size() > 0) {
			return utilisateur;
		}
		
		validerUtilisateur(utilisateur, erreurs);

		if(erreurs.size() > 0) {
			return utilisateur;
		}

		
		try {
			
			utilisateursDAO.addUtilisateurSecurise(utilisateur, motDePasse);
		} catch (DALException e) {
			
			erreurs.add(e.getLocalizedMessage());
			utilisateur = null;
		}
	
		return utilisateur;
	}
	
	public Utilisateur createUtilisateurDepuisLeWeb(String pseudo, String nom, String prenom, 
			String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, String credit, String administrateur, List<String> erreurs) {
		
		if(pseudo == null) erreurs.add("Le pseudo doit être renseigné");
		if(nom == null) erreurs.add("Le nom doit être renseigné");
		if(prenom == null) erreurs.add("Le prenom doit être renseigné");
		if(email == null) erreurs.add("L'email doit être renseigné");
		if(telephone == null) erreurs.add("Le telephone doit être renseigné");
		if(rue == null) erreurs.add("La rue doit être renseignée");
		if(codePostal == null) erreurs.add("Le code postal doit être renseigné");
		if(ville == null) erreurs.add("La ville doit être renseignée");
		if(motDePasse == null) erreurs.add("Le mot de passe doit être renseigné");
		if(credit == null) erreurs.add("Le credit doit être renseigné");
		
		boolean is_admin = false;
		
		if(administrateur == null) {
			is_admin = false;
		} else {
			if(administrateur.equalsIgnoreCase("on")) {
				is_admin = true;
			} else if (administrateur.equalsIgnoreCase("off")) {
				is_admin = false;
			} else {
				erreurs.add("Statut administrateur indefini");
			}
		}
		
		Integer credit_int = 0;
		
		try{
			credit_int = Integer.parseInt(credit);
		} catch(NumberFormatException e) {
			erreurs.add("Impossible de convertir le crédit en nombre entier");
		}
		
		if (!erreurs.isEmpty()) return null;
		
		return createUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit_int, is_admin, erreurs);
	}

	public void modifUtilisateurDepuisLeWeb(Utilisateur utilisateur, String pseudo, String nom, String prenom, 
			String email, String telephone, String rue,
			String codePostal, String ville, String credit, String administrateur, List<String> erreurs) {
		
		if(pseudo == null) erreurs.add("Le pseudo doit être renseigné");
		if(nom == null) erreurs.add("Le nom doit être renseigné");
		if(prenom == null) erreurs.add("Le prenom doit être renseigné");
		if(email == null) erreurs.add("L'email doit être renseigné");
		if(telephone == null) erreurs.add("Le telephone doit être renseigné");
		if(rue == null) erreurs.add("La rue doit être renseignée");
		if(codePostal == null) erreurs.add("Le code postal doit être renseigné");
		if(ville == null) erreurs.add("La ville doit être renseignée");
		if(credit == null) erreurs.add("Le credit doit être renseigné");
		
		boolean is_admin = false;
		if(administrateur == null) {
			is_admin = false;
		} else {
			if(administrateur.equalsIgnoreCase("on")) {
				is_admin = true;
			} else if (administrateur.equalsIgnoreCase("off")) {
				is_admin = false;
			} else {
				erreurs.add("Statut administrateur indefini");
			}
		}
		
		Integer credit_int = 0;
		try{
			credit_int = Integer.parseInt(credit);
		} catch(NumberFormatException e) {
			erreurs.add("Impossible de convertir le crédit en nombre entier");
		}
		
		if (!erreurs.isEmpty()) return;
		
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
			erreurs.add(e.getLocalizedMessage());
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

	public void sauvegarderUtilisateur(Utilisateur utilisateur, List<String> erreurs) throws BLLException {
		
		validerUtilisateur(utilisateur, erreurs);
		
		try {
			this.utilisateursDAO.update(utilisateur);
			this.utilisateursMap.put(utilisateur.getNoUtilisateur(), utilisateur);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	private void validerUtilisateur(Utilisateur utilisateur, List<String> erreurs) {
		
		if (utilisateur.getPseudo().length() < 1 || utilisateur.getPseudo().length() > 30) {
			erreurs.add("Le pseudo a pas la bonne longueur.");
		}
		
		if (utilisateur.getNom().length() < 1 || utilisateur.getNom().length() > 50) {
			erreurs.add("Le nom a pas la bonne longueur.");
		}
		
		if (utilisateur.getPrenom().length() < 1 || utilisateur.getPrenom().length() > 50) {
			erreurs.add("Le prénom a pas la bonne longueur.");
		}
		
		if (utilisateur.getEmail().length() < 1 || utilisateur.getEmail().length() > 50) {
			erreurs.add("L'email a pas la bonne longueur.");
		}
		
		if (utilisateur.getTelephone().length() < 1 || utilisateur.getTelephone().length() > 15) {
			erreurs.add("Le téléphone a pas la bonne longueur.");
		}
		
		if (utilisateur.getRue().length() < 1 || utilisateur.getRue().length() > 250) {
			erreurs.add("Le prénom a pas la bonne longueur.");
		}
		
		if (utilisateur.getCodePostal().length() != 5) {
			erreurs.add("Le code postal a pas la bonne longueur.");
		}
		
		if (utilisateur.getVille().length() < 1 || utilisateur.getVille().length() > 50) {
			erreurs.add("Le prénom a pas la bonne longueur.");
		}
	}
	
	private void validerMotDePasse(String motDePasse, List<String> erreurs) {
		
		if (motDePasse.length() < 6) {
			erreurs.add("Le mot de passe doit faire 6 caractères au moins !");
		}
	}


	private void validerMotDePasseRepete(String motDePasse, String motDePasseRepete, List<String> erreurs) {
		
		if (motDePasse.length() < 6) {
			erreurs.add("Le mot de passe doit faire 6 caractères au moins !");
		}
		
		if(!motDePasse.equals(motDePasseRepete)) {
			erreurs.add("Le mot de passe répété doit être identique !");
		}
	}
	
	public Utilisateur traiteRequeteInscription(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, String motDePasseRepete,
			List<String> errors) {
		
		if(pseudo == null) errors.add("Le pseudo doit être renseigné");
		if(nom == null) errors.add("Le nom doit être renseigné");
		if(prenom == null) errors.add("Le prenom doit être renseigné");
		if(email == null) errors.add("L'email doit être renseigné");
		if(telephone == null) errors.add("Le telephone doit être renseigné");
		if(rue == null) errors.add("La rue doit être renseignée");
		if(codePostal == null) errors.add("Le code postal doit être renseigné");
		if(ville == null) errors.add("La ville doit être renseignée");
		if(motDePasse == null) errors.add("Le mot de passe doit être renseigné");
		if(motDePasseRepete == null) errors.add("Le mot de passe répété doit être renseigné");

		if(errors.size() > 0) {
			return null;
		}

		validerMotDePasseRepete(motDePasse, motDePasseRepete, errors);
		
		if(errors.size() > 0) {
			return null;
		}
		
		return createUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false, errors);

	}
}
