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
	private int prixVente;
	private String etatVente;
	
	public ArticleVendu() {	
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEnchere, 
			LocalDate dateFinEnchere, int miseAPrix, int prixVente, String etatVente)  {
		
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere= dateFinEnchere;
		this.miseAPrix= miseAPrix;
		this.miseAPrix= miseAPrix;
		this.prixVente= prixVente;
		this.etatVente= etatVente;
		
			
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
	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPRIx (int mise_a_prix) {
		this.miseAPrix = mise_a_prix;
	}

	
	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prix_vente) {
		this.prixVente = prix_vente;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prix_vente) {
		this.prixVente = prix_vente;
		
	}
	public String etatVente() {
		return etatVente;
	}

	public void setEtatVente(String etat_vente) {
		this.prixVente = etat_vente;
	}
}
