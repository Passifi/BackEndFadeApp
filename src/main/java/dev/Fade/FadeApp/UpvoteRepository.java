package dev.Fade.FadeApp;


import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.Fade.FadeApp.entities.Fade;
import dev.Fade.FadeApp.entities.Upvote;

public interface UpvoteRepository extends  JpaRepository<Upvote,Long>{
    public List<Upvote> findByFade(Fade fade);    
    public List<Upvote> findByFadeId(long id);
    @Query("""
        select u.createdAt
        from Upvote u
        where u.fade.id = :fadeId
        and u.createdAt < :cutOff
        order by u.createdAt
        """
    )
    public List<Instant> findByCreatedAtBeforeAndFadeId(long id,Instant cutoff);
}
