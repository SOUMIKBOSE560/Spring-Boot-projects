package com.security.JWTpojos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
public class JwtResponse {

	private String JwtToken;
	
	private String username;

	public JwtResponse(String jwtToken, String username) {
		super();
		this.JwtToken = jwtToken;
		this.username = username;
	}



	public JwtResponse(JwtResponseBuilder builder) {
		this.JwtToken = JwtToken;
		this.username = username;
	}
	



	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "JwtResponse [JwtToken=" + JwtToken + ", username=" + username + "]";
	}
	
	
	
	
	//Builder class
	
	public static class JwtResponseBuilder{

		// required parameters
		private String JwtToken;
		
		private String username;

	
		
		public JwtResponseBuilder(String jwtToken, String username){
			this.JwtToken=jwtToken;
			this.username=username;
		}

		public JwtResponseBuilder setjwtToken(String jwtToken) {
			this.JwtToken = jwtToken;
			return this;
		}

		public JwtResponseBuilder setusername(String username) {
			this.username = username;
			return this;
		}
		
		public JwtResponse build(){
			return new JwtResponse(this);
		}

	}




	
}
