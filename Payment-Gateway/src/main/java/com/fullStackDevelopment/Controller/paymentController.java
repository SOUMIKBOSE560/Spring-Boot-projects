package com.fullStackDevelopment.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.fullStackDevelopment.Entities.*;

import org.json.JSONObject;


//Razorpay classes
import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.razorpay.Payment;
import com.razorpay.PaymentClient;
import com.razorpay.PaymentLink;



import java.net.http.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;



@RestController
@RequestMapping("/user")
public class paymentController {

	@Value("${razorpay.key_id}")
	private String KEY_ID ;
	
	@Value("${razorpay.key_secret}")
	private String KEY_SECRET;
	
	
	
	@PostMapping("/createOrder")
	public OrderResponse createOrder() {
		
	    
		OrderResponse orderResponse = new OrderResponse();
		
		try {
			
			JSONObject json = new JSONObject();
			json.put("amount", 1000); // Amount in paise (1000 paise = INR 10)
			json.put("currency", "INR");
			json.put("receipt", "order_rcptid_11");
			
			//Order order = new Order(json);
			
			RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);
			
			Order order = client.orders.create(json);
			
			System.out.println("Order ID : " + order.get("id"));
		}
		
		catch(RazorpayException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//Payment after creating order using SDK
	
	@GetMapping("/payment")
	public void payment() {
		
		try {
			
			RazorpayClient client = new RazorpayClient(KEY_ID,KEY_SECRET);
			
			JSONObject paymentObject  = new JSONObject();
			
			paymentObject.put("amount", 1000);
			paymentObject.put("currency", "INR");
			
			
			
			//Payment payment = new Payment(paymentObject);
			
		
			PaymentLink payment = client.paymentLink.create(paymentObject);
//			Payment payment2 = client.payments.createRecurringPayment(paymentObject);
//			Payment payment3 = client.payments.createUpi(paymentObject);
			
			System.out.println("JSON paymentLink : " + payment);
//			System.out.println("Recurring payment : " + payment2);
//			System.out.println("UPI payment : " + payment3);
			
			
			
			
		}
		
		catch(RazorpayException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	//Not using SDK
	
	@GetMapping("/createOrderNoSdk")
	public void createOrderNoSdk() throws URISyntaxException {
		
		
		  try {
	            // Your Razorpay API Key Secret
	            String apiKeySecret = KEY_SECRET;

	           
	            String apiKeySecretBase64 = Base64.getEncoder().encodeToString(apiKeySecret.getBytes());
	            // Razorpay API URL for creating an order
	            String apiUrl = "https://api.razorpay.com/v1/orders";

	            // Create an instance of HttpClient
	            HttpClient httpClient = HttpClient.newHttpClient();

	            // Create a JSON request payload for creating the order
	            String jsonRequest = "{\"amount\": 1000, \"currency\": \"INR\", \"receipt\": \"order_rcptid_11\"}";

	            // Build the HTTP request
	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(new URI(apiUrl))
	                    .header("Authorization", "Basic " + apiKeySecretBase64)
	                    .header("Content-Type", "application/json")
	                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest, StandardCharsets.UTF_8))
	                    .build();

	            // Send the HTTP request and get the response
	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	            // Parse the response and extract the order ID
	            String responseBody = response.body();
	            System.out.println("Response: " + responseBody);

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
		    }
		

	}

