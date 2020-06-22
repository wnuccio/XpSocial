package com.walter.xpsocial.domain;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public interface Clock {
    LocalDateTime currentTime();
    
    public static Clock REAL = () -> LocalDateTime.now();
}
