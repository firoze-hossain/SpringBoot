package com.roze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public InMemoryUserDetailsManager setUp() {
        GrantedAuthority admin = new SimpleGrantedAuthority("admin");
        GrantedAuthority user = new SimpleGrantedAuthority("user");
        GrantedAuthority visitor = new SimpleGrantedAuthority("visitor");
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(admin);
        grantedAuthorityList.add(user);
        grantedAuthorityList.add(visitor);
        UserDetails userDetails = new User("Firoze", "password", grantedAuthorityList);
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(userDetails);
        return inMemoryUserDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
