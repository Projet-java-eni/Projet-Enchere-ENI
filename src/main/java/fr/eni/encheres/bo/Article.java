//@author Frédéric

package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Article {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalTime timeDebutEnchere;
	private LocalDate dateFinEnchere;
	private LocalTime timeFinEnchere;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;

	// ajout
	private Categorie categorie;
	private Retrait retrait;
	private Utilisateur utilisateur;
	private List<Enchere> encheres;

	public Article() {
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
		this.prixVente = prixVente;
		this.etatVente = etatVente;

	}

	/**
	 * @author Sego Constructeur avec tous les paramètres
	 */
	public Article(int noArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
				   LocalDate dateFinEnchere, int miseAPrix, int prixVente, String etatVente, Categorie categorie,
			Retrait retrait, Utilisateur utilisateur, List<Enchere> encheres) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.utilisateur = utilisateur;
		this.encheres = encheres;
	}

	public Article(int noArticle) {
		super();
		this.noArticle = noArticle;
	}

	public int getNoArticle() {
		return this.noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return this.nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMiseAPrix() {
		return this.miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return this.prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return this.etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return this.retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Enchere> getEncheres() {
		return this.encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + this.noArticle + ", nomArticle=" + this.nomArticle + ", description="
				+ this.description + ", dateDebutEnchere=" + this.dateDebutEnchere + ", dateFinEnchere="
				+ this.dateFinEnchere + ", miseAPrix=" + this.miseAPrix + ", prixVente=" + this.prixVente
				+ ", etatVente=" + this.etatVente + ", categorie=" + this.categorie + ", retrait=" + this.retrait
				+ ", utilisateur=" + this.utilisateur + ", encheres=" + this.encheres + "]";
	}

	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalTime getTimeDebutEnchere() {
		return timeDebutEnchere;
	}

	public void setTimeDebutEnchere(LocalTime timeDebutEnchere) {
		this.timeDebutEnchere = timeDebutEnchere;
	}

	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public LocalTime getTimeFinEnchere() {
		return timeFinEnchere;
	}

	public void setTimeFinEnchere(LocalTime timeFinEnchere) {
		this.timeFinEnchere = timeFinEnchere;
	}

	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setHeureDebutEnchere(LocalDate toLocalDate) {
	}
}
