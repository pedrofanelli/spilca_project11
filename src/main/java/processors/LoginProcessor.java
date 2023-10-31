package processors;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import services.LoggedUserManagementService;
import services.LoginCountService;

@Component
@RequestScope
public class LoginProcessor {

	private String username;
	private String password;
	
	private final LoggedUserManagementService loggedUserManagementService;
	private final LoginCountService loginCountService;
	
	// autowired DI
	public LoginProcessor(LoggedUserManagementService loggedUserManagementService,
			LoginCountService loginCountService) {
		this.loggedUserManagementService = loggedUserManagementService;
		this.loginCountService = loginCountService;
	}
	
	public boolean login() {
		
		
		String username = this.getUsername();
		String password = this.getPassword();
		
		boolean loginResult = false;
		if ("natalie".equals(username) && "pass".equals(password)) {
			this.loggedUserManagementService.setUsername(username);
			
			System.out.println("LOGIN COUNT SERVICE antes de incrementar: "+loginCountService);
			loginCountService.increment();
			
			return true;
		} 
		return loginResult;
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
