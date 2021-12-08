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
		if (request.getAttribute("erreurs") != null) {
			errors.addAll((List<String>) request.getAttribute("erreurs"));
		}

		request.setAttribute("erreurs", errors);

		List<Utilisateur> allUsers = null;

		try {
			allUsers = utilisateursManager.getAllUtilisateur();
		} catch (BLLException e) {
			errors.add(e.getLocalizedMessage());
		}

		request.setAttribute("all_users", allUsers);

		Utilisateur modifUtilisateur = null;
		if (request.getParameter("modifier") != null && request.getParameter("id_utilisateur") != null) {
			int id_utilisateur = -1;
			try {
				id_utilisateur = Integer.parseInt((String) request.getParameter("id_utilisateur"));
			} catch (NumberFormatException e) {
				errors.add("Nombre ma formaté.");
			}
			try {
				modifUtilisateur = utilisateursManager.getUtilisateurById(id_utilisateur);
			} catch (BLLException e) {
				errors.add(e.getLocalizedMessage());
			}
		}

		request.setAttribute("modif_utilisateur", modifUtilisateur);

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
		String idUtilisateur = request.getParameter("id_utilisateur");

		List<String> erreurs = new ArrayList<>();

		Utilisateur nouvelUtilisateur = null;

		if (idUtilisateur == null) {
			
			nouvelUtilisateur = utilisateursManager.createUtilisateurDepuisLeWeb(pseudo, nom, prenom, email, telephone,
					rue, codePostal, ville, motDePasse, credit, administrateur, erreurs);
		}

		request.setAttribute("nouvel_utilisateur", nouvelUtilisateur);

		Utilisateur modifUtilisateur = null;

		if (idUtilisateur != null) {
			
			try {
				Integer intModifUtilisateur = Integer.parseInt(idUtilisateur);
				
				modifUtilisateur = utilisateursManager.getUtilisateurById(intModifUtilisateur);
				
				utilisateursManager.modifUtilisateurDepuisLeWeb(modifUtilisateur, pseudo, nom, prenom, email, telephone,
						rue, codePostal, ville, credit, administrateur, erreurs);
			} catch (NumberFormatException e) {
				erreurs.add("Id utilisateur malformé !");
			} catch (BLLException e) {
				erreurs.add(e.getLocalizedMessage());
			}
			
		}

		request.setAttribute("modif_utilisateur", modifUtilisateur);

		request.setAttribute("erreurs", erreurs);

		doGet(request, response);
	}

}
