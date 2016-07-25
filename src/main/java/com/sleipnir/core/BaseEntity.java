package com.sleipnir.core;

public class BaseEntity<TKey> {
	protected TKey id;

	public TKey getId() {
		return id;
	}

	public void setId(TKey id) {
		this.id = id;
	}
	
	/**
     * Indicates if the domain needs updating or inserting.
     * when id is present it is for update otherwise it's for insert
     * @return boolean
     */
	public boolean isNew(){
		return (this.id == null);
	}
}
