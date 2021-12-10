package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

/**
 * 
 * @author Sego
 *
 */

public interface RetraitsDAO extends DAO<Retrait> {

	public Retrait lieuRetrait(int noArticle) throws DALException;

	public Retrait getById(int idRetrait) throws DALException;

}
