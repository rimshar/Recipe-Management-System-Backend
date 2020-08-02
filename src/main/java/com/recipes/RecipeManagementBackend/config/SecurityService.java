package com.recipes.RecipeManagementBackend.config;


import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityService implements UserDetailsService {

	@Value("${app.authentication.signature.secret}")
	private String SECRET;

	@Value("${app.authentication.validity.period}")
	private int VALIDITY;

	private UserRepository userRepository;

	@Autowired
	public SecurityService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Username not found!"));
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User \"%s\" not found", username));
		}
		return user;
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(user.getId() + "")
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + VALIDITY))
				   .signWith(SignatureAlgorithm.HS512, SECRET)
				   .compact();
	}

	public long getUserId() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return Long.parseLong((String)authentication.getPrincipal());
	}
}
