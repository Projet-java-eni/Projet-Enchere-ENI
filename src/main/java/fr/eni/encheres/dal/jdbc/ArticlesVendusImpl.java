package fr.eni.encheres.dal.jdbc;


import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticlesDAO;
import fr.eni.encheres.dal.DALException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticlesVendusImpl implements ArticlesDAO {

	enum StoredStatements {
		GET_BY_ID("select noArticle libelle from dbo.articleVendu where noUtilisateur=?"),
		SELECT_ALL("select noArticle, libelle from dbo.articleVendu"),
		SELECT_BY_CATEGORIE("select noArticle libelle from dbo.articleVendu where categoriee=?"),
		INSERT("INSERT INTO dbo.articleVendu (libelle) VALUES (?)"),
		UPDATE("UPDATE dbo.articleVendu SET libelle=? WHERE noArticleVendu=?");
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
//		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
//				"select noArticle, nomArticle, description,dateDebutEnchere,dateFinEnchere,miseAPrix,prixVente,etatVente "
//						+ "from dbo.articles " + "where no_utilisateur=?")) {
//			statement.setInt(1, id);
//			try (ResultSet resultSet = statement.executeQuery()) {
//				resultSet.next();
//				Article articleVendu = new Article(resultSet.getInt("noArticle"),
//						resultSet.getString("nomArticle"), resultSet.getString("description"), resultSet.getLocalDate("dateDebutEnchere"),
//						resultSet.getLocalDate("dateFinEnchere"), resultSet.getTnt("miseAPrix"), resultSet.getInt("prixVente"),
//						resultSet.getString("etatVente"));
//				return articleVendu;
//			}
//		} catch (SQLException e) {
//			throw new DALException(e.getMessage(), e);
//		}
		return new Article();
	}
	
	@Override
	public List<Article> getAll() throws DALException {
		List<Article> articleVendu = new ArrayList<>();
//		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
//				"select noArticle,nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,prixVente,etatVente "
//						+ "from dbo.articleVendu ")) {
//			try (ResultSet resultSet = statement.executeQuery()) {
//				while (resultSet.next()) {
//					Article articleVendu = new Article(resultSet.getInt("noArticle"),
//							resultSet.getString("nomArticle"), resultSet.getString("description"), resultSet.getLocalDate("dateDebutEnchere"),
//							resultSet.getLocalDate("dateFinEnchere"), resultSet.getTnt("miseAPrix"), resultSet.getInt("prixVente"),
//							resultSet.getString("etatVente"));
//					articleVendu.add(articleVendu);
//				}
//			}
//		} catch (SQLException e) {
//			throw new DALException(e.getMessage(), e);
//		}

		return articleVendu;
	}
	
	
	public List<Article> selectArticleVenduByCategorie(int noCategorie) throws DALException {
		return new ArrayList<>();

	}

	@Override	
	public void add(Article articleVendu) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement("INSERT INTO dbo.ARTICLES_VENDUS "
				+ "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente)"
				+ "VALUES (?,?,?,?,?,?,?,?)", new String[] { "article_vendu" })) {
			int i = 1;
//			statement.setString(i++, articleVendu.getNoArticle());
//			statement.setString(i++, articleVendu.getNomArticle());
//			statement.setString(i++, articleVendu.getDescription());
//			statement.setString(i++, articleVendu.getDateDebutEnchere());
//			statement.setString(i++, articleVendu.getFinEnchere());
//			statement.setString(i++, articleVendu.getMiseAPrixe());
//			statement.setString(i++, articleVendu.getPrixVente());
//			statement.setString(i++, utilisateur.EtatVente());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

	
	@Override
	public void update(Article articleVendu) throws DALException {
//		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement("UPDATE dbo.articleVendu "
//				+ "SET noArticle=?, nomArticle=?,description=?,dateDebutEncheres=?,"
//				+ "dateFinEncheres=?,miseAPrix=?,prixVente=?,etatVente=? WHERE ArticleVendu=?\")) {
//			int i = 1;
//			statement.setString(i++, utilisateur.getNoArticle());
//			statement.setString(i++, utilisateur.getNomArticle());
//			statement.setString(i++, utilisateur.getDescription());
//			statement.setString(i++, utilisateur.getDateDebutEnchere());
//			statement.setString(i++, utilisateur.getFinEnchere());
//			statement.setString(i++, utilisateur.getMiseAPrixe());
//			statement.setString(i++, utilisateur.getPrixVente());
//			statement.setString(i++, utilisateur.EtatVente());
//
//			statement.executeUpdate();
//		} catch (SQLException e) {
//			throw new DALException(e.getMessage(), e);
//		}

	}

	@Override
	public void remove(Article article) throws DALException {

	}
}