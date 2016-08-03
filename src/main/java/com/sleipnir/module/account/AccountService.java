package com.sleipnir.module.account;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class AccountService {
	
	private final AtomicLong dataCounter = new AtomicLong(0);
	
	private final Map<Long, Account> dataStore = new ConcurrentHashMap<>();

    public AccountDraft.Builder draft() {
    	return new AccountDraft.Builder();
    }

    public Account save(AccountDraft draft) { 
    	return save(draft, dataCounter.getAndIncrement()); 
    }

    public Account save(AccountDraft draft, long id) {
        Account account = new Account(id, draft.getUsername(), draft.getPassword());
        dataStore.put(id, account);
        return account;
    }

    public Stream<Account> findAll() {
    	return dataStore.values().stream();
    }

    public Optional<Account> findById(long id) {
    	return Optional.ofNullable(dataStore.get(id));
    }
	
}
