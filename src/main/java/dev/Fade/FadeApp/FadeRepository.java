package dev.Fade.FadeApp;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.Fade.FadeApp.entities.Fade;

public interface FadeRepository extends JpaRepository<Fade,Long> {
    List<Fade> findAllByOrderByLastUpvoteDesc();        
    List<Fade> findTop20ByOrderByLastUpvoteDesc();        
    Fade findFadeById(long id);
    Page<Fade> findAll(Pageable pageable);
    List<Fade> findBylastUpvoteAfter(Instant cutoff);
    
}
