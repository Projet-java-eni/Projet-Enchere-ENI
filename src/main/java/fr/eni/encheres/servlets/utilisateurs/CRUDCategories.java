package fr.eni.encheres.servlets.utilisateurs;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.beans.Erreurs;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bo.beans.Infos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CRUDCategories", value = "/CRUDCategories")
public class CRUDCategories extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos) request.getAttribute("infos");

		Categorie categorie = new Categorie();


		CategoriesManager.GetInstance().getCategorieById(
				categorie, request.getParameter("id_cat"), erreurs
		);

		String reqCat = request.getParameter("id");
		if(reqCat != null) {
			Integer catReqNum = -1;
			try {
				catReqNum = Integer.parseInt(reqCat);
			} catch (NumberFormatException e) {
				erreurs.addErreur("nombre malformé");
			}
			String num = request.getParameter("num");
			String etiquette = request.getParameter("etiq");
			String libelle = request.getParameter("lib");
			if(num != null && etiquette != null && libelle != null) {
				try {
					categorie.setNoCategorie(Integer.parseInt(num));
				} catch (NumberFormatException e) {
					erreurs.addErreur("nombre malformé");
				}
				categorie.setEtiquette(etiquette);
				categorie.setLibelle(libelle);

				CategoriesManager.GetInstance().sauvegarderCategorie(categorie, erreurs);

				if(!erreurs.hasErrors()) {
					infos.addInfo("categorie changée");
				}
			}
		}

		request.setAttribute("cats", CategoriesManager.GetInstance().getAllCategories(erreurs));
		request.setAttribute("change", categorie);

		request.getRequestDispatcher("/WEB-INF/jsps/cruds/CRUDCategories.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
