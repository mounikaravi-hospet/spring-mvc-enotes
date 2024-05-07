package com.martian.springbootenotes.service;

//import java.util.List;

import org.springframework.data.domain.Page;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.model.Notes;

public interface NotesService {
	public Notes saveNotes(Notes notes);
	public Notes getNotesById(Integer id);
//	public List<Notes> getNotesByAccount(Account account); Without pagination
	public Page<Notes> getNotesByAccount(Account account, Integer pageNo); //with pagination
	public Notes updateNotes(Notes notes);
	public boolean deleteNotes(Integer id);
}
