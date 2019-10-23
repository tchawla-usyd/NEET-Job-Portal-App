package com.neet.jobsite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	  @Override
	  public void init(FilterConfig filterConfig) throws ServletException {

	  }

	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	      System.out.println("CORS");
		  HttpServletResponse httpResponse = (HttpServletResponse) response;
	      httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	      httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE");
	      httpResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");

	      chain.doFilter(request, response);
	  }

	  @Override
	  public void destroy() {

	  }
	}