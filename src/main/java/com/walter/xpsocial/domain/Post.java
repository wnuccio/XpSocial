package com.walter.xpsocial.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class Post implements Comparable<Post> {

    private final String message;
    private final LocalDateTime postingTime;
    private String username;

    public Post(String text) {
        this(text, Clock.REAL.currentTime());
    }

    public Post(String message, LocalDateTime postingTime) {
        this.message = message;
        this.postingTime = postingTime;
        this.username = "";
    }

    public String username() {
        return this.username;
    }

    public String message() {
        return this.message;
    }
    
    void setUsername(String username) {
        this.username = username;
    }

    public Duration timeFromPosting(Clock clock) {
        LocalDateTime currentTime = clock.currentTime();
        final Duration result = Duration.between(postingTime, currentTime);
        return result;
    }

    @Override
    public int compareTo(Post other) {
        int olderFirst = this.postingTime.compareTo(other.postingTime);
        int youngerFirst = -1 * olderFirst;
        return youngerFirst;
    }

}
