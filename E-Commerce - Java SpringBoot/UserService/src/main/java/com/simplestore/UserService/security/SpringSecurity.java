package com.simplestore.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Order(1)
    @Bean
    public SecurityFilterChain filteringCriteria(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        //example
        //httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "/auth/").hasRole("Admin"));
        //httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/*").permitAll());
        //httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/order/*").authenticated());
        httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
