package fr.eni.encheres.dal.jdbc;


package fr.eni.encheres.dal.jdbc;


public class ArticlesVendusImpl implements ArticlesVendusDAO {

	enum StoredStatements {
		GET_BY_ID("select noArticle libelle from dbo.articleVendu where noUtilisateur=?"),
		SELECT_ALL("select noArticle, libelle from dbo.articleVendu"),
		SELECT_BY_CATEGORIE("select noArticle libelle from dbo.articleVendu where categoriee=?"),
		INSERT("INSERT INTO dbo.articleVendu (libelle) VALUES (?)"),
		UPDATE("UPDATE dbo.articleVendu SET libelle=? WHERE noArticleVendu=?"),
		private String value; 
		StoredStatements(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	@Override
	public ArticleVendu getById(int id) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				"select noArticle, nomArticle, description,dateDebutEnchere,dateFinEnchere,miseAPrix,prixVente,etatVente "
						+ "from dbo.articleVendu " + "where no_utilisateur=?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				ArticleVendu articleVendu = new ArticleVendu(resultSet.getInt("noArticle"),
						resultSet.getString("nomArticle"), resultSet.getString("description"), resultSet.getLocalDate("dateDebutEnchere"),
						resultSet.getLocalDate("dateFinEnchere"), resultSet.getTnt("miseAPrix"), resultSet.getInt("prixVente"),
						resultSet.getString("etatVente"));
				return articleVendu;
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<ArticleVendu> getAll() throws DALException {
		List<ArticleVendu> articleVendu = new ArrayList<>();
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				"select noArticle,nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,prixVente,etatVente "
						+ "from dbo.articleVendu ")) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					ArticleVendu articleVendu = new ArticleVendu(resultSet.getInt("noArticle"),
							resultSet.getString("nomArticle"), resultSet.getString("description"), resultSet.getLocalDate("dateDebutEnchere"),
							resultSet.getLocalDate("dateFinEnchere"), resultSet.getTnt("miseAPrix"), resultSet.getInt("prixVente"),
							resultSet.getString("etatVente"));
					articleVendu.add(articleVendu);
				}
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

		return articleVendu;
	}
	
	
	
	@Override
	public List<ArticleVendu> selectArticleVenduByCategorie(int noCategorie) throws DALException {
		
	
	

	@Override	
	public void addArticleVendu(ArticleVendu articleVendu) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement("INSERT INTO dbo.articleVendu "
				+ "(noArticle,nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,prixVente,etatVente)"
				+ "VALUES (?,?,?,?,?,?,?,?)", new String[] { "article_vendu" })) {
			int i = 1;
			statement.setString(i++, utilisateur.getNoArticle());
			statement.setString(i++, utilisateur.getNomArticle());
			statement.setString(i++, utilisateur.getDescription());
			statement.setString(i++, utilisateur.getDateDebutEnchere());
			statement.setString(i++, utilisateur.getFinEnchere());
			statement.setString(i++, utilisateur.getMiseAPrixe());
			statement.setString(i++, utilisateur.getPrixVente());
			statement.setString(i++, utilisateur.EtatVente());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}
}
	
	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement("UPDATE dbo.articleVendu "
				+ "SET noArticle=?, nomArticle=?,description=?,dateDebutEncheres=?,"
				+ "dateFinEncheres=?,miseAPrix=?,prixVente=?,etatVente=? WHERE ArticleVendu=?\")) {
			int i = 1;
			statement.setString(i++, utilisateur.getNoArticle());
			statement.setString(i++, utilisateur.getNomArticle());
			statement.setString(i++, utilisateur.getDescription());
			statement.setString(i++, utilisateur.getDateDebutEnchere());
			statement.setString(i++, utilisateur.getFinEnchere());
			statement.setString(i++, utilisateur.getMiseAPrixe());
			statement.setString(i++, utilisateur.getPrixVente());
			statement.setString(i++, utilisateur.EtatVente());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}
}}