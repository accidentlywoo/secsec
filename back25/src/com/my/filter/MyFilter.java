package com.my.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

    public MyFilter() {
        // TODO Auto-generated constructor stub
    }
    public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
    	System.out.println("MyFilter START");
		chain.doFilter(request, response);
		System.out.println("MyFilter END");
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
