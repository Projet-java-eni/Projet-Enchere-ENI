package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;


@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateursManager utilisateursManager = null;

	public InscriptionServlet() throws Exception {
		super();
		try {
			utilisateursManager = UtilisateursManager.GetInstance();
		} catch (BLLException e) {
			e.printStackTrace();
			throw new Exception("Impossible de se connecter à la DAL");
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("inscription") != null) { 
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String motDePasse = request.getParameter("mot_de_passe");
			String motDePasseRepete = request.getParameter("mot_de_passe_repete");

			Erreurs errors = new Erreurs();
	
			Utilisateur utilisateur = utilisateursManager.traiteRequeteInscription(
					pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseRepete, errors);
			
			request.setAttribute("utilisateur_temp", utilisateur);
			
			if(errors.hasErrors()) {
				request.getRequestDispatcher("WEB-INF/jsps/auth/Register.jsp").forward(request, response);
			} else {
				request.setAttribute("message_succes", "Bienvenue sur TrocEncheres " + utilisateur.getPseudo() + " !");
				request.getRequestDispatcher("/Index").forward(request, response);
			}
		
		}

		request.getRequestDispatcher("WEB-INF/jsps/auth/Register.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
