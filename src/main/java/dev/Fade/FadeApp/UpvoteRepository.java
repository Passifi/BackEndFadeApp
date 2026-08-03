package dev.Fade.FadeApp;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.Fade.FadeApp.entities.Fade;
import dev.Fade.FadeApp.entities.Upvote;

public interface UpvoteRepository extends  JpaRepository<Upvote,Long>{
    public List<Upvote> findByFade(Fade fade);    
}
