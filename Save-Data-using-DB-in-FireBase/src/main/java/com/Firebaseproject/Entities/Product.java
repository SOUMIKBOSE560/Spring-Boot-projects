package com.Firebaseproject.Entities;

import cn.ipokerface.snowflake.SnowflakeIdGenerator;

public class Product {
	
	private static final SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(0, 0);
	private String id;
	
    private String name;
	
	private String description;
  
	

	
	
	public Product() {
		
	}
	
	public Product(String name,String description) {
		
		this.id =  String.valueOf(idGenerator.nextId());;
		this.name = name;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
