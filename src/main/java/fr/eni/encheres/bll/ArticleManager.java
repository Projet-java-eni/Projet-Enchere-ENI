package main.java.fr.eni.encheres.bll;
//@author frederic

import java.time.LocalDate;
import main.java.fr.eni.encheres.bo.Article;
import main.java.fr.eni.encheres.dal.DALException;
import main.java.fr.eni.encheres.dal.DAOFactory;


public class ArticleManager {
	
	public void addArticle(Article article, String nomArticle, String description, LocalDate dateDebutEncheres, 
			LocalDate dateFinEncheres, int miseAPrix, String etatVente){
		
		if(nomArticle == null) erreurs.addErreur("Le nom de l'article doit être renseigné");
		if(description == null) erreurs.addErreur("Une description de votre article doit être renseigné");
		if(dateDebutEncheres == null) erreurs.addErreur("Une date de début d'enchère doit être renseignée");
		if(dateFinEncheres == null) erreurs.addErreur("Une date de fin d'enchère doit être renseignée");
		if(miseAPrix == null) erreurs.addErreur("Le prix de départ doit être renseigné");
		if(etatVente == null) erreurs.addErreur("L'état de la vente doit être renseignée");
	}
	
	
	
	public void modifArticle(Article article, String nomArticle, String description, LocalDate dateDebutEncheres, 
			LocalDate dateFinEncheres, int miseAPrix, String etatVente) {
			
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

public void supprimerArticle(Article article) throws BLLException {
		
		try {
			articlesDAO.remove(article);
			articlesMap.remove(article.getNoArticle());
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}
public Article getArticleByCategorie(int IdRetrait) throws BLLException {

	Article article = null;
	if (articleMap.containsKey(articleId)) {
		article = articleMap.get(articleId);
	} else {
		try {
			article = articleDAO.article(articleId);
			articleMap.put(articleId, article);
		} catch (DALException ex) {
			throw new BLLException(ex.getLocalizedMessage(), ex);
		}
	}

	return article;
}
public Article getArticleById(int articleId) throws BLLException {
	
	Article article = null;
	
	if (articleMap.containsKey(articleId)) {
		article = articleMap.get(articleId);
	} else {
		
		try {
			article = articleDAO.getById(articleId);
			articleMap.put(articleId, article);
		} catch (DALException e) {
			throw new BLLException(e.getLocalizedMessage(), e);
		}
	}

	return article;
}
}


