package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.config.AuthenticationResult;
import com.recipes.RecipeManagementBackend.config.SecurityService;
import com.recipes.RecipeManagementBackend.model.UserTO;
import com.recipes.RecipeManagementBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AuthController {

	private AuthenticationManager authenticationManager;
	private SecurityService securityService;
	private UserService userService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, SecurityService securityService, UserService userService){
		this.authenticationManager = authenticationManager;
		this.securityService = securityService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public AuthenticationResult authenticate(@RequestBody UserTO userTO) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTO.getUsername(), userTO.getPassword()));
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Bad credentials");
		}
		Long role = userService.getUserRoleByUsername(userTO.getUsername());
		return new AuthenticationResult(securityService.generateToken(securityService.loadUserByUsername(userTO.getUsername())), role);
	}
}
