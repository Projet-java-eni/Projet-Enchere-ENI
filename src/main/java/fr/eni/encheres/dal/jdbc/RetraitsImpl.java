package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.RetraitsDAO;

public class RetraitsImpl implements RetraitsDAO {

	// Requête SQL pour récupérer les coordonnées du vendeur
	private static final String sqlSelectCoordonnees = "SELECT lieuRetrait FROM ArticleVendu WHERE noArticle=?";

	public ArticleVendu lieuRetrait(int noArticle) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		ArticleVendu artv = null;
		ResultSet rs = null;
		try {
			con = GetConnection.getConnexion();
			stmt = con.prepareStatement(sqlSelectCoordonnees);
			stmt.setInt(1, noArticle);
			rs = stmt.executeQuery();

			// on exploite le résultat du Statement
			if (rs.next())
				stmt.setString(2, artv.getLieuRetrait());

		}

		catch (

		DALException ex) {
			throw new DALException("selectCoordonnees failed - artv = " + noArticle, ex);
		}

//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}

		return artv;
	}
}
