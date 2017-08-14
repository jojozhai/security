/**
 * 
 */
package com.imooc.web.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class Filter implements javax.servlet.Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter start");
		chain.doFilter(request, response);
		System.out.println("doFilter end");
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init");
	}

}
