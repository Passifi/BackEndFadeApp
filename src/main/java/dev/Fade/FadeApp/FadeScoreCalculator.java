package dev.Fade.FadeApp;

import java.time.Instant;
import java.util.List;

public class FadeScoreCalculator {
    private float calculateDistanceScore(Instant reference, Instant other) {
       var result = reference.compareTo(other); 
       return Math.abs(result);
    } 
    public float calculateScore(List<Instant> upvotesTimes, Instant now) {
        if(upvotesTimes.isEmpty()) return 0.0f; 
        float totalScore = 0.0f; 
        for (Instant instant : upvotesTimes) {
            totalScore += 1.0/calculateDistanceScore(now, instant);
        } 
        return totalScore;
    }

    
}
