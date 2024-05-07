package com.martian.springbootenotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.repository.AccountRepository;

@Service
public class CustomAccountDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepo.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			return new CustomAccount(account);
		}
	}
	
}
