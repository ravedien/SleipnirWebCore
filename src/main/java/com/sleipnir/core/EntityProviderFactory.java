package com.sleipnir.core;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.jooq.RecordValueReader;

public abstract class EntityProviderFactory<T, TRECORD extends org.jooq.impl.UpdatableRecordImpl<TRECORD>, TTABLE extends org.jooq.impl.TableImpl<TRECORD>>
		implements DomainFactory<T, TRECORD> {
	
	  protected ModelMapper modelMapper;
	  private DSLContext jooq;
	  
	  private TTABLE table;
	  
	  /**
	   * Constructs the provider with the dependencies.
	   * @param modelMapper
	   * @param jooq
	   * @param classTable
	   */
	  public EntityProviderFactory(ModelMapper modelMapper,DSLContext jooq,Class<TTABLE> classTable){
	    this.modelMapper = modelMapper;
	    this.jooq = jooq;
	    table = createTableInstance(classTable);
	  }
	  
	  protected ModelMapper getModelMapperInstance(){
	    return this.modelMapper;
	  }
	  
	  /**
	   * Makes the requested real objects from the jooq's Record Type via ModelMapper
	   * it is used particularly on data retrieval
	   * and uses the Customized Property map to flexibly map dissimilar fields.
	   * 
	   * @param JooQ Record Type
	   * @return the target Domain Class<T>
	   */
	  public <TMAP extends PropertyMap<Record,T>> T make(Record record,Class<T> account,TMAP propertyMap) {
	    /*  This uses a new modelMapper instance since it's mapping using the Record object.
	      In case of some values are null from the first row,it will no longer
	      recognize the second one with non-null values and the Mapper needs to 'refresh'.
	    */
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().addValueReader(new RecordValueReader());
	    if(checkIfPropertyTypeExists(record.getClass(),account)){
	      modelMapper.createTypeMap(record, account).addMappings(propertyMap);
	    }
	    
	    T resultAccount = modelMapper.map(record,account);
	    return resultAccount;
	  }
	  
	  /**
	   * Checks if Property Type exists.
	   * @param record
	   * @param account
	   * @return
	   */
	  private boolean checkIfPropertyTypeExists(Class<?> source, Class<?> dest) {
	    if(modelMapper.getTypeMap(source, dest)!=null){
	      return false;
	    }else{
	      return true;
	    }
	  }

	  /**
	   * Makes the requested real objects from the jooq's Record Type via ModelMapper
	   * it is used particularly on data retrieval.
	   * 
	   * @param JooQ Record Type
	   * @return the target Domain Class<T>
	   */
	  public T make(Record record,Class<T> account) {
	    T resultAccount = modelMapper.map(record,account);
	    return resultAccount;
	  }
	  
	  /**
	   * Creates a Record(JOOQ generated) object from the Entity Type.
	   * used for persisting / updating data the jooq way.
	   * by mapping the Type -> Record
	   * 
	   * @param target Entity Class
	   * @return Record equivalent generated by JOOQ.
	   */
	  public TRECORD createRecord(T entity){
	    TRECORD record = null;
	    record = jooq.newRecord(table,entity);
	    modelMapper.map(entity, record);
	    
	    return record;
	  }
	  
	  /**
	   * Improved implementation using the POJO generated fields (Flat by nature)
	   * From the Model --> POJO 
	   *       POJO  --> Record
	   * 
	   * @param entity
	   * @param propertyMap
	   * @return
	   */
	  public <TMAP extends PropertyMap<T,POJO>,POJO> TRECORD createRecord(T entity,Class<POJO> pojo,TMAP propertyMap){
	    TRECORD record = null;
	    //Transform your model object into flat POJO
	    if(checkIfPropertyTypeExists(entity.getClass(),pojo)){
	      modelMapper.createTypeMap(entity, pojo).addMappings(propertyMap);
	    }
	    POJO mappedPojo = modelMapper.map(entity, pojo);
	    
	    //Creates a new Record Based on the flat POJO
	    record = jooq.newRecord(table,mappedPojo);
	        
	    return record;
	  }
	  
	  /**
	   * Creates a table instance.
	   * @param table
	   * @return
	   */
	  private TTABLE createTableInstance(Class<TTABLE> table) {
	    try {
	      return table.newInstance();
	    } catch (InstantiationException | IllegalAccessException e) {
	      System.out.println("Cannot create Table Object!");
	    }return null;
	  }
}
