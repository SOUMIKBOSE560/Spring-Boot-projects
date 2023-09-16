package com.Firebaseproject.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Firebaseproject.Entities.*;
import com.Firebaseproject.Services.ProductService;
import com.Firebaseproject.Services.uploadService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

@RestController
@RequestMapping("/admin")
public class restcontroller {
	
	
	@Autowired
	private ProductService ps;
	
	
	@Autowired
	private uploadService us;
	
	
	@PostMapping("/save")
	public Product saveProduct() {
		
		String name = "Soumik Bose";
		String description = "Active";
		
		Product product = new Product(name,description);
		
		
		
		return ps.saveProduct(product);
		
		
	}
	
	
    @GetMapping("/update")
    public String updateProduct() throws InterruptedException, ExecutionException {
    	
    	return ps.UpdateProduct();
    }
	
	
	
	@PostMapping("/Delete")
	public String Delete(){
		return ps.deleteProduct();
	}

	@PostMapping("/getById/{id}")
	public Product getById(@PathVariable String id) throws InterruptedException, ExecutionException{
		
		return ps.getdatabyid(id);
	}
	
	@PostMapping("/getAll")
	public String getAll(){
		return ps.getAll();
	}
	
	
	@GetMapping(value = "/deleteFile")
	public void deleteFile() throws IOException{
		 us.delete();
	}
	
	
	@GetMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException  {
		return us.upload(file);
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getValue(@PathVariable(value = "userId") int userId1) {
		
		return String.valueOf(userId1);
	}
	
	
	@GetMapping(value = "/download")
	public void download() throws IOException {
		us.downloadObject();
	}
	
}
