package com.walter.xpsocial.domain;

import java.time.LocalDateTime;

public class Post implements Comparable<Post> {

    private final String message;
    private final LocalDateTime postingTime;
    private String username;

    public Post(String text) {
        this(text, LocalDateTime.now());
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

    public Duration elapsedTimeFromPosting(Clock clock) {
        LocalDateTime currentTime = clock.currentTime();
        final java.time.Duration javaDuration
                = java.time.Duration.between(postingTime, currentTime);
        Duration result = new Duration(javaDuration);
        return result;
    }

    public String elapsedTimeAsString(Clock clock) {
        Duration d = elapsedTimeFromPosting(clock);
        String result = String.format("(%s ago)", d);
        return result;
    }

    @Override
    public int compareTo(Post other) {
        int olderFirst = this.postingTime.compareTo(other.postingTime);
        int youngerFirst = -1 * olderFirst;
        return youngerFirst;
    }

}
