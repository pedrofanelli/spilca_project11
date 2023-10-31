package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.LoggedUserManagementService;
import services.LoginCountService;

@Controller
public class MainController {

	private final LoggedUserManagementService loggedUserManagementService;
	private final LoginCountService loginCountService;
	
	public MainController(LoggedUserManagementService loggedUserManagementService,
			LoginCountService loginCountService) {
		this.loggedUserManagementService = loggedUserManagementService;
		this.loginCountService = loginCountService;
	}
	
	@GetMapping("/main")
  public String home(
		  @RequestParam(required = false) String logout,
		  Model model) {
		
		if (logout != null) {
			loggedUserManagementService.setUsername(null);
		}
		
    String username = loggedUserManagementService.getUsername();
 
    if (username == null) {                                      
      return "redirect:/"; // to login
    }
 
    model.addAttribute("username", username);
    return "main.html";                                          
  }
	
}
