package com.sleipnir.core;

public class Account {
	private final int id;
	private final String username;
	private final String password;

	private Account(AccountBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
	}

	public static class AccountBuilder {
		private int id;
		private final String username;
		private final String password;
		

		public AccountBuilder(String username,String password) {
			this.username = username;
			this.password = password;
		}

//		for implementation of to be changed values
		public AccountBuilder withId(int id) {
			this.id = id;
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
}