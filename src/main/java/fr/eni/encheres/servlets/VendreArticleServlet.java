package fr.eni.encheres.servlets;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.bll.ArticleManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VendreArticleServlet", value = "/Vendre")
public class VendreArticleServlet extends HttpServlet {
	static ArticleManager articleManager;

	public VendreArticleServlet() {
		articleManager = ArticleManager.GetInstance();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erreurs erreurs = (Erreurs) request.getAttribute("errors");

		if(request.getParameter("vendre") != null) {
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			String prix = request.getParameter("prix");
			String date = request.getParameter("date");
		}

        request.getRequestDispatcher("/WEB-INF/jsps/VendreArticle.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
