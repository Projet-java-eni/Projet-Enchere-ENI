package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.RetraitsDAO;

/**
 * 
 * @author Sego
 *
 */

public class RetraitsImpl implements RetraitsDAO {

	// Requête SQL pour récupérer les coordonnées du vendeur
	private static final String sqlSelectCoordonnees = "SELECT rue, code_postal, ville FROM Retraits WHERE noArticle=?";

	public Retrait lieuRetrait(int noArticle) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		Retrait adresseRetrait = null;
		ResultSet rs = null;
		try {
			con = GetConnection.getConnexion();
			stmt = con.prepareStatement(sqlSelectCoordonnees);
			stmt.setInt(1, noArticle);
			rs = stmt.executeQuery();

			// on exploite le résultat du Statement
			while (rs.next())
				stmt.setString(2, adresseRetrait.getRue());
			stmt.setString(3, adresseRetrait.getCodePostal());
			stmt.setString(4, adresseRetrait.getVille());

		}

		catch (SQLException ex) {
			throw new DALException("selectCoordonnees failed - artv = " + noArticle, ex);
		}

//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}

		return adresseRetrait;
	}
}