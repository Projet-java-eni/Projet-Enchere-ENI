//@author Lucie

package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class ValiderOffreServlet
 */
@WebServlet("/ValiderOffreServlet")
public class ValiderOffreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	EncheresManager encheresManager = EncheresManager.GetInstance();
	UtilisateursManager utilisateursManager = UtilisateursManager.GetInstance();
	
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
		int nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
		//response.getWriter().append("La nouvelle offre est : ").append(nouvelleOffre);
		
		//Vérifier que nouvelleOffre supérieur à meilleureOffre (Facultatif car input html paramétré pour ne pas pouvoir mettre un montant = ou <  à la meilleure offre existante)
		
		//Récupérer le noUtilisateur de l'utilisateur qui fait l'offre et qui est connecté
		//Récupérer le noArticle de l'article affiché dans DetailVente
		//Récupérer date et heure actuelle
		//pour les mettre dans nouvelleEnchere
		
		Enchere nouvelleEnchere = new Enchere(null, null, null, null, nouvelleOffre);
		
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
		//récupérer l'utilisateur via son noUtilisateur parcequ'il est connecté
		//update credit
		
		utilisateursManager.retirerCredits(utilisateur, nouvelleOffre);
		
	}

}
