package com.walter.xpsocial.domain;

import java.util.ArrayList;
import java.util.List;

public class Timeline {

    private final List<Post> posts;
    
    Timeline() {
        this.posts = new ArrayList<>();
    }

    List<Post> allPosts() {
        return new ArrayList<>(posts);
    }

    void addToHead(Post post) {
        posts.add(0, post);
    }
    
    void addToHead(String message) {
        posts.add(0, new Post(message, Clock.REAL.currentTime()));
    }
}
