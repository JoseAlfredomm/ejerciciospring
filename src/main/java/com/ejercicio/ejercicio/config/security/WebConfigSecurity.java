package com.ejercicio.ejercicio.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ejercicio.ejercicio.service.UserService;
import com.ejercicio.ejercicio.utils.uri.SaleUri;

@Configuration
@EnableWebSecurity(debug = false)
//@Order(1)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http .authorizeRequests()
        .antMatchers("/bootstrap*").anonymous()
        .antMatchers("/login*").anonymous()
        .antMatchers("/oauth/tocken").anonymous()
        .anyRequest().authenticated()
        .and()
    .formLogin().permitAll()
        .and()
    .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .and()
    .csrf().disable();
		
	}
	
}
