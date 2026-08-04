package dev.Fade.FadeApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.constraints.AssertTrue;

@SpringBootTest
class FadeAppApplicationTests {
	@Autowired
	FadeService service;
	FadeScoreCalculator scorer = new FadeScoreCalculator();
	@Test
	void contextLoads() {
	}
	@Test
	void getFades() {
		var result = service.getDisocveryFades().iterator();
		assertTrue(result.hasNext());
	}

	@Test 
	void getBadVotes() {
		var result = service.getUpvotesByFade(-1).iterator();
		assertTrue(!result.hasNext());
	
	}
	
	@Test void manyAgainstCurrentVotes() {

		Instant now = Instant.now();	
		Instant old = now.minus(30,ChronoUnit.DAYS);
		List<Instant> many = new ArrayList<Instant>();
		
		List<Instant> oneNew = new ArrayList<Instant>(){{add(now);}};
		for(int i = 0;i < 1220; i++) {
			many.add(old);
		}
		float manyScore = scorer.calculateScore(many, now);
		float oneScore = scorer.calculateScore(oneNew, now);
		System.out.println("Score for many votes:" + manyScore);
		System.out.println("Score for one recent vote:" + oneScore);
		
		assertTrue(manyScore>oneScore);
	}

}
