package com.masai.otpenabledapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.masai.otpenabledapp.exception.CustomAccessDeniedHandler;
import com.masai.otpenabledapp.service.UsersService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
//@Autowired
//private AccessDeniedHandler accessDeniedHandler;

@Autowired
private UsersService usersService;

//protected void configure(HttpSecurity http) throws Exception {
//	http.csrf().disable().authorizeRequests()
//	.requestMatchers("/","/aboutus").permitAll()  //dashboard , Aboutus page will be permit to all user 
//	.requestMatchers("/admin/**").hasAnyRole("ADMIN") //Only admin user can login 
//	.requestMatchers("/user/**").hasAnyRole("USER") //Only normal user can login 
//	.anyRequest().authenticated() //Rest of all request need authentication 
//	        .and()
//	        .formLogin()
//	.loginPage("/login")  //Loginform all can access .. 
//	.defaultSuccessUrl("/dashboard")
//	.failureUrl("/login?error")
//	.permitAll()
//	.and()
//	        .logout()
//	.permitAll()
//	.and()
//	.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//
//	}

@Bean
public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception{
	
	http
	.authorizeHttpRequests(auth->{
		auth.requestMatchers(HttpMethod.GET,"/","/aboutus","/dashboard","/aboutus","/login","/403").permitAll()
		//.requestMatchers(HttpMethod.POST,"/categories,/items/**,/Restaurant,/bill/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET,"/admin/**").hasAnyRole("ADMIN")
		.requestMatchers(HttpMethod.GET,"/user/**").hasAnyRole("USER")
		//.requestMatchers(HttpMethod.PUT,"/customers/**,/categories/**,/items,/Restaurant/**,/bills/**").hasRole("ADMIN")
		//.requestMatchers(HttpMethod.DELETE,"/customer/**,/categories/**,/items/**,/cancelOrder/**,/Restaurant/**,/bill/**").hasRole("ADMIN")
		//.requestMatchers(HttpMethod.GET,"/customers/**,/customers,/categories/**,/categories,/item/**,/items/**,/itemslist/**,/order/**,/ordersByRestaurant,"
			//	+ "/ordersByCustomer,/Restaurant/**,/Restaurant/**,/bill/**,/startDate,/customerId").hasAnyRole("ADMIN","USER")
		//.requestMatchers(HttpMethod.PATCH,"/order/**").hasRole("ADMIN")
		//.requestMatchers(HttpMethod.GET,"/customers","/customer/**").hasAnyRole("ADMIN","USER")
		//.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
		.anyRequest().authenticated();
	})
	.csrf(csrf-> csrf.disable())
	
	//.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
	//.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
	.formLogin(Customizer.withDefaults())
	.httpBasic(Customizer.withDefaults());
	
	return http.build();
}
	
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);;
 }

@Bean
public AccessDeniedHandler accessDeniedHandler(){
    return new CustomAccessDeniedHandler();
}
}