package com.sleipnir.module.account;

import java.util.Date;

public class Account {
	private final int id;
	private final String username;
	private final String password;
	private final String name;

	private Account(AccountBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.name = builder.name;
	}

	public static class AccountBuilder {
		private final int id;
		private final String username;
		private final String password;
		private String name;
		

		public AccountBuilder(int id, String username,String password) {
			this.id = id;
			this.username = username;
			this.password = password;
		}

//		for implementation of to be changed values
		public AccountBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
}