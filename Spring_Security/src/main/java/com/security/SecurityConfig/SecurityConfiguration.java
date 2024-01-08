package com.security.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


@Configuration
public class SecurityConfiguration {
	
	//In-Memory Authentication
	//The recommended way of doing this is registering an InMemoryUserDetailsManager bean:
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder().username("Soumik_Bose").password(passwordEncoder().encode("Soumik@1998")).roles("USER").build(); //building UserDetails object
        UserDetails user1 = User.builder().username("sb").password(passwordEncoder().encode("sb")).roles("USER").build();
        
        return new InMemoryUserDetailsManager(user,user1);
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
  

}

