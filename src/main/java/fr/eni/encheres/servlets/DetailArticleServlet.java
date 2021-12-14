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

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;

/**
 * Servlet implementation class DetailArticle
 */
@WebServlet("/DetailArticle")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ArticleManager articleManager;
	private EncheresManager encheresManager;
	private Article articleAAfficher;
	private int meilleureOffre;
	
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
	//Récupérer et afficher les caractéristiques de l'article sur lequel l'utilisateur connecté a cliqué
		
		//Récupérer le noArticle de l'article sur lequel l'utilisateur a cliqué
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		//utiliser le noArticle pour récupérer le contenu de l'article
		try {
			articleAAfficher = articleManager.getArticleById(noArticle);
		} catch (BLLException ex) {
			ex.printStackTrace();
		}
		
		//get attribute contenu de l'article
		String nomArticle = articleAAfficher.getNomArticle();
		String description = articleAAfficher.getDescription();
		int miseAPrix = articleAAfficher.getMiseAPrix();
		LocalDate dateFinEnchere = articleAAfficher.getDateFinEnchere();
		
		//Récupérer via catégorie
		Categorie categorie = articleAAfficher.getCategorie();
		String libelleCategorie = categorie.getLibelle();
		
		//Récupérer via l'utilisateur
		Utilisateur vendeur = articleAAfficher.getUtilisateur();	
		String pseudoVendeur = vendeur.getPseudo();
		
		//Récupérer via le retrait
		Retrait retrait = articleAAfficher.getRetrait();
		String rue = retrait.getRue();
		String codePostal = retrait.getCodePostal();
		String ville = retrait.getVille();
		
		//Récupérer la plus haute offre faite sur l'article via EncheresManager
		
		try {
			meilleureOffre = encheresManager.getMeilleureOffre(noArticle);
		} catch (BLLException ex) {
			ex.printStackTrace();
		}

		//set attribute contenu de l'article
		request.setAttribute("nomArticle", nomArticle);
		request.setAttribute("description", description);
		request.setAttribute("miseAPrix", miseAPrix);
		request.setAttribute("dateFinEnchere", dateFinEnchere);
		
		request.setAttribute("libelleCategorie", libelleCategorie);
		
		request.setAttribute("pseudoVendeur", pseudoVendeur);
		
		request.setAttribute("rue", rue);
		request.setAttribute("codePostal", codePostal);
		request.setAttribute("ville", ville);
		
		request.setAttribute("meilleureOffre", meilleureOffre);
		
		
		//Redirection vers la page d'affichage des détails de la vente
				RequestDispatcher rd = null;
				rd = request.getRequestDispatcher("/WEB-INF/jsps/DetailVente.jsp");
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
