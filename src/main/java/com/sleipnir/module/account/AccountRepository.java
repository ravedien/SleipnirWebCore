package com.sleipnir.module.account;

import java.util.List;

import org.jooq.Condition;
import org.jooq.generated.tables.records.MAccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sleipnir.core.DomainFactory;
import com.sleipnir.core.DomainGateway;
import com.sleipnir.core.UpdateableRepository;

@Repository
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

	@Transactional
	@Override
	public Account add(Account entity) {
		MAccountRecord newAccount = accountGateway.persist(accountFactory.createRecord(entity));
		return accountFactory.make(newAccount, Account.class);
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
