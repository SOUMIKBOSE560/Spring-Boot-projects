package com.security.Controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class Controller {

	@GetMapping("/add")
	public int add(HttpServletRequest req,HttpServletResponse res) {
		
		int a = Integer.parseInt(req.getParameter("a"));
		int b = Integer.parseInt(req.getParameter("b"));
		
		int sum  = a + b;
		return sum;
	}

	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		
		return (principal.getName());
	}
	
	
}
