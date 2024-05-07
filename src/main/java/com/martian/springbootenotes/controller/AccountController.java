package com.martian.springbootenotes.controller;

import java.security.Principal;
import java.time.LocalDate;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.model.Notes;
import com.martian.springbootenotes.repository.AccountRepository;
import com.martian.springbootenotes.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class AccountController {
	
	@Autowired
	private NotesService notesService;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@ModelAttribute //Any time the 'user' mapping is used, this method will be called
	public Account getAccount(Principal p, Model m) {
		String email = p.getName();
		Account account = accountRepo.findByEmail(email);
		m.addAttribute("account", account);
		return account;
	}
	
	@GetMapping("/addNotes")
	public String addNotes() {
		return "addNotes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes(Model m, Principal P, @RequestParam (defaultValue = "0")Integer pageNo) {
		Account account = getAccount(P, m);
		Page<Notes> notes = notesService.getNotesByAccount(account, pageNo);
		m.addAttribute("currentPage", pageNo);
		m.addAttribute("totalNotes", notes.getTotalElements());
		m.addAttribute("totalPages", notes.getTotalPages());
		m.addAttribute("notes", notes.getContent());
		return "viewNotes";
	}
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable Integer id, Model m) {
		Notes notesById = notesService.getNotesById(id);
		m.addAttribute("notesById",notesById);
		return "editNotes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setAccount(getAccount(p, m));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg", "Notes saved successfully");
		}else {
			session.setAttribute("msg", "Could not save the note");
		}
		return "redirect:/user/viewNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setAccount(getAccount(p, m));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg", "Notes updated successfully");
		}else {
			session.setAttribute("msg", "Could not update the note");
		}
		return "redirect:/user/viewNotes";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable Integer id, Model m, HttpSession session) {
		Boolean b = notesService.deleteNotes(id);
		if(b) {
			session.setAttribute("msg", "Notes Deleted successfully");
		}else {
			session.setAttribute("msg", "Could not delete the note");
		}
		return "redirect:/user/viewNotes";
	}
	
}
