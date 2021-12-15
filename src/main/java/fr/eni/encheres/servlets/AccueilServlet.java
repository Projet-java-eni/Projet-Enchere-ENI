package fr.eni.encheres.servlets;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.CategoriesManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccueilServlet", value = "/accueil")
public class AccueilServlet extends HttpServlet {
	CategoriesManager categoriesManager = CategoriesManager.GetInstance();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");

		request.setAttribute("categories", categoriesManager.getAllCategories(erreurs));

		request.getRequestDispatcher("/WEB-INF/jsps/accueil.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
