package fr.eni.encheres.bo;

/**
 * 
 * @author Sego
 *
 */

public class Retrait {

	private int noArticle;
	private String rue;
	private String codePostal;
	private String ville;

	/**
	 * 
	 */
	public Retrait() {
	}

	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @param noArticle
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(int noArticle, String rue, String codePostal, String ville) {
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getNoArticle() {
		return this.noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + this.rue + ", codePostal=" + this.codePostal + ", ville=" + this.ville + "]";
	}

}
