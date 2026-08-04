package dev.Fade.FadeApp;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class FadeScoreCalculator {
    public FadeScoreCalculator() {}
    private float calculateDistanceScore(Instant reference, Instant other) {
       Duration diff = Duration.between(reference, other);
       return Math.abs(diff.toMinutes())+1;
    } 
    public float calculateScore(List<Instant> upvotesTimes, Instant now) {
        if(upvotesTimes.isEmpty()) return 0.0f; 
        float totalScore = 0.0f; 
        for (Instant instant : upvotesTimes) {
            totalScore += calculateDistanceScore(now, instant);
        } 
        return totalScore;
    }

    
}
