//@author Lucie


package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bll.ArticleManager;

/**
 * Servlet implementation class DetailArticle
 */
@WebServlet("/DetailArticle")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ArticleManager articleManager;
	private UtilisateursManager utilisateurManager;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupérer le noArticle de l'article sur lequel l'utilisateur a cliqué
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		//utiliser le noArticle pour récupérer le contenu de l'article
		Article articleAAfficher = null;
		try {
			articleAAfficher = articleManager.getArticleById(noArticle);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		//get attribute contenu de l'article
		String nomArticle = articleAAfficher.getNomArticle();
		String categorie = articleAAfficher.getCategorie().toString();
		String description = articleAAfficher.getDescription();
		int miseAPrix = articleAAfficher.getMiseAPrix();
		LocalDate dateFinEnchere = articleAAfficher.getDateFinEnchere();
		
		//Récupérer via l'utilisateur
		Utilisateur vendeur = articleAAfficher.getUtilisateur();
		
		
		String pseudoVendeur = vendeur.getPseudo();
		//Récupérer via le retrait
		String rue = "Enbasdubourg";
		String codePostal = "11111";
		String ville = "Petaouchnok";
		//Récupérer via les enchères faites sur l'article
		int meilleureOffre = 112;
		
		
		//set attribute contenu de l'article
		request.setAttribute("nomArticle", nomArticle);
		request.setAttribute("categorie", categorie);
		request.setAttribute("description", description);
		request.setAttribute("miseAPrix", miseAPrix);
		request.setAttribute("dateFinEnchere", dateFinEnchere);
		
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
