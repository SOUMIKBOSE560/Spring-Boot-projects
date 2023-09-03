package net.test.ControllerJSP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.test.Entities.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Controller
@RequestMapping("/")
public class controller1 {
	
	@Autowired
	private ApplicationContext ac;

	@GetMapping
	public String homePage() {
		return "index";
	}
	

}
