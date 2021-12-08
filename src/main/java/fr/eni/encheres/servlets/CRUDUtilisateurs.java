package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class CRUDUtilisateurs
 */
@WebServlet("/CRUDUtilisateurs")
public class CRUDUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateursManager utilisateursManager = null;

	public CRUDUtilisateurs() throws Exception {
		super();
		try {
			utilisateursManager = UtilisateursManager.GetInstance();
		} catch (BLLException e) {
			e.printStackTrace();
			throw new Exception("Impossible de se connecter à la DAL!!");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		List<Utilisateur> allUsers = null;
		try {
			allUsers = utilisateursManager.getAllUtilisateur();
		} catch (BLLException e) {
			errors.add(e.getLocalizedMessage());
		}
		request.setAttribute("erreurs", errors);
		request.setAttribute("all_users", allUsers);
		
		request.getRequestDispatcher("WEB-INF/CRUDUtilisateurs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("mot_de_passe");
		String credit = request.getParameter("credit");
		String administrateur = request.getParameter("administrateur");

		List<String> erreurs = new ArrayList<>();
		Utilisateur nouvelUtilisateur = utilisateursManager.createUtilisateurDepuisLeWeb(pseudo, nom, prenom, email,
				telephone, rue, codePostal, ville, motDePasse, credit, administrateur, erreurs);
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("nouvel_utilisateur", nouvelUtilisateur);
		doGet(request, response);
	}

}
