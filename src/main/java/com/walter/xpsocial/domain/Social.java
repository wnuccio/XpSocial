package com.walter.xpsocial.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Social {

    private final Map<String, User> map = new HashMap<>();

    public Social() {
    }

    User user(String username) {
        map.computeIfAbsent(username, usernameAsKey -> new User(usernameAsKey));
        return map.get(username);
    }

    public Optional<User> findUser(String username) {
        return Optional.ofNullable(map.get(username));
    }
    
    public void post(String username, Post post) {
        User user = user(username);
        post.setUsername(username);
        user.addToTimeline(post);
    }

    public List<Post> allPosts(String username) {
        User user = user(username);
        return user.allPosts();
    }
    
    public void follows(String username, String followedName) {
        User user = user(username);
        User followed = user(followedName);
        user.addFollowed(followed);
    }
    
    public List<User> followed(String username) {
        User user = user(username);
        List<User> result = user.followed();
        return result;
    }
    
    public List<Post> allPostsIncludingFollowed(String username) {
        List<Post> result = new ArrayList<>();
       
        User user = user(username);
        List<Post> posts = user.allPosts();
        result.addAll(posts);
        
        List<User> followed = followed(username);
        followed.forEach(foll -> {
            result.addAll(foll.allPosts());
        });
        
        Collections.sort(result);
        return result;
    }
}
