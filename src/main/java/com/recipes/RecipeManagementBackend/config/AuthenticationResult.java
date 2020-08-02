package com.recipes.RecipeManagementBackend.config;

public class AuthenticationResult {

	private String token;

	public AuthenticationResult(final String generatedToken) {
		this.token = generatedToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}
}
