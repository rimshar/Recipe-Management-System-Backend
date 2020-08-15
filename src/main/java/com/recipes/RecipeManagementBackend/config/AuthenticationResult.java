package com.recipes.RecipeManagementBackend.config;

import com.recipes.RecipeManagementBackend.model.Role;

public class AuthenticationResult {

	private String token;
	private Long role;

	public AuthenticationResult(final String generatedToken, final Long role) {
		this.token = generatedToken;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public Long getRole() {
		return role;
	}

	public void	 setRole(Long role) {
		this.role = role;
	}
}
