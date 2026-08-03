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


@Service
public class FadeService {
    static long Max_Fades = 100;
 private final FadeRepository fadeRepository;
 private final UpvoteRepository upvoteRepository;
    public FadeService(FadeRepository repo, UpvoteRepository upvoteRepo) {
        fadeRepository = repo;
        upvoteRepository = upvoteRepo;
    }
    public void createNewFade(String content) {
        fadeRepository.save(new Fade(content));
    }
    public Iterable<Fade> getDisocveryFades() {
        try {
        Instant cutoff = Instant.now().minus(30,ChronoUnit.DAYS);
        var fades = fadeRepository.findBylastUpvoteAfter(cutoff);
        List<Fade> results = new ArrayList<Fade>();
        if(results.size() < Max_Fades)
            return fades;
        for(var fade : fades) {
            
        }
        return fades; }
        catch (Exception e) {
            throw e;
        }
    }

    public void vote(long fadeId) {
        try { 
        Upvote upvote =  new Upvote(Instant.now());
        var fade = fadeRepository.findById(fadeId); 
        
        if(fade == null) {
            throw new RuntimeException("Not Found");
        } 
        upvoteRepository.save(upvote);}
        catch(Exception e) {
            throw e;
        }
    }

    public Iterable<Fade> getFades() {
        try  {
        return fadeRepository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }

    public Iterable<Upvote> getUpvotesByFade(Fade fade) {
        try {
            return upvoteRepository.findByFade(fade);
        }
        catch(Exception e) {
            throw e;
        }
    }
}
