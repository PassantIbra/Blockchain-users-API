package com.blockchain.practicum.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.blockchain.practicum.security.filters.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	@Autowired
	private ApplicationContext context;

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().and().authorizeRequests().antMatchers("/users").permitAll().and()
				.addFilter(context.getBean(AuthorizationFilter.class));

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
