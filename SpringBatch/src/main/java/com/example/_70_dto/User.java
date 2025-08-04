package com.example._70_dto;

public class User {
	private String username;
	private String password;
	private String role;
	private Integer enabled;


	public User() {};

	public User(String username, String password, String role, Integer enabled) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		return String.format(
				"User[username='%s', password='%s', role='%s', enabled=%d]",
				username, password, role, enabled);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
