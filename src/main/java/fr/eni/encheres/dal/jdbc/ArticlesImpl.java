package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticlesDAO;
import fr.eni.encheres.dal.DALException;

public class ArticlesImpl implements ArticlesDAO {

	enum StoredStatements {
		GET_BY_ID("SELECT noArticle, nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,"
				+ "prixVente,etatVente,categorie,retrait,utilisateur,encheres  FROM dbo.article WHERE Utilisateur=?"),
		SELECT_ALL("SELECT noArticle, nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,"
				+ "prixVente,etatVente,categorie,retrait,utilisateur,encheres  FROM dbo.article"),
		SELECT_BY_CATEGORIE("SELECT noArticle, nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,"
				+ "prixVente,etatVente,categorie,retrait,utilisateur,encheres FROM dbo.article WHERE categorie=?"),
		INSERT("INSERT INTO dbo.article (noArticle, nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,"
				+ "prixVente,etatVente,categorie,retrait,utilisateur,encheres) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"),
		UPDATE("UPDATE dbo.article SET noArticle=?, nomArticle=?,description=?,dateDebutEnchere=?,dateFinEnchere=?,miseAPrix=?,"
				+ "prixVente=?,etatVente=?,categorie=?,retrait=?,utilisateur=?,encheres=? WHERE noArticle=?"),
		REMOVE("DELETE FROM Article WHERE noArticle=?");

		private String value;

		StoredStatements(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	@Override
	public Article getById(int id) throws DALException {
		Article article = new Article();
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		Connection con = null;

		try {
			con = GetConnection.getConnexion();
			statement = con.prepareStatement("GET_BY_ID");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next())

				// ivo: peux tu vérifier les setObject et LocalDate... j'ai des doutes??
				statement.setString(2, article.getNomArticle());
			statement.setString(3, article.getDescription());
			statement.setObject(4, (LocalDate) article.getDateDebutEnchere());
			statement.setObject(5, (LocalDate) article.getDateFinEnchere());
			statement.setInt(6, article.getMiseAPrix());
			statement.setInt(7, article.getPrixVente());
			statement.setString(8, article.getEtatVente());
			statement.setObject(9, article.getCategorie());
			statement.setObject(10, article.getRetrait());
			statement.setObject(11, article.getUtilisateur());
			statement.setObject(12, article.getEncheres());

		} catch (SQLException ex) {
			throw new DALException("getById failed  = " + id, ex);
		}

		finally {
			GetConnection.close(resultSet);
			GetConnection.close(statement);
			GetConnection.close(con);
		}

		return article;
	}

	@Override
	public List<Article> getAll() throws DALException {
		List<Article> listArticle = new ArrayList<Article>();
		ResultSet resultSet = null;
		Statement statement = null;
		Connection con = null;

		try {
			con = GetConnection.getConnexion();
			statement = con.createStatement();
			resultSet = statement.executeQuery("SELECT_ALL");
			Article article = null;
			while (resultSet.next())

				article = new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getLocalDate(4), resultSet.getLocalDate(5), resultSet.getInt(6), resultSet.getInt(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getString(12));

			listArticle.add(article);
		} catch (SQLException ex) {
			throw new DALException("selectAll failed - ", ex);
		}

		finally {
			GetConnection.close(resultSet);
			GetConnection.close(statement);
			GetConnection.close(con);
		}
		return listArticle;
	}

	@Override
	public Article getByCategorie(Categorie categorie) throws DALException {
		Article article = new Article();
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		Connection con = null;

		try {
			con = GetConnection.getConnexion();
			statement = con.prepareStatement("SELECT_BY_CATEGORIE");
			statement.setObject(1, categorie);
			resultSet = statement.executeQuery();

			while (resultSet.next())

				// ivo: peux tu vérifier les setObject et LocalDate... j'ai des doutes??
				statement.setObject(2, article.getNoArticle());
			statement.setString(3, article.getNomArticle());
			statement.setString(4, article.getDescription());
			statement.setObject(5, (LocalDate) article.getDateDebutEnchere());
			statement.setObject(6, (LocalDate) article.getDateFinEnchere());
			statement.setInt(7, article.getMiseAPrix());
			statement.setInt(8, article.getPrixVente());
			statement.setString(9, article.getEtatVente());
			statement.setObject(10, article.getRetrait());
			statement.setObject(11, article.getUtilisateur());
			statement.setObject(12, article.getEncheres());

		} catch (SQLException ex) {
			throw new DALException("getByCategorie failed  = " + categorie, ex);
		}

		finally {
			GetConnection.close(resultSet);
			GetConnection.close(statement);
			GetConnection.close(con);
		}

		return article;
	}

	@Override
	public void add(Article article) throws DALException {
		Connection con = null;
		PreparedStatement statement = null;
		Article nouvelArticle = new Article();
		ResultSet rs = null;

		try {
			con = GetConnection.getConnexion();
			statement = con.prepareStatement("INSERT", Statement.RETURN_GENERATED_KEYS);
			int i = 1;

			statement.setString(i++, nouvelArticle.getNomArticle());
			statement.setString(i++, nouvelArticle.getDescription());
			statement.setObject(i++, nouvelArticle.getDateDebutEnchere());
			statement.setObject(i++, nouvelArticle.getDateFinEnchere());
			statement.setInt(i++, nouvelArticle.getMiseAPrix());
			statement.setInt(i++, nouvelArticle.getPrixVente());
			statement.setString(i++, nouvelArticle.getEtatVente());
			statement.setObject(i++, nouvelArticle.getCategorie());
			statement.setObject(i++, nouvelArticle.getRetrait());
			statement.setObject(i++, nouvelArticle.getUtilisateur());
			statement.setObject(i++, nouvelArticle.getEncheres());

			int nbRows = statement.executeUpdate();
			if (nbRows == 1) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			}
			statement.execute();

		} catch (SQLException ex) {
			throw new DALException("Erreur dans l'insertion de l'article", ex);
		}

		finally {
			GetConnection.close(rs);
			GetConnection.close(statement);
			GetConnection.close(con);
		}
	}

	@Override
	public void update(Article article) throws DALException {
		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = GetConnection.getConnexion();
			statement = con.prepareStatement("UPDATE");

			int i = 1;
			statement.setInt(i++, article.getNoArticle());
			statement.setString(i++, article.getNomArticle());
			statement.setString(i++, article.getDescription());
			statement.setObject(i++, article.getDateDebutEnchere());
			statement.setObject(i++, article.getDateFinEnchere());
			statement.setInt(i++, article.getMiseAPrix());
			statement.setInt(i++, article.getPrixVente());
			statement.setString(i++, article.getEtatVente());
			statement.setObject(i++, article.getCategorie());
			statement.setObject(i++, article.getRetrait());
			statement.setObject(i++, article.getUtilisateur());
			statement.setObject(i++, article.getEncheres());

			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new DALException("update article failed - " + article, ex);
		}

		finally {
			GetConnection.close(statement);
			GetConnection.close(con);
		}
	}

	@Override
	public void remove(Article supArticle) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = GetConnection.getConnexion();
			stmt = con.prepareStatement("REMOVE");

			stmt.setInt(1, supArticle.getNoArticle());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DALException("Erreur dans la suppression de l'article ", ex);

		} finally {
			GetConnection.close(rs);
			GetConnection.close(stmt);
			GetConnection.close(con);
		}

	}
}