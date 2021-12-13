package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.misc.ArticleVendu;
import fr.eni.encheres.bo.misc.MeHasMany;

public class Categorie implements Serializable, MeHasMany<ArticleVendu> {

	@Override
	public String toString() {
		return String.format("Utilisateur : id %d, libelle : %s", noCategorie, libelle);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	// champs de la BDD
	private int noCategorie;
	private String libelle;

	// champs supplémentaires
	private List<ArticleVendu> categorieArticle = null;

	public Categorie(int id, String libelle) {
		super();
		this.noCategorie = id;
		this.libelle = libelle;
	}

	public Categorie(String libelle) {
		this(-1, libelle);
	}

	public int getId() {
		return noCategorie;
	}

	public void setId(int id) {
		this.noCategorie = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	// Création de méthodes permettant de faire le lien avec la classe ArticleVendu
	@Override
	public void ajouter(ArticleVendu typeArticle) {
		if (categorieArticle == null) {
			categorieArticle = new ArrayList<>();
		}

		categorieArticle.add(typeArticle);

	}

	public void supprimer(ArticleVendu typeArticle) {
		categorieArticle.remove(typeArticle);
	}
}
