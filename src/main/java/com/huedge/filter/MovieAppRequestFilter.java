package com.huedge.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MovieAppRequestFilter extends OncePerRequestFilter {
//implements Filter {

	private static Logger logger = LoggerFactory.getLogger(MovieAppRequestFilter.class);

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("Authorization Token Validation Filter ....");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (null != (httpRequest.getHeader("X-Auth-Token")) && 
				!httpRequest.getHeader("X-Auth-Token").equalsIgnoreCase("token")) {
			((HttpServletResponse) response).setStatus(401);
			response.getOutputStream().write(
					"Validation error - Auth Token Not Found".getBytes());
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	/*@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("Authorization Filter init method ....");
	}*/
}
