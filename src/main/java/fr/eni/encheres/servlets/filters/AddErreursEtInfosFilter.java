package fr.eni.encheres.servlets.filters;

import fr.eni.encheres.beans.Erreurs;
import fr.eni.encheres.beans.Infos;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "AddErreursEtInfosFilter", urlPatterns = "/*")
public class AddErreursEtInfosFilter implements Filter {

	// Ce filtre ajoute un attribut "errors" et "infos" pour pouvoir afficher des bulles d'erreur et info où l'on
	// veut depuis les JSP.

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		request.setAttribute("infos", new Infos());
		request.setAttribute("errors", new Erreurs());
		chain.doFilter(request, response);
	}
}
