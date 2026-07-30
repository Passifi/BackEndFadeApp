package dev.Fade.FadeApp.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
@Entity 
@Table(name="fades")
public class Fade {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    @CreationTimestamp
    private Instant createdAt;
    private Instant lastUpvote;
    protected Fade() {
    }

    public Fade(String content) {
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
}
