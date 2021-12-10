package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Retrait;

/**
 * 
 * @author Sego
 *
 */

public interface RetraitsDAO extends DAO<Retrait> {

	public Retrait lieuRetrait(int noArticle) throws DALException;

}
