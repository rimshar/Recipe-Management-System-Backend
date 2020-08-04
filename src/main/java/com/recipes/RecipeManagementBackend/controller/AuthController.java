package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.config.AuthenticationResult;
import com.recipes.RecipeManagementBackend.config.SecurityService;
import com.recipes.RecipeManagementBackend.model.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private SecurityService securityService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, SecurityService securityService){
		this.authenticationManager = authenticationManager;
		this.securityService = securityService;
	}

	@PostMapping("/login")
	public AuthenticationResult authenticate(@RequestBody UserTO userTO) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTO.getUsername(), userTO.getPassword()));
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Bad credentials");
		}

		return new AuthenticationResult(securityService.generateToken(securityService.loadUserByUsername(userTO.getUsername())));
	}
}
