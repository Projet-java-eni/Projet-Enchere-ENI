package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticlesDAO;
import fr.eni.encheres.dal.DALException;

public class ArticlesImpl implements ArticlesDAO {

	private static final String sqlSelectArticleVenduById = "SELECT rue, codePostal, ville FROM Retrait WHERE noArticle=?";
	private static final String sqlInsertArticleVendu = "INSERT INTO ArticleVendu "
			+ "((noArticle, nomArticle,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,etatVente) "
			+ "VALUES (?,?,?,?,?,?,?,?)";
	private static final String sqlDeleteArticleVendu = "DELETE FROM ArticleVendu WHERE ArticleVendu=?";
	private static final String sqlUpdateArticleVendu = "UPDATE ArticleVendu SET noArticle=?,"
			+ "(noArticle=?, nomArticle=?,description=?,dateDebutEncheres=?,dateFinEncheres=?,miseAPrix=?,prixVente=?,etatVente=? WHERE ArticleVendu=?";


//	@Override
//	public ArticleVendu ArticleVendu(int noArticle) throws DALException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//		Retrait adresseRetrait = new Retrait();
//		ResultSet rs = null;
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.prepareStatement(sqlSelectArticleVenduById);
//			stmt.setInt(1, noArticle);
//			rs = stmt.executeQuery();
//
//			while (rs.next())
//				stmt.setString(2, ArticleVendu.getNoArticle());
//			stmt.setString(3, ArticleVendu.getNomArticle());
//			stmt.setString(4, ArticleVendu.getDescription());
//			stmt.setString(5, ArticleVendu.getDateDebutEnchere());
//			stmt.setString(6, ArticleVendu.getDateFinEnchere());
//			stmt.setString(7, ArticleVendu.getMiseaPrix());
//
//		}
//
//		catch (SQLException ex) {
//			throw new DALException("selectCoordonnees failed - artv = " + noArticle, ex);
//		}
//
//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//
//		return adresseRetrait;
//	}
//
//	@Override
//	public Retrait pointDeRetrait(int idRetrait) throws DALException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//		Retrait adresseRetrait = new Retrait();
//		ResultSet rs = null;
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.prepareStatement(sqlSelectAdresseByIdRetrait);
//			stmt.setInt(1, idRetrait);
//			rs = stmt.executeQuery();
//
//			while (rs.next())
//				stmt.setString(2, adresseRetrait.getRue());
//			stmt.setString(3, adresseRetrait.getCodePostal());
//			stmt.setString(4, adresseRetrait.getVille());
//
//		}
//
//		catch (SQLException ex) {
//			throw new DALException("selectCoordonnees failed - artv = " + idRetrait, ex);
//		}
//
//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//
//		return adresseRetrait;
//	}
//
//	@Override
//	public void insertNouvelleAdresse(Retrait adresse) throws DALException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//		Retrait newAdresseRetrait = new Retrait();
//		ResultSet rs = null;
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.prepareStatement(sqlInsertAdresseRetrait, Statement.RETURN_GENERATED_KEYS);
//			stmt.setString(1, newAdresseRetrait.getRue());
//			stmt.setString(2, newAdresseRetrait.getCodePostal());
//			stmt.setString(3, newAdresseRetrait.getVille());
//
//			int nbRows = stmt.executeUpdate();
//			if (nbRows == 1) {
//				rs = stmt.getGeneratedKeys();
//				if (rs.next()) {
//					adresse.setIdRetrait(rs.getInt(1));
//				}
//			}
//			stmt.execute();
//
//		} catch (SQLException ex) {
//			throw new DALException("Erreur dans l'insertion de l'adresse", ex);
//		}
//
//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//	}
//
//	@Override
//	public void deleteAdresseRetrait(int idRetrait) throws DALException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.prepareStatement(sqlDeleteAdresseRetrait);
//
//			stmt.setInt(1, idRetrait);
//			stmt.executeUpdate();
//
//		} catch (SQLException ex) {
//			throw new DALException("Erreur dans la suppression de l'article ", ex);
//
//		} finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//
//	}
//
//	@Override
//	public void updateAdresseRetrait(Retrait adresse) throws DALException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.prepareStatement(sqlUpdateAdresseRetrait);
//			stmt.setInt(1, adresse.getNoArticle());
//			stmt.setInt(2, adresse.getIdRetrait());
//			stmt.setString(3, adresse.getRue());
//			stmt.setString(4, adresse.getCodePostal());
//			stmt.setString(5, adresse.getVille());
//		} catch (SQLException ex) {
//			throw new DALException("update article failed - " + adresse, ex);
//		}
//
//		finally {
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//	}
//
//	@Override
//	public List<Retrait> selectAll() throws DALException {
//		List<Retrait> listCoordonnees = new ArrayList<Retrait>();
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = GetConnection.getConnexion();
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(sqlSelectAllCoordonnees);
//			Retrait coordonnees = null;
//			while (rs.next())
//				coordonnees = new Retrait(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
//						rs.getString(5));
//			listCoordonnees.add(coordonnees);
//
//		} catch (SQLException ex) {
//			throw new DALException("selectAll failed - ", ex);
//		}
//
//		finally {
//			GetConnection.close(rs);
//			GetConnection.close(stmt);
//			GetConnection.close(con);
//		}
//		return listCoordonnees;
//	}

	@Override
	public List<Article> getAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Article t) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article t) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Article t) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
