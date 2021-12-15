//@author Lucie

package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;
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
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos) request.getAttribute("infos");
		Utilisateur utilisateur=new Utilisateur();
		utilisateur = utilisateursManager.getUtilisateurById((Integer) request.getSession().getAttribute("user_id"), erreurs);

		//Récupérer le montant de la nouvelleOffre via le formulaire/paramètre
		int nouvelleOffre = -1;
		try {
			nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
		} catch (NumberFormatException e) {
			erreurs.addErreur("Offre malformée");
		}

		//Vérifier que nouvelleOffre supérieur à meilleureOffre (Facultatif à faire que si on a le temps car formulaire html déjà paramétré pour ne pas pouvoir mettre un montant = ou <  à la meilleure offre existante)

		//Récupérer le noArticle de l'article affiché dans DetailVente via l'attribut déclaré dans DetailArticle
		int noArticle = -1;
		try {
			Integer.parseInt(request.getParameter("no_article"));
		} catch (NumberFormatException e) {
			erreurs.addErreur("nombre malformé");
		}
		Article article = new Article();
		articleManager.getByIdAvecInstance(noArticle, article, erreurs);

		//Récupérer date et heure actuelle

		//pour les mettre dans nouvelleEnchere
		encheresManager.essayerCreerEnchere(article, utilisateur, nouvelleOffre, erreurs);
//		Enchere nouvelleEnchere = new Enchere(utilisateur, article, dateActuelle, heureActuelle, nouvelleOffre);

		request.setAttribute("noArticle", article.getNoArticle());
		request.setAttribute("nomArticle", article.getNomArticle());
		request.setAttribute("description", article.getDescription());
		request.setAttribute("miseAPrix", article.getMiseAPrix());
		request.setAttribute("dateFinEnchere", article.getDateFinEnchere());

		request.setAttribute("libelleCategorie", article.getCategorie().getLibelle());

		request.setAttribute("pseudoVendeur", utilisateur.getPseudo());

		request.setAttribute("rue", article.getRetrait().getRue());
		request.setAttribute("codePostal", article.getRetrait().getCodePostal());
		request.setAttribute("ville", article.getRetrait().getVille());

		request.setAttribute("meilleureOffre", encheresManager.getMeilleureOffre(article.getNoArticle(), erreurs));

		//ET debiter le compte de crédits de l'utilisateur du montant de la nouvelle offre
		utilisateursManager.retirerCredits(utilisateur, nouvelleOffre, erreurs);

		if(!erreurs.hasErrors()) {
			infos.addInfo("Enchère créée avec succès !");
		}
		//Redirection vers la page d'affichage des détails de la vente avec le montant de la meilleure offre mise à jour avec la nouvelle offre
		request.getRequestDispatcher("/DetailArticle?no_article=" + noArticle).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupérer l'utilisateur via getSession
		doGet(request, response);
	}	

}
