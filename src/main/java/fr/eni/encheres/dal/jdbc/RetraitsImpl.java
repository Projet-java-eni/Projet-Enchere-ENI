package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.RetraitsDAO;
import fr.eni.javaee.module4.dal.ConnectionProvider;

public class RetraitsImpl implements RetraitsDAO {

	// Requête SQL pour récupérer les coordonnées du vendeur
	private static final String sqlSelectCoordonnees = "SELECT rue, codePostal, ville FROM Utilisateur WHERE noUtilisateur=?";

	public Utilisateur lieuRetrait(int noUtilisateur) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		Utilisateur user = null;
		ResultSet rs = null;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(sqlSelectCoordonnees);
			stmt.setInt(1, noUtilisateur);
			rs = stmt.executeQuery();

			// on exploite le résultat du Statement
			while(rs.next())
			stmt.setString(2, user.getRue());
			stmt.setString(3, user.getCodePostal());
			stmt.setString(4, user.getVille());
							
			}

		}catch(

	DALException ex)
	{
		throw new DALException("selectCoordonnees failed - user = " + noUtilisateur, ex);
	}

//		finally {
//			ConnectionProvider.close(rs);
//			ConnectionProvider.close(stmt);
//			ConnectionProvider.close(con);
//		}

	return user;
}
