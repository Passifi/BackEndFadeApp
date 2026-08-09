package dev.Fade.FadeApp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.boot.beanvalidation.GroupsPerOperation.Operation;
import org.springframework.stereotype.Service;

import dev.Fade.FadeApp.entities.Fade;
import dev.Fade.FadeApp.entities.Upvote;
import jakarta.transaction.Transactional;


@Service
public class FadeService {
    static long Max_Fades = 100;
 private final FadeRepository fadeRepository;
 private final UpvoteRepository upvoteRepository;
 private FadeScoreCalculator scorer = new FadeScoreCalculator();
    public FadeService(FadeRepository repo, UpvoteRepository upvoteRepo) {
        fadeRepository = repo;
        upvoteRepository = upvoteRepo;
    }
    public void createNewFade(String content) {
        fadeRepository.save(new Fade(content));
    }

    @Transactional
    public Fade updateFadeScore(long fadeId) {
        var fade = fadeRepository.findById(fadeId).orElseThrow();
        var upvotes = upvoteRepository.findByCreatedAtBeforeAndFadeId(fadeId,Instant.now().minus(30,ChronoUnit.DAYS));
        var score = scorer.calculateScore(upvotes, Instant.now());

        fade = fadeRepository.findById(fadeId).orElseThrow();

        fade.setScore(score);
        return fade;
    }
    public Iterable<Fade> getDisocveryFades() {
        try {
        Instant cutoff = Instant.now().minus(30,ChronoUnit.DAYS);
        var fades = fadeRepository.findBylastUpvoteAfter(cutoff);
        List<Fade> results = new ArrayList<Fade>();

        for(var fade : fades) {
            if(fade.getScore() == 0) {
                var upvotes = upvoteRepository.findByCreatedAtBeforeAndFadeId(fade.getId(),Instant.now().minus(30,ChronoUnit.DAYS));
                float score = scorer.calculateScore(upvotes, cutoff);
                fade.setScore(score);
            }
        }
        return fades; }
        catch (Exception e) {
            throw e;
        }
    }

    public Fade vote(long fadeId) {
        Upvote upvote =  new Upvote(Instant.now());
        var fade = fadeRepository.findById(fadeId).orElseThrow(); 
        upvote.setFade(fade);
        upvoteRepository.save(upvote);
        fade = updateFadeScore(fadeId);
        return fade;
    }

    public Iterable<Fade> getFades() {
        try  {
        return fadeRepository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }

    public Iterable<Upvote> getUpvotesByFade(long fadeId) {
        try {
            return upvoteRepository.findByFadeId(fadeId);
        }
        catch(Exception e) {
            throw e;
        }
    }
}
