package com.xtech.taskwatch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsSvc;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/register").permitAll().anyRequest().authenticated().and().formLogin()
                .loginProcessingUrl("/loginnn").defaultSuccessUrl("/").usernameParameter("username")
                .passwordParameter("password").loginPage("/loginn").permitAll()

                .and().logout().permitAll().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            /*
            .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .usersByUsernameQuery("select username, password, true from users where username=?")
            .authoritiesByUsernameQuery("select u.username, ur.user_roles from users u inner join user_roles ur on u.id = ur.user_id where u.username=?");
            */
            .userDetailsService(userDetailsSvc)
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = 
            User.withDefaultPasswordEncoder()
                .username("u")
                .password("p")
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    
}
