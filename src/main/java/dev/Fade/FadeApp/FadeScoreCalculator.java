package dev.Fade.FadeApp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
public class FadeScoreCalculator {
    static Duration maxDistance = Duration.ofDays(30);
    public FadeScoreCalculator() {}
    private float calculateDistanceScore(Instant reference, Instant other) {
       Duration diff = Duration.between(reference, other);
       return Math.abs(maxDistance.toMinutes()/(diff.toMinutes()+1.0f));
    } 
    public float calculateScore(List<Instant> upvotesTimes, Instant now) {
        if(upvotesTimes.isEmpty()) return 0.0f; 
        float totalScore = 0.0f; 
        for (Instant instant : upvotesTimes) {
            totalScore += maxDistance.toMinutes()/calculateDistanceScore(now, instant);
        } 
        return totalScore;
    }
}
