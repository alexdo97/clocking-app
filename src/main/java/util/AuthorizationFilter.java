package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();

			boolean activeSession = ses != null && ses.getAttribute("identityId") != null;
			boolean loginOrRegistrationURL = reqURI.indexOf("/login.xhtml") >= 0
					|| reqURI.indexOf("/register.xhtml") >= 0;

			if (loginOrRegistrationURL && activeSession) {
				resp.sendRedirect(reqt.getContextPath() + "/clocking.xhtml");

			} else if (activeSession || reqURI.contains("javax.faces.resource") || loginOrRegistrationURL) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}