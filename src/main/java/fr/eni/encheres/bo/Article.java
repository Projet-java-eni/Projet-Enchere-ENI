//@author Frédéric

package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.List;

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

	public Article(int id_article) {
	}

	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
				   LocalDate dateFinEnchere, int miseAPrix, int prixVente, String etatVente) {

		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;


	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int no_article) {
		this.noArticle = no_article;
	}

	public String getNomArticle() {
		return nomArticle;
	}
}