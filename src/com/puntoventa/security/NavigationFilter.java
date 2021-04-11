package com.puntoventa.security;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.puntoventa.utilities.Constants;

public class NavigationFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	@Override
	public void destroy() {
		filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		String loginURI = req.getContextPath() + "/" + Constants.INDEX_PATH;

		boolean isLoggedIn = session != null && session.getAttribute("usuario") != null;
		boolean isLoginRequest = req.getRequestURI().equals(loginURI);
		boolean resourceRequest = req.getRequestURI()
				.startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		if (isLoggedIn || isLoginRequest || resourceRequest) {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(loginURI);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
