package com.security.JWTsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {


    @Autowired
    private JWTAthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	
//    	
//    	http.cors().disable();
//    	http.csrf().disable();
//    	http.authorizeHttpRequests()
//        .requestMatchers("/api/public/**").permitAll()
//        .requestMatchers("/api/private/**").authenticated()
//        .requestMatchers(HttpMethod.POST, "/api/private/**").authenticated();
//    	http.exceptionHandling(ex ->ex.authenticationEntryPoint(point));
//    	http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	
    	
    	
    	

//        http.csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(
//        		
//        		auth -> auth.requestMatchers("/home/**").authenticated()
//        		.requestMatchers("/auth/login").permitAll()
//        		.anyRequest().authenticated()
//        		)
//       .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
//       .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//              ;
       
        return http.build();
    }


}
