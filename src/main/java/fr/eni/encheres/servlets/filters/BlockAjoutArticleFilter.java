package fr.eni.encheres.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "BlockAjoutArticleFilter", urlPatterns = "/Vendre")
public class BlockAjoutArticleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if(httpServletRequest.getSession().getAttribute("user_id") == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/Login");
		}

		else chain.doFilter(request, response);
	}
}
