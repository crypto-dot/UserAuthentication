package com.securewebapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MySQLUserDetailsService mySQLUserDetailsService;
	
	@PostMapping("/register") 
	public String createUser(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		User newUser = userRepository.findByUsername(username);
		if(newUser == null) {
			newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			mySQLUserDetailsService.Save(newUser);
			return "login";
		} else {
			model.addAttribute("exists", true);
			return "register";
		}
		
	}
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/topSecret")
	public String topSecret() {
		return "topSecret";
	}
	@GetMapping("/secure")
	public String secure() {
		return "secure";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
}
