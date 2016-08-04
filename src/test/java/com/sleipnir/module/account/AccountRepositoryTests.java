package com.sleipnir.module.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sleipnir.Application;
import com.sleipnir.core.UpdateableRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountRepositoryTests {

	@Autowired
	public UpdateableRepository<Account,Long> accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void addUser() {
//		Account account = new Account.AccountBuilder("ravedien", passwordEncoder.encode("password")).build();
		Account account = new Account();
		account.setUsername("hilbert");
		account.setPassword(passwordEncoder.encode("password"));
		account = accountRepository.add(account);
	}
}
