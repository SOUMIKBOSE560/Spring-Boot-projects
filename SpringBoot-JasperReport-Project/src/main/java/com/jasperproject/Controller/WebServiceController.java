package com.jasperproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.jasperproject.Service.JasperReportService;
import com.jasperproject.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import com.jasperproject.Models.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class WebServiceController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JasperReportService jasperService;
	
	@Value("${JasperReport}")
	private static String JASPER_REPORT_FORMAT;
	
	@PostMapping(value = "/save")
	public String save() {
		
		List<User> list = new ArrayList<>() ;
		
		list.add(new User("Soumik","Bose","Monoharpur","Dankuni"));
		list.add(new User("Soumik","Bose","Monoharpur","Dankuni"));
		list.add(new User("Soumik","Bose","Monoharpur","Dankuni"));
		list.add(new User("Soumik","Bose","Monoharpur","Dankuni"));
		
		return userService.saveUser(list);
		
		
		
	}
	
	
	@GetMapping("/report")
	public String getReport(HttpServletResponse response) throws FileNotFoundException, IOException, JRException {
		
		  
		   
		   List<User>userList = userService.allusers();
		   
		   response.setContentType("application/pdf");
	       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	       
	       
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	  
	       jasperService.exportjasperReport(userList, response);    
		
		return "Success";
	}
	
	
	
}
