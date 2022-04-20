package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().sessionManagement().maximumSessions(1).expiredUrl("/").and()
                .and().logout();
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("fa").password("{noop}fa").roles("USER")
                .and().withUser("ra").password("{noop}ra").roles("ITEM");
    }
}

