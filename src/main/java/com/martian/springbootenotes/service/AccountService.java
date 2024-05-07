package com.martian.springbootenotes.service;


import com.martian.springbootenotes.model.Account;


public interface AccountService {
	
	public Account saveAccount(Account account);
	public boolean existEmailCheck(String email);
}
