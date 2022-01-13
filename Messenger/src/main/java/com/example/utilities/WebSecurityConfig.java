package com.example.utilities;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.example.service.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	

	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**", "/jquery/**", "/fonts/**").permitAll()
                    .antMatchers("/registration*").not().authenticated()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/mainPage*","/profile").authenticated()	
                    .anyRequest().authenticated()
                    .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/mainPage").permitAll().and()
                .logout().permitAll().logoutSuccessUrl("/login");
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    
}