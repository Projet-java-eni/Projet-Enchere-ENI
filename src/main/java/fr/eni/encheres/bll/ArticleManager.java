package fr.eni.encheres.bll;
//@author frederic

import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.encheres.Utilitaires;
import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticlesDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;


public class ArticleManager {

	private static ArticlesDAO articlesDAO = null;
	private static ArticleManager instance = null;

	private ArticleManager() {
	}

	public static ArticleManager GetInstance() {

		if(articlesDAO == null) {
			try {
				articlesDAO = (ArticlesDAO) DAOFactory.getArticlesDAO();
			} catch (DALException e) {
				e.printStackTrace();
			}
		}

		if (instance == null) {
			instance = new ArticleManager();
		}

		return instance;
	}

	public void addArticle(Article article, String nomArticle, String description, LocalDate dateDebutEncheres,
						   LocalDate dateFinEncheres, Integer miseAPrix, String etatVente, Erreurs erreurs){
		
		if(nomArticle == null) erreurs.addErreur("Le nom de l'article doit être renseigné");
		if(description == null) erreurs.addErreur("Une description de votre article doit être renseigné");
		if(dateDebutEncheres == null) erreurs.addErreur("Une date de début d'enchère doit être renseignée");
		if(dateFinEncheres == null) erreurs.addErreur("Une date de fin d'enchère doit être renseignée");
		if(miseAPrix == null) erreurs.addErreur("Le prix de départ doit être renseigné");
		if(etatVente == null) erreurs.addErreur("L'état de la vente doit être renseignée");
	}
	
	
	
	public void modifArticle(Article article, String nomArticle, String description, LocalDate dateDebutEncheres, 
			LocalDate dateFinEncheres, Integer miseAPrix, String etatVente, Erreurs erreurs) {
			
		if(nomArticle == null) erreurs.addErreur("Le nom de l'article doit être renseigné");
		if(description == null) erreurs.addErreur("Une description de votre article doit être renseigné");
		if(dateDebutEncheres == null) erreurs.addErreur("Une date de début d'enchère doit être renseignée");
		if(dateFinEncheres == null) erreurs.addErreur("Une date de fin d'enchère doit être renseignée");
		if(miseAPrix == null) erreurs.addErreur("Le prix de départ doit être renseigné");
		if(etatVente == null) erreurs.addErreur("L'état de la vente doit être renseignée");
			
		try {
			sauvegarderArticle(article, erreurs);
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}
	}

	private void sauvegarderArticle(Article article, Erreurs erreurs) throws BLLException{
	}

	public void supprimerArticle(Article article) throws BLLException {
		
		try {
			articlesDAO.remove(article);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}
	public Article getArticleByCategorie(Categorie categorie) throws BLLException {

		Article article = null;

		try {
			article = articlesDAO.getByCategorie(categorie);
		} catch (DALException ex) {
			throw new BLLException(ex.getLocalizedMessage(), ex);
		}

		return article;
	}
	public Article getArticleById(int articleId) throws BLLException {

		Article article = null;


		try {
			article = articlesDAO.getById(articleId);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}

		return article;
	}

	public void sauvegarderDepuisLeWeb(String nom, String description, String prix, String date, Article article, Erreurs erreurs) {
		if(nom == null) erreurs.addErreur("Le nom doit être renseigné"); else article.setNomArticle(nom);
		if(description == null) erreurs.addErreur("La description doit être renseignée"); else article.setDescription(description);
		if(prix == null) erreurs.addErreur("Le prix doit être renseigné");
		if(date == null) erreurs.addErreur("La date doit être renseignée");

		if (erreurs.hasErrors()) return;

		int prixInt;

		try {
			assert prix != null;
			prixInt = Integer.parseInt(prix);
		} catch (NumberFormatException e) {
			erreurs.addErreur("Valeur mise à prix malformatée");
			prixInt = 0;
		}

		article.setMiseAPrix(prixInt);

		LocalDateTime dateMise = Utilitaires.fromHTMLDateTimeLocal(date);
		article.setDateDebutEnchere(dateMise.toLocalDate());
		article.setTimeDebutEnchere(dateMise.toLocalTime());
	}
}


