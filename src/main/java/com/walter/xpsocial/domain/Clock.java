package com.walter.xpsocial.domain;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime currentTime();
    
    public static Clock REAL = () -> LocalDateTime.now();
}
