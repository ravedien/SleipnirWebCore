package com.sleipnir.module.account;

public class AccountDraft {
	
	protected final String username;
    protected final String password;

    protected AccountDraft(String username,String password){
    	this.username=username;
    	this.password=password;
    }

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
    
    public Builder draft(){
    	return new Builder(this); 
    }
    
    public static class Builder{
    	
    	protected String username;
    	protected String password;
    	
    	Builder(){}
    	
    	protected Builder(AccountDraft draft){
    		this.username = draft.getUsername();
    		this.password = draft.getPassword();
    	}
    	
    	public Builder withUsername(String username){
    		this.username = username;
    		return this;
    	}
    	
    	public Builder withPassword(String password){
    		this.password = password;
    		return this;
    	}
    	
    	public AccountDraft build(){
    		return new AccountDraft(username, password);
    	}
    }
}
