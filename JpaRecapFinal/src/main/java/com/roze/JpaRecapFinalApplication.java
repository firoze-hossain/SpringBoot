package com.roze;

import com.roze.entity.Author;
import com.roze.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class JpaRecapFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaRecapFinalApplication.class, args);
    }

    //@Bean
    public CommandLineRunner commandLineRunner(AuthorRepository authorRepository) {
        return args -> {
            Author author = Author.builder()
                    .firstName("Md. Firoze")
                    .lastName("Hossain")
                    .age(30)
                    .email("firoze@gmail.com")
                    //.createdAt(LocalDateTime.now())
                    .build();
            authorRepository.save(author);

        };
    }

}
