//package com.roze.SpringBootRecapFinal;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@Profile("dev")
//public class ApplicationConfig {
//    //    @Bean
//    // @Bean(name = "firoze")
//    @Bean
//   // @Qualifier("bean1")
//   // @Profile("dev")
//    public FirstClass firstBean() {
//        return new FirstClass("Welcome in Java");
//    }
//
//    @Bean
//    //@Profile("test")
//   // @Qualifier("bean2")
//    public FirstClass secondBean() {
//        return new FirstClass("Welcome in Spring boot");
//    }
//    @Bean
//    //@Qualifier("bean2")
//    @Primary
//    public FirstClass thirdBean() {
//        return new FirstClass("Welcome in Third bean");
//    }
//}
