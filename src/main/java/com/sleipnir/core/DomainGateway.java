package com.sleipnir.core;

import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;

public interface DomainGateway<TKey,TRECORD  extends org.jooq.impl.UpdatableRecordImpl<TRECORD>> {
	/**
	   * Retrieves single Record using key
	   * @param id
	   * @return a Record
	   */
	  public Record retrieve(TKey key);
	  
	  /**
	   * Retrieves all. There's no limit.
	   * @return List of Records
	   */
	  public List<Record> retrieve();
	  
	  /**
	   * Retrieves with parametarized limit.
	   * @param limit
	   * @return List of Records
	   */
	  public List<Record> retrieve(int limit);
	  
	  /**
	   * Retrieves record using the defined Condition
	   * @param condition
	   * @return
	   */
	  public List<Record> retrieve(Condition condition);
	  
	  
	  /**
	   * Saves the record.
	   * @param data
	   * @return TRECORD
	   */
	  public TRECORD persist(TRECORD data);
	  
	  /**
	   * Updates the record specified by key
	   * @param key
	   * @param data
	   */
	  public void update(TRECORD data);
	  
	  /**
	   * Deletes the record specified by key
	   * @param id
	   */
	  public void delete(TRECORD data);
}
