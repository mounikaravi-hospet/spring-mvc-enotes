package com.martian.springbootenotes.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.model.Notes;
import com.martian.springbootenotes.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	NotesRepository notesRepo;
	
	@Override
	public Notes saveNotes(Notes notes) {
		return notesRepo.save(notes);
	}

	@Override
	public Notes getNotesById(Integer id) {
		return notesRepo.findById(id).get();
	}

	@Override
	public Page<Notes> getNotesByAccount(Account account, Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo, 5);
		return notesRepo.findByAccount(account, pageable);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		return notesRepo.save(notes);
	}

	@Override 
	public boolean deleteNotes(Integer id) {
		Notes notes = notesRepo.findById(id).get();
		if(notes!=null) {
			notesRepo.delete(notes);
			return true;
		}
		return false;
	}

}
