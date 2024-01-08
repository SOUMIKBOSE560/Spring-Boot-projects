package com.jasperproject.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usertable_jasperreport")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int Id;
	
	 @Column(name = "firstname")
	    private String firstname;
	    @Column(name = "lastname")
	    private String lastname;
	    @Column(name = "street")
	    private String street;
	    @Column(name = "city")
	    private String city;
	    
	    
	    
	    public User() {
	    	
	    }
	    
	    
		public User(String firstname, String lastname, String street, String city) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.street = street;
			this.city = city;
		}
		
		
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			this.Id = id;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	    
	    

}
