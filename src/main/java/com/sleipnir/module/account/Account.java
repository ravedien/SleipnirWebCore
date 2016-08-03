package com.sleipnir.module.account;

public class Account extends AccountDraft{
	
	private final long id;
	
	protected Account(long id,String username, String password) {
		super(username, password);
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	public Builder draft(){
		return new Builder(this);
	}
}
