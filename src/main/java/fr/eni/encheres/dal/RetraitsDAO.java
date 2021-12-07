package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

/**
 * 
 * @author Sego
 *
 */

public interface RetraitsDAO {

	public Retrait lieuRetrait(int noArticle) throws DALException;
}
