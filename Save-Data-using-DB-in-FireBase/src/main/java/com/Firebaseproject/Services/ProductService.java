package com.Firebaseproject.Services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Firebaseproject.Entities.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BucketGetOption;
import com.google.cloud.storage.StorageOptions;
import java.io.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentChange;

@Service
public class ProductService {
	
	// I created collection or table by the name of "Product"
	private String COLLECTION_NAME= "Product";
	
	private String PROJECT_ID = "save-product";
	
	private String STORAGE_BUCKET_NAME = PROJECT_ID + ".appspot.com";
	

	public Product saveProduct(Product product) {
		
		 Firestore FireBaseDB =  FirestoreClient.getFirestore(); 
		DocumentReference document = FireBaseDB.collection(COLLECTION_NAME).document(product.getId());

//		Map<String, Object> data = new HashMap<>();
//		data.put("name", product.getName());
//		data.put("description", product.getDescription());

		document.set(product);
		
		return  product;
	}
	
	
	public String deleteProduct() {
		 Firestore FireBaseDB =  FirestoreClient.getFirestore(); 
		
		DocumentReference doc = FireBaseDB.collection(COLLECTION_NAME).document("Soumik");
		
		doc.delete();
		
		
		return "Deleted";
		
	}
	
	public String getAll() {
		
		 Firestore FireBaseDB =  FirestoreClient.getFirestore(); 
		
		CollectionReference collection = FireBaseDB.collection(COLLECTION_NAME);
		
		return "Success";
	}
	
	
	public Product getdatabyid(String id) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore(); 

		CollectionReference collection = db.collection(COLLECTION_NAME);

		DocumentReference document = collection.document(id);

		ApiFuture<DocumentSnapshot> getDoc = document.get();
		
		
		DocumentSnapshot getDocfromcollection = getDoc.get();
		
		String DocId = getDocfromcollection.getId();
		
		Product product = null;
		
		if(getDocfromcollection.exists()) {
			
			System.out.println("The Product id is = " + getDocfromcollection.getId() + " And the product is = " + getDocfromcollection.getData());
			product = getDocfromcollection.toObject(Product.class);
			product.setId(DocId);
			return product;
		}else {
			return null;
		}
		
		
		
	}
	
	
	public String UpdateProduct() throws InterruptedException, ExecutionException {
		
		Firestore db = FirestoreClient.getFirestore(); 

		CollectionReference collection = db.collection(COLLECTION_NAME);

		DocumentReference document = collection.document("255753509977194496");

		ApiFuture<DocumentSnapshot> getDoc = document.get();
		
		
		DocumentSnapshot getDocfromcollection = getDoc.get();
		
		String DocId = getDocfromcollection.getId();
		
		Product product = null;
		
		if(getDocfromcollection.exists()) {
			
			System.out.println("The Product id is = " + getDocfromcollection.getId() + " And the product is = " + getDocfromcollection.getData());
			
			
			
			product = getDocfromcollection.toObject(Product.class);
		
			String ProductId = getDocfromcollection.getId();
			String ProductName = "HOLA my asssssssssss";
			String ProductDescription = product.getDescription();
			
			DocumentReference documentEdit = db.collection(COLLECTION_NAME).document(ProductId);

			Map<String, Object> data = new HashMap<>();
			data.put("name",ProductName);
			data.put("description", ProductDescription);

			document.set(data);
			
			
			return "Edited Successfully !!";
		}else {
			return null;
		}
		
		
		
	}
	
	
	
	public String deleteAll() {
		
		Firestore db = FirestoreClient.getFirestore();
		
		CollectionReference collection = db.collection(COLLECTION_NAME);
		
		
		
		return "Whole DB Cleaned";
	}
	
	
	
	
	
	
	//Method for uploading file
	
	public void upload(MultipartFile file) throws IOException{
		
//		FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");
//
//		FirebaseOptions options = FirebaseOptions.builder()
//		    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//		    .setStorageBucket("<BUCKET_NAME>.appspot.com")
//		    .build();
//		FirebaseApp.initializeApp(options);

		Bucket bucket = StorageClient.getInstance().bucket();
		
		System.out.println("Bucket is = " + bucket);
		
		// Specify the file's name and path in the bucket
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		byte[] content = file.getBytes();
		String type = file.getContentType();
		
		String stringbytearr = content.toString();
		
		
		

		// Create an InputStream from the file you want to upload
		
		//String filePath = "images/Soumik_Bose.jpeg";
		///InputStream fileStream = getClass().getClassLoader().getResourceAsStream(filePath);


		// Upload the file to Firebase Storage
		//Blob blob = bucket.create(fileName, fileStream);
		
		Blob blob = bucket.create(fileName, content, type);
		System.out.println("Blob obj of the file is: " + blob);

		// Print the public URL of the uploaded file
		String publicUrl = blob.getMediaLink();
		System.out.println("File uploaded to: " + publicUrl);

		// 'bucket' is an object defined in the google-cloud-storage Java library.
		// See http://googlecloudplatform.github.io/google-cloud-java/latest/apidocs/com/google/cloud/storage/Bucket.html
		// for more details.

		Date date  = new Date();
		
	
		
	}
	
	
	
	
}
