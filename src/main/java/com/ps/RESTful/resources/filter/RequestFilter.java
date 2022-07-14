package com.ps.RESTful.resources.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.config.tenant.TenantContextHolder;

@Component
@Order(1)
public class RequestFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(RequestFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (logger.isDebugEnabled())
			logger.debug("In request filter");

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (logger.isDebugEnabled())
			logger.debug("We are filtering the Request");
		if (logger.isDebugEnabled())
			logger.debug("____________________________________________");

		setTenant(httpRequest);

		chain.doFilter(request, response);

	}

	private void setTenant(HttpServletRequest httpRequest) {
		String requestURI = httpRequest.getRequestURI();
		String tenantID = httpRequest.getHeader("X-TenantID");
		if (logger.isDebugEnabled())
			logger.debug("RequestURI::" + requestURI + " || Search for X-TenantID  :: " + tenantID);
		if (logger.isDebugEnabled())
			logger.debug("____________________________________________");

		TenantContextHolder.setTenantId(tenantID);
	}

	@SuppressWarnings("unused")
	private byte[] formatResponse(Response response) throws IOException {
		String serealized = new ObjectMapper().writeValueAsString(response);
		return serealized.getBytes();
	}

}
