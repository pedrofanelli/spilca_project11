package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import processors.LoginProcessor;

@Controller
public class LoginController {
	
	private final LoginProcessor lp;  // si bien es final, atento que es un request-scope bean!
	
	public LoginController(LoginProcessor lp) {
		this.lp = lp;
	}

	@GetMapping("/")
	public String loginGet() {
		return "login.html";
	}
	
	@PostMapping("/")
	public String loginPost(
			@RequestParam String username,
			@RequestParam String password,
			Model model) {
		System.out.println(lp);
		lp.setUsername(username);
		lp.setPassword(password);
		boolean loggedIn = lp.login();
		
		if (loggedIn) {
			return "redirect:/main";
		} 
		
		model.addAttribute("message", "Login failed!");
		
		return "login.html";
		
	}
	
}
