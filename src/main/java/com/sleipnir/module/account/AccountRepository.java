package com.sleipnir.module.account;

import java.util.List;

import org.jooq.Condition;
import org.jooq.generated.tables.records.MAccountRecord;
import org.springframework.beans.factory.annotation.Autowired;

import com.sleipnir.core.DomainFactory;
import com.sleipnir.core.DomainGateway;
import com.sleipnir.core.UpdateableRepository;

public class AccountRepository implements UpdateableRepository<Account, Long>{

	private final DomainGateway<Long, MAccountRecord>accountGateway;
	private final DomainFactory<Account, MAccountRecord> accountFactory;
	
	@Autowired
	public AccountRepository(DomainGateway<Long,MAccountRecord> accountGateway,
			DomainFactory<Account, MAccountRecord> accountFactory){
		this.accountGateway = accountGateway;
		this.accountFactory = accountFactory;
	}
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findUsingCondition(Condition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account add(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
