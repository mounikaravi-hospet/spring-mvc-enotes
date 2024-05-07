package com.martian.springbootenotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Account saveAccount(Account account) {
		account.setRole("ROLE_USER");
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Account newAccount = accountRepo.save(account);
		return newAccount;
	}

	@Override
	public boolean existEmailCheck(String email) {
		return accountRepo.existsByEmail(email);
	}
	
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

}
