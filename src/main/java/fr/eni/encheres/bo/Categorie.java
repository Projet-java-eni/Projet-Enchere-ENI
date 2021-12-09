package fr.eni.encheres.bo;

import java.io.Serializable;


public class Categorie implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private int noCategorie;
	private String libelle;
	
	
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

}
