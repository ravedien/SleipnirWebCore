package com.sleipnir.module.account;

import org.jooq.DSLContext;
import org.jooq.generated.tables.records.MAccountRecord;
import org.jooq.impl.TableImpl;
import org.modelmapper.ModelMapper;

import com.sleipnir.core.EntityProviderFactory;

public class AccountFactory extends EntityProviderFactory<Long, MAccountRecord, TableImpl<MAccountRecord>>{

	public AccountFactory(ModelMapper modelMapper, DSLContext jooq, Class<TableImpl<MAccountRecord>> classTable) {
		super(modelMapper, jooq, classTable);
	}
}
