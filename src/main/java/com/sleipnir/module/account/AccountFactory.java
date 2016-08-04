package com.sleipnir.module.account;

import org.jooq.DSLContext;
import org.jooq.generated.tables.MAccount;
import org.jooq.generated.tables.records.MAccountRecord;
import org.jooq.impl.TableImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sleipnir.core.EntityProviderFactory;

@Component
public class AccountFactory extends EntityProviderFactory<Account, MAccountRecord, MAccount>{

	@Autowired
	public AccountFactory(ModelMapper modelMapper, DSLContext jooq) {
		super(modelMapper, jooq, MAccount.class);
	}
}
