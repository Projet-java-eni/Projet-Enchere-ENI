package fr.eni.encheres.bll;

import java.util.List;
import java.util.Map;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitsDAO;

/**
 * 
 * @author Sego
 *
 */
//voir avisDAO dans demo module4 et catalogue manager du cours de souheil
public class RetraitsManager {

	private static RetraitsDAO daoRetraits;

	public RetraitsManager() throws BLLException, DALException {
		// on instancie le DAO
		daoRetraits = DAOFactory.getRetraitsDAO();

	}

	public static List<Retrait> getRetraits() throws BLLException {
		List<Retrait> listCoordonnees = null;
		try {
			listCoordonnees = daoRetraits.getAll();
		} catch (DALException ex) {
			ex.printStackTrace();
			throw new BLLException("Erreur dans la récupération de l'adresse", ex);
		}
		return listCoordonnees;

	}

	/**
	 * Récupération de l'adresse via l'Id de Retrait
	 * 
	 * @throws BLLException
	 */
	private Map<Integer, Retrait> retraitsMap = null;

	public Retrait recuperationAdresseByIdRetrait(int IdRetrait) throws BLLException {

		Retrait adresseRetrait = null;
		if (retraitsMap.containsKey(IdRetrait)) {
			adresseRetrait = retraitsMap.get(IdRetrait);
		} else {
			try {
				adresseRetrait = daoRetraits.getById(IdRetrait);
				retraitsMap.put(IdRetrait, adresseRetrait);
			} catch (DALException ex) {
				throw new BLLException(ex.getLocalizedMessage(), ex);
			}
		}

		return adresseRetrait;
	}

	/**
	 * Récupération de l'adresse via l'Id de l'article
	 * 
	 * @throws BLLException
	 */

	public Retrait recuperationAdresseByIdArticle(int IdRetrait) throws BLLException {

		Retrait adresseRetrait = null;
		if (retraitsMap.containsKey(IdRetrait)) {
			adresseRetrait = retraitsMap.get(IdRetrait);
		} else {
			try {
				adresseRetrait = daoRetraits.lieuRetrait(IdRetrait);
				retraitsMap.put(IdRetrait, adresseRetrait);
			} catch (DALException ex) {
				throw new BLLException(ex.getLocalizedMessage(), ex);
			}
		}

		return adresseRetrait;
	}

	/**
	 * Suppression d'une adresse de retrait
	 */
	public void removeAdresse(Retrait adresseRetrait) throws BLLException {
		try {
			daoRetraits.remove(adresseRetrait);
		} catch (DALException ex) {
			throw new BLLException("echec de la suppression de l'adresse - ", ex);
		}
	}

	/**
	 * Ajout d'une adresse de retrait complementaire
	 * 
	 * @throws BusinessException
	 */
	public void ajouterAdresse(Retrait newAdresse) throws BLLException, BusinessException {
		BusinessException exception = new BusinessException();
		if (newAdresse.getIdRetrait() != 0) {
			System.out.println("Une adresse est existante");
			try {
				validerAdresse(newAdresse, exception);
				daoRetraits.add(newAdresse);
			} catch (DALException ex) {
				throw new BLLException("Echec dans l'insertion de l'adresse", ex);
			}

		}
	}

	/**
	 * Mise à jour d'une adresse de retrait
	 * 
	 *
	 */

	public void updateAdresse(Retrait adresseRetrait) throws BLLException, BusinessException {
		BusinessException exception = new BusinessException();
		try {
			validerAdresse(adresseRetrait, exception);
			daoRetraits.update(adresseRetrait);
		} catch (DALException ex) {
			throw new BLLException("Echec dans la mise à jour de l'adresse :" + adresseRetrait, ex);

		}
	}

	/**
	 * Avant de transmettre l'adresse on la vérifie
	 */
	public void validerAdresse(Retrait adresseValidation, BusinessException exception) throws BusinessException {

		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (adresseValidation == null) {
			throw new BusinessException("adresse non renseignée");
		}

		if (adresseValidation.getNoArticle() == 0) {

			sb.append("Adresse invalide : identifiant de l'article manquant");
			valide = false;
		}

		if (adresseValidation.getRue() == null) {

			sb.append("Adresse invalide : nom de rue manquante");
			valide = false;
		}

		if (adresseValidation.getCodePostal() == null) {

			sb.append("Adresse invalide : code postal manquant");
			valide = false;
		}

		if (adresseValidation.getVille() == null) {

			sb.append("Adresse invalide : ville manquante");
			valide = false;
		}

		if (!valide) {
			throw new BusinessException("L'adresse n'a pas pu être renseignée");
		}

	}
}
