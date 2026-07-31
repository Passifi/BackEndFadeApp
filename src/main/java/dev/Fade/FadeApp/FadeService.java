package dev.Fade.FadeApp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import dev.Fade.FadeApp.entities.Fade;

@Service
public class FadeService {
 private final FadeRepository fadeRepository;
 
    public FadeService(FadeRepository repo) {
        fadeRepository = repo;
    }

    public void createNewFade(String content) {
        fadeRepository.save(new Fade(content));
    }
    public Iterable<Fade> getDisocveryFades() {
        Instant cutoff = Instant.now().minus(30,ChronoUnit.DAYS);
        var fades = fadeRepository.findBylastUpvoteAfter(cutoff);

        return fades;
    }

    public void vote() {
        
    }

    public Iterable<Fade> getFades() {
        return fadeRepository.findAll();
    }
}
