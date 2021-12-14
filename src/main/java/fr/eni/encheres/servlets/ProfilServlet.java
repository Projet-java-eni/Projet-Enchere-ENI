package fr.eni.encheres.servlets;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;
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

@WebServlet(name = "ProfilServlet", value = "/Profil")
public class ProfilServlet extends HttpServlet {

	private final UtilisateursManager utilisateursManager;

	public ProfilServlet() {
		utilisateursManager = UtilisateursManager.GetInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos) request.getAttribute("infos");
		request.setAttribute("utilisateur", new Utilisateur(-1));

		String addresse = "WEB-INF/jsps/ProfilUtilisateur.jsp";

		try {
			Integer userId = ((Integer) request.getSession().getAttribute("user_id"));
			if(userId == null) {
				erreurs.addErreur("Pas connecté");

			} else {
				Utilisateur utilisateurConnecte = utilisateursManager.getUtilisateurById(userId);

				if(request.getParameter("mise_a_jour") != null) {
					utilisateursManager.modifieDepuisLeWeb(
							utilisateurConnecte, request.getParameter("pseudo"),
							request.getParameter("nom"),
							request.getParameter("prenom"),
							request.getParameter("email"),
							request.getParameter("telephone"),
							request.getParameter("rue"),
							request.getParameter("codePostal"),
							request.getParameter("ville"),
							request.getParameter("mot_de_passe_original"),
							request.getParameter("mot_de_passe_nouveau"),
							request.getParameter("mot_de_passe_repete"),
							erreurs
					);

					if(!erreurs.hasErrors()) {
						infos.addInfo("Profil modifié !");
					}

				}

				if(request.getParameter("supprimer") != null) {

					utilisateurConnecte.setActif(false);
					utilisateursManager.sauvegarderUtilisateur(utilisateurConnecte, erreurs);
					infos.addInfo("Au revoir " + utilisateurConnecte.getPseudo());
					addresse = "WEB-INF/jsps/accueil.jsp";
				}

				request.setAttribute("utilisateur", utilisateurConnecte);

			}
		} catch (BLLException e) {
			erreurs.addErreur(e.getLocalizedMessage());
		}

		request.getRequestDispatcher(addresse).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
