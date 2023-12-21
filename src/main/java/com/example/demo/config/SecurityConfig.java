package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/icon/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/picture/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/entryEnterprise/insert")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/entryEnterprise/update")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/entryEnterprise/delete")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/user/insert")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/user/update")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/user/delete")).hasAnyAuthority("admin")
				.requestMatchers(AntPathRequestMatcher.antMatcher("/masterData/**")).hasAnyAuthority("admin")
				.anyRequest().authenticated());
		
		http.formLogin(login -> login
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
				.usernameParameter("email")
				.passwordParameter("password"));
	
		http.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
	
	
	
	
	
	
	

}
