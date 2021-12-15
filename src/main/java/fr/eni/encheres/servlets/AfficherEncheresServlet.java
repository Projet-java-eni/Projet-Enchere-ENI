package fr.eni.encheres.servlets;

/**
 * 
 * @author Sego
 *ivo
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class AfficherEncheresServlet
 */
@WebServlet("/AfficherEncheresServlet")
public class AfficherEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherEncheresServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		// On récupère la liste des articles avant de les trier par date de début
		ArticleManager articleManager = ArticleManager.GetInstance();
		List<Article> listeArticle = new ArrayList<>();

		listeArticle = articleManager.getCatalogue(erreurs);

		if (!erreurs.hasErrors()) {

			// On trie en fonction de la date de début (cf BO article)
			Collections.sort(listeArticle);
		}
// Transfert de l'affichage à la JSP
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//TODO: SI ON CLIQUE SUR CONNEXION DANS ACCUEIL: servlet/jsp pour mettre identifiants
//puis on revient ici pour l'affichage sur la page d'accueil des éléments de connexion

		// Affichage de toutes les enchères sans distinction
		try {
			EncheresManager encheresManager = EncheresManager.GetInstance();
			List<Enchere> listeEncheres = null; // problème avec le try catch

			listeEncheres = encheresManager.getAllEncheres();

		} catch (BLLException ex) {
			ex.printStackTrace();
			request.setAttribute("listeCodesErreur", ex.getListeCodesErreur());
		}
		// Transfert de l'affichage à la JSP accueilConnecte
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

}
