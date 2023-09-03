package net.test.Entities;

import java.util.Arrays;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class File {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String type;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Lob
	private byte[] data;
	
	public File(String name,String type, byte[] data) {
		super();
		this.type = type;
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	} 
	
	
	public File() {
		
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data) + "]";
	}

	
	
	
}
