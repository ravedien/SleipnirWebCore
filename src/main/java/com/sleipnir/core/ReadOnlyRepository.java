package com.sleipnir.core;

import java.util.List;

import org.jooq.Condition;

public interface ReadOnlyRepository<T,TKey> {
	/**
	   * Finds all Records
	   * @return List of Entity.
	   */
	  public List<T> findAll();
	  /**
	   * Finds Record by Unique Id.
	   * @param id
	   * @return Entity.
	   */
	  public T findById(TKey id);
	  /**
	   * Finds Records based on the defined condition
	   * @param condition
	   * @return List of Entity.
	   */
	  public List<T> findUsingCondition(Condition condition);
}
