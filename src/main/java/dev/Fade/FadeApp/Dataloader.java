package dev.Fade.FadeApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.Fade.FadeApp.entities.Fade;

@Configuration
public class Dataloader {
        @Bean
        CommandLineRunner initDatabase(FadeRepository repo) {
            return args -> {
                if(repo.count() == 0) {
                    repo.save(new Fade("It's good to be alive"));
                    repo.save(new Fade("Life is peachy!"));
                    repo.save(new Fade("No more VIDEOS!"));

                }
            };
        }
}
