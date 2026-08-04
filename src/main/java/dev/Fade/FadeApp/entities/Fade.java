package dev.Fade.FadeApp.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
@Entity 
@Table(name="fades")
public class Fade {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private Instant createdAt;
    private Instant lastUpvote;
    private float score;
    @OneToMany(mappedBy = "fade",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Upvote> upvotes = new ArrayList<>();
    
    
    protected Fade() {
    }

    public Fade(String content) {
        this.content = content;
        this.createdAt = Instant.now();
        this.lastUpvote = Instant.now();
    }

    public void addUpvote(Upvote upvote) {
        upvotes.add(upvote);
        upvote.setFade(this); 
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public Instant getLastUpvote() {
        return lastUpvote;
    }
    public void removeUpvote(Upvote upvote) {
        upvotes.remove(upvote);
        upvote.setFade(null);
    }
}
