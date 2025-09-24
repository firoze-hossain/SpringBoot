package com.roze.feignclient;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(name = "ADDRESS-SERVICE", configuration = MyCustomLoadBalancerConfiguration.class)
public class AddressServiceLoadBalancer {
    @Bean
    @LoadBalanced
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
