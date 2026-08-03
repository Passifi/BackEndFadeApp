package dev.Fade.FadeApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.constraints.AssertTrue;

@SpringBootTest
class FadeAppApplicationTests {
	@Autowired
	FadeService service;
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

}
