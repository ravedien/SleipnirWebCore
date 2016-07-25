package com.sleipnir.core;

public interface UpdateableRepository <T,Tkey> extends ReadOnlyRepository<T, Tkey>{
	/**
	   * Adds new entity / Aggregate.
	   * @param account
	   * @return
	   */
	  public T add(T entity);
	  
	  /**
	   * Deletes an entity / Aggregate
	   * @param id
	   * @return
	   */
	  public T delete(Tkey id);
	  
	  /**
	   * Updates an entity / Aggregate
	   * @param account
	   * @return
	   */
	  public T update(T entity);
}
