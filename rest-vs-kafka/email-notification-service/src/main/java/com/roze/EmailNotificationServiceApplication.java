package com.roze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class EmailNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailNotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "new-user-events", groupId = "email-service")
    public void senEmail(String username) {
        System.out.println("EmailNotificationService-> sending mail to: " + username);
    }

}
