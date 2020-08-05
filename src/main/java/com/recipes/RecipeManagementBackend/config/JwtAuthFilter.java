package com.recipes.RecipeManagementBackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtAuthFilter extends BasicAuthenticationFilter {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";

	@Value("${app.authentication.signature.secret}")
	private String SECRET;

	public JwtAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String requestTokenHeader = request.getHeader(HEADER);

		if (requestTokenHeader == null) {
			chain.doFilter(request, response);
			return;
		}
		if (!requestTokenHeader.startsWith(PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String jwtToken = requestTokenHeader.substring(PREFIX.length());
		System.out.println(jwtToken);
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();

		if (!claims.getExpiration().after(new Date())) {
			chain.doFilter(request, response);
			return;
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));

		Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
