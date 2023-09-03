package net.test.Controller;

import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.test.Entities.*;
import net.test.Repository.FileUploadRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class ControllerRest {
	
	@Autowired
	private FileUploadRepository fr;
	
	@Autowired
	private ApplicationContext ac;

	@PostMapping("/upload")
	public File uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
		
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileType = file.getContentType();
		byte[] fileContent = file.getBytes();
		
		if(fileType.equals(null)) {
			System.out.println("The file content is null");
		}
		
		File file1 = new File(fileName,fileType,fileContent);
		
		
		
		System.out.println("file is" + file);
		
		
		return fr.save(file1);
		
	}
	
	
	@PostMapping("/uploadMulti")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception {
		
		ArrayList<File> filearr = new ArrayList<File>();
		
		for(MultipartFile file : files) {
			
			String fileName = file.getOriginalFilename();
			 String fileType = file.getContentType();
			 byte[] fileContent = file.getBytes();
			 
			 filearr.add(new File(fileName,fileType,fileContent));
			 
			
		}
		
		for (File x : filearr) {
			fr.save(x);
		}
		
		System.out.println("Array size is = " + filearr.size());
		
		return "Multiple upload success";
	}
	
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<byte[]> download(@PathVariable int fileId) {
		
		Optional<File> file = fr.findById(fileId);
		
		if(file.isPresent()) {
			File file1 = file.get();
			System.out.println("Retrieved file is =" + file1);
			
		//	HttpHeaders  headers = new HttpHeaders();
			
		//	headers.setContentDispositionFormData("attachment", file1.getName());
		//	headers.setContentType(MediaType.parseMediaType(file1.getType()));
			
			return ResponseEntity.ok().body(file1.getData());
		}else {
			System.out.println("Error while downloading the file");
			return null;
		}
					
					
		    
	}
	
	@GetMapping("/deleteAllFileFromDB")
	public String deleteAll() {
		
		fr.deleteAll();
		
		return "ALL FILES DELETED SUCCESSFULLY!";
	}
	
	
	@RequestMapping("/bean")
	public Object getBean() throws BeansException {
		//It represents the Spring IoC container and is responsible for instantiating, configuring, and assembling the beans. 
				Object file1 = ac.getBean("controller1");
		
		return file1;
	}
	
	@GetMapping("/getAllFiles")
	public List<File> getAllFiles() {
		List<File> arrlist = fr.findAll();
		 
		return arrlist;
	}
}
