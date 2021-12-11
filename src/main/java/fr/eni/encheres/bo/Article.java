//@author Frédéric

package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Article {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	
	//ajout
	private Categorie categorie;
	private Retrait retrait;
	private Utilisateur utilisateur;
	private List<Enchere> encheres;
	
	
	
	public Article() {	
	}
	
	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere, 
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

	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDate date_debut_enchere) {
		this.dateDebutEnchere = date_debut_enchere;
	}
	
	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDate date_fin_enchere) {
		this.dateFinEnchere = date_fin_enchere;
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
	public String etatVente() {
		return etatVente;
	}

	public void setEtatVente(int etat_vente) {
		this.prixVente = etat_vente;
	}
}
