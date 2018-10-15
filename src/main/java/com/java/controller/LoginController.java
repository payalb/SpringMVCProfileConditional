package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	

	@RequestMapping("/welcome")
	public String getWelcomePage() {
		return "welcome";
	}
	
	@RequestMapping("/form")
	public String getformPage() {
		return "form";
	}
}
