package com.Firebaseproject.Services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.BatchResult;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.StorageBatch;
import com.google.cloud.storage.StorageException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Paths;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



@Service
public class uploadService {
	
	
	private BlobId blobid1 = null;
	
	@Value(value = "${FireBaseAutthenticationKey}")
	private String key;
	

	public String upload(MultipartFile file) throws IOException {
		
		Bucket bucket = StorageClient.getInstance().bucket();
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		byte[] filecontent = file.getBytes();
		String filetype = file.getContentType();
		
		
	    Blob blob =	bucket.create(filename, filecontent, filetype);
	    
	    String downloadLink = blob.getMediaLink();
	    
	    System.out.println("Downloadlink : " + downloadLink);
	    
	    String id = blob.getGeneratedId();
	    
	    System.out.println("Generated ID : " + id);
	    
	    BlobId blobid = blob.getBlobId();
	    
	    blobid1  = blobid;
	    
	  
	    
	    System.out.println("Blob id type is BlobId : " + blobid);
	    
	
	    
		
	    return id;
		
	}
	
	
	public void delete() throws IOException{
		
		GoogleCredentials googlecredentials = GoogleCredentials.fromStream(new ClassPathResource(key).getInputStream());
		

		StorageOptions storageoptions = StorageOptions.newBuilder().setCredentials(googlecredentials).build();
		
		Storage storage = storageoptions.getService();
		
		String bucketName = "save-product.appspot.com";
		
		String objectName = "SoumikBose_CV.pdf";
		
		
		 Blob blob = storage.get(bucketName, objectName);
		    if (blob == null) {
		      System.out.println("The object " + objectName + " wasn't found in " + bucketName);
		     
		    } else {
		    	
		    	System.out.println("Found");
		    }
		
		    
		    BlobId blobId = BlobId.of("save-product.appspot.com", "SoumikBose_CV.pdf",1694729135873476L);
		    
		    boolean result = storage.delete(blobId);
		
		    if(result) {
		    	System.out.println("Success");
		    	
		    }else {
		    	System.out.println("Error");
		    }
	
	}
	
	
	
	public void downloadObject() throws IOException {
		
		   
	   GoogleCredentials googlecredentials = GoogleCredentials.fromStream(new ClassPathResource(key).getInputStream());
		

		StorageOptions storageoptions = StorageOptions.newBuilder().setCredentials(googlecredentials).setProjectId("save-product").build();
		    // The ID of your GCP project
		    // String projectId = "your-project-id";

		    // The ID of your GCS bucket
		    // String bucketName = "your-unique-bucket-name";

		    // The ID of your GCS object
		    // String objectName = "your-object-name";

		    // The path to which the file should be downloaded
		    // String destFilePath = "/local/path/to/file.txt";

		    Storage storage = storageoptions.getService();
		    
		 // The local file path where you want to save the downloaded object
		    String destFilePath = System.getProperty("user.home") + "\\Downloads\\SoumikBose.pdf";


		    try {
		    Blob blob = storage.get(BlobId.of("save-product.appspot.com", "SoumikBose_CV.pdf"));
		    blob.downloadTo(Paths.get(destFilePath));
		    System.out.println("Dropped");
		    }
		    
		    catch(Exception e){
		    	
		    	System.out.println("Exception is :: " + e.getMessage());
		    }
		 

	}
}
