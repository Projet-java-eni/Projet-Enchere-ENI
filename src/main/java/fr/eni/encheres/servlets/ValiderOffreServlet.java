//@author Lucie

package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ValiderOffreServlet
 */
@WebServlet("/ValiderOffreServlet")
public class ValiderOffreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	EncheresManager encheresManager = EncheresManager.GetInstance();
	UtilisateursManager utilisateursManager = UtilisateursManager.GetInstance();
	ArticleManager articleManager = ArticleManager.GetInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderOffreServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupérer l'utilisateur via getSession
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Utilisateur utilisateur=new Utilisateur();
		try {
			utilisateur = utilisateursManager.getUtilisateurById((Integer) request.getSession().getAttribute("user_id"));
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}
		
		//Récupérer le montant de la nouvelleOffre via le formulaire/paramètre
		int nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
		
		
		//Vérifier que nouvelleOffre supérieur à meilleureOffre (Facultatif à faire que si on a le temps car formulaire html déjà paramétré pour ne pas pouvoir mettre un montant = ou <  à la meilleure offre existante)
		
	
		//Récupérer le noArticle de l'article affiché dans DetailVente via l'attribut déclaré dans DetailArticle
		int noArticle = (int) request.getAttribute("noArticle");
		Article article = articleManager.getArticleById(noArticle, erreurs);
		
		//Récupérer date et heure actuelle
		LocalDate dateActuelle = LocalDate.now();
		LocalTime heureActuelle = LocalTime.now();
		
		//pour les mettre dans nouvelleEnchere
		Enchere nouvelleEnchere = new Enchere(utilisateur, article, dateActuelle, heureActuelle, nouvelleOffre);
		
		try {
			encheresManager.validerEnchere(nouvelleEnchere);
		} catch (BLLException ex) {
			ex.printStackTrace();
		}
		
		try {
			encheresManager.addEnchere(nouvelleEnchere);
		} catch (BLLException ex) {
			ex.printStackTrace();
		}
		
	//ET debiter le compte de crédits de l'utilisateur du montant de la nouvelle offre
		try {
			utilisateursManager.retirerCredits(utilisateur, nouvelleOffre);
		} catch (BLLException ex) {
			ex.printStackTrace();
		}
	}

}
