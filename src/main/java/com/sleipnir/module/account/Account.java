package com.sleipnir.module.account;

import com.sleipnir.core.BaseEntity;

public class Account extends BaseEntity<Long>{
	private String username;
	private String password;
	private String enabled;
	
	public Account(String username, String password, String enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
