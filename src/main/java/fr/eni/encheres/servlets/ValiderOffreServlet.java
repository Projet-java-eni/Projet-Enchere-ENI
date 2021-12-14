//@author Lucie

package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValiderOffreServlet
 */
@WebServlet("/ValiderOffreServlet")
public class ValiderOffreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		CharSequence nouvelleOffre = (request.getParameter("nouvelleOffre"));
		response.getWriter().append("La nouvelle offre est : ").append(nouvelleOffre);
		
	//vérifier que l'offre est bien supérieure à la meilleure offre
	//Si non Erreur
	//Vérifier si le nombre de crédit de l'utilisateur est supérieur au montant de nouvelleOffre
	//Si non Erreur
		
	//Else 
	//meilleureOffre=nouvelleOffre 
	//créer nouvelleEnchere
	//nouvelleEnchere.montantEnchere = nouvelleEnchere
	//nouvelleEnchere.noUtilisateur = noUtilisateur de l'Utilisateur connecté
	//nouvelleEnchere.noArticle = noArticle de l'article affiché
	//nouvelleEnchere.dateEnchere = current date
	//ET debiter le compte de crédits de l'utilisateur du montant de la nouvelle offre
		
	//
		
	}

}
