package com.dejanroshkovski.mplatform.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // TODO Implement token based authentication with JWT or something similar and
                // add a /login url for generation

                // Going to be using REST API
                http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
                .httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                // TODO change authentication from in memory to DB

                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                auth.inMemoryAuthentication().withUser("dejan").password(encoder.encode("password")).roles("ADMIN");
                auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
                auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user")).roles("USER");
        }
}   