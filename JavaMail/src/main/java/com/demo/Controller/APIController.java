package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.MailService;

@RestController
public class APIController {

	@Autowired
	private MailService mailService;
	
	@GetMapping("/sendMail")
	public void sendMail() {
		 String jsonBody = "{ \"name\": \"demo\", \"class\": \"demo\" }";
		 
		mailService.sendSimpleMail("sennayanika650@gmail.com",jsonBody , "sample subject");
	}
	
}
