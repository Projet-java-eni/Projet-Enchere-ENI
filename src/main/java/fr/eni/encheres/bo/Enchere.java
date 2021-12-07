// @author Lucie
package fr.eni.encheres.bo;

import java.util.Date;

public class Enchere {
	
	private int noUtilisateur;
	private int noArticle;
	private int montantEnchere;
	private Date dateEnchere;
	
	public Enchere() {	
	}
	
	public Enchere(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.montantEnchere = montantEnchere;
		this.dateEnchere = dateEnchere;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [noUtilisateur=" + this.noUtilisateur + ", noArticle=" + this.noArticle + ", montantEnchere="
				+ this.montantEnchere + ", dateEnchere=" + this.dateEnchere + "]";
	}

}
