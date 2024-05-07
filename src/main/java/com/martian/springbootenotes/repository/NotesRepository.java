package com.martian.springbootenotes.repository;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer>{
	public Page<Notes> findByAccount(Account account, Pageable pageable);
}
