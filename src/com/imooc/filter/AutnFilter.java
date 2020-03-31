package com.imooc.filter;

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

import com.library.database.User;

/**
 * Servlet Filter implementation class AutnFilter
 */
public class AutnFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutnFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		User login = (User)req.getSession().getAttribute("login");
		if(login != null) {
			chain.doFilter(request, response);
		}else {
			String uri = req.getRequestURI();
			if(uri.endsWith("login.jsp") || uri.endsWith("/")) {
				chain.doFilter(request, response);
			}else {
				req.setAttribute("msg", "需要先登录！");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
