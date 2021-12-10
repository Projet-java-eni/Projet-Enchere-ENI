//@author Frédéric

package fr.eni.encheres.bo;

import java.util.Date;

public class ArticleVendu {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private int miseAPrix;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	
	public ArticleVendu() {	
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEnchere, 
			LocalDate dateFinEnchere, int miseAPrix, int prixInitial, int prixVente, int noUtilisateur, int noCategorie) {
		
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere= dateFinEnchere;
		this.miseAPrix= miseAPrix;
		this.prixInitial= prixInitial;
		this.prixVente= prixVente;
		this.noUtilisateur= noUtilisateur;
		this.noCategorie= noCategorie;
			
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle (int no_article) {
		this.noArticle = no_article;
	}
	
	public String getNomArticle() { 
		return nomArticle;
	}


	public void setNomArticle(String nom_article) {
		this.nomArticle = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(Date date_debut_enchere) {
		this.dateDebutEnchere = date_debut_enchere;
	}
	
	public Date getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(Date date_fin_enchere) {
		this.dateFinEnchere = date_fin_enchere;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPRIx (int mise_a_prix) {
		this.miseAPrix = mise_a_prix;
	}
	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixIntitial(int prix_initial) {
		this.prixInitial = prix_initial;
	}
	
	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prix_vente) {
		this.prixVente = prix_vente;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur (int no_utilisateur) {
		this.noUtilisateur = no_utilisateur;
	}
	
	public int getNoCategorie() {
			return noCategorie;
		}

	public void setNoCategorie(int no_categorie) {
			this.noCategorie = no_categorie;
	}	
	
}
