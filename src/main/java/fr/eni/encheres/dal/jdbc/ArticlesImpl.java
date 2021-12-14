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
import fr.eni.encheres.dal.*;

public class ArticlesImpl implements ArticlesDAO {
	UtilisateursDAO utilisateursDAO = null;
	CategoriesDAO categoriesDAO = null;



	private static final String sqlSelectById = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,"
			+ "prix_vente,no_categorie, no_utilisateur  FROM dbo.ARTICLES_VENDUS WHERE no_article=?";

	private static final String sqlSelectAll = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,"
			+ "prix_vente,etat_vente,no_categorie,no_utilisateur  FROM dbo.ARTICLES_VENDUS";

	private static final String sqlSelectByCategorie = "SELECT no_article, nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial, " +
			" prix_vente,etat_vente,no_categorie,no_utilisateur FROM dbo.ARTICLES_VENDUS WHERE no_categorie=?";

	private static final String sqlInsert = "INSERT INTO dbo.ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial, "
			+ "prix_vente,etat_vente,no_categorie,no_utilisateur) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String sqlUpdate = "UPDATE dbo.ARTICLES_VENDUS SET  nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?, "
			+ "prix_vente=?,etat_vente=?,no_categorie=?,no_utilisateur=? WHERE no_article=?";

	private static final String sqlDelete = "DELETE FROM dbo.ARTICLES_VENDUS WHERE no_article=?";

	public ArticlesImpl() {
		try {
			this.utilisateursDAO = (UtilisateursDAO) DAOFactory.getUtilisateursDAO();
			this.categoriesDAO = (CategoriesDAO) DAOFactory.getCategoriesDAO();
		} catch (DALException e) {
			e.printStackTrace();
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
			statement = con.prepareStatement(sqlSelectById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			article.setNomArticle(resultSet.getString("nom_article"));
			article.setDescription(resultSet.getString("description"));
			article.setDateDebutEnchere(resultSet.getDate("date_debut_encheres").toLocalDate());
			article.setDateFinEnchere(resultSet.getDate("date_fin_encheres").toLocalDate());
			article.setMiseAPrix(resultSet.getInt("prix_initial"));
			article.setPrixVente(resultSet.getInt("prix_vente"));
			article.setCategorie(categoriesDAO.getById(resultSet.getInt("no_categorie")));
			article.setUtilisateur(utilisateursDAO.getById(resultSet.getInt("no_utilisateur")));
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
			resultSet = statement.executeQuery(sqlSelectAll);
			Article article = null;
			while (resultSet.next())

//				article = new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
//						resultSet.getLocalDate(4), resultSet.getLocalDate(5), resultSet.getInt(6), resultSet.getInt(7),
//						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
//						resultSet.getString(11), resultSet.getString(12));

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
			statement = con.prepareStatement(sqlSelectByCategorie);
			statement.setObject(1, categorie);
			resultSet = statement.executeQuery();

			article.setNomArticle(resultSet.getString("nom_article"));
			article.setDescription(resultSet.getString("description"));
			article.setDateDebutEnchere(resultSet.getDate("date_debut_encheres").toLocalDate());
			article.setDateFinEnchere(resultSet.getDate("date_fin_encheres").toLocalDate());
			article.setMiseAPrix(resultSet.getInt("prix_initial"));
			article.setPrixVente(resultSet.getInt("prix_vente"));
			article.setCategorie(categoriesDAO.getById(resultSet.getInt("no_categorie")));
			article.setUtilisateur(utilisateursDAO.getById(resultSet.getInt("no_utilisateur")));

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
			statement = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
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
			statement = con.prepareStatement(sqlUpdate);

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
			stmt = con.prepareStatement(sqlDelete);

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