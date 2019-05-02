package com.bridgelabs.project;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
//@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
	private ServletContext context;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
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
		// place your code here
				 HttpServletRequest req = (HttpServletRequest) request;
			        HttpServletResponse res = (HttpServletResponse) response;

			        HttpSession session = req.getSession();
			       
			        if (session==null || session.getAttribute("name") == null) {   //checking whether the session exists
			            this.context.log("Unauthorized access request");
			            res.sendRedirect(req.getContextPath() + "/login.jsp");
			        } else {
			           
			          chain.doFilter(request, response);
			        }

				// pass the request along the filter chain
//				PrintWriter out = response.getWriter();
		//
//				String id=request.getParameter("id");
//				if(id.length()>=2) {
//					chain.doFilter(request, response);
//				}
//				else
//					out.println("Enter valid information!!!!!");
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
        ((ServletContext) this.context).log("AuthenticationFilter initialized");
	}

}
