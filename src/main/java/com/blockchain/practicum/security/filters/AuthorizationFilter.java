package com.blockchain.practicum.security.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blockchain.practicum.service.UserService;
/**
 *  AuthorizationFiltern class is a filter to validate id header
 *
 */
@Component
public class AuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private UserService service;

	// @Autowired
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// excluding the URI of creating new account
		if ("/users".equals(req.getRequestURI())) {
			chain.doFilter(req, res);
			return;
		}

		String id = req.getHeader("id");
		if (id == null) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "id header is mandatory");
			return;
		}
		// check if the id is valid
		try {
			String userEmail = service.findUserById(id);
		} catch (EmptyResultDataAccessException e) {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "the id (" + id + ") is invalid");
			return;
		}
		
		chain.doFilter(req, res);
	}

}
