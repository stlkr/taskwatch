package com.xtech.taskwatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginProcessingUrl("/loginnn")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/loginn").permitAll()
               
            .and()
                .logout().permitAll()
            .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = 
            User.withDefaultPasswordEncoder()
                .username("u")
                .password("p")
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
