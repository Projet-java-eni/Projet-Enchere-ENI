//@author Lucie


package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.*;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;

/**
 * Servlet implementation class DetailArticle
 */
@WebServlet("/DetailArticle")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static ArticleManager articleManager = ArticleManager.GetInstance();
	private static EncheresManager encheresManager = EncheresManager.GetInstance();
	private static UtilisateursManager utilisateursManager = UtilisateursManager.GetInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailArticleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos) request.getAttribute("infos");
		//Récupérer et afficher les caractéristiques de l'article sur lequel l'utilisateur connecté a cliqué
		
		Article articleAAfficher = new Article();

		int meilleureOffre = -1;

		Utilisateur utilisateur = new Utilisateur();
		Integer userId = (Integer) request.getSession().getAttribute("user_id");
		if(request.getSession().getAttribute("user_id") != null) {
			utilisateur = utilisateursManager.getUtilisateurById(userId, erreurs); // On peut encherir si on est connecté et si on n'est pas la personne qui a créé la vente
		}
		
		//Récupérer le noArticle de l'article sur lequel l'utilisateur a cliqué
		Integer noArticle = null;
		try {
			noArticle = Integer.parseInt(request.getParameter("no_article"));
		} catch (NumberFormatException e) {
			erreurs.addErreur("Impossible de reconnaitre l'article id");
		}

		//utiliser le noArticle pour récupérer le contenu de l'article

		if(noArticle != null) {
			articleAAfficher = articleManager.getByIdAvecInstance(noArticle, articleAAfficher, erreurs);
		}

		//Récupérer via l'utilisateur
		Utilisateur vendeur = articleAAfficher.getUtilisateur();	

		//Récupérer la plus haute offre faite sur l'article via EncheresManager
		
		try {
			meilleureOffre = encheresManager.getMeilleureOffre(noArticle);
		} catch (BLLException ex) {
			// si le jeu d'encheres est vide, aucune enchère existe
			// on met l'offre actuelle à sa mise à prix.
			meilleureOffre = articleAAfficher.getMiseAPrix();
		}

		Enchere meilleureEnchere = new Enchere();
		meilleureEnchere.setMontantEnchere(meilleureOffre);

		// On fait les traitementsd si on demande une enchère
		if(request.getParameter("encherir") != null) {
			//Récupérer le montant de la nouvelleOffre via le formulaire/paramètre
			int nouvelleOffre = -1;
			try {
				nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
			} catch (NumberFormatException e) {
				erreurs.addErreur("Offre malformée");
			}

			articleManager.getByIdAvecInstance(noArticle, articleAAfficher, erreurs);

			//Récupérer date et heure actuelle

			//pour les mettre dans nouvelleEnchere
			encheresManager.essayerCreerEnchere(articleAAfficher, utilisateur, nouvelleOffre, erreurs);

			meilleureOffre = nouvelleOffre;
			if(!erreurs.hasErrors()) {
				infos.addInfo("Enchère créé avec succès !");
			}

		}

		//set attribute contenu de l'article
		request.setAttribute("noArticle", articleAAfficher.getNoArticle());
		request.setAttribute("nomArticle", articleAAfficher.getNomArticle());
		request.setAttribute("description", articleAAfficher.getDescription());
		request.setAttribute("miseAPrix", articleAAfficher.getMiseAPrix());
		request.setAttribute("dateFinEnchere", articleAAfficher.getDateFinEnchere());

		request.setAttribute("libelleCategorie", articleAAfficher.getCategorie().getLibelle());

		request.setAttribute("pseudoVendeur", articleAAfficher.getUtilisateur().getPseudo());

		request.setAttribute("rue", articleAAfficher.getRetrait().getRue());
		request.setAttribute("codePostal", articleAAfficher.getRetrait().getCodePostal());
		request.setAttribute("ville", articleAAfficher.getRetrait().getVille());

		request.setAttribute("meilleureOffre", meilleureOffre);

		request.setAttribute("errors", erreurs);
		

		//mettre l'Article en attribute pour le récupérer dans ValiderOffreServlet
		request.setAttribute("article", articleAAfficher);

		request.setAttribute("connecte", userId != null);
		request.setAttribute("peut_encherir", userId != null && (userId != vendeur.getNoUtilisateur()));
		request.setAttribute("peut_annuler_vente", userId != null && (userId == vendeur.getNoUtilisateur()));

		//Redirection vers la page d'affichage des détails de la vente
		 request.getRequestDispatcher("/WEB-INF/jsps/DetailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
