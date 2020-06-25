package com.walter.xpsocial.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class User {
    
    private final String name;
    private final List<Post> timeline;
    private final LinkedHashSet<User> followed;

    public User(String name) {
        this.name = name;
        this.timeline = new ArrayList<>();
        this.followed = new LinkedHashSet<>();
    }

    List<User> followed() {
        final ArrayList<User> result = new ArrayList<>(followed);
        Collections.reverse(result);
        return result;
    }

    List<Post> allPosts() {
        return new ArrayList<>(this.timeline);
    }

    void addToTimeline(Post post) {
        timeline.add(0, post);
    }
    
    void addFollowed(User other) {
        if (other == this) return;
        if (alreadyFollowedUser(other)) return;
        
        this.followed.add(other);
    }

    private boolean alreadyFollowedUser(User other) {
        boolean result = this.followed
                .stream()
                .anyMatch(user -> user.name.equals(other.name));
        return result;
    }

}
