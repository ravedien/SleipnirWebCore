package com.sleipnir.core;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasicGatewayImpl<TKey, TRECORD extends org.jooq.impl.UpdatableRecordImpl<TRECORD>>
		implements DomainGateway<TKey, TRECORD> {
	@Autowired
	  protected DSLContext jooq;
	    
	  /**
	   * Updates a single record specified by id
	   */
	  public void update(TRECORD data) {
	    jooq.attach(data);
	    data.update();
	  }
	  
	  /**
	   * Deletes a single record specified by id
	   */
	  public void delete(TRECORD data) {
	    //attached with the currently define context.
	    jooq.attach(data);
	    //delete the record
	    data.delete();
	  }
	  
	  /**
	   * Persist a single record
	   */
	  public TRECORD persist(TRECORD data) {
	    //attached with the currently define context.
	    jooq.attach(data);
	    data.store();
	    return data;
	  }
}
