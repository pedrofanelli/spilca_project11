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
	
	/**
	 * If this controller's constructor is injected with a @RequestScope bean, it's important to note that the actual 
	 * creation of the @RequestScope bean doesn't happen at the time the @Controller is created. Instead, Spring will 
	 * inject a proxy or placeholder for the @RequestScope bean into your controller.
	 * 
	 * When a request comes in, Spring will create a new instance of the @RequestScope bean and associate it with the 
	 * current request. This is the moment when the actual @RequestScope bean is created and initialized.
	 * 
	 * The proxy or placeholder in your controller will point to the correct instance of the @RequestScope bean for 
	 * that specific request.
	 * 
	 * Once the request is processed, the request-scoped bean associated with that request is discarded. The proxy or 
	 * placeholder in your controller will then point to a new instance for the next request.
	 */
	
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
		System.out.println("REQUEST SCOPE LoginProcessor: "+lp);
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
