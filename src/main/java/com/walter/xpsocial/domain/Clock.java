package com.walter.xpsocial.domain;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime currentTime();
    
    Clock REAL = () -> LocalDateTime.now();
}
