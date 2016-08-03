package com.sleipnir.module.account;

public class AccountTester {
	public static void main(String[] args) {
		AccountService service = new AccountService();
		AccountDraft draft = service.draft().withUsername("Hilbert").withPassword("Hpapai").build();
		
		Account account = service.save(draft);
		
		AccountDraft updatedDraft = account.draft().withUsername("HP").withPassword("wew").build();
		
		Account updatedAccount = service.save(updatedDraft, account.getId());
		
		AccountDraft forkedDraft = updatedDraft.draft().withUsername("MIKO").build();
		Account forkedAccount = service.save(forkedDraft);
		
		System.out.println("Persisted Employees:\n" + service.findAll()
        .map(e -> String.format("#%d \"%s %s\" $%d",
                e.getId(), e.getUsername(), e.getPassword())));
	}
}
