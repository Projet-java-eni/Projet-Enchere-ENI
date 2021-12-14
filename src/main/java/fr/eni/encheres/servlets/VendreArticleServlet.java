package fr.eni.encheres.servlets;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bo.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VendreArticleServlet", value = "/Vendre")
public class VendreArticleServlet extends HttpServlet {

	static ArticleManager articleManager = ArticleManager.GetInstance();
	static CategoriesManager categoriesManager = CategoriesManager.GetInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");
		Infos infos = (Infos) request.getAttribute("infos");
		Article article = new Article();

		if(request.getParameter("vendre") != null) {
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			String prix = request.getParameter("prix");
			String date = request.getParameter("date");

			articleManager.sauvegarderDepuisLeWeb(nom, description, prix, date, article, erreurs);
			if(!erreurs.hasErrors()) {
				infos.addInfo("Nouvel article ajouté !");
			}
		}

		request.setAttribute("article", article);
		request.setAttribute("categories", categoriesManager.getAllCategories(erreurs) );

        request.getRequestDispatcher("/WEB-INF/jsps/VendreArticle.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
