package com.martian.springbootenotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.martian.springbootenotes.model.Account;
import com.martian.springbootenotes.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired 
	AccountService accountService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/saveAccount")
	public String saveAccount(@ModelAttribute Account account, HttpSession session) {
		boolean e = accountService.existEmailCheck(account.getEmail());
		if(e) {
			session.setAttribute("msg", "User already exists. Try to log in.");
		}else {
			Account newAccount = accountService.saveAccount(account);
			if(newAccount != null) {
				session.setAttribute("msg", "Registration Successful");
			}else {
				session.setAttribute("msg", "Could nor register");
			}
		}
		return "redirect:/signin";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}

}
