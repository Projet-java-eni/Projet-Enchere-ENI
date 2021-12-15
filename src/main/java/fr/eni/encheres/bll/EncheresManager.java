package fr.eni.encheres.bll;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.daos.DAOFactory;
import fr.eni.encheres.dal.daos.EncheresDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import fr.eni.encheres.bo.Enchere;


public class EncheresManager {

	private static EncheresDAO daoEnchere = null;
	private static EncheresManager instance = null;
		
	private EncheresManager() {
	}

	public static EncheresManager GetInstance() {
		if(instance == null) {
			instance = new EncheresManager();
		}

		if(daoEnchere == null) {
			try {
				daoEnchere = (EncheresDAO) DAOFactory.getEncheresDAO();
			} catch (DALException ex) {
				ex.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * Valider les données d'une enchère
	 * @param e
	 * @throws BLLException
	 */
	public void validerEnchere(Enchere e) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(e==null){
			throw new BLLException("Enchère null");
		}
		//Les attributs des enchères sont obligatoires
		//le numéro utilisateur ne peut pas être inférieur ou égal à 0. Il est récupéré via la connection au compte utilisateur dans ValiderOffreServlet
		if(e.getUtilisateur().getNoUtilisateur()<=0){
			sb.append("Le numéro d'utilisateur est obligatoire.\n");
			valide = false;
		}
		//le numéro article ne peut pas être inférieur ou égal à 0. Il est récupéré via la sélection de l'article par l'utilisateur au moment de faire l'enchère
		if(e.getArticle().getNoArticle()<=0){
			sb.append("Le numéro de l'article est obligatoire.\n");
			valide = false;
		}
		//le montant de l'enchère ne peut pas être inférieur ou égal à 0. L'utilisateur entre le montant souhaité
		if(e.getMontantEnchere()<=0){
			sb.append("Le montant de l'enchère doit être supérieur à zéro.\n");
			valide = false;
		}
		//la date de l'enchère ne peut pas être null. Elle est instanciée dans ValiderOffreServlet
		if(e.getDateEnchere() == null){
			sb.append("Date invalide.\n");
			valide = false;
		}
		
		//l'heure de l'enchère ne peut pas être null. Elle est instanciée dans ValiderOffreServlet
			if(e.getHeureEnchere() == null){
				sb.append("Heure invalide.\n");
				valide = false;
			}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}
	}
	
	/**
	 * Ajout d'une enchère
	 * @param newEnchere
	 * @return index du nouvel article dans le catalogue
	 * @throws BLLException 
	 */
	public void addEnchere(Enchere newEnchere) throws BLLException {
		//TODO vérifier que montantEnchere de newEnchere != meilleure offre sur cet article -> contrainte check sur l'insert sur la table Encheres en bdd?
		//TODO vérifier que ce n'est pas une enchère déjà existante -> contrainte check?
		try {
			validerEnchere(newEnchere);
			daoEnchere.add(newEnchere);
		} catch (DALException e) {
			throw new BLLException("Echec addEnchere", e);
		}
	}
	
	/**
	 * getAllEncheres : Afficher toutes les enchères existantes
	 * @param 
	 * @throws BLLException
	 */
		public List<Enchere> getAllEncheres() throws BLLException{
			List<Enchere> encheres=null;
		try {
			
			encheres = daoEnchere.getAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération liste de toutes les enchères", e);
		}
		
		return encheres;
	}
	
	/**
	 * getEncheresByNoUtilisateur : Afficher toutes les enchères faites par un utilisateur
	 * @param noUtilisateur
	 * @throws BLLException
	 */
		public List<Enchere> getEncheresByNoUtilisateur(int noUtilisateur) throws BLLException{
			List<Enchere> encheres=null;
			try {
				encheres = ((EncheresDAO) daoEnchere).selectEncheresByNoUtilisateur(noUtilisateur);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Erreur récupération liste enchères de l'utilisateur", e);
			}
			
			return encheres;
		}
	
	/**
	 * getEncheresByNoArticle : Afficher toutes les enchères faites sur un article
	 * @param noArticle
	 * @throws BLLException
	 */
		public List<Enchere> getEncheresByNoArticle(int noArticle) throws BLLException{
			List<Enchere> encheres=null;
			try {
				encheres = ((EncheresDAO) daoEnchere).selectEncheresByNoArticle(noArticle);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Erreur récupération liste enchères de l'article", e);
			}
			
			return encheres;
		}
	
	/**
	 * getEncheresByNoUtlisateurEtNoArticle : Afficher toutes les enchères faites par un utilisateur sur un article
	 * @param noUtilisateur, noArticle
	 * @throws BLLException
	 */
		public List<Enchere> getEncheresByNoUtlisateurEtNoArticle(int noUtilisateur, int noArticle) throws BLLException{
			List<Enchere> encheres=null;
			try {
				encheres = ((EncheresDAO) daoEnchere).selectEncheresByNoUtilisateurEtNoArticle(noUtilisateur, noArticle);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Erreur récupération liste enchères de l'utilisateur sur l'article", e);
			}
			
			return encheres;
		}
	
	
	/**
	 * updateEnchere : Modifier une enchère
	 * @param enchere
	 * @throws BLLException
	 */
	public void updateEnchere(Enchere enchere) throws BLLException{
		try {
			validerEnchere(enchere);
			daoEnchere.update(enchere);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateEnchere-enchère: "+ enchere, e);
		}
	}
	
	/**
	 * removeEnchere : Supprimer une enchère
	 * @param noUtilisateur, noArticle
	 * @throws BLLException
	 */
	public void removeEnchere(int noUtilisateur, int noArticle) throws BLLException{
		try {
			((EncheresDAO) daoEnchere).deleteEnchereByNoUtilisateurEtNoArticle(noUtilisateur, noArticle);
		} catch (DALException e) {
			throw new BLLException("Echec removeEnchere - ", e);
		}
		
	}
	
	/**
	 * selectMeilleureOffre : récupérer le montant de la plus haute offre faite sur un article
	 * @param noArticle
	 * @throws BLLException
	 */
	public int getMeilleureOffre(int noArticle) throws BLLException{
		int meilleureOffre;
		try {
			meilleureOffre = daoEnchere.selectMeilleureOffreByNoArticle(noArticle);
		} catch (DALException e) {
			throw new BLLException("Echec selectMeilleureOffre - ", e);
		}
		return meilleureOffre;
	}

	public int getMeilleureOffre(int noArticle, Erreurs erreurs) {
		int meilleureOffre = 0;
		try {
			meilleureOffre = daoEnchere.selectMeilleureOffreByNoArticle(noArticle);
		} catch (DALException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}
		return meilleureOffre;
	}

	/**
	 * Essaye de creer une enchère, sans en instancier une
	 * @param article
	 * @param utilisateur
	 * @param montant
	 * @param erreurs
	 */
	public void essayerCreerEnchere(Article article, Utilisateur utilisateur, Integer montant, Erreurs erreurs) {
		if(article == null) {
			erreurs.addErreur("Article n'est pas trouvé.");
		}
		if(utilisateur == null) {
			erreurs.addErreur("Utilisateur pas trouvé.");
		}
		if(article != null && utilisateur != null){
			if (article.getUtilisateur().getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
				erreurs.addErreur("Le propriétaire de la vente ne peut pas enchérir dessus.");
			}
		}

		if(erreurs.hasErrors()) {
			return;
		}

		Enchere enchere = new Enchere(utilisateur, article, LocalDate.now(), LocalTime.now(), montant);
		try {
			validerEnchere(enchere);
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}
	}
}
