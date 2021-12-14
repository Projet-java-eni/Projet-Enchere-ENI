package fr.eni.encheres.servlets.utilisateurs;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateursManager utilisateursManager = UtilisateursManager.GetInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur_temp = new Utilisateur(-1);
		request.setAttribute("utilisateur_temp", utilisateur_temp);
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos)request.getAttribute("infos");

		String addresse = "WEB-INF/jsps/auth/Login.jsp";

		if(request.getParameter("inscription") != null) {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("password");

			Utilisateur utilisateur = utilisateursManager.getUtilisateurAvecLoginMotDePasse(
					pseudo, motDePasse, erreurs);

			if (utilisateur != null) {
				request.setAttribute("utilisateur_temp", utilisateur);
			}

			utilisateur_temp.setPseudo(pseudo);


			if(!erreurs.hasErrors()) {

				infos.addInfo("Bon retour sur TrocEncheres " + utilisateur.getPseudo() + " !");

				request.getSession().setAttribute("user_id", utilisateur.getNoUtilisateur());
				request.getSession().setAttribute("user_pseudo", utilisateur.getPseudo());

				addresse = "/WEB-INF/jsps/accueil.jsp";
			}
		}

		try {
			request.getRequestDispatcher(addresse).forward(request, response);
		} catch (ServletException e) {
			try {
				response.sendError(500, e.getLocalizedMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
