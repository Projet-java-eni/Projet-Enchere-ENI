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
		
			this.utilisateursDAO = DAOFactory.getUtilisateursDAO();
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
		
		validerMotDePasse(motDePasse, erreurs);
		
		if(erreurs.size() > 0) {
			return null;
		}
		
		Utilisateur utilisateur = new Utilisateur(
				pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur
		);
		
		validerUtilisateur(utilisateur, erreurs);

		if(erreurs.size() > 0) {
			return null;
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
		
		if (utilisateur.getNom() == null) {
			erreurs.add("Le nom d'utilisateur ne peut pas être vide");
		}
	}
	
	private void validerMotDePasse(String motDePasse, List<String> erreurs) {
		
		if (motDePasse.length() < 6) {
			erreurs.add("Le mot de passe doit faire 6 caractères au moins !");
		}
	}

	public void traiteRequeteInscription(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, String motDePasseRepete,
			List<String> errors) {
		// TODO Auto-generated method stub
		
	}
}
