package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategoriesManager;

@WebServlet(name = "AccueilServlet", value = "/accueil")
public class AccueilServlet extends HttpServlet {
	static CategoriesManager categoriesManager = CategoriesManager.GetInstance();
	static ArticleManager articleManager = ArticleManager.GetInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");

		request.setAttribute("categories", categoriesManager.getAllCategories(erreurs));
		request.setAttribute("articles", articleManager.getCatalogue(erreurs));

		request.getRequestDispatcher("/WEB-INF/jsps/accueil.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
