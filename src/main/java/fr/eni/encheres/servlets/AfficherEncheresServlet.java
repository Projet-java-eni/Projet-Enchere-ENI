package fr.eni.encheres.servlets;

/**
 * 
 * @author Sego
 *
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class AfficherEncheresServlet
 */
@WebServlet("/AfficherEncheresServlet")
public class AfficherEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherEncheresServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Affichage de toutes les enchères sans distinction
		try {
			EncheresManager encheresManager = new EncheresManager();
			List<Enchere> listeEncheres = null; // problème avec le try catch

			listeEncheres = encheresManager.getAllEncheres();

		} catch (BLLException ex) {
			ex.printStackTrace();
			request.setAttribute("listeCodesErreur", ex.getListeCodesErreur());
		}
		// Transfert de l'affichage à la JSP
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/afficherEncheres.jsp");
		rd.forward(request, response);

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
