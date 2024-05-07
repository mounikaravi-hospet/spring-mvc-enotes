package com.martian.springbootenotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martian.springbootenotes.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	public boolean existsByEmail(String email);
	public Account findByEmail(String email);
}
