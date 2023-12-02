package com.syr.whispy.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form.loginPage("/usr/login").defaultSuccessUrl("/usr/timeline"))
                .oauth2Login(oauth -> oauth.loginPage("/usr/login").defaultSuccessUrl("/usr/timeline"))
                .logout(logout -> logout.logoutUrl("/usr/logout").logoutSuccessUrl("/usr/login"));
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll());

        return http.build();
    }

}