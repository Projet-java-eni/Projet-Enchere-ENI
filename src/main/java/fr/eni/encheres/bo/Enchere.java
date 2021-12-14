// @author Lucie
package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Enchere {
	private Utilisateur utilisateur;
	private Article article;
	/*private int noUtilisateur;
	private int noArticle;*/
	private int montantEnchere;
	private LocalDate dateEnchere;
	private LocalTime heureEnchere;

	public Enchere() {	
	}
	
	public Enchere(Utilisateur utilisateur, Article article, LocalDate dateEnchere, LocalTime heureEnchere, int montantEnchere) {
		super();
		this.setUtilisateur(utilisateur);
		this.setArticle(article);
		this.montantEnchere = montantEnchere;
		this.dateEnchere = dateEnchere;
		this.heureEnchere = heureEnchere;
	}

	/*public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}*/
	
	/*public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}*/

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [noUtilisateur=" + this.utilisateur.getNoUtilisateur() + ", noArticle=" + this.article.getNoArticle() + ", montantEnchere="
				+ this.montantEnchere + ", dateEnchere=" + this.dateEnchere + "]";
	}


	public LocalTime getHeureEnchere() {
		return heureEnchere;
	}

	public void setHeureEnchere(LocalTime heureEnchere) {
		this.heureEnchere = heureEnchere;
	}
}
