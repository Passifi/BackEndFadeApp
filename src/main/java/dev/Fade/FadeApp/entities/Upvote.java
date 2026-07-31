package dev.Fade.FadeApp.entities;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="upvotes")
public class Upvote {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false) 
    private Instant createdAt;
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="fade_id",nullable = false)
    private Fade fade;
    protected Upvote() {

    }

    protected Upvote(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Fade getFade() {
        return this.fade;
    }

    public void setFade(Fade fade) {
            this.fade = fade;
    }
}
