package com.roze;

import com.github.javafaker.Faker;
import com.roze.entity.Author;
import com.roze.entity.Video;
import com.roze.repository.AuthorRepository;
import com.roze.repository.VideoRepository;
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

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository,
            VideoRepository videoRepository
    ) {
        return args -> {
//            for (int i = 0; i < 100; i++) {
//                Faker faker = new Faker();
//                Author author = Author.builder()
//                        .firstName(faker.name().firstName())
//                        .lastName(faker.name().lastName())
//                        .age(faker.number().numberBetween(18, 50))
//                        .email(faker.name().username()+ "@gmail.com")
//                        .createdAt(LocalDateTime.now())
//                        .createdBy(faker.name().name())
//                        .build();
//                authorRepository.save(author);
//            }

            Author author = Author.builder()
                    .id(1)
                    .firstName("Md. Firoze")
                    .lastName("Hossain")
                    .age(28)
                    .email("firoze.hossain01@gmail.com")
                    //.createdAt(LocalDateTime.now())
                    .createdBy("Md. Firoze Hossain")
                    .build();
            //authorRepository.save(author);

            //update author age by id
            //  authorRepository.updateAuthorAgeById(33,1);
            //update all authors age
            authorRepository.updateAllAuthorsAge(68);


            /*Video video = Video.builder()
                    .name("Firoze Bio")
                    .length(6)
                    .build();
            videoRepository.save(video);*/

        };
    }

}
