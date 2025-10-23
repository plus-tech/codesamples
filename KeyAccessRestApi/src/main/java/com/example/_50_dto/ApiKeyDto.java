package com.example._50_dto;


public class ApiKeyDto {
	private String key;
	private String username;
	private String description;
	
	public ApiKeyDto(String key, String username, String description) {
		this.key = key;
		this.username = username;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format(
				"ApiKey[key=%s, name='%s', description=%s]", key, username, description);
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUsername() {
		return username;
	}
	public void setUser(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
