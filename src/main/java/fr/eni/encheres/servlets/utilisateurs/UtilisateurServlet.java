package fr.eni.encheres.servlets.utilisateurs;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateursDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UtilisateurServlet", urlPatterns = {"/Utilisateur/*"})
public class UtilisateurServlet extends HttpServlet {
	private UtilisateursManager utilisateursManager = UtilisateursManager.GetInstance();



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Utilisateur utilisateur = new Utilisateur();

		int userId = -1;
		try {
			userId=Integer.parseInt(request.getPathInfo().substring(1));
		} catch (NumberFormatException e) {
			erreurs.addErreur("Nombre malformé");
		}

		try {
			utilisateur = utilisateursManager.getUtilisateurById(userId);
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}

		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsps/AutresUtilisateurs.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
