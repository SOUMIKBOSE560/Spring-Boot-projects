package com.Firebaseproject.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialization {
	
	
	@Value("${FireBaseAutthenticationKey}")
	private String key;
	
	
	@PostConstruct
	public void firebaseApplicationinitialization() throws IOException {

	
	GoogleCredentials googlecredentials = GoogleCredentials.fromStream(new ClassPathResource(key).getInputStream());
	
	FirebaseOptions firebaseoptions = FirebaseOptions.builder().setCredentials(googlecredentials).setStorageBucket("save-product.appspot.com").build();
	
	FirebaseApp app = FirebaseApp.initializeApp(firebaseoptions);
	
	System.out.println("Firebase application is == " + app);
	
	}
	
	
}
