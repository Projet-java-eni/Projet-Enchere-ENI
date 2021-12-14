package fr.eni.encheres.servlets;

/**
 * 
 * @author Sego
 *
 */

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class encheresEnCoursServlet
 */
@WebServlet(name = "encheresEnCours", urlPatterns = { "/encheresEnCours" })
public class encheresEnCoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public encheresEnCoursServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Je lis les ecnhères en cours: donc date de fin > date du jour
		// lien avec BLL encheresManager et BO encheres
		LocalDate dateDuJour = LocalDate.now();
  Date dateEnchere= Enchere.
		  EncheresManager enchereManager = new EncheresManager();
	List<Repas> listeRepas = null;
	if (dateFiltre == null) {
		listeRepas = enchereManager.selectionnerEncheresEnCours();
	} else {
		listeRepas = repasManager.selectionnerTousLesRepasDUnJour(dateFiltre);
	}
	request.setAttribute("listeRepas", listeRepas);
		  
		  
		if (request.getParameter("dateDuJour") <= request.getParameter("dateEnchere"))
			;
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
