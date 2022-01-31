package com.example.utilities;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;



@Configuration
@EnableWebSecurity
@EnableOAuth2Client
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
                    .antMatchers("/login","/oauth2/autorization/google").permitAll()
                    .antMatchers("/mainPage**","/profile**","/chat/create","/chat**").authenticated()	
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
    
    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
    	return map -> {
    		String email= (String) map.get("email");
    		if(userRepository.findUserByEmail(email)==null) {
    			User user=new User();
    			user.setEmail(email);
    			user.setUserName((String) map.get("name"));
    			return user;
    		}
    		User old=userRepository.findUserByEmail(email);
    		return userRepository.save(old);
    		
    	};
    }
    
    
}