package com.roze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.roze")
public class AppConfig {
    @Bean
    public InMemoryUserDetailsManager setUpUsers() {
        UserDetails firozeUser = User
                .withUsername("firoze")
                // .password("firoze28")
                .password("{noop}firoze28")
                .roles("admin,user")
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
