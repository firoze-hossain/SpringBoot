package com.roze.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;


import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.roze")
public class AppConfig {
    @Autowired
    HttpSecurity httpSecurity;

    @Bean
    public InMemoryUserDetailsManager setUpUsers() {
        UserDetails firozeUser = User
                .withUsername("firoze")
                // .password("firoze28")
                //.password("{noop}firoze28")
                .password("$2a$10$wuZtJJ1B1pKoVvfLtb4eQuD94XNAU14dJhzBEOksSC1N2DlCYXZRS")
                .roles("admin", "user")
                .build();
        UserDetails hossainUser = User
                .withUsername("hossain")
                //.password("fir28")
                // .password("{noop}fir28")
                //.password("{bcrypt}$2a$10$PWO6YCIGbQVHts7xrV9FD.xUAXvh7kHWoyCitnv8jl6JYOTork1rC")
                .password("$2a$10$PWO6YCIGbQVHts7xrV9FD.xUAXvh7kHWoyCitnv8jl6JYOTork1rC")
                .roles("user")
                .build();
        // InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        // inMemoryUserDetailsManager.createUser(firozeUser);
        //inMemoryUserDetailsManager.createUser(hossainUser);
        return new InMemoryUserDetailsManager(firozeUser, hossainUser);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain setUpHttpSecurity() throws Exception {
        //requirements-> /hi-will be authenticated, /hello-will permit all, /bye-will deny all
        //spring-security 6.0-authorizeHttpRequests()
//        httpSecurity.authorizeHttpRequests().requestMatchers("/hi","/bye").authenticated();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/hello").permitAll();
        //  httpSecurity.authorizeHttpRequests().requestMatchers("/bye").denyAll();
        //spring security-6.0
//        httpSecurity.authorizeHttpRequests().requestMatchers(antMatcher("/hi"), antMatcher("/bye")).authenticated();
//        httpSecurity.authorizeHttpRequests().requestMatchers(antMatcher("/hello")).permitAll();
        //spring security-6.1.5(Lambda DSL)
        httpSecurity.authorizeHttpRequests(customizer -> {
            customizer.requestMatchers(antMatcher("/hi")).authenticated();
            customizer.requestMatchers(antMatcher("/bye")).hasRole("admin");
            customizer.requestMatchers(antMatcher("/hello")).permitAll();
        });
        //httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        //spring security-6.0
//        httpSecurity.formLogin();
//        httpSecurity.httpBasic();
        //spring security 6.1.5
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    //mvcHandlerMappingIntrospector
//    @Bean(name = "mvcHandlerMappingIntrospector")
//    HandlerMappingIntrospector handlerMappingIntrospector() {
//        return new HandlerMappingIntrospector();
//    }
//    @Bean
//    public InMemoryUserDetailsManager setUp() {
//        GrantedAuthority admin = new SimpleGrantedAuthority("admin");
//        GrantedAuthority user = new SimpleGrantedAuthority("user");
//        GrantedAuthority visitor = new SimpleGrantedAuthority("visitor");
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        grantedAuthorityList.add(admin);
//        grantedAuthorityList.add(user);
//        grantedAuthorityList.add(visitor);
//        UserDetails userDetails = new User("Firoze", "password", grantedAuthorityList);
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(userDetails);
//        return inMemoryUserDetailsManager;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
